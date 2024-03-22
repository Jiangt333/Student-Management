FROM openjdk:8-jre

ENTRYPOINT ["java","-jar","/eureka.jar","--spring.profiles.active=prod"]

COPY /target/eureka-0.0.1-SNAPSHOT.jar /eureka.jar