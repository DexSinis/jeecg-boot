FROM java:openjdk-8-jdk-alpine
VOLUME /tmp
ADD yewyw-interface.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

#docker run -d -p 9001:9001 yewyw-interface
