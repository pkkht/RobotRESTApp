FROM maven:3.8.4-openjdk-17 AS MAVEN_BUILD
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn package spring-boot:repackage
#COPY ./target/RobotRESTApp*.jar /RobotApp/robot-api.jar
#WORKDIR /RobotApp/
ENTRYPOINT ["java", "-jar", "RobotRESTApp*.jar"]