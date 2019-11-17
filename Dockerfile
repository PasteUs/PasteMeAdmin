FROM openjdk:11-jre-slim
LABEL maintainer="Lucien Shui" \
      email="lucien@lucien.ink"
COPY target/*.jar /app/app.jar
WORKDIR /app/
RUN mkdir /app/config /app/log
CMD ["java", "-jar", "./app.jar", "--spring.config.location=/app/config/application.yml"]
