FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD admin-web.jar admin-web.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/admin-web.jar"]