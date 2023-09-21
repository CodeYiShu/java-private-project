#设置镜像基础，jdk8
FROM openjdk:8
#设置镜像对外暴露端口
EXPOSE 8088
#ARG：引用plugin中配置的 JAR_FILE 文件
ARG JAR_FILE
#将当前 target 目录下的 jar 放置在根目录下，命名为 app.jar，推荐使用绝对路径。
ADD target/${JAR_FILE} /app.jar
#执行启动命令
ENTRYPOINT ["java", "-jar","/app.jar"]
