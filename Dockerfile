FROM eclipse-temurin
WORKDIR /app
COPY build.gradle /
COPY gradlew  /
COPY gradle /
RUN gradlew build \
COPY build/*.jar /
CMD ["java","-jar","drone-dispatcher.jar"]