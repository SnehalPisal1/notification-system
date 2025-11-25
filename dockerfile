# Use Java 21 JDK slim image
FROM eclipse-temurin:21-jdk-jammy

# Set working directory inside container
WORKDIR /app

# Copy the built JAR
COPY build/libs/app.jar app.jar

# Copy all Spring Boot YAML configs
COPY src/main/resources/application*.yaml ./

# Expose the port Spring Boot runs on
EXPOSE 8080

# Set active profile
ENV SPRING_PROFILES_ACTIVE=dev

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
