FROM openjdk:11-jre-slim as builder
WORKDIR application
RUN sudo apt install curl
RUN curl -u dev:dev_ES2021 -o .jar "http://192.168.160.49:8082/artifactory/maven_es2021/pt/ua/deti/es/esp20/consumerdash/0.0.1-SNAPSHOT/consumerdash-0.0.1-20210518.164316-1.jar" -o /usr/app/app.jar

RUN java -Djarmode=layertools -jar /usr/app/app.jar extract

FROM openjdk:11-jre-slim
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
