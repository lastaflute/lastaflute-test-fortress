FROM amazoncorretto:latest

RUN mkdir app
COPY ./target/lastaflute-test-fortress.war /app
WORKDIR /app
EXPOSE 8152

CMD ["java", "-jar", "lastaflute-test-fortress.war"]
