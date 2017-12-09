# zuul-server

[TOC]

## 1. Deploy

```sh
mvn clean deploy
```

## 2. 上传latest

```sh
docker tag nnzbz/zuul-server:1.0.3-SNAPSHOT nnzbz/zuul-server:latest
docker push nnzbz/zuul-server:latest
```

## 3. 删除之前的容器

```sh
docker stop zuul-server
docker rm zuul-server
```

## 4. 创建并运行容器(单机版)

```sh
docker run -d --net=host --name zuul-server --restart=always nnzbz/zuul-server
```

## 5. 修改容器中 bootstrap-prod.yml文件的服务端口号

```sh
docker exec -it zuul-server /bin/bash
vi config/bootstrap-prod.yml
```

```text
server:
  port: 80
```

## 6. 开启防火墙

```sh
firewall-cmd --zone=dmz --permanent --add-port=端口号/tcp
firewall-cmd --reload
```