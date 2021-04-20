#!/bin/sh
mvn -B package

docker build -t openbanking .

docker-compose up -d