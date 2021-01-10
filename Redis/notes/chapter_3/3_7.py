# -*- coding: utf-8 -*-
import time
import random
from concurrent.futures import ThreadPoolExecutor, as_completed
from functools import wraps

from Redis.redisbase.config.default import REDIS_KEY_PREFIX
from Redis.redisbase.conn import RedisInstSingleTon

REDIS_INST = RedisInstSingleTon.get_inst()


# 3.7.2 3-13 缺少事务造成线程安全问题
def notrans(key: str, order: int) -> None:
    # 对键进行自增
    incr_result = REDIS_INST.incr(key)

    time.sleep(.1)

    print(f"# [{order}]incr_result: {incr_result}")

    REDIS_INST.decr(key)


def no_trans_main() -> None:
    key = f"{REDIS_KEY_PREFIX}notrans:"

    # 初始化时删除键
    REDIS_INST.delete(key)

    thread_pool = ThreadPoolExecutor(max_workers=1024)
    for order in range(3):
        thread_pool.submit(notrans, key=key, order=order)

    # output
    # [1]incr_result: 2
    # [2]incr_result: 3
    # [0]incr_result: 1


# 3.7.2 3-14 使用事务处理命令并行执行问题
def trans(key: str, order: int) -> None:
    pipeline = REDIS_INST.pipeline()
    pipeline.incr(key)
    time.sleep(.1)
    pipeline.decr(key)
    responses = pipeline.execute()
    incr_result = responses[0]

    print(f"# [{order}]incr_result: {incr_result}")


def trans_main() -> None:
    key = f"{REDIS_KEY_PREFIX}trans:"

    # 初始化时删除键
    REDIS_INST.delete(key)

    thread_pool = ThreadPoolExecutor(max_workers=1024)
    for order in range(3):
        thread_pool.submit(trans, key=key, order=order)

    # output
    # [1]incr_result: 1
    # [0]incr_result: 1
    # [2]incr_result: 1


# pipeline for test
def pipeline_range():
    hash_members_key = f"{REDIS_KEY_PREFIX}hash:member:"
    pipeline = REDIS_INST.pipeline()
    for index in range(1, 10):
        pipeline.hset(name=hash_members_key, key=f"member-{index}", value=index)
        pipeline.delete(*[f"{REDIS_KEY_PREFIX}member:{index}"])
        pipeline.set(name=f"{REDIS_KEY_PREFIX}member:{index}", value=index)
        pipeline.expire(name=f"{REDIS_KEY_PREFIX}member:{index}", time=300)
    pipeline.execute()


# compare pipeline and not pipeline

def program_timer(func):
    @wraps(func)
    def inner(*args, **kwargs):
        start_time = time.time()
        result = func(*args, **kwargs)
        cost_time = time.time() - start_time
        print(f"{func.__name__} cost time: {cost_time}.\n")
        return result

    return inner


def op_without_pipeline(slice_size: int, slice_index: int) -> None:
    """
    pipeline page - 20
    1000  -  11.3s
    5000  -  52.4s
    10000 -  ...

    pipeline page - 5
    1000  -  3s
    5000  -  17s
    10000 -  30s
    """
    large_pkg_str = "|".join([str(num) for num in range(1, 10)])
    for index in range(slice_size * (slice_index - 1), slice_size * slice_index):
        REDIS_INST.hset(
            name=f"{REDIS_KEY_PREFIX}hash:without_pipeline:{random.randint(0, 5)}",
            key=f"index-{index}",
            value=f"{large_pkg_str}-{index}"
        )


def op_with_pipeline(slice_size: int, slice_index: int) -> None:
    """
    1000  -  1.2s
    5000  -  4.5s
    10000 -  11.4s

    pipeline page - 5
    1000  -  1s
    5000  -  6s
    10000 -  11.6s
    """
    large_pkg_str = "|".join([str(num) for num in range(1, 10)])
    pipeline = REDIS_INST.pipeline()
    for index in range(slice_size * (slice_index - 1), slice_size * slice_index):
        pipeline.hset(
            name=f"{REDIS_KEY_PREFIX}hash:with_pipeline:{random.randint(0, 5)}",
            key=f"index-{index}",
            value=f"{large_pkg_str}-{index}"
        )
    pipeline.execute()
    # print(f"[{slice_index}]op_with_pipeline finished.\n")


@program_timer
def pipeline_perform_test(mode: str):

    cases = {
        "with": op_with_pipeline,
        "without": op_without_pipeline
    }

    hash_key_used = []
    for index in range(0, 5 + 1):
        hash_key_used.extend([
            f"{REDIS_KEY_PREFIX}hash:with_pipeline:{index}",
            f"{REDIS_KEY_PREFIX}hash:without_pipeline:{index}"
        ])
    REDIS_INST.delete(*hash_key_used)

    with ThreadPoolExecutor(max_workers=100) as ex:
        tasks = [ex.submit(cases[mode], slice_index=slice_index, slice_size=20) for slice_index in range(1, 1000)]

    for future in as_completed(tasks):
        future.result()


if __name__ == '__main__':
    # no_trans_main()
    # trans_main()
    # pipeline_range()
    pipeline_perform_test("with")
