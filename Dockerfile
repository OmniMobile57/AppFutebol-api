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

COPY --from=build /app/target/demo-1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
