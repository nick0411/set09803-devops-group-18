FROM eclipse-temurin:17-jre

COPY ./target/set09803-devops-group-18-1.0-SNAPSHOT-jar-with-dependencies.jar /tmp/app.jar

WORKDIR /tmp

ENTRYPOINT ["java", "-jar", "app.jar"]