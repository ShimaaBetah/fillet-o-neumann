FROM maven:3.8-openjdk-18-slim


WORKDIR /fillet
COPY pom.xml /fillet/pom.xml
COPY src/ /fillet/src

RUN mvn clean install
CMD mvn test
