# 基础镜像
FROM nnzbz/spring-boot-app:1.0.1
# 镜像的作者和邮箱
LABEL maintainer="nnzbz@163.com"
# 镜像的版本
LABEL version="1.1.0"
# 镜像的描述
LABEL description="config-server"

# 定义形参，实参可在下列情况下传入
# 1. 在pom.xml文件中的buildArgs节点下定义实参)
# 2. 在docker build命令中以--build-arg a_name=a_value形式赋值
ARG JAR_FILE

# 加入jar
COPY target/${JAR_FILE} /usr/local/myservice/myservice.jar
# 加入资源
COPY src/main/resources/config/bootstrap.yml /usr/local/myservice/config/bootstrap.yml
COPY src/main/resources/config/bootstrap-prod.yml /usr/local/myservice/config/bootstrap-prod.yml
COPY src/main/resources/config/log4j2.xml /usr/local/myservice/config/log4j2.xml

# 修改为生产模式
RUN sed -i 's/active: dev/active: prod/' config/bootstrap.yml