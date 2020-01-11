##  一、开关机

- **sync** ：把内存中的数据写到磁盘中（关机、重启前都需先执行sync）
- **shutdown -rnow**或**reboot** ：立刻重启
- **shutdown -hnow** ：立刻关机
- **shutdown -h 19:00** ：预定时间关闭系统（晚上7点关机，如果现在超过8点则第二天）
- **shutdown -h +10** ：预定时间关闭系统（10分钟后关机）
- **shutdown -c** ：取消按预定时间关闭系统
- **init 0 ：**关闭系统
- **telinit 0 ：**关闭系统
- **logout ：**注销 

------

##  二、系统信息

 

- **arch ：**显示机器的处理器架构
- **uname -m ：**显示机器的处理器架构
- **uname -r ：**显示正在使用的内核版本 
- **dmidecode -q ：**显示硬件系统部件 - (SMBIOS / DMI) 
- **hdparm -i /dev/hda ：**罗列一个磁盘的架构特性 
- **hdparm -tT /dev/sda ：**在磁盘上执行测试性读取操作 
- **cat /proc/cpuinfo ：**显示CPU info的信息 
- **cat /proc/interrupts ：**显示中断 
- **cat /proc/meminfo ：**校验内存使用 
- **cat /proc/swaps ：**显示哪些swap被使用 
- **cat /proc/version ：**显示内核的版本 
- **cat /proc/net/dev ：**显示网络适配器及统计 
- **cat /proc/mounts ：**显示已加载的文件系统 
- **cat /etc/passwd :**显示密码信息
- **lspci -tv ：**罗列 PCI 设备 
- **lsusb -tv ：**显示 USB 设备 
- **date ：**显示系统日期 
- **cal 2007 ：**显示2007年的日历表 
- date 041217002007.00 ：设置日期和时间 - 月日时分年.秒 
- **clock -w ：**将时间修改保存到 BIOS
- **who ami：**查看当前使用的终端
- **who或w ：**查看所有终端
- **uname -m ：**显示机器的处理器架构（如x86_64）
- **uname -f:** 显示系统信息

------

##  三、文件和目录

 

