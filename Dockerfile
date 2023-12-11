FROM gradle as base
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -x test --no-daemon

FROM eclipse-temurin:17-jammy
WORKDIR /app
COPY --from=base /home/gradle/src/build/libs/*.jar  /app/musala-task.jar
ENTRYPOINT ["java","-jar","/app/musala-task.jar"]