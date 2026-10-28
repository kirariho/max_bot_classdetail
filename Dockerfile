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

COPY certs/russian_trusted_root_ca_pem.crt /tmp/root.crt
COPY certs/russian_trusted_sub_ca_pem.crt /tmp/sub.crt

RUN keytool -importcert -noprompt \
      -alias russian_trusted_root \
      -file /tmp/root.crt \
      -keystore "$JAVA_HOME/lib/security/cacerts" \
      -storepass changeit \
 && keytool -importcert -noprompt \
      -alias russian_trusted_sub \
      -file /tmp/sub.crt \
      -keystore "$JAVA_HOME/lib/security/cacerts" \
      -storepass changeit \
 && rm /tmp/root.crt /tmp/sub.crt

COPY --from=build /app/build/libs/bot-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]