- **cd /home**：进入 '/ home' 目录'
- **cd .. ：**返回上一级目录
- **cd ../..** ：返回上两级目录
- **cd ：**进入个人的主目录
- **cd ~ ：**进入个人的主目录
- **cd - ：**返回上次所在的目录
- **pwd ：**显示当前工作路径
- **ls ：**查看目录中的文件
- **ls -F ：**查看目录中的文件
- **ls -l ：**显示文件和目录的详细资料
- **ls -a ：**显示隐藏文件 ls *[0-9]* 显示包含数字的文件名和目录名
- **tree ：**显示文件和目录由根目录开始的树形结构
- **lstree ：**显示文件和目录由根目录开始的树形结构
- **mkdir dir1 ：**创建一个叫做 'dir1' 的目录'
- **mkdir dir1 dir2 ：**同时创建两个目录
- **mkdir -p /tmp/dir1/dir2 ：**创建一个目录树
- **rm -f file1 ：**删除一个叫做 'file1' 的文件'
- **rmdir dir1 ：**删除一个叫做 'dir1' 的目录'
- **rm -rf dir1 ：**删除一个叫做 'dir1' 的目录并同时删除其内容
- **rm -rf dir1 dir2 ：**同时删除两个目录及它们的内容
- **mv dir1 new_dir ：**重命名/移动 一个目录
- **cp file1 file2 ：**复制一个文件 cp dir/* . 复制一个目录下的所有文件到当前工作目录
- **cp -a /tmp/dir1 ：**复制一个目录到当前工作目录
- **cp -a dir1 dir2 ：**复制一个目录
- **ln -s file1 lnk1 ：**创建一个指向文件或目录的软链接
- **ln file1 lnk1 ：**创建一个指向文件或目录的物理链接
- **touch “test”：**创建一个名为test的文件

------

## 四、文件搜索

 

- **find / -name file1 ：**从 '/' 开始进入根文件系统搜索文件和目录 
- **find / -user user1 ：**搜索属于用户 'user1' 的文件和目录 
- **find /home/user1 -name \*.bin ：**在目录 '/ home/user1' 中搜索带有'.bin' 结尾的文件 
- **find /usr/bin -type f -atime +100 ：**搜索在过去100天内未被使用过的执行文件 
- **find /usr/bin -type f -mtime -10 ：**搜索在10天内被创建或者修改过的文件 
- **find / -name \*.rpm -exec chmod 755 '{}' \ ：**搜索以 '.rpm' 结尾的文件并定义其权限 
- **find / -xdev -name \*.rpm ：**搜索以 '.rpm' 结尾的文件，忽略光驱、捷盘等可移动设备 
- **locate \*.ps ：**寻找以 '.ps' 结尾的文件 - 先运行 'updatedb' 命令 
- **whereis halt ：**显示一个二进制文件、源码或man的位置 
- **which halt ：**显示一个二进制文件或可执行文件的完整路径

------

##  五、挂载系统文件

 

- **mount /dev/hda2 /mnt/hda2 ：**挂载一个叫做hda2的盘 - 确定目录 '/ mnt/hda2' 已经存在
- **umount /dev/hda2 ：**卸载一个叫做hda2的盘 - 先从挂载点 '/ mnt/hda2' 退出
- **fuser -km /mnt/hda2 ：**当设备繁忙时强制卸载
- **umount -n /mnt/hda2 ：**运行卸载操作而不写入 /etc/mtab 文件- 当文件为只读或当磁盘写满时非常有用
- **mount /dev/fd0 /mnt/floppy ：**挂载一个软盘
- **mount /dev/cdrom /mnt/cdrom ：**挂载一个cdrom或dvdrom
- **mount /dev/hdc /mnt/cdrecorder ：**挂载一个cdrw或dvdrom
- **mount /dev/hdb /mnt/cdrecorder ：**挂载一个cdrw或dvdrom
- **mount -o loop file.iso /mnt/cdrom ：**挂载一个文件或ISO镜像文件
- **mount -t vfat /dev/hda5 /mnt/hda5 ：**挂载一个Windows FAT32文件系统
- **mount /dev/sda1 /mnt/usbdisk ：**挂载一个usb 捷盘或闪存设备
- **mount -t smbfs -o username=user,password=pass //WinClient/share /mnt/share ：**挂载一个windows网络共享

------

##  五、用户和群组 

 

- **useradd 用户名** ：创建用户
- **userdel -r 用户名** :删除用户：（-r表示把用户的主目录一起删除）
- **usermod -g 组名 用户名** ：修改用户的组
- **usermod -aG 组名 用户名** ：将用户添加到组
- **groups test** ：查看test用户所在的组
- **cat /etc/group |grep test** ：查看test用户详情：用户名:口令:用户标识号:组标识号:注释性描述:主目录:登录Shell
- **passwd [ludf] 用户名** ：用户改自己密码，不需要输入用户名，选项-d:指定空口令,-l:禁用某用户，-u解禁某用户，-f：强迫用户下次登录时修改口令
- **groupadd 组名** ：创建用户组
- **groupdel 用户组** ：删除组
- **groupmod -n 新组名 旧组名** ：修改用户组名字
- **su - 用户名**：完整的切换到一个用户环境（相当于登录）（建议用这个）（退出用户：exit）
- **su 用户名** :切换到用户的身份（环境变量等没变，导致很多命令要加上绝对路径才能执行）
- **sudo 命令** ：以root的身份执行命令（输入用户自己的密码，而su为输入要切换用户的密码，普通用户需设置/etc/sudoers才可用sudo）
- **chage -E 2005-12-31 用户名：**设置用户口令的失效期限
- **pwck ：**检查 '/etc/passwd' 的文件格式和语法修正以及存在的用户
- **grpck ：**检查 '/etc/passwd' 的文件格式和语法修正以及存在的群组
- **newgrp group_name ：**登陆进一个新的群组以改变新创建文件的预设群组

------

##  六、文件权限操作

 

```
u 表示“用户（user）”，即文件或者目录所有者。``g 表示“同组（group）用户”，即文件属主有相同组``ID``的所有用户。``o 表示“其他（others）用户”，即系统默认值。
文字设定：（r：可读）、（w：可写）、（ x：可执行）``数字设定：（r：``4``）、（w：``2``）、（ x：``1``）
```

- **chmod ugo+rwx test.txt:** 表示分别给test.txt文件g、u、o用户可读、可写、可执行权限(也可单独给权限用逗号隔开)
- **chmod 777 test.txt:** 表示分别test.txt文件 g、u、o用户可读、可写、可执行权限（数字表示）
- **chmod u-wx test.txt：**表示删除test.txt文件 u用户可写、可执行权限。

------

##  七、打包压缩文件

 

- **bunzip2 file1.bz2 ：**解压一个叫做 'file1.bz2'的文件 
- **bzip2 file1 ：**压缩一个叫做 'file1' 的文件 
- **gunzip file1.gz ：**解压一个叫做 'file1.gz'的文件 
- **gzip file1 ：**压缩一个叫做 'file1'的文件 
- **gzip -9 file1 ：**最大程度压缩 
- **rar a file1.rar test_file ：**创建一个叫做 'file1.rar' 的包 
- **rar a file1.rar file1 file2 dir1 ：**同时压缩 'file1', 'file2' 以及目录 'dir1' 
- **rar x file1.rar ：**解压rar包 
- **unrar x file1.rar ：**解压rar包 
- **tar -cvf archive.tar file1 ：**创建一个非压缩的 tarball 
- **tar -cvf archive.tar file1 file2 dir1 ：**创建一个包含了 'file1', 'file2' 以及 'dir1'的档案文件 
- **tar -tf archive.tar ：**显示一个包中的内容 
- **tar -xvf archive.tar ：**释放一个包 
- **tar -xvf archive.tar -C /tmp ：**将压缩包释放到 /tmp目录下 
- **tar -cvfj archive.tar.bz2 dir1 ：**创建一个bzip2格式的压缩包 
- **tar -jxvf archive.tar.bz2 ：**解压一个bzip2格式的压缩包 
- **tar -cvfz archive.tar.gz dir1 ：**创建一个gzip格式的压缩包 
- **tar -zxvf archive.tar.gz ：**解压一个gzip格式的压缩包 
- **zip file1.zip file1 ：**创建一个zip格式的压缩包 
- **zip -r file1.zip file1 file2 dir1 ：**将几个文件和目录同时压缩成一个zip格式的压缩包 
- **unzip file1.zip ：**解压一个zip格式压缩包

------

##  八、RPM包

 

- **rpm -ivh 名字.rpm ：**安装一个rpm包 
- **rpm -ivh --nodeeps package.rpm ：**安装一个rpm包而忽略依赖关系警告 
- **rpm -U package.rpm ：**更新一个rpm包但不改变其配置文件 
- **rpm -F package.rpm ：**更新一个确定已经安装的rpm包 
- **rpm -e package_name.rpm ：**删除一个rpm包 
- **rpm -qa ：**显示系统中所有已经安装的rpm包 
- **rpm -qa | grep httpd ：**显示所有名称中包含 "httpd" 字样的rpm包 
- **rpm -qi package_name ：**获取一个已安装包的特殊信息 
- **rpm -qg "System Environment/Daemons" ：**显示一个组件的rpm包 
- **rpm -ql package_name ：**显示一个已经安装的rpm包提供的文件列表 
- **rpm -qc package_name ：**显示一个已经安装的rpm包提供的配置文件列表 
- **rpm -q package_name --whatrequires ：**显示与一个rpm包存在依赖关系的列表 
- **rpm -q package_name --whatprovides ：**显示一个rpm包所占的体积 
- **rpm -q package_name --scripts ：**显示在安装/删除期间所执行的脚本l 
- **rpm -q package_name --changelog ：**显示一个rpm包的修改历史 
- **rpm -qf /etc/httpd/conf/httpd.conf ：**确认所给的文件由哪个rpm包所提供 
- **rpm -qp package.rpm -l ：**显示由一个尚未安装的rpm包提供的文件列表 
- **rpm --import /media/cdrom/RPM-GPG-KEY ：**导入公钥数字证书 
- **rpm --checksig package.rpm ：**确认一个rpm包的完整性 
- **rpm -qa gpg-pubkey ：**确认已安装的所有rpm包的完整性 
- **rpm -V package_name ：**检查文件尺寸、 许可、类型、所有者、群组、MD5检查以及最后修改时间 
- **rpm -Va ：**检查系统中所有已安装的rpm包- 小心使用 
- **rpm -Vp package.rpm** ：确认一个rpm包还未安装 
- **rpm2cpio package.rpm | cpio --extract --make-directories \*bin\*** ：从一个rpm包运行可执行文件 
- **rpm -ivh /usr/src/redhat/RPMS/`arch`/package.rpm ：**从一个rpm源码安装一个构建好的包 
- **rpmbuild --rebuild package_name.src.rpm ：**从一个rpm源码构建一个 rpm 包 

------

##  九、yum软件包

 

- **yum install 软件名：**下载并安装一个rpm包 
- **yum localinstall 软件包名：** 将安装一个rpm包，使用你自己的软件仓库为你解决所有依赖关系 
- **yum update 软件包名.rpm ：**更新当前系统中所有安装的rpm包 
- **yum update 软件包名：**更新一个rpm包 
- **yum remove 软件包名：** 删除一个rpm包 
- **yum list**：列出当前系统中安装的所有包 
- **yum search 软件包名：** 在rpm仓库中搜寻软件包 
- **yum clean packages ：**清理rpm缓存删除下载的包 
- **yum clean headers ：**删除所有头文件 
- **yum clean all ：**删除所有缓存的包和头文件

------

##  十、磁盘管理

 

- **df -h :**显示磁盘的空间使用情况 及挂载点

- **df -h /var/log :**（显示log所在分区（挂载点）、目录所在磁盘及可用的磁盘容量）

- **du -sm /var/log/\* | sort -rn :** 根据占用磁盘空间大小排序（MB）某目录下文件和目录大小

- **fdisk -l :**查所有分区及总容量，加/dev/sda为查硬盘a的分区）

- **fdisk /dev/sdb :**对硬盘sdb进行分区

- **mount /dev/sda1 /mnt ：**硬盘sda1挂载到/mnt目录（mount 装置文件名 挂载点）

- **mount -t cifs -o username=luolanguo,password=win用户账号密码,vers=3.0 //10.2.1.178/G /mnt/usb :**远程linux 共享挂载windows的U盘,G为U盘共享名，需设置U盘共享

- **mount -o loop /opt/soft/CentOS-7-x86_64-DVD-1708.iso /media/CentOS ：**挂载iso文件

- **umount /dev/sda1 ：**取消挂载（umount 装置文件名或挂载点）

------

##  十一、DEB包

 

- **dpkg -i package.deb ：**安装/更新一个 deb 包 
- **dpkg -r package_name ：**从系统删除一个 deb 包 
- **dpkg -l ：**显示系统中所有已经安装的 deb 包 
- **dpkg -l | grep httpd ：**显示所有名称中包含 "httpd" 字样的deb包 
- **dpkg -s package_name ：**获得已经安装在系统中一个特殊包的信息 
- **dpkg -L package_name ：**显示系统中已经安装的一个deb包所提供的文件列表 
- **dpkg --contents package.deb ：**显示尚未安装的一个包所提供的文件列表 
- **dpkg -S /bin/ping ：**确认所给的文件由哪个deb包提供 

------

##  十二、APT软件工具

 

- **apt-get install package_name ：**安装/更新一个 deb 包 
- **apt-cdrom install package_name ：**从光盘安装/更新一个 deb 包 
- **apt-get update ：**升级列表中的软件包 
- **apt-get upgrade ：**升级所有已安装的软件 
- **apt-get remove package_name ：**从系统删除一个deb包 
- **apt-get check ：**确认依赖的软件仓库正确 
- **apt-get clean ：**从下载的软件包中清理缓存 
- **apt-cache search searched-package ：**返回包含所要搜索字符串的软件包名称 

------

## 十三、文本处理

 

- c**at file1 file2 ... | command <> file1_in.txt_or_file1_out.txt** general syntax for text manipulation using PIPE, STDIN and STDOUT 
- **cat file1 | command( sed, grep, awk, grep, etc...) > result.txt** 合并一个文件的详细说明文本，并将简介写入一个新文件中 
- **cat file1 | command( sed, grep, awk, grep, etc...) >> result.txt** 合并一个文件的详细说明文本，并将简介写入一个已有的文件中 
- **grep Aug /var/log/messages** 在文件 '/var/log/messages'中查找关键词"Aug" 
- **grep ^Aug /var/log/messages** 在文件 '/var/log/messages'中查找以"Aug"开始的词汇 
- **grep [0-9] /var/log/messages** 选择 '/var/log/messages' 文件中所有包含数字的行 
- **grep Aug -R /var/log/\*** 在目录 '/var/log' 及随后的目录中搜索字符串"Aug" 
- s**ed 's/stringa1/stringa2/g' example.txt** 将example.txt文件中的 "string1" 替换成 "string2" 
- **sed '/^$/d' example.txt** 从example.txt文件中删除所有空白行 
- **sed '/ \*#/d; /^$/d' example.txt** 从example.txt文件中删除所有注释和空白行 
- **echo 'esempio' | tr '[:lower:]' '[:upper:]'** 合并上下单元格内容 
- **sed -e '1d' result.txt** 从文件example.txt 中排除第一行 
- s**ed -n '/stringa1/p'** 查看只包含词汇 "string1"的行 
- **sed -e 's/ \*$//' example.txt** 删除每一行最后的空白字符 
- **sed -e 's/stringa1//g' example.txt** 从文档中只删除词汇 "string1" 并保留剩余全部 
- **sed -n '1,5p;5q' example.txt** 查看从第一行到第5行内容 
- **sed -n '5p;5q' example.txt** 查看第5行 
- **sed -e 's/00\*/0/g' example.txt** 用单个零替换多个零 
- **cat -n file1** 标示文件的行数 
- **cat example.txt | awk 'NR%2==1'** 删除example.txt文件中的所有偶数行 
- **echo a b c | awk '{print $1}'** 查看一行第一栏 
- **echo a b c | awk '{print $1,$3}'** 查看一行的第一和第三栏 
- **paste file1 file2** 合并两个文件或两栏的内容 
- **paste -d '+' file1 file2** 合并两个文件或两栏的内容，中间用"+"区分 
- **sort file1 file2** 排序两个文件的内容 
- **sort file1 file2 | uniq** 取出两个文件的并集(重复的行只保留一份) 
- **sort file1 file2 | uniq -u** 删除交集，留下其他的行 
- **sort file1 file2 | uniq -d** 取出两个文件的交集(只留下同时存在于两个文件中的文件) 
- **comm -1 file1 file2** 比较两个文件的内容只删除 'file1' 所包含的内容 
- **comm -2 file1 file2** 比较两个文件的内容只删除 'file2' 所包含的内容 
- **comm -3 file1 file2** 比较两个文件的内容只删除两个文件共有的部分 

