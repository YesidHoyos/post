FROM openjdk:8-jre-alpine
RUN mkdir -p /usr/src/apps/woloxgram/post
COPY target/post-0.0.1-SNAPSHOT.jar /usr/src/apps/woloxgram/post
WORKDIR /usr/src/apps/woloxgram/post
CMD ["nohup", "java", "-jar", "post-0.0.1-SNAPSHOT.jar", "&"]