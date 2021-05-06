FROM maven as builder
COPY . /campfire/
WORKDIR /campfire
RUN mvn package

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=builder /campfire/target/campfire.jar /usr/app/
WORKDIR /usr/app
CMD java -jar campfire.jar
