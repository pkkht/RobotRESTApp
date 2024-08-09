FROM maven:3.8.4-openjdk-17 AS MAVEN_BUILD
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn -DskipTests=true package spring-boot:repackage
WORKDIR /build/target/
ENTRYPOINT ["java", "-jar", "RobotRESTApp*.jar"]
