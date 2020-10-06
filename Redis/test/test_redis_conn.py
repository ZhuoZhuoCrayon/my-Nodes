# -*- coding:utf-8 -*-
# author: Crayon
# datetime: 2020/10/6 11:33
# IDE: PyCharm
# filename: test_redis_conn

from concurrent.futures import ThreadPoolExecutor

from Redis.redisbase.conn import RedisInstSingleTon

TEST_KEY = "test:redis"


def get_redis_inst():
    return RedisInstSingleTon.get_inst()


if __name__ == '__main__':
    redis_inst = RedisInstSingleTon.get_inst()
    redis_inst.set(TEST_KEY, "redis connect success!")
    print(str(redis_inst.get(TEST_KEY)))
    redis_inst.delete(TEST_KEY)

    thread_pool = ThreadPoolExecutor(max_workers=1024)
    futures = []
    for index in range(1000):
        futures.append(thread_pool.submit(get_redis_inst))
    inst_id_set = {id(future.result()) for future in futures}
    print(inst_id_set)
