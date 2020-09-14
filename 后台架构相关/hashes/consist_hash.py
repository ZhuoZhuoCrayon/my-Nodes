# -*- coding: utf-8 -*-

from hashlib import md5
from struct import unpack_from
from bisect import bisect_left

ITEMS = 10000000
NODES = 100
node_stat = [0 for i in range(NODES)]

ring = []
hash2node = {}


def _hash(value):
    k = md5(str(value).encode("utf-8")).digest()
    ha = unpack_from(">I", k)[0]
    return ha


if __name__ == '__main__':

    for n in range(NODES):
        h = _hash(n)
        ring.append(h)
        ring.sort()
        hash2node[h] = n

    for item in range(ITEMS):
        h = _hash(item)
        n = bisect_left(ring, h) % NODES
        node_stat[hash2node[ring[n]]] += 1

    _ave = ITEMS / NODES
    _max = max(node_stat)
    _min = min(node_stat)

    print("Ave: %d" % _ave)
    print("Max: %d\t(%0.2f%%)" % (_max, (_max - _ave) * 100.0 / _ave))
    print("Min: %d\t(%0.2f%%)" % (_min, (_ave - _min) * 100.0 / _ave))

# Ave: 100000
# Max: 596413	(496.41%)
# Min: 103	(99.90%)
