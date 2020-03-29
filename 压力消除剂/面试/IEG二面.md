## IEG二面

### 介绍Spring IOC/AOP及实现

> 当场去世

### Spring MVC实现

> 当场去世

### 设计模式

* 单例模式
* 观察者模式
* 工厂模式
  * 简单工厂
  
  * 工厂方法
  
  * 抽象工厂
  
  * Spring用了哪种？
  
    > 不懂

### Interface & Abstract 区别及应用场景



### 进程线程

#### 进程通信

* 无名管道

* 命名管道

* 消息队列

* 信号量

* 共享内存

* socket

  * socket是怎么实现的？Java中怎么建立socket

    > 一脸懵逼，答了ServerSocket

#### 线程状态

> 问了英语？？？？

* 新建（New）
* 就绪（Runnable）
* 阻塞（Blocked）
* 运行（Running）
* 死亡（Dead）

#### Java中创建线程的方法



### Linux的用户密码放在哪个文件下？

* /etc/passwd 
* /etc/shadow

### 数据库相关

#### 索引

* 索引的概念？

* 索引能干嘛？

* 什么时候不能建索引

  * 数据唯一性差（一个字段的取值只有几种时）的字段不要使用索引

    > 比如性别，只有两种可能数据。意味着索引的二叉树级别少，多是平级。这样的二叉树查找无异于全表扫描。
    >
    
  * 频繁更新的字段不要使用索引
  
    > 比如logincount登录次数，频繁变化导致索引也频繁变化，增大数据库工作量，降低效率。
  
  * **字段不在where语句出现时不要添加索引**，如果where后含IS NULL /IS NOT NULL/ like ‘%输入符%’等条件，不建议使用索引
  
    > 只有在where语句出现，mysql才会去使用索引
    >
    
  * where 子句里对索引列使用不等于（<>），使用索引效果一般
  
  * 字段太长不建议用索引，或者截断
  
#### InnoDB和Myiasm的区别

* Myiasm不支持数据库事务，行级锁和**外键**，执行删改时会锁住整张表，但是执行读取操作速度快
* InnoDB支持事务，回滚，多版本并发控制

#### Spring 中如何显式配置事务

> **声明式事务注解@Transactional** 标注该方法会开启手动事务模式。
>
> **@EnableTransactionManagement(mode = AdviceMode.PROXY)**

#### MySQL中的锁

* S锁/X锁/意向锁/行级锁/表锁/页锁

#### 事务（要说出英语）

* 原子性（Atomicity）
* 一致性（Consistency）
* 隔离性（Isolation）
* 持久性（Durability）

#### 主从复制

> 不会

#### Mysql中死锁是怎么产生的

> 不会



### Vue

* 双向绑定
* 数据请求？axios？？？



### Markdown



### Git常用命令

* [Git常用命令](https://www.jianshu.com/p/cdccfef91ae1)



### 求个评价和建议

* 基础不错，但是Spring框架的回答不是很好
* 要有自己感兴趣的技术，不然就变成码农了

