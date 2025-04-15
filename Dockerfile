FROM openjdk:22-jdk

RUN mkdir /bank

WORKDIR /bank

COPY target/*.jar /bank/api-bank.jar

CMD ["java", "-jar","/bank/api-bank.jar"]
