FROM openjdk:latest

WORKDIR /app

COPY /target/testTask-1.0-SNAPSHOT.jar .

ENTRYPOINT ["java", "-jar", "testTask-1.0-SNAPSHOT.jar"]