FROM java:8

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    MIMIRON_SLEEP=0 \
    JAVA_OPTS=""

ADD mimiron-gateway.jar /app.jar

EXPOSE 8080 5701/udp
CMD echo "The application will start in ${MIMIRON_SLEEP}s..." && \
    sleep ${MIMIRON_SLEEP} && \
    java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /app.jar
