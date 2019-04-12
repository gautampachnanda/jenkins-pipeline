pipeline {
    agent none
    stages {
        stage('Build') {
            steps {
                echo 'Build' 
            }
        }
        stage('Test') {
            steps {
                echo 'Test' 
            }
        }
        stage('Deploy Local') {
            steps {
                echo 'Deploy' 
            }
        }
        
        stage('Deploy Integration') {
            steps {
                echo 'Deploying to Integration' 
            }
        }
         stage('Deploy QA') {
            steps {
                echo 'Deploying to QA' 
            }
        }
        
         stage('QA Acceptence') {
            steps {
                echo 'Running Acceptence in QA' 
            }
        }
        
        stage('Deploy Prod') {
            steps {
                echo 'Deploying to prod' 
            }
        }
    }
}
