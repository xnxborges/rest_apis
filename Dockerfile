FROM openjdk:11-jdk-slim
VOLUME /tmp
ADD /target/rest_apis-0.0.1-SNAPSHOT.jar rest_apis.jar