FROM openjdk:8-jre-alpine
ADD target/studentApplication-0.0.1-SNAPSHOT.jar studentApplication-0.0.1-SNAPSHOT.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "studentApplication-0.0.1-SNAPSHOT.jar"]