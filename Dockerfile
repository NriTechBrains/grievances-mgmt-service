FROM maven:3.9.8-amazoncorretto-21-al2023 as builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ ./src/
RUN mvn clean package -DskipTests=true

FROM amazoncorretto:21-al2023-jdk as prod
RUN mkdir /app
COPY --from=builder /app/target/*.jar /app/app.jar
ENV SERVER_PORT=8080
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java","-jar","./app.jar"]