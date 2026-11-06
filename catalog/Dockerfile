# Compilación reproducible sin instalar Maven ni Java en la EC2.
FROM maven:3.10.0-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests package

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /workspace/target/*.jar /app/app.jar
ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=70.0"
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
