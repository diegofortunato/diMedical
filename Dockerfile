FROM adoptopenjdk/openjdk11:latest
MAINTAINER Diego Fortunato
COPY build/libs/diMedical-0.0.1-SNAPSHOT.jar diMedical-0.0.1.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container", "-jar", "/diMedical-0.0.1.jar"]
