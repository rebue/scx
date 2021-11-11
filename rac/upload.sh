#!/bin/bash
ROOT_DIR=$(cd "$(dirname "$0")";pwd)
cd $ROOT_DIR
read -p  "请输入远程主机地址：" REMOTE_HOST
read -p  "请输入登录远程主机地址的账号：" REMOTE_LOGIN_NAME
if [ -n "${REMOTE_LOGIN_NAME}" ];then
	REMOTE_LOGIN_NAME=${REMOTE_LOGIN_NAME}@
fi
echo ===============================
echo 参数详情
echo 远程主机地址：$REMOTE_HOST
echo 远程登录账户：$REMOTE_LOGIN_NAME
echo ===============================
rsync --progress -z rac-svr/target/rac-svr-*.jar ${REMOTE_LOGIN_NAME}${REMOTE_HOST}:/usr/local/rac-svr/ --exclude='*-javadoc.jar' --exclude='*-sources.jar'
rsync --progress -z rac-svr/src/main/resources/config/log4j2.xml ${REMOTE_LOGIN_NAME}${REMOTE_HOST}:/usr/local/rac-svr/config/
rsync --progress -z rac-svr/src/main/resources/config/smart-doc.json ${REMOTE_LOGIN_NAME}${REMOTE_HOST}:/usr/local/rac-svr/config/
rsync -r --progress -z rac-svr/src/main/resources/static/ ${REMOTE_LOGIN_NAME}${REMOTE_HOST}:/usr/local/rac-svr/static/
echo ===============================
