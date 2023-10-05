FROM docker-prod.registry.kroger.com/library/java-openjdk:17-latest

WORKDIR app

COPY loader/build/libs/loader-0.1.0.jar app/loader.jar

EXPOSE 8000

ENTRYPOINT ["java","-jar","app/loader.jar"]

# Run below command to create an image of the application
# docker build -f loader/Dockerfile -t <image_name> .

# Create a container to run the application
# docker run -p 8000:8000 loader-test
