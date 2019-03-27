FROM openjdk:8-jdk-alpine
ENV TZ=Asia/Shanghai
VOLUME /tmp
ADD admin-web.jar admin-web.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Duser.timezone=GMT+08","-jar","/admin-web.jar"]