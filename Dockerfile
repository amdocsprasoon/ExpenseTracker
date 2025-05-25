# Use an Amazon Corretto base image
FROM amazoncorretto:17

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/ExpenseTracker-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application runs on internal port OF DOCKER
# THIS PORT AND THE PORT IN APPLICATION.PROPERTIES FILE MUST MATCH
# However, external port can be different
EXPOSE 8091

# Run the JAR file
ENTRYPOINT ["java","-Dspring.profiles.active=docker", "-jar", "app.jar"]