FROM jenkins/jenkins:lts

MAINTAINER Gautam Pachnanda

# if we want to install via apt
USER root

RUN apt-get update && apt-get install -y ruby make less sudo vim 
# drop back to the regular jenkins user - good practice

RUN apt-get update && apt-get install -y maven wget curl syslog-ng-core unzip
RUN service syslog-ng start

RUN mkdir /usr/share/gradle 
RUN cd /usr/share/gradle
RUN wget https://services.gradle.org/distributions/gradle-4.4-bin.zip 
RUN unzip gradle-4.4-bin.zip
RUN rm /gradle-4.4-bin.zip
RUN mv /gradle-4.4/* /usr/share/gradle/
RUN rm -rf /gradle-4.4
ENV PATH="/usr/share/gradle/bin:${PATH}"
RUN env
RUN ls -lah /usr/share/gradle
RUN gradle

#RUN sudo apt-get remove docker docker-engine docker.io
RUN sudo apt-get update
RUN sudo apt-get upgrade -y

RUN apt-get install -y  apt-transport-https dirmngr
RUN echo 'deb https://apt.dockerproject.org/repo debian-stretch main' >> /etc/apt/sources.list
RUN apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys 58118E89F3A912897C070ADBF76221572C52609D
RUN apt-get update
RUN apt-get install -y docker-engine







EXPOSE 8080 50000
#USER jenkins
