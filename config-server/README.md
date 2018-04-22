# config-server

[TOC]


## 1. 上传latest

```sh
docker tag nnzbz/config-server:1.0.7 nnzbz/config-server:latest
docker push nnzbz/config-server:latest
```

## 2. 创建并运行容器(单机版)

```sh
# 创建并运行容器
docker run -d --net=host --name config-server --restart=always nnzbz/config-server
# 进入容器
docker exec -it config-server /bin/bash
# 修改配置文件
vi /usr/local/myservice/config/bootstrap-prod.yml
# 主要修改spring:cloud:config:server:git:下的配置项....
```

## 3. 开启防火墙

```sh
firewall-cmd --zone=dmz --permanent --add-port=端口号/tcp
firewall-cmd --reload
```