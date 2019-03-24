FROM openjdk:latest
COPY ./target/semCoursework.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "semCoursework.jar"]
