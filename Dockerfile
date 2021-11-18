FROM openjdk:11
EXPOSE 8080
ADD build/petstore-runner.jar petstore.jar
ENTRYPOINT ["java","-jar","petstore.jar"]