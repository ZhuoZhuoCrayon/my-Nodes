# -*- coding: utf-8 -*-

from hashlib import md5
from struct import unpack_from

ITEMS = 10000000
NODES = 100

node_stat = [0 for i in range(NODES)]

if __name__ == '__main__':

    for item in range(ITEMS):
        k = md5(str(item).encode("utf-8")).digest()
        h = unpack_from(">I", k)[0]
        n = h % NODES
        node_stat[n] += 1

    _ave = ITEMS / NODES
    _max = max(node_stat)
    _min = min(node_stat)

    print("Ave: %d" % _ave)
    print("Max: %d\t(%0.2f%%)" % (_max, (_max - _ave) * 100.0 / _ave))
    print("Min: %d\t(%0.2f%%)" % (_min, (_ave - _min) * 100.0 / _ave))

# Ave: 100000
# Max: 100695	(0.69%)
# Min: 99073	(0.93%)
