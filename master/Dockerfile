FROM openjdk:11.0.7

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} master.jar
ENTRYPOINT ["java","-jar","/master.jar"]