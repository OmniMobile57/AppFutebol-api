FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG SPRING_PROFILES_ACTIVE
COPY target/appfutebol-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}"]