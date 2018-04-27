# eureka-server

[TOC]

## 1. 上传latest

```sh
docker tag nnzbz/eureka-server:1.0.7 nnzbz/eureka-server:latest
docker push nnzbz/eureka-server:latest
```

## 2. 创建并运行容器(单机版)

### 2.1. 单机版

```sh
docker run -dp8761:8761 --name eureka-server --restart=always nnzbz/eureka-server
```

### 2.2. 集群版

- 修改hosts文件

```sh
vi /etc/hosts
```

```text
# 注意这里最好列出本机指定网卡的ip，在分布式环境中避免不必要的麻烦
192.168.1.201 eureka-server-a eureka-server-b eureka-server-c
```

- 创建并运行容器

```sh
docker run -d --net=host --name eureka-server-a --restart=always nnzbz/eureka-server
docker run -d --net=host --name eureka-server-b --restart=always nnzbz/eureka-server
docker run -d --net=host --name eureka-server-c --restart=always nnzbz/eureka-server
```

- 修改配置文件

a

```sh
docker exec -it eureka-server-a /bin/bash
sed -i 's/active: prod/active: prod-cluster/' config/application.yml
## 第一个不用修改配置文件里面的内容（默认）
exit
```

b

```sh
docker exec -it eureka-server-b /bin/bash
sed -i 's/active: prod/active: prod-cluster/' config/application.yml
sed -i 's/port: 8761/port: 8762/' config/application-prod-cluster.yml
sed -i 's/hostname: eureka-server-a/hostname: eureka-server-b/' config/application-prod-cluster.yml
sed -i 's#defaultZone: http://eureka-server-b:8762/eureka/,http://eureka-server-c:8763/eureka/#defaultZone: http://eureka-server-a:8761/eureka/,http://eureka-server-c:8763/eureka/#' config/application-prod-cluster.yml
exit
```

c

```sh
docker exec -it eureka-server-c /bin/bash
sed -i 's/active: prod/active: prod-cluster/' config/application.yml
sed -i 's/port: 8761/port: 8763/' config/application-prod-cluster.yml
sed -i 's/hostname: eureka-server-a/hostname: eureka-server-c/' config/application-prod-cluster.yml
sed -i 's#defaultZone: http://eureka-server-b:8762/eureka/,http://eureka-server-c:8763/eureka/#defaultZone: http://eureka-server-a:8761/eureka/,http://eureka-server-b:8762/eureka/#' config/application-prod-cluster.yml
exit
```

- 重启容器

```sh
docker restart eureka-server-a
docker restart eureka-server-b
docker restart eureka-server-c
```

## 3. 开启防火墙端口

```sh
firewall-cmd --zone=dmz --permanent --add-port=8761/tcp
firewall-cmd --zone=dmz --permanent --add-port=8762/tcp
firewall-cmd --zone=dmz --permanent --add-port=8763/tcp
firewall-cmd --reload
```