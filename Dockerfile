FROM openjdk:latest
COPY ./target/semCoursework-0.1.0.4-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "semCoursework-0.1.0.4-jar-with-dependencies.jar"]
