FROM openjdk:11.0.7

ENV DEBIAN_FRONTEND noninteractive
RUN apt-get update && \
    apt-get -y install gcc mono-mcs && \
    rm -rf /var/lib/apt/lists/* && \
    apt-get install g++

COPY --from=golang:1.19.0 /usr/local/go/ /usr/local/go/
ENV PATH="/usr/local/go/bin:${PATH}"

RUN apt-get update -y && \
    apt-get install -y python3

# update
RUN apt-get update && \
    apt-get install curl && \
    curl -sL https://deb.nodesource.com/setup_4.x | bash && \
    apt-get install nodejs

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} master.jar
ENTRYPOINT ["java","-jar","/master.jar"]