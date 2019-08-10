# zuul-server

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
docker tag nnzbz/zuul-server:1.1.0 nnzbz/zuul-server:latest
docker push nnzbz/zuul-server:latest
```

## 3. 创建并运行容器

```sh
# 单服务
docker run -d --net=host --name zuul-server --restart=always nnzbz/zuul-server
# 多服务
docker run -d --net=host --name zuul-server-a -v /usr/local/zuul-server/a:/usr/local/myservice --restart=always nnzbz/zuul-server
```

## 4. 修改容器中 bootstrap-prod.yml文件的服务端口号

```sh
docker exec -it zuul-server /bin/bash
vi config/bootstrap-prod.yml
```

```text
server:
  port: 80
```

## 5. 开启防火墙

```sh
firewall-cmd --zone=dmz --permanent --add-port=端口号/tcp
firewall-cmd --reload
```

## 6. 过滤器官方示例

<https://github.com/spring-cloud-samples/sample-zuul-filters>
