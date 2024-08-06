FROM openjdk:17
WORKDIR /usr/src/myapp
COPY . /usr/src/myapp
EXPOSE 8079
CMD ["java", "target/Demy-0.0.1-SNAPSHOT.war"]
