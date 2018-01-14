
VM_NAME = 'jenkins-pipeline'
VM_USER = 'vagrant'
MAC_USER = 'gautampachnanda'
VAGRANT_BOX = 'bento/ubuntu-16.04'

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
    apt-get install -y git sudo
    apt-get install -y build-essential wget curl less vim
    apt-get update
    apt-get upgrade -y
    apt-get autoremove -y
    apt-get update && apt-get install -y maven wget curl syslog-ng-core unzip
	service syslog-ng start
	mkdir /usr/share/gradle 
	cd /usr/share/gradle
	wget https://services.gradle.org/distributions/gradle-4.4-bin.zip 
	unzip gradle-4.4-bin.zip
	rm /usr/share/gradle/gradle-4.4-bin.zip
	mv /usr/share/gradle/gradle-4.4/ /usr/share/gradle/
	rm -rf /usr/share/gradle/gradle-4.4/
	echo -n  >  /etc/profile.d/gradle.sh
	chmod +x /etc/profile.d/gradle.sh
  	echo 'export GRADLE_HOME=$HOME/gradle'  >> /etc/profile.d/gradle.sh
  	echo 'export PATH=$PATH:$GRADLE_HOME/bin' >> /etc/profile.d/gradle.sh
	gradle
    echo "Install Ruby"
    apt-get install -y ruby
    echo "Install Python 2"
    apt-get install -y python python-pip
    echo "Install Python 3"
    apt-get install -y python3 python3-pip
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
          cat /var/lib/jenkins/secrets/initialAdminPassword
        fi
    fi
    sudo usermod -aG docker $(whoami)
    sudo usermod -aG docker $USER
    sudo usermod -aG docker vagrant
    sudo usermod -aG docker jenkins
    sudo groupadd docker
	sudo gpasswd -a ${USER} docker
	sudo service docker restart
  SHELL
end