
VM_NAME = 'jenkins-pipeline'
VM_USER = 'vagrant'
MAC_USER = 'gautampachnanda'
VAGRANT_BOX = 'ubuntu/trusty64'

# Host folder to sync
HOST_PATH = '/Users/' + MAC_USER + '/' + VM_NAME
# Where to sync to on Guest — 'vagrant' is the default user name
GUEST_PATH = '/home/' + VM_USER + '/' + VM_NAME

Vagrant.configure("2") do |config|

  config.vm.box = VAGRANT_BOX
  
  config.vm.hostname = VM_NAME

  config.vm.provider "virtualbox" do |v|
    v.name = VM_NAME
    v.memory = 2048
  end
  #DHCP — comment this out if planning on using NAT instead
  #config.vm.network "private_network", type: "dhcp"
  config.vm.network "private_network", ip: "172.30.1.5"
  # config.vm.network "forwarded_port", guest: 80, host: VM_PORT
  # Sync folder
  config.vm.synced_folder HOST_PATH, GUEST_PATH
  # Disable default Vagrant folder, use a unique path per project
  config.vm.synced_folder '.', '/home/'+VM_USER+'', disabled: true

  config.vm.network "forwarded_port", guest: 8080, host: 8090
  # Install Git, gradle, jenkins, etc
  config.vm.provision "shell", inline: <<-SHELL
    apt-get update
    sudo apt-get install nodejs -y
    sudo apt-get remove java -y
    sudo apt-get install -y python-software-properties debconf-utils
    sudo add-apt-repository ppa:webupd8team/java
    sudo apt-get update
    sudo apt-get update
    echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 select true" | sudo debconf-set-selections
    sudo apt-get install -y oracle-java8-installer
    apt-get install -y git sudo
    apt-get install -y build-essential wget curl less vim
    apt-get update
    apt-get upgrade -y
    apt-get autoremove -y
    apt-get update && apt-get install -y maven wget curl syslog-ng-core unzip
    service syslog-ng start
    mkdir /usr/share/gradle 
    cd /usr/share/gradle && sudo wget https://services.gradle.org/distributions/gradle-4.4-bin.zip && sudo unzip gradle-4.4-bin.zip && sudo mv /gradle-4.4/* . && sudo rm gradle-4.4-bin.zip
    echo -n  >  /etc/profile.d/gradle.sh
    chmod +x /etc/profile.d/gradle.sh
    echo 'export GRADLE_HOME=/usr/share/gradle/gradle-4.4'  >> /etc/profile.d/gradle.sh
    echo 'export PATH=$PATH:$GRADLE_HOME/bin' >> /etc/profile.d/gradle.sh
    gradle
    echo "Install Ruby"
    apt-get install -y ruby
    echo "Install Python 2"
    apt-get install -y python python-pip
    echo "Install Python 3"
    apt-get install -y python3 python3-pip
    apt-get update
    apt-get install -y apt-transport-https ca-certificates
    apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys 58118E89F3A912897C070ADBF76221572C52609D
    echo "deb https://apt.dockerproject.org/repo ubuntu-trusty main" >> /etc/apt/sources.list.d/docker.list 
    apt-get update
    apt-get install -y docker-engine
    service docker start
    if [ ! -f /usr/bin/nodejs ]; then
        curl -sL https://deb.nodesource.com/setup_6.x | sudo -E bash -
        apt-get install -y nodejs
        sudo apt-get install -y build-essential
        npm install -g grunt-cli gulp-cli bower
    fi
    if [ ! -d /var/lib/jenkins ]; then
        wget -q -O - https://pkg.jenkins.io/debian-stable/jenkins.io.key | sudo apt-key add -
        if [ ! -f /etc/apt/sources.list.d/jenkins.list ]; then
            echo "deb https://pkg.jenkins.io/debian-stable binary/" >> /etc/apt/sources.list.d/jenkins.list
        fi
        apt-get update
        apt-get install jenkins -y
        if [ -f /var/lib/jenkins/secrets/initialAdminPassword ]; then
          cat /var/lib/jenkins/secrets/initialAdminPassword > ~/initialAdminPassword
        fi
    fi
    sudo usermod -aG docker $(whoami)
    sudo usermod -aG docker $USER
    sudo usermod -aG docker vagrant
    sudo usermod -aG docker jenkins
    sudo gpasswd -a ${USER} docker
    sudo service docker restart
    sudo service jenkins restart
    sudo chmod 777 /var/run/docker.sock
  SHELL
end