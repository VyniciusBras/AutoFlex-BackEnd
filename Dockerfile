FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY . .

RUN chmod +x ./gradlew

RUN ./gradlew bootJar -x test --no-daemon -Porg.gradle.java.installations.auto-detect=false

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "build/libs/autoflex-0.0.1-SNAPSHOT.jar"]