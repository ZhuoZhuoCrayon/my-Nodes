# -*- coding:utf-8 -*-
# author: Crayon
# datetime: 2020/11/14 23:29
# IDE: PyCharm
# filename: use_fnmatch

import fnmatch


def test_filter():
    # It is the same as [n for n in names if fnmatch(n, pattern)], but implemented more efficiently.
    file_paths = ["pat.txt", "pat.tar", "aa.txt"]
    match_files = fnmatch.filter(file_paths, "*.txt")
    print(f"{test_filter.__name__}: {match_files}")


def test_match():
    # 不区分大小写
    match_result = fnmatch.fnmatch("pat.txt", "*.txt")
    print(f"{test_match.__name__}: {match_result}")


def test_matchcase():
    # 区分大小写
    match_result = fnmatch.fnmatchcase("pat.txt", "*.txt")
    print(f"{test_matchcase.__name__}: {match_result}")


def test_fnmatch_function():
    file_paths = ["pat.txt", "pat.tar", "aa.txt", "1.tar", "2.tar", "12.tar", "13.tar"]
    # 通配符
    print(fnmatch.filter(file_paths, "*"))
    # 前缀
    print(fnmatch.filter(file_paths, "*a.txt"))
    # 后缀
    print(fnmatch.filter(file_paths, "pat*"))
    # 中间
    print(fnmatch.filter(file_paths, "p*.txt"))

    # 枚举
    print(fnmatch.filter(file_paths, "[1, 2].*"))

    # 单词枚举，不支持！
    print(fnmatch.filter(file_paths, "[pat, aa].*"))

    # 字符范围枚举
    print(fnmatch.filter(file_paths, "[1-2].*"))
    print(fnmatch.filter(file_paths, "1[2-3].*"))
    print(fnmatch.filter(file_paths, "[b-z][a-b]*"))


def test_gsekit_match():
    expressions = [
        "awx.gamesvr.mysql_agent.mysql.1",
        "awx.gamesvr.mysql_agent.mysql.2",
        "awx.gamesvr.redis_agent.redis.1",
        "aqq.gamesvr.db.redis.1",
        "aqq.gamesvr.db.redis.2"
    ]
    # 全选
    print(fnmatch.filter(expressions, "*.*.*.*.*"))

    # 选中集群
    print(fnmatch.filter(expressions, "awx.*.*.*.[1, 2]"))


if __name__ == '__main__':
    test_filter()
    test_match()
    test_matchcase()
    test_fnmatch_function()
    test_gsekit_match()

