### BUILD image
FROM maven:3-jdk-11 as builder
# create app folder for sources
RUN mkdir -p /build
WORKDIR /build
COPY pom.xml /build
#Download all required dependencies into one layer
RUN mvn -B dependency:resolve dependency:resolve-plugins
#Copy source code
COPY src /build/src
# Build application
RUN mvn package

FROM tomcat:9.0.64-jdk11-corretto

COPY --from=builder /build/target/accountant-1.0.war /usr/local/tomcat/webapps/accountant.war

EXPOSE 8080

CMD ["catalina.sh", "run"]