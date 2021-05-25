FROM openjdk:11-jre-slim as builder
WORKDIR application
COPY target/dubaidash-0.0.1-SNAPSHOT.jar /usr/app/dubaidash.jar

RUN java -Djarmode=layertools -jar /usr/app/dubaidash.jar extract

FROM openjdk:11-jre-slim
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]

