FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/algalog-api-*.jar /app/app.jar

CMD ["java", "-jar", "app.jar", "-Djava.security.egd=file:/dev/./urandom"]

EXPOSE 8080
