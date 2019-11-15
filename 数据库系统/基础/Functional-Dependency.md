​     

# Functional-Dependency

### key

#### Types of Key

* **key-码**： 码是一个或多个属性的集合
* **super key-超码**： 一个或多个属性的集合，可以在一个实体集中__唯一地标识一个实体__
* **candicate key-候选码**： 候选码是**极小的超码集合**，也就是它的任意真子集都不是超码，而他本身是超码。 
* **primary key-主码**： 如果一个关系有多个候选码，则选定其中一个为主码



## FD-Base

### Definition

> Let R(A1, ..., An) be a relation schema. Let X and Y be two subsets of {A1, ..., An}. X is said to functionally determine Y (or Y is functionally dependent on X) if for every legal relation instance r(R), for any two tuples t1 and t2 in r(R), we have t1[X] = t2[X]   **→**  t1[Y] = t2[Y].   

#### 理解

* R(A1,...An)是属性集{A1...An}的关系模式（表）
* r(R)是R的可能关系集(属性集的任意子集)
* X，Y是属性集的某个子集，对任意的r(R)，对任意的t1,t2∈