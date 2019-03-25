#### 1.使用docker部署springboot项目

* 将springboot项目打成可执行jar包，如admin-web.jar
* 编写Dockerfile文件，内容如下

```
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD admin-web.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]


```

解释：
FROM：表示使用 Jdk8 环境 为基础镜像，如果镜像不是本地的会从 DockerHub 进行下载VOLUME ，  
VOLUME：指向了一个/tmp的目录，由于 Spring Boot 使用内置的Tomcat容器，Tomcat 默认使用/tmp作为工作目录。这个命令的效果是：在宿主机的/var/lib/docker目录下创建一个临时文件并把它链接到容器中的/tmp目录  
ADD：拷贝文件并且重命名  
ENTRYPOINT：为了缩短 Tomcat 的启动时间，添加java.security.egd的系统属性指向/dev/urandom作为 ENTRYPOINT

* 将admin-web.jar 与Dockerfile放入linux下某个文件夹，执行命令  
     docker build -t app .  
 * app为生成的镜像名，注意最后的点.
 * 执行完成后用docker images可以看到生成了一个app的镜像
 * 创建容器 docker run -id -p 8080:8080 --name=admin-web app   （-d表示容器后台运行）
 * 启动成功后在浏览器访问地址即可

mvn package  
mvb package docker:build