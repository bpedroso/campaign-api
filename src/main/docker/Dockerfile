FROM java:8
VOLUME /tmp
COPY campaign-api-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT exec java $JAVA_OPTS -jar /app.jar