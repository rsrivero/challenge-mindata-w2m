FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests

FROM openjdk:21
COPY src/main/resources/application.yml /usr/app/application.yml
COPY --from=build /usr/src/app/target/challenge-mindata-w2m-0.0.1.jar /usr/app/challenge-mindata-w2m-0.0.1.jar
EXPOSE 8080:8080
ENTRYPOINT ["java", "-jar", "/usr/app/challenge-mindata-w2m-0.0.1.jar"]
