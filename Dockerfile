FROM maven:3.8.4-openjdk-17 AS MAVEN_BUILD
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn package spring-boot:repackage
#FROM openjdk:17-jdk-slim-buster
#FROM busybox
#RUN set -x && yum update && \
    #
    # install *.UTF-8 locales otherwise some apps get trouble
    #
    # install other utilities
    #apt-get -y install \
     #   sudo curl wget tar.
WORKDIR /RobotApp/
COPY ./target/RobotRESTApp*.jar /RobotApp/robot-api.jar
ENTRYPOINT ["java", "-jar", "robot-api.jar"]