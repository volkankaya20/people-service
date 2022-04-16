FROM openjdk:8-jdk-alpine AS builder
WORKDIR /build
COPY . /build
RUN apk add --no-cache bash
RUN chmod +x gradlew
RUN ./gradlew clean build

FROM openjdk:8-jdk-alpine AS runner
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=build/libs/people-service-0.1.0.jar
COPY --from=builder /build/build/libs/people-service-0.1.0.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
