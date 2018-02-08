pipeline {
    agent none
    stages {
        stage('Build') {
            agent { 
                docker {
                    image 'gautampachnanda/tiny-j8:latest'
                }    
            }
            steps {
                echo 'Build' 
                sh 'if [ -d simplespringboot ]; then  rm -rf  simplespringboot; echo xxxx;  fi'
                sh 'git clone https://github.com/gautampachnanda/simplespringboot.git'
                sh 'cd simplespringboot && mvn clean compile'
            }
        }
        stage('Test') {
            agent { 
                docker {
                    image 'gautampachnanda/tiny-j8:latest'
                }    
            }
            steps {
                echo 'Test' 
                sh 'if [ -d simplespringboot ]; then  rm -rf  simplespringboot; echo xxxx;  fi'
                sh 'git clone https://github.com/gautampachnanda/simplespringboot.git'
                sh 'cd simplespringboot && mvn clean test'
            }
        }
        stage('Deploy') {
            agent { 
                docker {
                    image 'gautampachnanda/tiny-j8:latest'
                }    
            }
            steps {
                echo 'Deploy' 
                sh 'if [ -d simplespringboot ]; then  rm -rf  simplespringboot; echo xxxx;  fi'
                sh 'git clone https://github.com/gautampachnanda/simplespringboot.git'
                sh 'cd simplespringboot && mvn clean install'
                sh 'cd simplespringboot && java -Dserver.port=9999 -jar target/simplespringboot-0.1.0.jar & export FOO_PID=$!  && echo $FOO_PID && env'
                sh 'echo $FOO_PID && sleep 10 && curl -I --connect-timeout 5 --max-time 10 --retry 5 --retry-delay 30 --retry-max-time 60 http://localhost:9999 && kill \"$FOO_PID\"'
                archiveArtifacts 'simplespringboot/target/*.jar'
            }
        }
    }
}