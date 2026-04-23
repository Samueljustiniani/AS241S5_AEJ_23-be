# Stage 1: Build with Maven
FROM maven:3.9.0-eclipse-temurin-17-alpine AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run with Java
FROM amazoncorretto:17-alpine-jdk
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "app.jar"]

# COMANDOS DOCKER
# docker build -t samuel/as241s5_aej_23-be:1.0 .
# docker run -d --name as241s5_aej_23-be -p 9090:9090 samuel/as241s5_aej_23-be:1.0
# docker push samuel/as241s5_aej_23-be:1.0
