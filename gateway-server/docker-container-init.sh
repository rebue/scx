#!/bin/sh
#####################################################################################
# Docker容器启动时要运行的脚本 															#
# 注意: gateway-server的init.sh与其它微服务的不同在于多了一个spring-cloud-gateway的插件 	#
#####################################################################################
mv ${SKYWALKING_AGENT_DIR}/optional-plugins/apm-spring-cloud-gateway-2.1.x-plugin-${SKYWALKING_AGENT_VERSION}.jar ${SKYWALKING_AGENT_DIR}/plugins/
mv ${SKYWALKING_AGENT_DIR}/optional-plugins/apm-mybatis-3.x-plugin-${SKYWALKING_AGENT_VERSION}.jar ${SKYWALKING_AGENT_DIR}/plugins/
mv ${SKYWALKING_AGENT_DIR}/optional-plugins/apm-spring-webflux-5.x-plugin-${SKYWALKING_AGENT_VERSION}.jar ${SKYWALKING_AGENT_DIR}/plugins/
mv ${SKYWALKING_AGENT_DIR}/optional-plugins/apm-trace-ignore-plugin-${SKYWALKING_AGENT_VERSION}.jar ${SKYWALKING_AGENT_DIR}/plugins/
