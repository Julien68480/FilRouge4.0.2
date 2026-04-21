FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY StoryEnFolie-1.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
