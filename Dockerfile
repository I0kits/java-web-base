FROM gradle:6.3.0-jdk8 as builder

RUN apt-get update \
        && DEBIAN_FRONTEND=noninteractive apt-get install tzdata \
        && ln -fs /usr/share/zoneinfo/Asia/Chongqing /etc/localtime

WORKDIR /app
COPY src ./src
COPY build.gradle ./
RUN echo "$(date +'%Y%m%d%H%M')" > ./src/main/resources/static/VERSION.html

# RUN gradle -Dorg.gradle.daemon=false -q -x test assemble
RUN gradle clean test asciidoctor bootJar

# ----------------------------------
FROM openjdk:8-jdk-alpine3.9 as prod

ENV APP_PORT 8080
EXPOSE $APP_PORT

WORKDIR /app
CMD ["java", "-jar", "app.jar"]
HEALTHCHECK CMD wget --quiet --tries=1 --spider http://localhost:$APP_PORT/actuator/health || exit 1

COPY --from=builder /app/build/libs/app.jar ./app.jar
