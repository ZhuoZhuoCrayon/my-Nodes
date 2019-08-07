## 1-Git基本操作

### 查看修改
修改readme.txt为如下内容
```text
Git is a distributed version control system.
Git is free software.
```
运行`git status`查看结果
```
$ git status
On branch master
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

        modified:   readme.txt

no changes added to commit (use "git add" and/or "git commit -a")
```
`git status`命令用于查看仓库当前状态，上面输出表示`readme.txt`修改但**未提交**\
使用`git diff` 查看修改内容
```
$ git diff
diff --git a/readme.txt b/readme.txt
index d8036c1..013b5bc 100644
--- a/readme.txt
+++ b/readme.txt
@@ -1,2 +1,2 @@
-Git is a version control system.
+Git is a distributed version control system.
 Git is free software.
\ No newline at end of file
```

### 提交修改
**提交修改和提交新文件是一样的步骤**\
第一步，`git add`
```
$ git add readme.txt
```
再次运行`git status`查看当前仓库状态
```
$ git status
On branch master
Changes to be committed:
  (use "git reset HEAD <file>..." to unstage)

        modified:   readme.txt
```
输出说明被提交的修改包括`readme.txt`\
第二步，`git commit`
```
$ git commit -m "add distributed"
[master 3a9eeb5] add distributed
 1 file changed, 1 insertion(+), 1 deletion(-)
```
提交后，再次用`git status`查看仓库状态
```
$ git status
On branch master
nothing to commit, working tree clean
```
**working tree clean**表示没有需要提交的修改，工作目录是干净的

### 版本回退
对`readme.txt`文件进行第三次修改
```
Git is a distributed version control system.
Git is free software distributed under the GPL.
```
提交
```
$ git add readme.txt
$ git commit -m "append GPL"
[master 751aa86] append GPL
 1 file changed, 1 insertion(+), 1 deletion(-)
```
当文件修改到一定程度时就可以保存，这个存档在Git中称为`commit`，当文件被改乱或者误删，可以从最近一个`commit`恢复

自此，一共有三个版本的`readme.txt`文件提交到Git
版本1：wrote a readme file
```text
Git is a version control system.
Git is free software.
```
版本2：add distributed
```text
Git is a distributed version control system.
Git is free software.
```
版本3：append GPL
```text
Git is a distributed version control system.
Git is free software distributed under the GPL.
```
在实际工作中回忆工程的版本修改是不可能的，在Git中可以使用`git log`命令查看历史记录
```
$ git log
commit 751aa86bafe81df9d95c48b28ef958568f9c6229 (HEAD -> master)
Author: caixiaoxin <873217631@qq.com>
Date:   Sun Aug 4 19:35:35 2019 +0800

    append GPL

commit 3a9eeb5a55ff51196dcb270d3713a735212027f3
Author: caixiaoxin <873217631@qq.com>
Date:   Sun Aug 4 19:25:19 2019 +0800

    add distributed

commit 79e674fe0c6f50d3330920df0ab8309c7342c4a7
Author: caixiaoxin <873217631@qq.com>
Date:   Sat Aug 3 22:53:17 2019 +0800

    write a readme file
```
`git log`显示从**最近到最远**的提交日志\
`--pretty=oneline`参数可以简化输出信息
```text
$ git log --pretty=oneline
751aa86bafe81df9d95c48b28ef958568f9c6229 (HEAD -> master) append GPL
3a9eeb5a55ff51196dcb270d3713a735212027f3 add distributed
79e674fe0c6f50d3330920df0ab8309c7342c4a7 write a readme file
```
`751aa86ba...`是版本号（`commit id`）

**回退**\
在git中，`HEAD`表示当前版本，也就是最新的`751a...`，上一个版本是`HEAD^`，上上版本是`HEAD^^`,^可以省略成`~x`\
例如往上100个版本是`HEAD~100`

