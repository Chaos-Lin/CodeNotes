折磨了我一下午的xformers安装
- python 3.11
- cuda 12.1
- torch 2.6.0
然后`pip install xformers`
发现triton这个模块没有，windows需要直接用二进制文件编译
直接就是从"https://github.com/woct0rdho/triton-windows/releases"
找到"triton 3.2.0"下载下来然后进入该目录`pip instll 文件名`