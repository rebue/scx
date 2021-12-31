#!/bin/bash
CURRENT_PATH=$(cd "$(dirname "$0")";pwd)
cd $CURRENT_PATH
SVR_NAME=$(basename `pwd`)
read -p  "请输入远程主机地址：" REMOTE_HOST
read -p  "请输入登录远程主机地址的账号：" REMOTE_LOGIN_NAME
if [ -n "${REMOTE_LOGIN_NAME}" ];then
 	REMOTE_LOGIN_NAME=${REMOTE_LOGIN_NAME}@
fi

echo 请选择nacos配置：
NACOS_CONF=
IS_TRUE=true
while $IS_TRUE
do
	echo 1.单机  2.集群  3.自定义
	read NACOS_MODLE
	case ${NACOS_MODLE} in 
		1) 
		NACOS_MODLE="单机"
		NACOS_CONF='nacos1:8848'
		IS_TRUE=false
		;;
		2) 
		NACOS_MODLE="集群"
		IS_TRUE=false
		;;
		3) 
		NACOS_MODLE="自定义"
		read -p  "请输入nacos配置：" CU_NACOS
		NACOS_CONF=$CU_NACOS
		IS_TRUE=false
		;;
		*)
		echo 没有该选项，请重新选择
	esac
done

echo ===============================
echo 参数详情
echo 远程主机地址：$REMOTE_HOST
echo 远程登录账户：$REMOTE_LOGIN_NAME
echo 微服务的名称：$SVR_NAME
echo ===============================

rsync --progress -z target/${SVR_NAME}-*.jar ${REMOTE_LOGIN_NAME}${REMOTE_HOST}:/usr/local/${SVR_NAME}/ --exclude='*-javadoc.jar' --exclude='*-sources.jar'

rsync --progress -z src/main/resources/config/bootstrap-prod.yml ${REMOTE_LOGIN_NAME}${REMOTE_HOST}:/usr/local/${SVR_NAME}/config/

rsync --progress -z src/main/resources/config/log4j2.xml ${REMOTE_LOGIN_NAME}${REMOTE_HOST}:/usr/local/${SVR_NAME}/config/

TEMP_FILE="src/main/resources/config/smart-doc.json"
if [ -f "$TEMP_FILE" ];then
 	rsync --progress -z $TEMP_FILE ${REMOTE_LOGIN_NAME}${REMOTE_HOST}:/usr/local/${SVR_NAME}/config/
fi

TEMP_DIR="src/main/resources/static"
if [ -d "$TEMP_DIR" ];then
 	rsync -r --progress -z $TEMP_DIR ${REMOTE_LOGIN_NAME}${REMOTE_HOST}:/usr/local/${SVR_NAME}/
fi

case ${NACOS_MODLE} in
	'单机')
	ssh $REMOTE_LOGIN_NAME$REMOTE_HOST "sed -i '/server-addr/s/:.*/: ${NACOS_CONF}/g' /usr/local/${SVR_NAME}/config/bootstrap-prod.yml"
	;;
	'集群')
	;;
	'自定义')
	ssh $REMOTE_LOGIN_NAME$REMOTE_HOST "sed -i '/server-addr/s/:.*/: ${NACOS_CONF}/g' /usr/local/${SVR_NAME}/config/bootstrap-prod.yml"
	;;
esac
echo ===============================
