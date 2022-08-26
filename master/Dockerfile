FROM openjdk:11.0.7

ENV DEBIAN_FRONTEND noninteractive
RUN apt-get update && \
    apt-get -y install gcc mono-mcs && \
    rm -rf /var/lib/apt/lists/* && \
    apt-get install g++


# Install Go
COPY --from=golang:1.19.0 /usr/local/go/ /usr/local/go/
ENV PATH="/usr/local/go/bin:${PATH}"

# Install Python
RUN apt-get install -y python3

# Install Php
RUN apt-get install -y php

# Install Node
RUN apt-get install -y nodejs

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} master.jar
ENTRYPOINT ["java","-jar","/master.jar"]