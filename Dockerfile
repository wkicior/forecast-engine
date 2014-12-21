FROM fedora
MAINTAINER https://github.com/wkicior
RUN yum update -y
RUN yum install -y scala sbt
RUN mkdir /forecast-engine
ADD src /forecast-engine/src
ADD built.sbt /forecast-engine/

EXPOSE 80
WORKDIR /forecast-engine

CMD sbt run

