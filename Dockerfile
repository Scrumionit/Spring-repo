#
# Mvn Build
#
FROM eclipse-temurin:21-jdk AS build

WORKDIR /home/app

COPY ./kyselypalvelu/src /home/app/src
COPY ./kyselypalvelu/pom.xml /home/app
RUN mvn -f -x /home/app/pom.xml clean package
 
#
# Jar Package
#
# StudentListSecureDB-0.0.1-SNAPSHOT.jar  = <artifactId>-<version>.jar (pom.xml)
FROM eclipse-temurin:21-jdk
COPY --from=build /home/app/target/kyselypalvelu-0.0.1-SNAPSHOT.jar /usr/local/lib/kyselypalvelu.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/kyselypalvelu.jar"]