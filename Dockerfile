FROM amd64/amazoncorretto:17
COPY build/libs/*.jar /MVP_Backend.jar
ENTRYPOINT ["java", "-jar", "/MVP_Backend.jar"]
EXPOSE 8080
