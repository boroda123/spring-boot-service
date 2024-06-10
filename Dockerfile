
#
# Build stage
#
FROM openjdk:17 AS build
WORKDIR /home/app

COPY target/spring_rest_docker.jar /home/app/.
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "/home/app/spring_rest_docker.jar"]