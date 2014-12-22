FROM fedora
MAINTAINER https://github.com/wkicior
RUN yum update -y

# Download and install TomEE
RUN yum install -y python-setuptools
RUN easy_install pip
RUN pip install supervisor
#ADD supervisord.conf /etc/
ADD http://apache.arvixe.com/tomee/tomee-1.7.1/apache-tomee-1.7.1-plus.tar.gz /opt/apache-tomee-1.7.1-plus.tar.gz
RUN cd /opt && tar xzf apache-tomee-1.7.1-plus.tar.gz && rm apache-tomee-1.7.1-plus.tar.gz
RUN cd /opt && mv apache-tomee-plus-1.7.1 tomee
RUN mkdir -p /opt/tomee/logs/

EXPOSE 80
WORKDIR /forecast-engine

CMD sbt run

