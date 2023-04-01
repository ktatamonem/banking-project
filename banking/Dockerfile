#
# Build
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY banking/src /home/app/src
COPY banking/pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package
#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/banking-0.0.1-SNAPSHOT.jar /usr/local/lib/banking.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/banking.jar"]