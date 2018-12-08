SHELL := /bin/sh

include .env

db_image_list=$(foreach image,$(docker_db_repos), docker pull $(image);)
stop_dbs=$(foreach db,$(db_names), docker stop $(db);)
remove_dbs=$(foreach db,$(db_names), docker rm $(db);)

vm_image_list=$(foreach image,$(docker_vm_repos), docker pull $(image);)
stop_vms=$(foreach vm,$(vm_names), docker stop $(vm);)
remove_vms=$(foreach vm,$(vm_names), docker rm $(vm);)

.PHONY: help
help:
	@fgrep -h "##" $(MAKEFILE_LIST) | fgrep -v fgrep | sed -e 's/\\$$//' | sed -e 's/\.PHONY://' | sed -e 's/##/ -> /'

.PHONY: all ## Retrieves all container images (VMs and DBMSs)
all: clean dbms vms

.PHONY: run ## Runs all containers (VMS and DBMSs)
run: run-dbms run-vms

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

.PHONY: vms ## Retrieves all remote vm images
vms:
	$(vm_image_list)

.PHONY: run-vms ## Runs all of the IGT VMs
run-vms: vm

.PHONY: clean-vms ## Attempts to stop and remove all IGT VMs
clean-vms:
	@echo "Stopping all VM containers..."
	- $(stop_vms)
	@echo "Removing all VM containers..."
	- $(remove_vms)
	@echo "... done." ;

.PHONY: mysql ## Runs the MySQL relational database container
mysql:
	docker run --name $(vm_prefix)$(igt_mysql) -e MYSQL_ROOT_PASSWORD=$(skelleton_key) -d -p $(mysql_ports) mysql

.PHONY: mongo ## Runs the MongoDB document store container
mongo:
	mkdir ~/data
	docker run --name $(vm_prefix)$(igt_mongo) -d -p $(mongo_ports) -v ~/data:/data/db mongo

.PHONY: cassandra ## Runs the Cassandra column store container
cassandra:
	docker run --name $(vm_prefix)$(igt_cassandra) -d -p $(cassandra_ports) cassandra

.PHONY: redis ## Runs the Redis key-value store container
redis:
	docker run --name $(vm_prefix)$(igt_redis) -d -p $(redis_ports) redis

.PHONY: neo ## Runs the neo4j graph store container
neo:
	docker run --name $(vm_prefix)$(igt_neo) -d -p $(neo_ports) -v ~/neo4j/data:/data -v ~/neo4j/logs:/logs neo4j:3.0

.PHONY: vm ## Runs the Ubuntu virtual machine container
vm:
	docker run --name $(vm_prefix)$(igt_ubuntu_vm) -p $(ubuntu_vm_ports) -v $(docker_socket) 1527079/igt-ubuntu-vm
#Removes all containers and resources associated with the IGT datastores

.PHONY: clean ## Stops and removes all containers as well as resources [will ask for sudo]
clean : clean-dbms clean-vms
	@echo "Removing data and neo4j folder from HOME..."
	sudo rm -f -r ~/data ~/neo4j
	@echo "...done"