------

## **十四、字符设置和文件格式转换** 

 

- **dos2unix filedos.txt fileunix.txt** 将一个文本文件的格式从MSDOS转换成UNIX 
- **unix2dos fileunix.txt filedos.txt** 将一个文本文件的格式从UNIX转换成MSDOS 
- **recode ..HTML < page.txt > page.html** 将一个文本文件转换成html 
- **recode -l | more** 显示所有允许的转换格式 

------

##  十五、**文件系统分析** 

- **badblocks -v /dev/hda1** 检查磁盘hda1上的坏磁块 
- **fsck /dev/hda1** 修复/检查hda1磁盘上linux文件系统的完整性 
- **fsck.ext2 /dev/hda1** 修复/检查hda1磁盘上ext2文件系统的完整性 
- **e2fsck /dev/hda1** 修复/检查hda1磁盘上ext2文件系统的完整性 
- **e2fsck -j /dev/hda1** 修复/检查hda1磁盘上ext3文件系统的完整性 
- **fsck.ext3 /dev/hda1** 修复/检查hda1磁盘上ext3文件系统的完整性 
- **fsck.vfat /dev/hda1** 修复/检查hda1磁盘上fat文件系统的完整性 
- **fsck.msdos /dev/hda1** 修复/检查hda1磁盘上dos文件系统的完整性 
- **dosfsck /dev/hda1** 修复/检查hda1磁盘上dos文件系统的完整性 

