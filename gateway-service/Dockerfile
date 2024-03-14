FROM openjdk:8-jre

ENTRYPOINT ["java","-jar","/gateway-service.jar","--spring.profiles.active=prod"]

COPY /target/gateway-service-0.0.1-SNAPSHOT.jar /gateway-service.jar