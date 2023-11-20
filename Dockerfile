# Use a base image with Java pre-installed
FROM amazoncorretto:17

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/controller-0.0.1-SNAPSHOT.jar /app/app.jar

# Copy the wait script
#COPY wait-for-mongo.sh /app/wait-for-mongo.sh

# Expose the port your Spring Boot app is listening on (default is 8080)
EXPOSE 8080

# Command to run the Spring Boot application
#CMD ["sh", "wait-for-mongo.sh"]
CMD ["java", "-jar", "app.jar"]
