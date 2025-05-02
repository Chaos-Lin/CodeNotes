首先需要再文件夹中新建一个`Dockerfile`

构建docker镜像`docker build -t hello-docker .`
`hello-docker` 镜像名称
`.` means 当前目录

查看已有的docker镜像 `docker image ls`

运行镜像 `docker run hello-docker`

拉取镜像 `docker pull username/imagename`


用于存储依赖和运行环境文件`docker-compose.yml`
配置依赖和运行环境`docker compose up`