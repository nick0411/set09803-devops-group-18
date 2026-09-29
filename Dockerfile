FROM eclipse-temurin:17-jre

COPY ./target/set09803-devops-group-18-v0.1.0-jar-with-dependencies.jar /tmp/app.jar

WORKDIR /tmp

ENTRYPOINT ["java", "-jar", "app.jar", "db:3306"]