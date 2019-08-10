# config-server

[TOC]

## 1. 制作Docker镜像

### 1.1. 编辑pom.xml文件

```xml
....
<build>
    <plugins>
        ....
        <plugin>
            <groupId>com.spotify</groupId>
            <artifactId>dockerfile-maven-plugin</artifactId>
            <executions>
                <execution>
                    <id>default</id>
                    <!-- 绑定到deploy阶段执行 -->
                    <phase>deploy</phase>
                    <!-- <phase></phase> -->
                    ....
                </execution>
            </executions>
            ....
        <plugin>
        ....
    </plugins>
</build>
....
```

### 1.2. 登录docker hub

```sh
docker login -unnzbz
```

### 1.3. maven build

**注意：如果编译出现 `Cannot run program "docker-credential-desktop": error=2, No such file or directory` 错误，请打开 `~/.docker/config.json` 文件，移除 `"credsStore": "desktop",` 这一行**

## 2. 上传latest

```sh
docker tag nnzbz/config-server:1.1.0 nnzbz/config-server:latest
docker push nnzbz/config-server:latest
```

## 3. 创建并运行容器(单机版)

```sh
# 创建并运行容器
docker run -d --net=host --name config-server --restart=always nnzbz/config-server
# 进入容器
docker exec -it config-server /bin/bash
# 修改配置文件
vi /usr/local/myservice/config/bootstrap-prod.yml
# 主要修改spring:cloud:config:server:git:下的配置项....
#...
# Ctrl+d退出
# 重启
docker restart config-server
```

## 4. 开启防火墙

```sh
firewall-cmd --zone=dmz --permanent --add-port=端口号/tcp
firewall-cmd --reload
```
