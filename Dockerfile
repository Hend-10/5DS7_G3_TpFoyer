# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from target to the working directory
ADD target/tp-foyer-5.0.0.jar tp-foyer.jar

# Define the command to run the application
CMD ["java", "-jar", "tp-foyer.jar"]
