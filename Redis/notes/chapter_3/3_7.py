# -*- coding: utf-8 -*-
import time
from concurrent.futures import ThreadPoolExecutor

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


if __name__ == '__main__':
    no_trans_main()
    trans_main()
