FROM adoptopenjdk/openjdk15:ubi
COPY target/mediScreenAssessementReport-0.0.1-SNAPSHOT.jar mediScreenAssessementReport.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "mediScreenAssessementReport.jar"]