# -*- coding:utf-8 -*-
# author: Crayon
# datetime: 2020/10/6 17:09
# IDE: PyCharm
# filename: conn

import threading

from redis import StrictRedis

from Redis.redisbase.config.default import REDIS_SETTINGS


class RedisInstSingleTon:
    _inst_lock = threading.Lock()
    _inst_name = "redis_inst"

    @classmethod
    def get_inst(cls):
        if hasattr(RedisInstSingleTon, RedisInstSingleTon._inst_name):
            return getattr(RedisInstSingleTon, RedisInstSingleTon._inst_name)
        with RedisInstSingleTon._inst_lock:
            if hasattr(RedisInstSingleTon, RedisInstSingleTon._inst_name):
                return getattr(RedisInstSingleTon, RedisInstSingleTon._inst_name)
            connect_params = {
                "host": REDIS_SETTINGS.get("host", "localhost"),
                "port": REDIS_SETTINGS.get("port", 6379)
            }
            if "db" in REDIS_SETTINGS:
                connect_params["db"] = REDIS_SETTINGS["db"]
            if "password" in REDIS_SETTINGS:
                connect_params["password"] = REDIS_SETTINGS["password"]
            setattr(RedisInstSingleTon, RedisInstSingleTon._inst_name, StrictRedis(**connect_params))
        return getattr(RedisInstSingleTon, RedisInstSingleTon._inst_name)
