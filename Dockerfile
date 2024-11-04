# Utiliser l'image OpenJDK 11
FROM openjdk:11-jdk-slim

# Exposer le port de votre application Spring Boot
EXPOSE 8080

# Ajouter le JAR généré dans le conteneur
ADD target/tp-foyer-5.0.0.jar tp-foyer.jar

# Définir la commande à exécuter
ENTRYPOINT ["java", "-jar", "/tp-foyer.jar"]
