@rem 打包脚本
chdir /d c:
cd C:\Users\lq\Desktop\maiyue-workspace\rebue\server\common-pom
call mvn clean install -DskipTests
cd C:\Users\lq\Desktop\maiyue-workspace\rebue\server\wheel
call mvn clean install -DskipTests
cd C:\Users\lq\Desktop\maiyue-workspace\rebue\server\robotech
call mvn clean install -DskipTests
cd C:\Users\lq\Desktop\maiyue-workspace\rebue\server\common-pom\sb-dependencies
call mvn clean install -DskipTests
cd C:\Users\lq\Desktop\maiyue-workspace\rebue\server\sbs
call mvn clean install -DskipTests
cd C:\Users\lq\Desktop\maiyue-workspace\rebue\server\common-pom\sb-dependencies\sb-parent
call mvn clean install -DskipTests
cd C:\Users\lq\Desktop\maiyue-workspace\rebue\server\mbgx
call mvn clean install -DskipTests
cd C:\Users\lq\Desktop\maiyue-workspace\rebue\server\scx
call mvn clean package -DskipTests
pause