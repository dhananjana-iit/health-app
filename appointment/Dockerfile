# Use an OpenJDK base image
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file into the container
COPY target/appointment-0.0.1-SNAPSHOT.jar appointment-scheduling-service.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "appointment-scheduling-service.jar"]
