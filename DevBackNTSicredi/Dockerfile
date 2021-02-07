FROM maven:3-openjdk-11-slim as build-stage

WORKDIR /app-build

COPY ./pom.xml /app-build

RUN ls -al

RUN mvn dependency:go-offline

COPY . /app-build

RUN ls -al

RUN mvn clean install

RUN mvn package

FROM openjdk:11-jre-slim as run

WORKDIR /app

COPY --from=build-stage /app-build/target/*.jar /app/app.jar

RUN ls -al

ENTRYPOINT [ "java", "-jar", "/app/app.jar" ]