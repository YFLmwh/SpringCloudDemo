# 使用openjdk:17-jdk-alpine官方镜像作为基础镜像
FROM openjdk:17-jdk-alpine

# 指定镜像内部的工作目录
WORKDIR /app

# 将jar包添加到工作目录
COPY target/server-gateway.jar .

# 暴露端口
EXPOSE 7573

# 启动命令
ENTRYPOINT ["java", "-jar", "/app/server-gateway.jar"]