# 怎么系统学习后端(Java 方向)技术

> crayon
>
> 2021 / 01 / 16

### 前期准备

* 下载 **[IntelliJ IDEA](https://www.jetbrains.com/idea/download/)**，之后使用IDEA编写Java程序
  * 使用专业版-Ultimate（学生认证可免费使用）
  * 平时可以参考**[IntelliJ-IDEA-Tutorial](https://github.com/judasn/IntelliJ-IDEA-Tutorial)** 学习使用技巧
* ⚠️ 养成习惯，使用Google作为搜索引擎，推荐在[StackOverFlow](https://stackoverflow.com/) 查找问题解决方案
* 配置**[Java](https://www.java.com/en/download/)**环境

* 注册一个GitHub账号



### 初学阶段

> 学习时间：2 个月
>
> 学习基础语法、多线程、集合类以及网络编程基础

* 推荐[廖雪峰Java教程](https://www.liaoxuefeng.com/wiki/1252599548343744/1260471862687712)，按顺序看完以下章节

  * [Java教程](https://www.liaoxuefeng.com/wiki/1252599548343744)
  * [Java快速入门](https://www.liaoxuefeng.com/wiki/1252599548343744/1255883772263712)
  * [面向对象编程](https://www.liaoxuefeng.com/wiki/1252599548343744/1255943520012800)
  * [异常处理](https://www.liaoxuefeng.com/wiki/1252599548343744/1255943543190176)
  * [泛型](https://www.liaoxuefeng.com/wiki/1252599548343744/1255945193293888)
  * [集合](https://www.liaoxuefeng.com/wiki/1252599548343744/1255943629175808)
  * [IO](https://www.liaoxuefeng.com/wiki/1252599548343744/1255945227202752)
  * [日期与时间](https://www.liaoxuefeng.com/wiki/1252599548343744/1255943660631584)
  * [多线程](https://www.liaoxuefeng.com/wiki/1252599548343744/1255943750561472)
  * [网络编程](https://www.liaoxuefeng.com/wiki/1252599548343744/1255945371526048)
  * [函数式编程](https://www.liaoxuefeng.com/wiki/1252599548343744/1255943847278976)

* 上述教程讲述不清晰的地方，参考**《Java核心技术 卷1》** 、**《Java网络编程》**对应章节细读

* 实现一个多线程，可支持断点续传的下载器

  * 通过多线程下载文件
  * 可以暂停、继续（需要从原来下载进度上继续下载）
  * ⚠️不需要界面，可以用终端命令行
  * 加分项：合理的设计及可复用性

* 实现一个文件管理器

  * 文件夹的增删改查
  * ⚠️不需要界面，可以打印出终端

* 实现一个简单的Web服务器

  * 使用线程池管理客户端连接
  * 设计合理的POST、GET请求及返回
  * 实现登录及Cookie功能即可
  * ⚠️不需要前端，可以搜索并下载PostMan，模拟请求发送即可
  
* ⬆️ 使用集合类，规范化语法实现上述功能
  * [阿里代码规范插件](https://www.jianshu.com/p/e14171373c36)




### 进阶阶段

> 学习时间：1 个月

完成上述的学习，你已经能用Java编写各类简单的应用程序，这时需要学习一些实用的工具

* MarkDown：轻量文本标记语言，用极少的标签可以写出简洁美观的文档
  * 推荐学习使用MarkDown编辑器——[Typora](https://typora.io/)
  * 推荐菜鸟教程的[MarkDown教程](https://www.runoob.com/markdown/md-tutorial.html)
  * 找一个GitHub项目的README.md文档，下载后模仿复刻，比如https://github.com/liangliangyy/DjangoBlog/edit/master/README.md
* Git：版本控制工具，用于项目管理
  * 推荐[廖雪峰Git教程](https://www.liaoxuefeng.com/wiki/896043488029600)，按顺序学到**分支管理**，不光是看，要实践教程上举的例子
  * 将初学阶段的作业使用Git进行管理并分享到GitHub
    * `checkout` 2个分支，一个用MarkDown写开发文档，另一个在源代码上做修改，并合入`master`
* 继续看书
  * [图解 HTTP[2014]](https://item.jd.com/11449491.html) —— 加深对网络的认知
  * 《Java核心技术 卷 1》集合 / 多线程 / 语法基础
  * 加分项：用MarkDown写笔记
* 刷题：[LeetCode 剑指Offer专题](https://leetcode-cn.com/problemset/lcof/)
  * 先从简单题入手，无论是否独自解决，都要参考题解，学习优质代码
  * 等到熟悉LeetCode后，逐渐完成该专题

* 回顾并优化初学阶段代码，用Git管理并提交到GitHub



### 高级阶段

> 学习时间：4个月以上

该阶段将学习到数据库、Web框架，并进一步学习Java高级语法

...

