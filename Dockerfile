FROM eclipse-temurin:17-jdk-alpine

VOLUME /tmp

ARG target=target
ARG PORT=PORT

ENV PORT=$PORT
COPY ${target}/*.jar /app.jar

EXPOSE ${PORT}

ENTRYPOINT ["java","-jar","/app.jar"]

