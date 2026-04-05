FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /build

COPY pom.xml ./
RUN mvn -q -DskipTests dependency:go-offline

COPY src ./src
RUN mvn -q -DskipTests clean package

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=builder /build/target/docker-asset-backend-0.0.1-SNAPSHOT.jar app.jar

RUN mkdir -p /app/data

ENV SPRING_PROFILES_ACTIVE=docker
EXPOSE 8080

CMD ["java", "-jar", "/app/app.jar"]
