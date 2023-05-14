From maven:3.9.1-eclipse-temurin-8 as build
ARG SERVICE_NAME=ts-station-service

WORKDIR /app

COPY pom.xml pom.xml
COPY ts-common/pom.xml ts-common/pom.xml
COPY $SERVICE_NAME/pom.xml $SERVICE_NAME/pom.xml

# Remove all modules except ts-common and $SERVICE_NAME
RUN sed -i "/<module>\(ts-common\|$SERVICE_NAME\)<\/module>/!{/<module>.*<\/module>/d}" pom.xml

RUN mvn dependency:go-offline -B

COPY ts-common/src ./ts-common/src
COPY $SERVICE_NAME/src ./$SERVICE_NAME/src

RUN mvn package -DskipTests

FROM eclipse-temurin:8-jre
ARG SERVICE_NAME=ts-station-service
ENV SERVICE_NAME=$SERVICE_NAME

RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

COPY --from=build /app/$SERVICE_NAME/target/$SERVICE_NAME-1.0.jar /app/
CMD java -Xmx200m -jar /app/$SERVICE_NAME-1.0.jar

EXPOSE 12345