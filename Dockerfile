FROM openjdk:17
WORKDIR /myapp
COPY . /myapp
EXPOSE 8079
CMD ["java","-jar", "target/Demy-0.0.1-SNAPSHOT.war"]
