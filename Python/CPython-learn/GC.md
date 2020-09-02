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
* 参数传入：`sys.getrefcount` 的参数占用`hello world`的一个计数

`id(s)`也是传入参数，为何没有计数？

* 变量作用域，在`id(s)`方法生命周期内`hello world`字符串对象的引用计数+1，当返回时参数`s`销毁被回收，引用计数-1
* 进入`sys.getrefcount`，`return ob->ob_refcnt;`时尚且在函数域内，引用占有1



把`s`赋值给`s2`

```python
>>> s2 = s
>>> id(s2)
4321629008 # 和 id(s) 的值一样
>>> sys.getrefcount(s)
3 # 多出来的一个来自 s2
```

![](./images/refcount2.png)

### 字节码执行

执行以上Python语句并输出字节码

```shell
$ cat test.py
s = []
s2 = s
del s
$ ./python.exe -m dis test.py
```

输出字节码：

```Python
1         0 BUILD_LIST               0
          2 STORE_NAME               0 (s)

2         4 LOAD_NAME                0 (s)
          6 STORE_NAME               1 (s2)

3         8 DELETE_NAME              0 (s)
         10 LOAD_CONST               0 (None)
         12 RETURN_VALUE
```

Code Line 1：`s = []`

`0 BUILD_LIST` 

* 申请堆空间，创建一个空白新`list`对象，把对象引用计数设为1，并入栈

`2 STORE_NAME` ，执行字节码：

```c
case TARGET(STORE_NAME): {
    PyObject *name = GETITEM(names, oparg); 	// str对象，值为's'（变量名）
    PyObject *v = POP();	//出栈前一个字节码创建的list对象
    PyObject *ns = f->f_locals;	// ns 是 local namespace，存变量k-v
    int err;
    if (ns == NULL) {
        PyErr_Format(PyExc_SystemError,
                     "no locals found when storing %R", name);
        Py_DECREF(v);
        goto error;
    }
    if (PyDict_CheckExact(ns))
    	/* 在这个位置, v 的引用计数 为 1
           PyDict_SetItem 会把 's' 作为键加到 local namespace 中, 值为对象 v
           ns 类型为 字典对象, 这一操作会同时把 's' 和 v 的引用计数都增加 1
       */
        // 设置变量值
        err = PyDict_SetItem(ns, name, v);
        /* 做完上面的操作, v 的引用计数变为了 2 */
    else
        err = PyObject_SetItem(ns, name, v);
    Py_DECREF(v);
    /* Py_DECREF 之后, v 的引用计数变为了 1 */
    if (err != 0)
        goto error;
    DISPATCH();
}
```

* 为什么初始化引用数为1：前面讲过，在堆上申请空间并且赋值之前需要入栈，引用数为1

综上，第一行代码执行完，引用计数为`1`

Code Line2：`s2 = s`

`4 LOAD_NAME`

* 在`local namespace`取出键为`s`的对象，引用计数+1并推到栈中
* 此时新建`list`对象的引用计数为`2`（s，stack）

`6 STORE_NAME`

* 执行`2 STORE_NAME`过程的代码，把`s2`加入`local namespace`，出栈`list`对象，引用数-1（1）
* `list`对象被`s2`引用，+1（2）

综上，第二行代码的赋值操作使得引用计数`1`->`2`

`8 

```C
case TARGET(DELETE_NAME): {
    PyObject *name = GETITEM(names, oparg);		// name 这里为 's'
    PyObject *ns = f->f_locals;		// ns 为 the local namespace
    int err;
    if (ns == NULL) {
        PyErr_Format(PyExc_SystemError,
                     "no locals when deleting %R", name);
        goto error;
    }
    /* 到这里, list 对象的引用计数为 2
       下面的操作会找到键 's' 对应的位置, 把 indices 设置为 DKIX_DUMMY,
       entries 中的 key 和 value 位置都置为空指针, 并把 key 和 value 本身对象引用计数减1
    */
    err = PyObject_DelItem(ns, name);
    /* 到了这里, list 对象的引用计数变为了 1 */
    if (err != 0) {
        format_exc_check_arg(PyExc_NameError,
                             NAME_ERROR_MSG,
                             name);
        goto error;
    }
    DISPATCH();
}
```



### 触发垃圾回收的条件

```C
/* cpython/Include/object.h */
static inline void _Py_DECREF(const char *filename, int lineno,
                              PyObject *op)
{
    _Py_DEC_REFTOTAL;
    if (--op->ob_refcnt != 0) {
#ifdef Py_REF_DEBUG
        if (op->ob_refcnt < 0) {
            _Py_NegativeRefcount(filename, lineno, op);
        }
#endif
    }
    else {
    	/* // _Py_Dealloc 会找到对应类型的 descructor, 并且调用这个 descructor
        destructor dealloc = Py_TYPE(op)->tp_dealloc;
        (*dealloc)(op);
        */
        _Py_Dealloc(op);
    }
}
```



### 引用循环问题

> `DELETE_NAME` 只会清除传入对象的引用
>
> 引用循环问题是指在某些情况下，由于对象相互引用，引用计数无法变为`0`导致的垃圾回收无法触发的现象

#### 互相引用

```python
class A:
    pass

>>> a1 = A()
>>> a2 = A()
>>> a1.other = a2
>>> a2.other = a1
```

此时`a1` `a2`的引用计数都为2

![](./images/ref_each1.png)

```python
>>> del a1
>>> del a2
```

清空`local namespace`的引用，但由于对象自身都有一个来自对方的引用，`a1` `a2`的引用计数只会变成`1`

![](./images/ref_each2.png)



#### 引用自身

```python
>>> a = list()
>>> a.append(a)
>>> a
[[...]]
```

![](./images/ref_cycle1.png)

````python
>>> del a
````