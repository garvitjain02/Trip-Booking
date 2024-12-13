FROM openjdk:21-oracle
EXPOSE 8000
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} trip-booking-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/trip-booking-0.0.1-SNAPSHOT.jar"]