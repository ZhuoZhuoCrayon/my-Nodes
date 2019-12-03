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
* X，Y是属性集的某个子集，对任意的r(R)，对任意的t1,t2∈r(R)，在X上属性值相等，在Y上属性值也应相等

### 推论

#### 关于超码

>  If X is a superkey of R and Y is any subset of R, then X**→**Y in R.   

##### 理解

* 如果X是超码，Y是R的子集，X函数确定Y，Y函数依赖于X

##### 证明

* X是超码，对于任意的t1,t2∈r(R)，X相等，可确定唯一的Y

#### 性质

* `X → Y`：X称为**决定因素（ Determinant  ）**
* `X → Y` and `Y → X`：`X←→Y`





###  Trivial FD AND NON-Trivial FD

#### Definition

* X → Y，X**不包含**Y，则称为**非平凡函数依赖（NON-Trivial FD）**
* X → Y，X**包含**Y，则称为**平凡函数依赖（Trivial FD）**



###      FULL FD and PARTIAL FD  

#### Definition

* X → Y，但对于X的任意真子集X_sub，X_sub不能函数确定Y，称Y对X**完全函数依赖（FULL FD）**
* X → Y，Y不完全依赖X，称Y对X**部分函数依赖（PARTIAL FD）**

##### 理解

* 完全函数依赖表示当前的**X已是`最小`可确定Y的属性集**

##### Example

* `•{CNO, CNAME} → CLOCATION : partial FD`
  * `CNO`可单一确定`CLOCATION[课程地点]`-也就是存在X的真子集满足`X_sub→Y`
  * `CNO → CLOCATION: full FD  `



###   Transitive Functional Dependency

#### Definition

> `X → Y` 的前提条件是`X → Z`&&`Z → Y`
>
> 称为**Y传递函数依赖于( transitively functionally dependent on )X** 

#### Warning

> IF `Y → X` 即 `X←→Y`
>
> 此时**Y是直接依赖于X，不是传递函数依赖**



### More

#### Implied Logic（隐含逻辑）

##### Definition

> `F`是`R`的一个函数依赖集合,则F的**闭包[closure]**是**`F`逻辑上**隐含的所有关于`R`的函数依赖集合
> $$
> F^{+}
> $$

#### Armstrong's Axioms (1974)

* `Reflexivity rule`   Y is the subset of X，then X→Y
* ` Augmentation rule  ` { X →Y } |= XZ→YZ.

* `Transitivity rule`  { X→Y, Y→Z } |= X→Z 

#### Additional rules derivable(推导) from Armstrong's Axioms. 

* `Decomposition` { X→Y, Z is the subset of Y } |= X→Z 
  * because `Y→Z`（`Reflexivity rule`） and then `Transitivity rule`
* `Union rule` { X→Y, X→Z } |= X→YZ 
  * `XY→YZ`(use` Augmentation rule` by  X→Z) and `XX→XY` then `XX→X`
  * so `X→XY` and `XY→YZ`  then use `Transitivity rule`

* `Pseudotransitivity(伪传递) rule`  { X→Y, WY→Z } |= WX→Z 
  * because `X→Y` so `WX→WY` use ` Augmentation rule  `
  * then use `Transitivity rule`
  * so WX→Z 

##### Notice

> If `X` is the subset of `R` and `A, B, ..., C` are attributes in R, 
>
> then `X→ A B ... C` equal to `{ X→A, X→B, ..., X→C }`
>
> **use `Union rule` { X→Y, X→Z } |= X→YZ **

##### Compute the closure of X

> X的闭包表示的是在F关系闭包下，由X可推及的属性集合

$$
X^{+} = \{A|X→A\in F^{+}\}
$$

给定关系模式`R(A1, ..., An)`,`K`是R的子集，K是`超码superkey`，当且仅当`K的闭包=  {A1, ..., An}`

* 即由K可以通过关系依赖集合F确定关系模式R

* K是候选码的条件是，K的任意子集的闭包不等于 `{A1, ..., An}`

  

