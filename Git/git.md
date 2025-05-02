# linux命令
```bash
cd
cd ..
pwd 显示当前所在的目录路径
ls
touch 新建文件
rm
mkdir 新建目录
rm -r
mv 移动文件
reset 初始化终端
clear
history 查看命令历史
help
exit
#表示注释
```
# git命令
```bash
git config --list 查看配置
    --system 系统配置
    --global 本地
```

## 设置全局用户信息
```bash
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```
![alt text](image-1.png)

![alt text](image.png)


## 创建一个新的项目
```bash

git init

# 添加文件
git add .

# 添加信息 “可以填任何信息”
git commit -m "first commit"

# 分支命名
git branch -M main

# 关联远程仓库
git remote add origin <URL>

# 推送至远程仓库
git push -u origin main
```
origin 是远程仓库的名称，指向远程仓库的 URL。
main 是你的本地分支的名称，表示你当前的主要开发分支。

## 连接至原有的git项目
```bash
git init

git clone <URL>
# URL用SSH更好

# 关联至远程仓库
git remote add origin <URL>

# 分支命名
git branch -M main
```
不同主机上分支名一直 可以保证代码移植


## 上传
```bash
git add .

git commit -m "info"

git push
```

## 下载
```bash
git pull
```


## 修改远程仓库的URL
`git remote set-url origin 新的远程仓库URL`

## 生成ssh密钥
```bash
ssh-keygen -t ed25519 -C "your_email@example.com"
[press enter]
[type a 密码]
```
然后在自己的账户上添加就可以了

## ssh连接
```bash
git remote set-url origin git@github.com:<username>/<repository>.git
```
## ignore文件
#开头的行是 注释。
*.log 忽略所有 .log 结尾的文件。
!important.log 例外规则，表示 不忽略 important.log。
/node_modules/ 忽略 node_modules 目录（/ 表示目录）。
/dist/ 只忽略仓库根目录下的 dist/，但不影响子目录的 dist/。
debug/ 忽略所有 debug 目录，不管在哪里。


# 协作
## 初始设置

### 设置全局用户信息
```bash
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```
### 新的设备连接ssh
生成公钥github
```bash
ssh-keygen -t ed25519 -C "your@email.com"
```
将公钥（~/.ssh/id_ed25519.pub 的内容）添加到 GitHub：
进入 GitHub SSH Keys 设置页面
点击 "New SSH Key"，粘贴公钥内容。

## 新项目初始化
### ​在设备A上创建本地仓库并推送到 GitHub​​：
```bash
mkdir my-project
cd my-project
git init
echo "# My Project" > README.md
git add .
git commit -m "Initial commit"
git branch -M main  # 确保分支名为 main
git remote add origin git@github.com:YourName/my-project.git
git push -u origin main
```
### ​​在设备B上克隆仓库​​：
```bash
git clone git@github.com:YourName/my-project.git
cd my-project
​
```
## 现有项目同步​​​
### ​设备A 推送更改​​：
```bash
git add .
git commit -m "info"
git push origin main
```

### ​​设备B 拉取最新更改​​：
```bash
git pull origin main
```



## 日常协作最佳实践​​
​
### ​频繁提交与推送​​：
完成一个小功能或修复后立即提交，避免代码丢失。
```bash
git add .
git commit -m "Fix login bug"
git push origin main​
```

### ​拉取前先同步​​：
在设备B上工作前，先拉取最新代码：
```bash
git pull origin main
```
​
### ​处理冲突​​：
如果两台设备同时修改了同一文件，拉取时会提示冲突。手动解决冲突后：
```bash
git add .
git commit -m "Resolve merge conflict"
git push origin main
```

## 分支隔离开发
### 从主分支创建新分支​​
```bash
# 确保当前在主分支
git checkout main

# 拉取最新代码（避免基于过时的代码开发）
git pull origin main

# 创建并切换到新分支（例如开发登录功能）
git checkout -b feature/login
```

​​说明​​：
- feature/login 是分支命名惯例（类型/功能描述），

常见类型：
- feature/xxx：新功能开发
- bugfix/xxx：问题修复
- hotfix/xxx：紧急修复


### 在分支上独立开发​​
​​提交代码到当前分支​​
```bash
# 修改代码后，提交到当前分支（feature/login）
git add .
git commit -m "实现用户登录接口"

# 如果需要推送到远程仓库（方便多设备协作）
git push origin feature/login
```

​​关键点​​：
所有修改和提交仅在 feature/login 分支上生效，不会影响 main 分支。
如果多人协作，推送分支后可在 GitHub 创建 Pull Request（PR）提前讨论代码。

### 合并分支到主分支​​

方法1：通过 Pull Request（推荐）​​
​1. ​推送分支到远程仓库​​（如果尚未推送）：
`git push origin feature/login`
2. 在 GitHub 上操作：
进入仓库 → 点击 Pull Requests → New Pull Request。
选择 base: main 和 compare: feature/login。
填写 PR 描述，请求团队成员审核代码。
审核通过后，点击 Merge pull request 合并到 main。


​​方法2：本地手动合并​​
```bash
# 切换回主分支
git checkout main

# 拉取最新代码（确保主分支是最新的）
git pull origin main

# 合并 feature/login 分支到 main
git merge feature/login

# 推送合并后的主分支
git push origin main
```


### 处理合并冲突​​
如果其他分支修改了相同文件的相同位置，合并时会出现冲突。解决方法：

​1. ​触发冲突后​​，Git 会标记冲突文件，例如：
```plaintext
<<<<<<< HEAD
main分支的代码
=======
feature/login分支的代码
>>>>>>> feature/login
```
​2. ​手动编辑文件​​，保留需要的代码，删除冲突标记。
​3. ​标记冲突已解决​​：
```bash
git add .
git commit -m "解决合并冲突"
```
### 分支管理最佳实践​​

​​1. 保持分支短生命周期​​
- 一个分支只做一件事（例如开发一个功能或修复一个 Bug）。
- 完成后立即合并到主分支，避免长期游离。

​​2. 定期同步主分支​​
- 如果主分支（main）有更新，可以同步到当前开发分支：
```bash
git checkout feature/login
git merge main  # 将 main 的更新合并到当前分支
```
​​3. 删除已合并的分支​​
- 本地删除：
```bash
git branch -d feature/login
```
- 远程删除：
```bash
git push origin --delete feature/login
```

​​4. 使用 rebase 替代 merge（可选）​​
- 如果希望提交历史更线性，可以在合并前变基：
```bash
git checkout feature/login
git rebase main   # 将当前分支的修改“重新播放”到 main 的最新提交上
git checkout main
git merge feature/login  # 此时会快进合并（无冲突）
```
​​注意​​：rebase 会改写历史，仅推荐在个人分支使用。
​​
### 可视化工具辅助​​
​- ​命令行查看分支拓扑​​：
```bash
git log --graph --oneline --all
```
​- ​使用 GUI 工具​​（如 VS Code 的 Git 插件、GitHub Desktop、Sourcetree）直观操作分支。
