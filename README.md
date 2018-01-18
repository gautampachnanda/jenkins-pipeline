# jenkins-pipeline

A new repo for installing jenkins with pipeline plugin example

If you are using mac you can install vagrant using

```sh
brew cask install vagrant
```

For other OS please using vagrant documentation to install vagrant

https://www.vagrantup.com/intro/getting-started/

checkout the repo from github https://github.com/gautampachnanda/jenkins-pipeline and cd jenkins-pipeline
Run the following commands
```sh
# To start & provision
vagrant up
# To provision
vagant provision
# To ssh into VM
vagrant ssh
# For help on command
vagrant -h

```


The source for vm is in Vagrantfile

There is an alterntaive docker file if you want to use a docker image

Once the VM is up you can go to http://172.30.1.5:8080/ and : 

Using the intialAdminPassword set admin password

Continue the setup using the default plugins provided

You can now create a new item

You can create a example pipeline using the configuration are provided in files. These based on a vargant build VM using docker containers for building.


```
java-pipeline.pipeline

node-pipeline.pipeline
```

Further documentation is available on https://jenkins.io/doc/book/pipeline/