把当前版本`append GPL`回退到上一个版本`add distributed`，使用`git reset`命令
```
$ git reset --hard HEAD^
HEAD is now at 3a9eeb5 add distributed
```
`git log`查看版本库的状态
```text
$ git log
commit 3a9eeb5a55ff51196dcb270d3713a735212027f3 (HEAD -> master)
Author: caixiaoxin <873217631@qq.com>
Date:   Sun Aug 4 19:25:19 2019 +0800

    add distributed

commit 79e674fe0c6f50d3330920df0ab8309c7342c4a7
Author: caixiaoxin <873217631@qq.com>
Date:   Sat Aug 3 22:53:17 2019 +0800

    write a readme file
```
`append GPL`这个版本已经消失\
在命令行窗口没有关掉前，可以通过`append GPL`的`commit id`回到未来的某个版本
```text
$ git reset --hard 751a
HEAD is now at 751aa86 append GPL
```
关掉后，可以通过`git reflog`命令查看版本回退状态
```text
$ git reflog
751aa86 (HEAD -> master) HEAD@{0}: reset: moving to 751a
3a9eeb5 HEAD@{1}: reset: moving to HEAD^
751aa86 (HEAD -> master) HEAD@{2}: commit: append GPL
3a9eeb5 HEAD@{3}: commit: add distributed
79e674f HEAD@{4}: commit (initial): write a readme file
```
通过`commit id`回到未来版本

### 工作区和暂存区
**工作区（Working Directory）**\
在电脑中能看到的目录，比如`learngit`文件夹就是一个工作区

**版本库（Repository）**\
隐藏目录`.git`是Git的版本库
* 最重要的是`stage(index)`的暂存区
* 自动创建的分支`master`
* `master`指向的一个指针`HEAD`

![alt](img/index.png)

文件添加到版本库
* `git add` 将文件修改添加到暂存区
* `git commit` 将暂存区的**全部内容**提交到当前分支

**实践**

对`readme.txt`进行修改
```text
Git is a distributed version control system.
Git is free software distributed under the GPL.
Git has a mutable index called stage.
```
在工作区新增一个`LICENSE.txt`文件\
`git status`命令查看状态
```text
$ git status
On branch master
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

        modified:   readme.txt

Untracked files:
  (use "git add <file>..." to include in what will be committed)

        LICENSE.txt

no changes added to commit (use "git add" and/or "git commit -a")
```
查看输出，`readme.txt`被修改，`LICENSE.txt`从未被添加，所以状态是`Untracked`\
使用两次`git add`把两个文件添加，再用`git status`查看
```text
$ git add readme.txt
$ git add LICENSE.txt
$ git status
On branch master
Changes to be committed:
  (use "git reset HEAD <file>..." to unstage)

        new file:   LICENSE.txt
        modified:   readme.txt
```
现在的暂存区\
![alt now status](img/stage.png)\
`git add`命令将提交的所有修改放到暂存区，执行`git commit`可以一次性把暂存区的修改提交到分支
```text
$ git commit -m "understand how stage works"
[master feda12c] understand how stage works
 2 files changed, 3 insertions(+), 1 deletion(-)
 create mode 100644 LICENSE.txt
```
提交后如果没有对工作区做出修改，那么工作区就是干净的
```text
$ git status
On branch master
nothing to commit, working tree clean
```
![alt clean](img/workplace.png)

### 管理修改
**Git管理的修改而不是文件**

`readme.txt`添加一行
```text
$ cat readme.txt
Git is a distributed version control system.
Git is free software distributed under the GPL.
Git has a mutable index called stage.
Git tracks changes.
```
`git add`命令添加到暂存区
```text
$ git add readme.txt
$ git status
On branch master
Changes to be committed:
  (use "git reset HEAD <file>..." to unstage)

        modified:   readme.txt
```
再次修改`readme.txt`
```text
$ cat readme.txt
Git is a distributed version control system.
Git is free software distributed under the GPL.
Git has a mutable index called stage.
Git tracks changes of files.
```
提交
```text
$ git commit -m "git tracks changes"
[master 131817b] git tracks changes
 1 file changed, 1 insertion(+)
```
查看状态
```text
$ git status
On branch master
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

        modified:   readme.txt

no changes added to commit (use "git add" and/or "git commit -a")
```
第二次修改并没有被提交
操作过程：第一次修改->`git add`->第二次修改->`git commit`\
在`git add`后，工作区第一次修改被放入暂存区，准备提交，但第二次修改并木有放入，所以`git commit`只负责把暂存区的修改提交\
提交后用`git diff HEAD --readme.txt`查看工作区和版本区的区别
```text
$ git diff HEAD -- readme.txt
diff --git a/readme.txt b/readme.txt
index db28b2c..9a8b341 100644
--- a/readme.txt
+++ b/readme.txt
@@ -1,4 +1,4 @@
 Git is a distributed version control system.
 Git is free software distributed under the GPL.
 Git has a mutable index called stage.
-Git tracks changes.
\ No newline at end of file
+Git tracks changes of files.
\ No newline at end of file
```
可见第二次修改并没有提交

