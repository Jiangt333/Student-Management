FROM openjdk:8-jre

ENTRYPOINT ["java","-jar","/score-service.jar","--spring.profiles.active=prod"]

COPY /target/score-service-0.0.1-SNAPSHOT.jar /score-service.jar