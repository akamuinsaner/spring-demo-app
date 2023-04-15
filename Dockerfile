FROM eclipse-temurin:17-jdk-alpine

VOLUME /tmp

ARG target=target
COPY ${target}/*.jar /app.jar

EXPOSE 8888

ENTRYPOINT ["java","-jar","/app.jar"]

