#先执行source /etc/profile
#再sh 该文件
cd /home/yuanman/git/common-pom/
mvn clean install -DskipTests
cd /home/yuanman/git/wheel//wheel-core/lib/
mvn install:install-file -Dfile=ojdbc6-11.2.0.3.jar -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0.3 -Dpackaging=jar -Dfile=ojdbc6-11.2.0.3.jar
cd /home/yuanman/git/wheel/
mvn clean install -DskipTests
cd /home/yuanman/git/robotech/
mvn clean install -DskipTests
cd /home/yuanman/git/common-pom/sb-dependencies/
mvn clean install -DskipTests
cd /home/yuanman/git/sbs/
mvn clean install -DskipTests
cd /home/yuanman/git/common-pom/sb-dependencies/sb-parent/
mvn clean install -DskipTests
cd /home/yuanman/git/mbgx/
mvn clean install -DskipTests
cd /home/yuanman/git/scx/orp/orp-core/lib/
mvn install:install-file -Dfile=taobao-sdk-java-auto_1479188381469-20210908.jar -DgroupId=com.dingtalk.open -DartifactId=taobao-sdk-java-auto -Dversion=20210908 -Dpackaging=jar
cd /home/yuanman/git/scx/
mvn clean install -DskipTests



