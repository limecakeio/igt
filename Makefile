SHELL := /bin/sh

include .env

db_image_list=$(foreach image,$(docker_db_repos), docker pull $(image);)
vm_image_list=$(foreach image,$(docker_vm_repos), docker pull $(image);)

container_list=$(shell docker ps -aq | tr '\n' ' ')
cmds=$(foreach container,$(container_list), docker rm -f $(container);)

.PHONY: all
all: clean dbms vms

.PHONY: dbms
dbms: dbimages mysql mongo cassandra redis neo

.PHONY: vms
vms: vmimages vm

.PHONY: dbimages
dbimages:
	$(db_image_list)

.PHONY: vmimages
vmimages:
	$(vm_image_list)

.PHONY: mysql
mysql:
	docker run --name $(igt_mysql) -e MYSQL_ROOT_PASSWORD=$(skelleton_key) -d -p $(mysql_ports) mysql

.PHONY: mongo
mongo:
	mkdir ~/data
	docker run --name $(igt_mongo) -d -p $(mongo_ports) -v ~/data:/data/db mongo

.PHONY: cassandra
cassandra:
	docker run --name $(igt_cassandra) -d -p $(cassandra_ports) cassandra

.PHONY: redis
redis:
	docker run --name $(igt_redis) -d -p $(redis_ports) redis

.PHONY: neo
neo:
	docker run -d -p $(neo_ports) -v ~/neo4j/data:/data -v ~/neo4j/logs:/logs neo4j:3.0

.PHONY: vm
vm:
	docker run --name $(igt_ubuntu_vm) -p $(ubuntu_vm_ports) 1527079/igt-ubuntu-vm
#Removes all containers and resources associated with the IGT datastores
.PHONY: clean
clean :
	$(cmds)
	sudo rm -rf ~/data
	sudo rm -rf ~/neo4j