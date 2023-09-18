FROM openjdk:8-jdk-alpine

ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

ENV TZ=America/Argentina/Buenos_Aires
WORKDIR /app

ENTRYPOINT java -cp .:lib/* $JAVA_OPTS cl.nisum.userchallenge.UserchallengeApplication