SHELL := /bin/sh

include .env
image_list=$(foreach image,$(docker_repos), docker pull $(image);)
container_list=$(shell docker ps -aq | tr '\n' ' ')
cmds=$(foreach container,$(container_list), docker rm -f $(container);)

.PHONY: all
all: images containers

.PHONY: containers
containers: clean mysql mongo couchdb redis neo


.PHONY: images
images:
	$(image_list)

.PHONY: mysql
mysql:
	docker run --name $(igt_mysql) -e MYSQL_ROOT_PASSWORD=$(skelleton_key) -d -p $(mysql_ports) mysql

.PHONY: mongo
mongo:
	mkdir ~/data
	docker run --name $(igt_mongo) -d -p $(mongo_ports) -v ~/data:/data/db mongo

.PHONY: couchdb
couchdb:
	docker run --name $(igt_couchdb) -e COUCHDB_USER=$(default_user) -e COUCHDB_PASSWORD=$(skelleton_key) -d -p $(couchdb_ports) -v ~/couchdb/data couchdb

.PHONY: redis
redis:
	docker run --name $(igt_redis) -d -p $(redis_ports) redis

.PHONY: neo
neo:
	docker run -d -p $(neo_ports) -v ~/neo4j/data:/data -v ~/neo4j/logs:/logs neo4j:3.0

#Removes all containers and resources associated with the IGT datastores
.PHONY: clean
clean :
	$(cmds)
	sudo rm -rf ~/data
	sudo rm -rf ~/neo4j
	sudo rm -rf ~/couchdb