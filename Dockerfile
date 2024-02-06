FROM maven:3.9.5-eclipse-temurin-21 AS build
WORKDIR /
COPY /src /src
COPY /config /config
COPY pom.xml /
RUN mvn -f /pom.xml clean package

FROM openjdk:21-jdk-slim
COPY --from=build /target/*.jar application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "application.jar"]