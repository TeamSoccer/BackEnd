FROM openjdk:21
VOLUME /tmp
COPY build/libs/soccerteam-project.jar soccerteam-project.jar
ENTRYPOINT ["java","-jar","/soccerteam-project.jar","--spring.profiles.active=prod"]