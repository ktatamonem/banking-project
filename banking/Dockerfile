#
# Build
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src .
COPY pom.xml .
RUN mvn -f pom.xml clean package
#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build target/banking-0.0.1-SNAPSHOT.jar /usr/local/lib/banking.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/banking.jar"]