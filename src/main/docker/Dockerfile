FROM java:8
VOLUME /tmp
COPY app.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT exec java $JAVA_OPTS -jar /app.jar