------

## 十六、初始化一个文件系统

- **mkfs /dev/hda1** 在hda1分区创建一个文件系统 
- **mke2fs /dev/hda1** 在hda1分区创建一个linux ext2的文件系统 
- **mke2fs -j /dev/hda1** 在hda1分区创建一个linux ext3(日志型)的文件系统 
- **mkfs -t vfat 32 -F /dev/hda1** 创建一个 FAT32 文件系统 
- **fdformat -n /dev/fd0** 格式化一个软盘 
- **mkswap /dev/hda3** 创建一个swap文件系统 

------

## 十七、SWAP文件系统

- **mkswap /dev/hda3** 创建一个swap文件系统 
- **swapon /dev/hda3** 启用一个新的swap文件系统 
- **swapon /dev/hda2 /dev/hdb3** 启用两个swap分区 

------

## 十八、备份

 

- **dump -0aj -f /tmp/home0.bak /home** 制作一个 '/home' 目录的完整备份 
- **dump -1aj -f /tmp/home0.bak /home** 制作一个 '/home' 目录的交互式备份 
- **restore -if /tmp/home0.bak** 还原一个交互式备份 
- **rsync -rogpav --delete /home /tmp** 同步两边的目录 
- **rsync -rogpav -e ssh --delete /home ip_address:/tmp** 通过SSH通道rsync 
- **rsync -az -e ssh --delete ip_addr:/home/public /home/local** 通过ssh和压缩将一个远程目录同步到本地目录 
- **rsync -az -e ssh --delete /home/local ip_addr:/home/public** 通过ssh和压缩将本地目录同步到远程目录 
- **dd bs=1M if=/dev/hda | gzip | ssh user@ip_addr 'dd of=hda.gz'** 通过ssh在远程主机上执行一次备份本地磁盘的操作 
- **dd if=/dev/sda of=/tmp/file1** 备份磁盘内容到一个文件 
- **tar -Puf backup.tar /home/user** 执行一次对 '/home/user' 目录的交互式备份操作 
- **( cd /tmp/local/ && tar c . ) | ssh -C user@ip_addr 'cd /home/share/ && tar x -p'** 通过ssh在远程目录中复制一个目录内容 
- **( tar c /home ) | ssh -C user@ip_addr 'cd /home/backup-home && tar x -p'** 通过ssh在远程目录中复制一个本地目录 
- **tar cf - . | (cd /tmp/backup ; tar xf - )** 本地将一个目录复制到另一个地方，保留原有权限及链接 
- **find /home/user1 -name '\*.txt' | xargs cp -av --target-directory=/home/backup/ --parents** 从一个目录查找并复制所有以 '.txt' 结尾的文件到另一个目录 
- **find /var/log -name '\*.log' | tar cv --files-from=- | bzip2 > log.tar.bz2** 查找所有以 '.log' 结尾的文件并做成一个bzip包 
- **dd if=/dev/hda of=/dev/fd0 bs=512 count=1** 做一个将 MBR (Master Boot Record)内容复制到软盘的动作 
- **dd if=/dev/fd0 of=/dev/hda bs=512 count=1** 从已经保存到软盘的备份中恢复MBR内容 

