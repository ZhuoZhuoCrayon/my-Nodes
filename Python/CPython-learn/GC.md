[TOC]

# CPython 垃圾回收

> Author: `crayon`
>
> Ref: [CPython-Internals-GC](https://github.com/zpoint/CPython-Internals/blob/master/Interpreter/gc/gc_cn.md)
>
> Date: `2020/08/29`



## CPython中的垃圾回收机制

### 引用计数机制

> `Include/object.h`
>
> 在计算机科学中, 引用计数是计算机编程语言中的一种**内存管理技术**，是指将资源（可以是对象、内存或磁盘空间等等）的被引用次数保存起来，**当被引用次数变为零时就将其释放**的过程。使用引用计数技术可以实现自动资源管理的目的。同时引用计数还可以指使用引用计数技术回收未使用资源的垃圾回收算法。—— 维基百科

* 创建一个对象并在堆上申请内存时，对象引用计数为1（堆初始化过程）
* 其他对象需要持有该对象时，引用计数+1
* 释放（不需要）一个对象时，例如覆盖值操作，引用计数-1
* 对象引用计数为0，对象进入垃圾回收阶段，回收内存

### 分代回收机制

> `Modules/gcmodule.c`

分代回收机制是一种“combine”机制，包含清理方法及回收策略：

* 分代回收策略
* 标记清除
* ...



## 引用计数法

### 获取引用计数

在CPython中的`/Include/object.h`，`PyObject` 中的`ob_refcnt`表示引用计数，获取方式如下：

```c
static inline Py_ssize_t _Py_REFCNT(const PyObject *ob) {
    return ob->ob_refcnt;
}
```

在Python中可以通过`sys.getrefcount(obj)`获取引用计数

```python
import sys
a = 1
sys.getrefcount(a)
>>> 88
```

### 举个栗子

声明一个名为`s`的变量并初始化值`hello world`，并通过` sys.getrefcount(s)`获取引用计数

```python
s = "hello world"

>>> id(s)
4346803024
>>> sys.getrefcount(s)
2 # 一个来自变量 s, 一个来自 sys.getrefcount 的参数
```

![](./images/refcount1.png)

引用计数为`2`：

*  赋值操作：`s`占有一个计数
* 参数传入：sys.getrefcount 的参数占用`hello world`的一个计数

`id(s)`也是传入参数，为何没有计数？

* 变量作用域，在`id(s)`方法生命周期内`hello world`字符串对象的引用计数+1，当返回时参数`s`销毁被回收，引用计数-1
* woc 所以为啥sys.getrefcount的参数会占用一个计数？？？



把`s`赋值给`s2`

```python
>>> s2 = s
>>> id(s2)
4321629008 # 和 id(s) 的值一样
>>> sys.getrefcount(s)
3 # 多出来的一个来自 s2
```

