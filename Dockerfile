FROM fedora
MAINTAINER https://github.com/wkicior
RUN yum update -y
RUN yum install -y wget tar curl unzip zip expect && wget --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u5-b13/jdk-8u5-linux-x64.rpm
RUN rpm -ivh jdk-8u5-linux-x64.rpm && rm jdk-8u5-linux-x64.rpm

RUN curl -L -o /tmp/glassfish-4.0.zip http://download.java.net/glassfish/4.0/release/glassfish-4.0.zip && \
unzip /tmp/glassfish-4.0.zip -d /usr/local && \
rm -f /tmp/glassfish-4.0.zip

RUN cd /opt && wget http://mirror.symnds.com/software/Apache/maven/maven-3/3.0.5/binaries/apache-maven-3.0.5-bin.tar.gz
RUN cd /opt && tar -zxvf apache-maven*
ENV JAVA_HOME /usr/java/jdk1.8.0_05

# Build our apps from source code
RUN mkdir -p /opt/forecast-engine
ADD pom.xml /opt/forecast-engine/

EXPOSE 8080 4848 8181
WORKDIR /opt/forecast-engine
ADD src /opt/forecast-engine/src
ADD change_admin_password_func.sh /usr/local/glassfish4/bin/
RUN chmod +x /usr/local/glassfish4/bin/change_admin_password_func.sh
#CMD /usr/local/glassfish4/bin/asadmin start-domain --verbose
CMD /usr/local/glassfish4/bin/asadmin start-domain && /usr/local/glassfish4/bin/change_admin_password_func.sh admin123 && /opt/apache-maven-3.0.5/bin/mvn package glassfish:deploy && tail -f /usr/local/glassfish4/glassfish/domains/domain1/logs/server.log


