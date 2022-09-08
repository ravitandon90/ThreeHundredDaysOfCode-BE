FROM openjdk:11.0.7

ENV DEBIAN_FRONTEND noninteractive

# Install C++
RUN apt-get update && \
    apt-get install -y g++


# Install Go
COPY --from=golang:1.19.0 /usr/local/go/ /usr/local/go/
ENV PATH="/usr/local/go/bin:${PATH}"

# Install Python
RUN apt-get update && \
    apt-get install -y python3

# Install Php
RUN apt-get update && \
    apt-get install -y php

# Install Node
RUN apt-get update && \
    apt-get install -y nodejs

# Install VIM
RUN apt-get update && \
    apt-get install -y vim

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} master.jar
ENTRYPOINT ["java","-jar","/master.jar"]