## 安装并设置Git
### 下载Git
[官网下载](https://git-scm.com/downloads)\
安装完成后，在开始菜单找到`Git->Git Bash`，点击并出现弹窗，说明安装成功
### 设置用户
```
$ git config --global user.name "Your Name"
$ git config --global user.email "email@example.com"
```
`--global`参数表示这台机器上所有的Git仓库都会使用这个配置，当然也可以对某个仓库指定不同的用户名和Email地址

### 创建版本库
版本库又名仓库，英文名repository，你可以简单理解成一个目录，这个目录里面的所有文件都可以被Git管理起来，每个文件的修改、删除，Git都能跟踪，以便任何时刻都可以追踪历史，或者在将来某个时刻可以“还原”

选择一个合适的地方，创建一个空目录
```
$ mkdir learngit
$ cd learngit
$ pwd
/c/Users/crayon/onedrive/learngit
```
`pwd`命令用于显示当前目录

通过`git init`把这个目录编程Git可管理的目录
```
$ git init
Initialized empty Git repository in C:/Users/crayon/OneDrive/learngit/.git/
```
当前目录下多了一个`.git`目录，用于Git来跟踪管理版本库\
__也可以在一个有东西的目录下创建Git仓库__

### 将文件添加到版本库
__Tip:__
* 所有的版本控制系统，其实只能跟踪文本文件的改动，比如TXT文件，网页，所有的程序代码等等
* Microsoft的Word格式是二进制格式，因此，版本控制系统是没法跟踪Word文件的改动的
* 不要使用Windows自带的**记事本**编辑任何文本文件
* 下载[NotePad++](https://notepad-plus-plus.org/download/v7.7.1.html)替代记事本

编写一个`readme.txt`文件
```text
Git is a version control system.
Git is free software.
```
该文件需要放在`learngit`(也就是创建的Git仓库)的目录或子目录下\
第一步，命令`git add`将文件添加到仓库
```
$ git add readme.txt
```
第二步，命令`git commit`通知Git，把文件提交到仓库
```
$ git commit -m "wrote a readme file"
[master (root-commit) eaadf4e] wrote a readme file
 1 file changed, 2 insertions(+)
 create mode 100644 readme.txt
```
`-m "xxx"`是本次提交的说明，__务必填写__\
可以多次`add`不同的文件，最后一次性`commit`
```
$ git add file1.txt
$ git add file2.txt file3.txt
$ git commit -m "add 3 files."
```


---
>### Acknowledgement & Notice
>>**[致谢]** 在Git学习的过程中，`Init` `1-Git基本操作` `2-远程仓库Github` 完全参考廖雪峰老师的Git教程，并结合本地操作环境及本人对Git的需求进行编写，笔记简略，注重操作
>>* 在此推荐 [廖雪峰Git教程](https://www.liaoxuefeng.com/wiki/896043488029600)
>>
>>**[版权声明]** 如上，内容方面是廖老师教程的压缩版，并且目录齐全，非常欢迎`star` `folk` 后根据目录增添自己所需，但**严禁商用**，一切不以学习目的使用本仓库所造成一切后果自负！
>>
>>**[学习进度说明]** 鉴于本人当前需要，前三篇笔记学习内容截止到廖老师Git教程的`远程仓库`部分