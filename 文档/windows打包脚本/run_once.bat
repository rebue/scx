set baseDir=C:\Users\lq\Desktop\maiyue-workspace\rebue\server

chdir /d c:
cd %baseDir%\wheel\wheel-core\lib\
mvn install:install-file -Dfile=ojdbc6-11.2.0.3.jar -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0.3 -Dpackaging=jar -Dfile=ojdbc6-11.2.0.3.jar
cd /%baseDir%\scx\orp\orp-core\libs\
mvn install:install-file -Dfile=taobao-sdk-java-auto_1479188381469-20210908.jar -DgroupId=com.dingtalk.open -DartifactId=taobao-sdk-java-auto -Dversion=20210908 -Dpackaging=jar