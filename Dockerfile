FROM openjdk:11
EXPOSE 4201
ADD target/medicarespringapp.jar medicarespringapp.jar
ENTRYPOINT ["java","-jar","/medicarespringapp.jar"]