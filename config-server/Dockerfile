# 基础镜像
FROM nnzbz/spring-boot-app:1.0.0
# 作者及邮箱
MAINTAINER zbz <nnzbz@163.com>

# 定义形参，实参可在下列情况下传入
# 1. 在pom.xml文件中的buildArgs节点下定义实参)
# 2. 在docker build命令中以--build-arg a_name=a_value形式赋值
ARG JAR_FILE

# 加入服务
COPY target/${JAR_FILE} /usr/local/myservice/myservice.jar
COPY src/main/resources /usr/local/myservice
# 修改为生产模式
RUN sed -i 's/active: dev/active: prod/' config/application.yml


# 定义eureka server启动的端口号的缺省值到环境变量中（在ENTRYPOINT中使用）
ENV SERVER_PORT 8888