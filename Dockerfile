FROM maven:3.8.4-openjdk-17 AS maven-builder
COPY pom.xml /app/
COPY src /app/src/
RUN mvn -f /app/pom.xml clean package -DskipTests

#FROM openjdk:17-alpine
ADD target/RobotRESTApp-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
