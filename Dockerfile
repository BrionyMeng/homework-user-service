FROM openjdk:11
ARG JAR_FILE
ADD build/libs/UserService-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
