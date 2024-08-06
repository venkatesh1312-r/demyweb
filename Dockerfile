FROM ubuntu:20.04
RUN apt-get update
RUN apt-get install  -y openjdk-17-jdk
WORKDIR /app
COPY . /app
EXPOSE 8079
CMD ["java", "-jar", "target/Demy-0.0.1-SNAPSHOT.war"]
