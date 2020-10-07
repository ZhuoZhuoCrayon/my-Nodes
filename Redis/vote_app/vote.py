# -*- coding:utf-8 -*-
# author: Crayon
# datetime: 2020/10/7 16:21
# IDE: PyCharm
# filename: vote

import time

from redis import StrictRedis

# 入选文章选票门槛
UP_VOTE = 200

ONE_DAY_IN_SECONDS = 60 * 60 * 24

ONE_WEEK_IN_SECONDS = 7 * 60 * 60 * 24

# 一张选票的得分
VOTE_SCORE = ONE_DAY_IN_SECONDS / UP_VOTE

# 分页大小
ARTICLES_PER_PAGE = 25


def article_vote(conn: StrictRedis, user: str, article: str) -> bool:
    """
    user, article like "user[article]:id"
    """
    cutoff = time.time() - ONE_WEEK_IN_SECONDS
    # 非一周内发布的文章，直接pass
    if conn.zscore("time:", article) < cutoff:
        return False
    article_id = article.split(":")[-1]

    # 不允许重复投票
    if not conn.sadd(f"voted:{article_id}", user):
        return False
    # 文章投票分值提高
    conn.zincrby("score:", article, VOTE_SCORE)
    # 投票数量+1
    conn.hincrby(article, "votes", 1)
    return True


def post_article(conn: StrictRedis, user: str, title: str, link: str) -> str:
    # 1. 获取文章id
    article_id = str(conn.incr("article:"))
    # 2. 建立该文章的投票用户集并设置一周的过期时间
    voted = f"voted:{article_id}"
    conn.sadd(voted, user)
    conn.expire(voted, ONE_WEEK_IN_SECONDS)

    now = time.time()
    article = f"article:{article_id}"
    # 3. 记录文章概要信息，包括投票数
    conn.hmset(article, {
        "title": title,
        "link": link,
        "poster": user,
        "time": now,
        "votes": 1
    })
    # 4. 初始化文章投票分数/发布时间排行榜
    conn.zadd("score:", article, now + VOTE_SCORE)
    conn.zadd("time:", now)
    return article_id


def get_articles(conn: StrictRedis, page: int, order: str = "score:") -> list:
    start = (page - 1) * ARTICLES_PER_PAGE
    end = start + ARTICLES_PER_PAGE - 1

    # 取出指定排行榜的文章id列表
    article_ids = conn.zrevrange(order, start, end)
    articles = []
    for article_id in article_ids:
        # 取出文章概要信息
        article = conn.hgetall(article_id)
        article["id"] = id
        articles.append(article)
    return articles


def add_remove_groups(conn: StrictRedis, article_id: str, to_add=[], to_remove=[]):
    article = f"article:{article_id}"
    for group in to_add:
        conn.sadd(f"group:{group}", article)
    for group in to_remove:
        conn.srem(f"group:{group}", article)


def get_group_articles(conn: StrictRedis, group: str, page: int, order="score:"):
    key = f"{order}{group}"
    if not conn.exists(key):
        # 取指定排行榜与群组的所有文章，并按照指定排行榜进行排序
        conn.zinterstore(key, [f"group:{group}", order], aggregate="max")
        conn.expire(key, 60)
    return get_articles(conn, page, key)
