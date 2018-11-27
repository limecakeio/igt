# Integration Technologies (IGT) WS2018
This repository includes the course work for IGT (Integration Technologies) at the University of Applied Sciences Mannheim, WS2018

## Prerequisites
In order to successfully launch this application, the following programs need to be running as well as configured properly:

* [Docker](https://www.docker.com/)
Docker needs to be configured in order to **[not require sudo](https://docs.docker.com/install/linux/linux-postinstall/)** to run

* [Maven](https://maven.apache.org/install.html) 

## Installation
If the prerequisites are fulfilled, follow these steps to get everything going

````
git clone https://github.com/limecakeio/igt igt
cd igt
make
````
This will obtain and launch the images for 6 containers:

1. MySQL
1. Redis
1. Mongo DB
1. neo4j
1. couchDB
1. Ubuntu Virtual Machine

## Launch
The following explains how to launch each obtained image individually:

* MySQL
  ```
  make mysql # Launches MySQL container on port 3306
  ```
* Redis
  ```
  make redis # Launches Redis KV-Store container on port 6379
  ```
* MongoDB
  ```
  make mongo # Launches MongoDB Document-Store container on port 27017
  ```
* neo4j
  ```
  make neo # Launches neo4j Graph-based Store container on port 7474
  NOTE: neo4j is also accessible via a web-interface on localhost:7474
  ```
* cassandra
  ```
  make cassandra # Launches Cassandra Column-based Store container on port 9042
  ```
* cassandra
  ```
  make vm # Launches the ubuntu VM containing running instances all above mentioned DBMS containers on port 4000
  ```
 

## About Us
The team for this semester's IGT lecture is comprised of:

+ Anusan Ranjan
+ Eugen Krizki
+ Richard Vladimirskij 
