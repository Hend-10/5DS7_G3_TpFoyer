# Utiliser l'image OpenJDK 11
FROM openjdk:17


# Exposer le port de votre application Spring Boot
EXPOSE 8082

# Set the Nexus URL environment variable
ENV NEXUS_URL="http://192.168.50.4:8081"

# Accept a build argument for the version
ARG VERSION

# Set the path to the JAR file in Nexus using the version argument
ENV JAR_FILE_PATH="/repository/maven-releases/tn/esprit/tp-foyer/${VERSION}/tp-foyer${VERSION}.jar"
# Download the JAR file from Nexus using wget
RUN curl -o tp-foyer.jar "${NEXUS_URL}${JAR_FILE_PATH}"

# Définir la commande à exécuter
ENTRYPOINT ["java", "-jar", "/tp-foyer.jar"]
