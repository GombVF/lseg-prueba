FROM openjdk:17-oracle as builder
WORKDIR /app
copy target/prueba-0.0.1-SNAPSHOT.jar prueba-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "prueba-0.0.1-SNAPSHOT.jar"]