# Stage 1: Build the application
FROM maven:3.8.1-openjdk-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package

# Stage 2: Run the application
FROM openjdk:17-jdk-slim
COPY --from=build /app/target/test-0.0.1-SNAPSHOT.jar /test_app.jar
ENTRYPOINT ["java","-jar","/test_app.jar"]
