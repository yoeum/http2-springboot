# Use Red Hat UBI as the base image with OpenJDK 17
FROM registry.access.redhat.com/ubi9/openjdk-17

# Install Maven for building the application
USER root
RUN yum install -y maven && yum clean all

# Switch to the root directory
# RUN mkdir -p /opt/app-root/src
WORKDIR /tmp

# Copy the source code into the container
COPY ./*.jpg  /tmp/

# Build the application using Maven
RUN mvn clean package -DskipTests

# Expose the port the application will run on
EXPOSE 8080

# Run the application
CMD ["java", "-Djava.net.preferIPv4Stack=true", "-Dserver.port=8080", "-Dserver.http2.enabled=true", "-Dserver.ssl.enabled=false", "-jar", "target/h2c-example-0.0.1-SNAPSHOT.jar"]
