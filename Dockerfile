FROM fedora
MAINTAINER https://github.com/wkicior
RUN yum update -y
RUN yum install -y wget tar && wget --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u5-b13/jdk-8u5-linux-x64.rpm
RUN rpm -ivh jdk-8u5-linux-x64.rpm && rm jdk-8u5-linux-x64.rpm


RUN cd /opt && wget http://mirror.symnds.com/software/Apache/maven/maven-3/3.0.5/binaries/apache-maven-3.0.5-bin.tar.gz
RUN cd /opt && tar -zxvf apache-maven*
ENV JAVA_HOME /

# Build our apps from source code
RUN mkdir -p /opt/forecast-engine
ADD pom.xml /opt/forecast-engine/

EXPOSE 8020
WORKDIR /opt/forecast-engine
ADD src /opt/forecast-engine/src
CMD /opt/apache-maven-3.0.5/bin/mvn package embedded-glassfish:run 


