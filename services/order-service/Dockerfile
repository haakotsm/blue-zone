FROM openjdk:21-jdk-slim

LABEL maintainer="Blue Zone Team"
LABEL service="order-service"

WORKDIR /app

# Copy the Maven pom.xml and download dependencies
COPY pom.xml ./

# Copy the source code
COPY src ./src

# Install Maven
RUN apt-get update && apt-get install -y maven

# Build the application
RUN mvn clean package -DskipTests

# Expose the port
EXPOSE 8081

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
    CMD curl -f http://localhost:8081/actuator/health || exit 1

# Run the application
CMD ["java", "-jar", "target/order-service-1.0.0-SNAPSHOT.jar"]
