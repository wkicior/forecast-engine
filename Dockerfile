FROM fedora
MAINTAINER https://github.com/wkicior
RUN yum update -y
RUN yum -y  install maven
# Download and install TomEE

# Build our apps from source code
RUN mkdir -p /opt/forecast-engine
ADD pom.xml /opt/forecast-engine/
ADD src /opt/forecast-engine/src
RUN cd /opt/forecast-engine && mvn clean install tomee:build

EXPOSE 8080
WORKDIR /opt/forecast-engine

CMD mvn tomee:start && tail -f target/apache-tomee/logs/catalina*log

