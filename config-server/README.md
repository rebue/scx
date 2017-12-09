# config-server

[TOC]

## 1. Deploy

```sh
mvn clean deploy
```

## 2. 上传latest

```sh
docker tag nnzbz/config-server:1.0.3-SNAPSHOT nnzbz/config-server:latest
docker push nnzbz/config-server:latest
```

## 3. 删除之前的容器

```sh
docker stop config-server
docker rm config-server
```

## 4. 创建并运行容器(单机版)

```sh
docker run -d --net=host --name config-server --restart=always nnzbz/config-server
```

## 5. 开启防火墙

```sh
firewall-cmd --zone=dmz --permanent --add-port=端口号/tcp
firewall-cmd --reload
```