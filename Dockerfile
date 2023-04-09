#FROM maven:3.9-sapmachine-17 as build
#
#ENV HOME=/workspace/app
#
#WORKDIR $HOME
#
#ARG PROFILE
#
#ADD . $HOME
#
#RUN mvn -B dependency:go-offline
#RUN --mount=type=cache,target=/root/.m2 mvn package -DskipTests -P${PROFILE}
#RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM eclipse-temurin:17-jdk-alpine

VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

