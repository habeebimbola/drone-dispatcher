FROM eclipse-temurin
WORKDIR /app
COPY build.gradle /.
COPY gradlew  /.
COPY .gradle/. gradle/. settings.gradle /
RUN /gradlew build
COPY build/libs/*.jar /
CMD ["java","-jar","musala-task-0.0.1.jar"]