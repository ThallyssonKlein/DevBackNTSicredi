FROM adoptopenjdk/openjdk11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY wait-for-it.sh ./
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]