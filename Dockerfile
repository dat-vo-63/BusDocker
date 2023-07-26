


#
# Build stage
#
# syntax = docker/dockerfile:1
#
# Package stage
#
FROM openjdk:oraclelinux8
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","/spring-boot-docker.jar"]
RUN ./mvnw dependency:go-offline
COPY src ./src
CMD ["./mvnw","spring-boot:run"]