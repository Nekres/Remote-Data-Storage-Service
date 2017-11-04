#!/bin/bash

#maven build
mvn compile
mvn package
#build the app container
docker build -t nekres/cloud-service .

#create the network
docker network create cs

#run the containers
#mysql
docker run -d --rm --net cs -p 3307:3306 -e MYSQL_ROOT_PASSWORD=finished -e MYSQL_DATABASE=RemoteDataStoreService --name csm mysql
echo Waiting for mysql to init
sleep 60
#tomcat
docker run --rm --net cs -p 8080:8080 --name cloud-service-tomcat nekres/cloud-service

