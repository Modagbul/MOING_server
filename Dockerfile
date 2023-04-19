FROM openjdk:11-jdk
# Add firebase-key.json
ARG FIREBASE_KEY_PATH
COPY ${FIREBASE_KEY_PATH} src/main/resources/firebase-key.json

ARG JAR_FILE=./build/libs/BE-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]