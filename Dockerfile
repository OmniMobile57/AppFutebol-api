# Primeira etapa de construção (build)
FROM maven:3.8.1-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ /app/src/
RUN mvn package -DskipTests

# Segunda etapa de construção (imagem final)
FROM openjdk:17-jdk-slim

WORKDIR /app

EXPOSE 8080

COPY --from=build /app/target/appfutebol-0.0.1-SNAPSHOT.jar app.jar

ARG SPRING_PROFILES_ACTIVE
ARG DB_PASSWORD
ARG DB_USER

ENV PROFILE=$SPRING_PROFILES_ACTIVE \
    PASSWORD=$DB_PASSWORD \
    USER=$DB_USER

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=${PROFILE}", "--spring.datasource.username=${USER}", "--spring.datasource.password=${PASSWORD}"]
