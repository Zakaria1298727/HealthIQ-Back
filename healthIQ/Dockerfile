FROM eclipse-temurin:17-jdk AS builder

WORKDIR /app

RUN apt-get update && apt-get install -y maven

COPY . .

RUN mvn clean install

FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=builder /app/target/healthIQ-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]