### 撤销修改
在`readme.txt`添加一行
```text
$ cat readme.txt
Git is a distributed version control system.
Git is free software distributed under the GPL.
Git has a mutable index called stage.
Git tracks changes of files.
My stupid boss still prefers SVN.
```
`git checkout -- file`命令可以丢弃工作区的修改\
`git checkout -- readme.txt`表示将`readme.txt`文件在工作区的修改全部撤销
* 文件修改后还没有放入暂存区，撤销修改后与版本库一致
* 已添加到暂存区，又做了修改，撤销修改后回到添加到暂存区的状态
* __总之，就是让文件回到最近一次`git commit` 或 `git add` 时候的状态__

将上述文件`git add`到暂存区，但还未`git commit`\
可以用`git reset HEAD <file>`将暂存区的修改撤销(unstage)，放回工作区
```text
$ git reset HEAD readme.txt
Unstaged changes after reset:
M       readme.txt
```
`git reset`的两个功能
* 回退版本
* 把暂存区的修改回退到工作区

查看暂存区和工作区的状态
```text
$ git status
On branch master
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

        modified:   readme.txt

no changes added to commit (use "git add" and/or "git commit -a")
```
暂存区是干净的，工作区有修改，`git checkout -- readme.txt`丢弃工作区修改
```text
$ git checkout -- readme.txt
$ git status
On branch master
nothing to commit, working tree clean

$ cat readme.txt
Git is a distributed version control system.
Git is free software distributed under the GPL.
Git has a mutable index called stage.
Git tracks changes.
```
回到最初状态

### 删除文件
添加新文件`test.txt`到Git
```text
$ git add test.txt
$ git commit -m "add test.txt"
[master a60936d] add test.txt
 1 file changed, 1 insertion(+)
 create mode 100644 test.txt
```
在文件管理器或者`rm`命令删除文件
```text
$ rm test.txt

$ git status
On branch master
Changes not staged for commit:
  (use "git add/rm <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

        deleted:    test.txt

no changes added to commit (use "git add" and/or "git commit -a")
```
`git status`告知删除\
第一种选择，从版本库中删除文件，`git rm`->`git commit`
```text
$ git rm test.txt
rm 'test.txt'

$ git commit -m "remove test.txt"
[master 4095354] remove test.txt
 1 file changed, 1 deletion(-)
 delete mode 100644 test.txt
```
第二种选择，误删，利用`git checkout -- file` 命令将误删文件恢复到最新版本
```text
$ git checkout -- test.txt
```
**`commit`后无法恢复，`rm`又`commit`，亲人两行泪**

---
>### Acknowledgement & Notice
>>**[致谢]** 在Git学习的过程中，`Init` `1-Git基本操作` `2-远程仓库Github` 完全参考廖雪峰老师的Git教程，并结合本地操作环境及本人对Git的需求进行编写，笔记简略，注重操作
>>* 在此推荐 [廖雪峰Git教程](https://www.liaoxuefeng.com/wiki/896043488029600)
>>
>>**[版权声明]** 如上，内容方面是廖老师教程的压缩版，并且目录齐全，非常欢迎`star` `folk` 后根据目录增添自己所需，但**严禁商用**，一切不以学习目的使用本仓库所造成一切后果自负！
>>
>>**[学习进度说明]** 鉴于本人当前需要，前三篇笔记学习内容截止到廖老师Git教程的`远程仓库`部分