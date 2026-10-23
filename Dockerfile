FROM ubuntu:latest
LABEL authors="PC-1C"

ENTRYPOINT ["top", "-b"]

# Этап сборки (Build stage)
FROM gradle:9.8-jdk25-alpine AS build
WORKDIR /app
COPY build.gradle settings.gradle ./
COPY src ./src
COPY .openapi-generator-ignore ./.openapi-generator-ignore
COPY shema.yaml ./shema.yaml
RUN gradle bootJar --no-daemon

# Этап выполнения (Run stage)
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]