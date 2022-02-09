#!/bin/sh
#####################################################################################
# Docker容器启动时要运行的脚本 															#
# 注意: gateway-server的init.sh与其它微服务的不同在于多了一个spring-cloud-gateway的插件 	#
#####################################################################################
# 添加插件
mv ${SKYWALKING_AGENT_DIR}/optional-plugins/apm-spring-cloud-gateway-2.1.x-plugin-${SKYWALKING_AGENT_VERSION}.jar ${SKYWALKING_AGENT_DIR}/plugins/
mv ${SKYWALKING_AGENT_DIR}/optional-plugins/apm-mybatis-3.x-plugin-${SKYWALKING_AGENT_VERSION}.jar ${SKYWALKING_AGENT_DIR}/plugins/
mv ${SKYWALKING_AGENT_DIR}/optional-plugins/apm-spring-webflux-5.x-plugin-${SKYWALKING_AGENT_VERSION}.jar ${SKYWALKING_AGENT_DIR}/plugins/
mv ${SKYWALKING_AGENT_DIR}/optional-plugins/apm-trace-ignore-plugin-${SKYWALKING_AGENT_VERSION}.jar ${SKYWALKING_AGENT_DIR}/plugins/
# 移除不用的插件
mv ${SKYWALKING_AGENT_DIR}/plugins/dubbo-3.x-conflict-patch-${SKYWALKING_AGENT_VERSION}.jar ${SKYWALKING_AGENT_DIR}/optional-plugins/
mv ${SKYWALKING_AGENT_DIR}/plugins/apm-dubbo-3.x-plugin-${SKYWALKING_AGENT_VERSION}.jar ${SKYWALKING_AGENT_DIR}/optional-plugins/
mv ${SKYWALKING_AGENT_DIR}/plugins/apm-springmvc-annotation-3.x-plugin-${SKYWALKING_AGENT_VERSION}.jar ${SKYWALKING_AGENT_DIR}/optional-plugins/
mv ${SKYWALKING_AGENT_DIR}/plugins/apm-springmvc-annotation-4.x-plugin-${SKYWALKING_AGENT_VERSION}.jar ${SKYWALKING_AGENT_DIR}/optional-plugins/
mv ${SKYWALKING_AGENT_DIR}/plugins/apm-springmvc-annotation-5.x-plugin-${SKYWALKING_AGENT_VERSION}.jar ${SKYWALKING_AGENT_DIR}/optional-plugins/
mv ${SKYWALKING_AGENT_DIR}/plugins/apm-mysql-6.x-plugin-${SKYWALKING_AGENT_VERSION}.jar ${SKYWALKING_AGENT_DIR}/optional-plugins/
