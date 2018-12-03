FROM ubuntu:18.04

# Obtain and install dependencies via RUN
RUN apt-get update && apt-get install -y \
    git \
    docker.io \
    maven \
    make
# Entry point is our own repo's setup file
WORKDIR /home/root
CMD git clone https://github.com/limecakeio/igt && cd igt && make \
    mysql \
    redis \
    mongo \
    cassandra \
    neo


EXPOSE 80