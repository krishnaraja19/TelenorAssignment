FROM openjdk:8
EXPOSE 8080
ADD target/product-docker.jar product-docker.jar
ENTRYPOINT ["java","-jar","product-docker.jar"]