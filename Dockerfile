FROM maven:3.9-sapmachine-17 as build

ENV HOME=/workspace/app

WORKDIR $HOME

ARG PROFILE

ADD . $HOME

#RUN mvn -B dependency:go-offline
RUN --mount=type=cache,target=/root/.m2 mvn package -DskipTests -P${PROFILE}


FROM eclipse-temurin:17-jdk-alpine

VOLUME /tmp

ARG target=/workspace/app/target
COPY --from=build ${target}/*.jar /app.jar

EXPOSE 8888

ENTRYPOINT ["java","-jar","/app.jar"]

