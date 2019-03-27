FROM openjdk:8-jdk-alpine
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo "Asia/Shanghai" > /etc/timezone
VOLUME /tmp
ADD admin-web.jar admin-web.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/admin-web.jar"]