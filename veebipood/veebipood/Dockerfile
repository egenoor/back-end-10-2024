# Use an official OpenJDK 21 image as the base image
FROM eclipse-temurin:21-jdk AS base

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file to the container
COPY target/veebipood.jar app.jar

# Expose the application port (replace with your application's port if different)
EXPOSE 8080

# Define the entry point to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]