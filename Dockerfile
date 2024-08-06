FROM openjdk:17
WORKDIR /app
COPY . /app
EXPOSE 8079
CMD ["java", "target/Demy-0.0.1-SNAPSHOT.war"]
