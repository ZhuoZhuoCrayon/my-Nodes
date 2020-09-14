# -*- coding: utf-8 -*-

from hashlib import md5
from struct import unpack_from

ITEMS = 10000000
NODES = 100
NEW_NODES = 101

node = [0 for i in range(NODES)]

change = 0

if __name__ == '__main__':
    for item in range(ITEMS):
        k = md5(str(item).encode("utf-8")).digest()
        h = unpack_from(">I", k)[0]
        n = h % NODES
        n_new = h % NEW_NODES
        if n_new != n:
            change += 1
    print("Change: %d\t(%0.2f%%)" % (change, change * 100.0 / ITEMS))

# Change: 9900989	(99.01%)
