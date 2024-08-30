
# Use Red Hat UBI as the base image with OpenJDK 17
FROM registry.access.redhat.com/ubi9/openjdk-17

RUN echo "yoeum Container v1.0" && ls -al
# Install Maven for building the application
USER root
RUN yum install -y maven && yum clean all

# Copy the source code into the container
RUN echo "Current directory during build:" && pwd
COPY .  /tmp/


# Build the application using Maven
RUN mvn clean package -DskipTests

# Expose the port the application will run on
EXPOSE 8080

# Switch to the root directory
#RUN mkdir -p /opt/app-root/src
#WORKDIR /opt/app-root/src


# Run the application
CMD ["java", "-Djava.net.preferIPv4Stack=true", "-Dserver.port=8080", "-Dserver.http2.enabled=true", "-Dserver.ssl.enabled=false", "-jar", "target/h2c-example-0.0.1-SNAPSHOT.jar"]
