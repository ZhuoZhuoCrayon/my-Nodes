# -*- coding:utf-8 -*-
# author: Crayon
# datetime: 2020/10/6 17:06
# IDE: PyCharm
# filename: default

import os

REDIS_SETTINGS = {
    "host": os.getenv("REDIS_HOST", "localhost"),
    "port": os.getenv("REDIS_PORT", "6379"),
    "password": os.getenv("REDIS_PASSWORD", "")
}

# 键需带上此前缀，便于后续清理无效键
REDIS_KEY_PREFIX = "my-nodes:"
