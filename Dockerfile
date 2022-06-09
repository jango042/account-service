FROM adoptopenjdk/openjdk11:alpine-jre

VOLUME /tmp

EXPOSE 8005

ADD target/*.jar account-service.jar

ENV JAVA_OPTS=""

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /account-service.jar" ]