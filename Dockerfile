FROM ubuntu:18.04

COPY . /igt

# Obtain and install dependencies via RUN
RUN apt-get update && apt-get install -y \
    nano \
    curl \
    make \
    docker.io \
    maven; \
    cd /igt/flightsystem; \
    mvn clean compile package -DskipTests;

WORKDIR /igt/flightsystem

EXPOSE 80