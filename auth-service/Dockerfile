FROM openjdk:8-jre

ENTRYPOINT ["java","-jar","/auth-service.jar","--spring.profiles.active=prod"]

COPY /target/auth-service-0.0.1-SNAPSHOT.jar /auth-service.jar