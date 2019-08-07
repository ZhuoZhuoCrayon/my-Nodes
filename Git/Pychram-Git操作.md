## Pycharm-Git操作

### 在Pycharm添加GitHub账户
`File`->`Settings`->`Version Control`->`GitHub`，`Auth Type` 选择 `password`，输入账户密码，点击`Test`

![alt ](img/login.png)

### 上传仓库
`VCS`->`Import into Version Control`->`Share Poject on GitHub`

![alt up](img/up.png)

出现报错，需要修改Git默认路径
![alt error](img/error.png)

`File`->`Settings`->`Git`，将`Path to Git ..`修改为本机`git.exe`的位置
![alt solve](img/solve.png)

再重复上传的步骤，就可以成功将仓库上传到GitHub

