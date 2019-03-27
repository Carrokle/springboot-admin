FROM openjdk:8-jdk-alpine
RUN sh -c 'touch $RUN_LIB' \
    && ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo "Asia/Shanghai" > /etc/timezone
VOLUME /tmp
ADD admin-web.jar admin-web.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/admin-web.jar"]