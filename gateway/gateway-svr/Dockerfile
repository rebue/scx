ARG VERSION

# 基础镜像
FROM nnzbz/spring-boot-app:17-alpine
# 镜像的作者和邮箱
LABEL maintainer="nnzbz@163.com"
# 镜像的版本
LABEL version=${VERSION}
# 镜像的描述
LABEL description="gateway-server"

# 定义形参，实参可在下列情况下传入
# 1. 在pom.xml文件中的buildArgs节点下定义实参)
# 2. 在docker build命令中以--build-arg a_name=a_value形式赋值
ARG JAR_FILE

# 加入jar包
COPY target/${JAR_FILE} /usr/local/myservice/myservice.jar
# 加入资源
COPY src/main/resources/config/application.yml /usr/local/myservice/config/application.yml
COPY src/main/resources/config/application-prod.yml /usr/local/myservice/config/application-prod.yml
COPY src/main/resources/config/logback* /usr/local/myservice/config/
COPY src/main/resources/config/logging-prod.yml /usr/local/myservice/config/

# 修改为生产模式
RUN sed -i 's/active: dev/active: prod/' config/application.yml