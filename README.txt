# An engine for checking the surfing weather conditions. Gather the forecast for given location and forward it to notification service

#BUILD:
$ docker build -t wkicior/forecast-engine .

#RUN:
$ docker run -d -p 8000:80 wkicior/forecast-engine


~/Pobrane/apache-maven-3.0.5/bin/mvn clean install
~/Pobrane/apache-maven-3.0.5/bin/mvn package embedded-glassfish:run
