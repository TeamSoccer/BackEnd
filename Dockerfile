FROM openjdk:21
COPY build/libs/teamsoccer-server.jar teamsoccer-server.jar
ENTRYPOINT ["java","-jar","/teamsoccer-server.jar","--spring.profiles.active=prod"]