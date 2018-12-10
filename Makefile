SHELL := /bin/sh

include .env

db_image_list=$(foreach image,$(docker_db_repos), docker pull $(image);)
stop_dbs=$(foreach db,$(db_names), docker stop $(db);)
remove_dbs=$(foreach db,$(db_names), docker rm $(db);)

.PHONY: help
help:
	@fgrep -h "##" $(MAKEFILE_LIST) | fgrep -v fgrep | sed -e 's/\\$$//' | sed -e 's/\.PHONY://' | sed -e 's/##/ -> /'

.PHONY: all ## Retrieves all container images (VMs and DBMSs)
all: clean dbms vm

.PHONY: run ## Runs all containers (VMS and DBMSs)
run: run-dbms run-vm

.PHONY: dbms ## Retrieves all remote dbms images
dbms:
	$(db_image_list)

.PHONY: run-dbms ## Runs all of the IGT DBMSs
run-dbms: mysql mongo neo cassandra redis

.PHONY: clean-dbms ## Attempts to stop and remove all IGT DBMSs
clean-dbms:
	@echo "Stopping all DB containers..."
	- $(stop_dbs)
	@echo "Removing all DB containers..."
	- $(remove_dbs)
	@echo "... done." ;

.PHONY: vm ## Builds the Ubuntu VM for IGT
vm:
	docker build -t "igt_vm:latest" .

.PHONY: run-vm ## Runs all the IGT VMs
run-vm:
	docker run --name $(igt_ubuntu_vm) -p $(ubuntu_vm_ports) -v $(docker_socket) --network host -it igt_vm


.PHONY: clean-vm ## Attempts to stop and remove all IGT VMs
clean-vm:
	@echo "Stopping all IGT VM containers..."
	- docker stop $(igt_ubuntu_vm)
	@echo "Removing all VM containers..."
	- docker rm -f $(igt_ubuntu_vm)
	@echo "... done." ;

.PHONY: mysql ## Runs the MySQL relational database container
mysql:
	docker run --name $(igt_mysql) -e MYSQL_ROOT_PASSWORD=$(skelleton_key) -d -p $(mysql_ports) mysql

.PHONY: mongo ## Runs the MongoDB document store container
mongo:
	mkdir ~/data
	docker run --name $(igt_mongo) -d -p $(mongo_ports) -v ~/data:/data/db mongo

.PHONY: cassandra ## Runs the Cassandra column store container
cassandra:
	docker run --name $(igt_cassandra) -d -p $(cassandra_ports) cassandra

.PHONY: redis ## Runs the Redis key-value store container
redis:
	docker run --name $(igt_redis) -d -p $(redis_ports) redis

.PHONY: neo ## Runs the neo4j graph store container
neo:
	docker run --name $(igt_neo) -d -p $(neo_ports) -p "7687:7687" -v ~/neo4j/data:/data -v ~/neo4j/logs:/logs neo4j:3.0
	@echo "Sleeping 10 seconds before changing neo4j password..."
	sleep 10
	curl -H "Content-Type: application/json" -X POST -d '{"password":"igt"}' -u neo4j:neo4j http://localhost:7474/user/neo4j/password

.PHONY: clean ## Stops and removes all containers as well as resources [will ask for sudo]
clean : clean-dbms clean-vm
	@echo "Removing data and neo4j folder from HOME..."
	sudo rm -f -r ~/data ~/neo4j
	@echo "...done"
