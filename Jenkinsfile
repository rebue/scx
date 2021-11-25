pipeline {
    agent any
    stages {
        stage('Build') { 
            steps {
                sh 'mvn install:install-file -Dfile=scx/orp/orp-core/lib/taobao-sdk-java-auto_1479188381469-20210908.jar -DgroupId=com.dingtalk.open -DartifactId=taobao-sdk-java-auto -Dversion=20210908 -Dpackaging=jar' 
                sh 'mvn -B -DskipTests clean package' 
            }
        }
    }
}