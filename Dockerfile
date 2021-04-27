FROM openjdk:11-jre-slim

ENV TZ America/Sao_Paulo

COPY ./target/*.jar /app/openbanking.jar

ENTRYPOINT ["java","-Xmx256m","-jar","/app/openbanking.jar"]
#ENTRYPOINT exec java $JAVA_OPTIONS -jar /app/openbanking.jar

EXPOSE $PORT
