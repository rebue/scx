ROOT_DIR=$(cd "$(dirname "$0")/..";pwd)
cd $ROOT_DIR/scx/orp/orp-core/lib/
mvn install:install-file -Dfile=taobao-sdk-java-auto_1479188381469-20210908.jar -DgroupId=com.dingtalk.open -DartifactId=taobao-sdk-java-auto -Dversion=20210908 -Dpackaging=jar
cd $ROOT_DIR/scx/
mvn clean install -DskipTests
