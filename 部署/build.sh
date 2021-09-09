#先执行source /etc/profile
#再sh 该文件
cd /home/yuanman/git/common-pom/
mvn clean install -DskipTests
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
cd /home/yuanman/git/scx/
mvn clean install -DskipTests

