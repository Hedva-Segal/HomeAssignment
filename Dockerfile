# Use the official OpenJDK 17 image as the base image
FROM openjdk:17
# Copy the JAR file into the container
COPY target/HomeAssignment*.jar /usr/src/HomeAssignment.jar
COPY src/main/resources/application.properties /opt/conf/application.properties
COPY src/main/resources/player.csv usr/src/player.csv
# Command to run your application
CMD ["java", "-jar","/usr/src/HomeAssignment.jar", "--spring.config.location=file:/opt/conf/application.properties"]
