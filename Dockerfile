FROM openjdk:8-jdk-alpine
MAINTAINER Livhuwani Matsigila
ADD ./target/bet-engine-0.0.1-SNAPSHOT.jar bet-engine.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar","bet-engine.jar"]
