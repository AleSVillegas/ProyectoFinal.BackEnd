
FROM amazoncorretto:8-alpine-jdk

MAINTAINER asv

COPY target/asv-0.0.1-SNAPSHOT.jar my-backend.jar

ENTRYPOINT ["java","-jar","/my-backend.jar"]


