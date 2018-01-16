# jenkins-pipeline

A new repo for installing jenkins with pipeline plugin example

If you are using mac you can install vagrant using
brew cask install vagrant

For other OS please using vagrant documentation to install vagrant

https://www.vagrantup.com/intro/getting-started/

checkout the repo from github https://github.com/gautampachnanda/jenkins-pipeline and cd jenkins-pipeline
Run the following commands
```sh
vagrant up
vagrant ssh
```

Go to http://172.30.1.5:8080/
Using the intialAdminPassword set admin password

Continue the setup using the default plugins provided

Now you can create a new item

You can create a example pipeline using the configuration

```
pipeline {
    agent {
        docker { image 'node:7-alpine' }
    }
    stages {
        stage('Build') {
            steps {
             
                sh 'node --version'
            }
        }
        stage('Test') {
            steps {
             
                sh 'node --version'
            }
        }
        stage('Deploy') {
            steps {
             
                sh 'node --version'
            }
        }
    }
}
```