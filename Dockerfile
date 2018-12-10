FROM ubuntu:18.04

# Default args that can be changed in build statement
ENV github=https://github.com/limecakeio/igt
ENV repo=igt

# Obtain and install dependencies via RUN
RUN apt-get update && apt-get install -y \
    git \
    make \
    docker.io \
    maven

# Entry point is our own repo's setup file
CMD git clone ${github} ${repo};

WORKDIR /${repo}

CMD /bin/bash

EXPOSE 80