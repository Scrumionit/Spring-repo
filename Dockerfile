#
# Mvn Build
#
FROM eclipse-temurin:21-jdk AS build

# Install Maven
RUN apt-get update && apt-get install -y maven

#Copy project files
COPY ./kyselypalvelu/src /home/app/src
COPY ./kyselypalvelu/pom.xml /home/app

#Build project
RUN mvn -f /home/app/pom.xml clean package
#RUN mvn clean package -DskipTests
#
# Jar Package
#
# StudentListSecureDB-0.0.1-SNAPSHOT.jar  = <artifactId>-<version>.jar (pom.xml)
FROM eclipse-temurin:21-jdk
COPY --from=build /home/app/target/kyselypalvelu-0.0.1-SNAPSHOT.jar /usr/local/lib/kyselypalvelu.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/kyselypalvelu.jar"]