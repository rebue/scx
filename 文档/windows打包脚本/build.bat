@rem 打包脚本
set baseDir=C:\Users\lq\Desktop\maiyue-workspace\rebue\server

cd %baseDir%\common-pom
call mvn clean install -DskipTests
cd %baseDir%\wheel
call mvn clean install -DskipTests
cd %baseDir%\robotech
call mvn clean install -DskipTests
cd %baseDir%\common-pom\sb-dependencies
call mvn clean install -DskipTests
cd %baseDir%\sbs
call mvn clean install -DskipTests
cd %baseDir%\common-pom\sb-dependencies\sb-parent
call mvn clean install -DskipTests
cd %baseDir%\mbgx
call mvn clean install -DskipTests

cd %baseDir%\scx
call mvn clean package -DskipTests
pause
