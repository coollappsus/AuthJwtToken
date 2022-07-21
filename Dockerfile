FROM alpine:latest
EXPOSE 5555
RUN apk add openjdk16
COPY /target/testTask-1.0-SNAPSHOT.jar /testTask-1.0.jar

ENTRYPOINT ["java", "-jar", "/testTask-1.0.jar"]