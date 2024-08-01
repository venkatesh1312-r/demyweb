FROM ubuntu:20.04
RUN apt-get update
RUN apt-get install -y openjdk-17-jdk
WORKDIR /app
EXPOSE 8079
CMD ["java" ,"-jar" ,"/app/app.war"]
