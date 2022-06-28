FROM openjdk:15
# copy the packaged jar file into our docker image
COPY build/libs/server.jar /server.jar
# set the startup command to execute the jar
CMD ["java", "-jar", "/server.jar"]