------

## 十九、光盘 

 

- **cdrecord -v gracetime=2 dev=/dev/cdrom -eject blank=fast -force** 清空一个可复写的光盘内容 
- **mkisofs /dev/cdrom > cd.iso** 在磁盘上创建一个光盘的iso镜像文件 
- **mkisofs /dev/cdrom | gzip > cd_iso.gz** 在磁盘上创建一个压缩了的光盘iso镜像文件 
- **mkisofs -J -allow-leading-dots -R -V "Label CD" -iso-level 4 -o ./cd.iso data_cd** 创建一个目录的iso镜像文件 
- **cdrecord -v dev=/dev/cdrom cd.iso** 刻录一个ISO镜像文件 
- **gzip -dc cd_iso.gz | cdrecord dev=/dev/cdrom -** 刻录一个压缩了的ISO镜像文件 
- **mount -o loop cd.iso /mnt/iso** 挂载一个ISO镜像文件 
- **cd-paranoia -B** 从一个CD光盘转录音轨到 wav 文件中 
- **cd-paranoia -- "-3"** 从一个CD光盘转录音轨到 wav 文件中（参数-3） 
- **cdrecord --scanbus** 扫描总线以识别scsi通道 
- **dd if=/dev/hdc | md5sum** 校验一个设备的md5sum编码，例如一张 CD 

