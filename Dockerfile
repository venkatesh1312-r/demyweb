FROM openjdk:17-jdk
WORKDIR /app
COPY target/*.war /app/app.war
EXPOSE 8079
CMD ["java" ,"-jar" ,"target/Demy-0.0.1-SNAPSHOT.war"]
