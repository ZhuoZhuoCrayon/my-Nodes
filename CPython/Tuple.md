[TOC]



# Tuple

> Author: `crayon`
>
> Date: `2020/09/01`

## 概述

### 源码位置

* cpython/Include/cpython/tupleobject.c
* cpython/Include/tupleobject.h
* cpython/Objects/tupleobject.c

### 内存构造

![alt](./images/tuple.png)

* `ob_item`和`list`一样，是一个二维指针类型，存储**地址**列表`PyObject`



## 源码分析

### 头文件

> `cpython/Include/cpython/tupleobject.c`
>
> `cpython/Include/tupleobject.h`

```c
#ifndef Py_CPYTHON_TUPLEOBJECT_H
#  error "this header file must not be included directly"
#endif

typedef struct {
    PyObject_VAR_HEAD
    /* ob_item contains space for 'ob_size' elements.
       Items must normally not be NULL, except during construction when
       the tuple is not yet visible outside the function that builds it. */
    PyObject *ob_item[1];
} PyTupleObject;

PyAPI_FUNC(int) _PyTuple_Resize(PyObject **, Py_ssize_t);
PyAPI_FUNC(void) _PyTuple_MaybeUntrack(PyObject *);

/* Macros trading safety for speed */

/* Cast argument to PyTupleObject* type. */
#define _PyTuple_CAST(op) (assert(PyTuple_Check(op)), (PyTupleObject *)(op))

#define PyTuple_GET_SIZE(op)    Py_SIZE(_PyTuple_CAST(op))

#define PyTuple_GET_ITEM(op, i) (_PyTuple_CAST(op)->ob_item[i])

/* Macro, *only* to be used to fill in brand new tuples */
#define PyTuple_SET_ITEM(op, i, v) (_PyTuple_CAST(op)->ob_item[i] = v)

PyAPI_FUNC(void) _PyTuple_DebugMallocStats(FILE *out);
```

* 体现数据结构
* 体现基础api
  * `PyTuple_GET_SIZE(op)`
  * `PyTuple_GET_ITEM(op, i)`
  * `PyTuple_SET_ITEM(op, i, v)`

```c
/* Tuple object interface */

#ifndef Py_TUPLEOBJECT_H
#define Py_TUPLEOBJECT_H
#ifdef __cplusplus
extern "C" {
#endif

/*
Another generally useful object type is a tuple of object pointers.
For Python, this is an immutable type.  C code can change the tuple items
(but not their number), and even use tuples as general-purpose arrays of
object references, but in general only brand new tuples should be mutated,
not ones that might already have been exposed to Python code.

*** WARNING *** PyTuple_SetItem does not increment the new item's reference
count, but does decrement the reference count of the item it replaces,
if not nil.  It does *decrement* the reference count if it is *not*
inserted in the tuple.  Similarly, PyTuple_GetItem does not increment the
returned item's reference count.
*/

PyAPI_DATA(PyTypeObject) PyTuple_Type;
PyAPI_DATA(PyTypeObject) PyTupleIter_Type;

#define PyTuple_Check(op) \
                 PyType_FastSubclass(Py_TYPE(op), Py_TPFLAGS_TUPLE_SUBCLASS)
#define PyTuple_CheckExact(op) Py_IS_TYPE(op, &PyTuple_Type)

PyAPI_FUNC(PyObject *) PyTuple_New(Py_ssize_t size);
PyAPI_FUNC(Py_ssize_t) PyTuple_Size(PyObject *);
PyAPI_FUNC(PyObject *) PyTuple_GetItem(PyObject *, Py_ssize_t);
PyAPI_FUNC(int) PyTuple_SetItem(PyObject *, Py_ssize_t, PyObject *);
PyAPI_FUNC(PyObject *) PyTuple_GetSlice(PyObject *, Py_ssize_t, Py_ssize_t);
PyAPI_FUNC(PyObject *) PyTuple_Pack(Py_ssize_t, ...);

#ifndef Py_LIMITED_API
#  define Py_CPYTHON_TUPLEOBJECT_H
#  include  "cpython/tupleobject.h"
#  undef Py_CPYTHON_TUPLEOBJECT_H
#endif

#ifdef __cplusplus
}
#endif
#endif /* !Py_TUPLEOBJECT_H */
```



