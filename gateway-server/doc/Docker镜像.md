# Docker镜像

[TOC]

## 1. 第一次准备工作

### 1.1. 创建Dockerfile文件

```Dockerfile
# 基础镜像
FROM nnzbz/spring-boot-app:latest
# 镜像的作者和邮箱
LABEL maintainer="nnzbz@163.com"
# 镜像的版本
LABEL version="1.2.2"
# 镜像的描述
LABEL description="gateway-server"

# 定义形参，实参可在下列情况下传入
# 1. 在pom.xml文件中的buildArgs节点下定义实参)
# 2. 在docker build命令中以--build-arg a_name=a_value形式赋值
ARG JAR_FILE

# 加入jav
COPY target/${JAR_FILE} /usr/local/myservice/myservice.jar
# 加入资源
COPY src/main/resources/config/bootstrap.yml /usr/local/myservice/config/bootstrap.yml
COPY src/main/resources/config/bootstrap-prod.yml /usr/local/myservice/config/bootstrap-prod.yml
COPY src/main/resources/config/log4j2.xml /usr/local/myservice/config/log4j2.xml

# 修改为生产模式
RUN sed -i 's/active: dev/active: prod/' config/bootstrap.yml
```

### 1.2. 编辑settings.xml

编辑Maven的配置文件 `settings.xml`

```xml
....
<servers>
    ....
    <!-- hub.docker.com -->
    <server>
        <id>docker.io</id>
        <username>nnzbz</username>
        <password>xxxxxxxx</password>
    </server>
</servers>
....
```

### 1.3. 编辑pom.xml文件

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
                    <!-- 绑定到install阶段执行 -->
                    <phase>install</phase>
                    <!-- 绑定到deploy阶段执行 -->
                    <!-- <phase>deploy</phase> -->
                    <!-- 不绑定到任何阶段，也就不会执行 -->
                    <!-- <phase></phase> -->
                    <goals>
                        <goal>build</goal>
                        <goal>push</goal>
                    </goals>
                </execution>
            </executions>
            <configuration>
                <!-- 此选项指定在settings.xml文件中设置的server -->
                <useMavenSettingsForAuth>true</useMavenSettingsForAuth>
                <!-- 部署到私服，${my-docker.host}在settins.xml中统一配置 -->
                <!-- <repository>${my-docker.host}/${docker.image.prefix}/${project.artifactId}</repository> -->
                <!-- 部署到hub.docker.com -->
                <repository>nnzbz/gateway-server</repository>
                <tag>${project.version}</tag>
                <buildArgs>
                    <JAR_FILE>${project.build.finalName}.jar</JAR_FILE>
                </buildArgs>
                <verbose>true</verbose>
                <googleContainerRegistryEnabled>false</googleContainerRegistryEnabled>
            </configuration>
        <plugin>
        ....
    </plugins>
</build>
....
```

### 1.4. 创建仓库

<https://hub.docker.com/>

## 2. 编译并上传镜像

### 2.1. 登录docker hub

```sh
docker login -unnzbz
```

### 2.2. maven build

运行时设置 `Goals` 参数为 `clean install`，并且勾选 `Skip Test`

**注意：如果编译出现 `Cannot run program "docker-credential-desktop": error=2, No such file or directory` 错误，请打开 `~/.docker/config.json` 文件，移除 `"credsStore": "desktop",` 这一行**

### 2.3. 上传latest

```sh
docker tag nnzbz/gateway-server:1.2.2 nnzbz/gateway-server:latest
docker push nnzbz/gateway-server:latest
```

## 3. 创建并运行容器

```sh
# 单服务
docker run -d --net=host --name gateway-server --restart=always nnzbz/gateway-server
# 多服务
docker run -d --net=host --name gateway-server-a -v /usr/local/gateway-server/a:/usr/local/myservice --restart=always nnzbz/gateway-server
```

## 4. 修改容器中 bootstrap-prod.yml文件的服务端口号

```sh
docker exec -it gateway-server /bin/bash
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
