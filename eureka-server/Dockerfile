# 基础镜像
FROM nnzbz/spring-boot-app
# 作者及邮箱
MAINTAINER zbz <nnzbz@163.com>

# 定义形参，实参可在下列情况下传入
# 1. 在pom.xml文件中的buildArgs节点下定义实参)
# 2. 在docker build命令中以--build-arg a_name=a_value形式赋值
ARG JAR_FILE

# 加入jar
COPY target/${JAR_FILE} /usr/local/myservice/myservice.jar
# 加入资源
COPY src/main/resources/config/application.yml /usr/local/myservice/config/application.yml
COPY src/main/resources/config/application-prod-single.yml /usr/local/myservice/config/application-prod-single.yml
COPY src/main/resources/config/application-prod-cluster.yml /usr/local/myservice/config/application-prod-cluster.yml
COPY src/main/resources/config/log4j2.xml /usr/local/myservice/config/log4j2.xml

# 修改为生产模式(默认单机版的配置)
RUN sed -i 's/active: dev/active: prod-single/' config/application.yml