# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.5/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.5/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.5/reference/htmlsingle/#web)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.0.5/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [Validation](https://docs.spring.io/spring-boot/docs/3.0.5/reference/htmlsingle/#io.validation)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.0.5/reference/htmlsingle/#using.devtools)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

From Project Base Directory:
 run ./gradlew clean
     ./gradlew bootRun to run project without Deployment
     ./gradlew build to build executable JAR file

From Project Base Directory, cd build/libs directory: {project-name}.jar file is here
run java -jar {project-name}.jar to run as executable 

Server Running At URL : http://localhost:8000

Check localhost:8000/h2-console from Browser Window to view in-memory Database table.

Drone Dispatch Service API Endpoints For Musala Task

drone-dispatch-service/register
drone-dispatch-service/drone/load/{serialNo}
drone-dispatch-service/drone/medications/{serialNo}
drone-dispatch-service/drone/available
drone-dispatch-service/drone/{serialNo}/battery-level