------

## 二十、网络 - （以太网和WIFI无线） 

- **ifconfig eth0** 显示一个以太网卡的配置 
- **ifup eth0** 启用一个 'eth0' 网络设备 
- **ifdown eth0** 禁用一个 'eth0' 网络设备 
- **ifconfig eth0 192.168.1.1 netmask 255.255.255.0** 控制IP地址 
- **ifconfig eth0 promisc** 设置 'eth0' 成混杂模式以嗅探数据包 (sniffing) 
- **dhclient eth0** 以dhcp模式启用 'eth0' 
- **route -n show routing table** 
- route add -net 0/0 gw IP_Gateway configura default gateway 
- route add -net 192.168.0.0 netmask 255.255.0.0 gw 192.168.1.1 configure static route to reach network '192.168.0.0/16' 
- route del 0/0 gw IP_gateway remove static route 
- echo "1" > /proc/sys/net/ipv4/ip_forward activate ip routing 
- hostname show hostname of system 
- host www.example.com lookup hostname to resolve name to ip address and viceversa
- nslookup www.example.com lookup hostname to resolve name to ip address and viceversa
- ip link show show link status of all interfaces 
- mii-tool eth0 show link status of 'eth0' 
- ethtool eth0 show statistics of network card 'eth0' 
- netstat -tup show all active network connections and their PID 
- netstat -tupl show all network services listening on the system and their PID 
- tcpdump tcp port 80 show all HTTP traffic 
- iwlist scan show wireless networks 
- iwconfig eth1 show configuration of a wireless network card 
- hostname show hostname 
- host www.example.com lookup hostname to resolve name to ip address and viceversa 
- nslookup www.example.com lookup hostname to resolve name to ip address and viceversa 
- whois www.example.com lookup on Whois database 