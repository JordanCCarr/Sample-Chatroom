#!/bin/bash

cd server;
gradle build;
docker build -t app/app:1.0 .;
cd ..;
cd client;
docker build -t client/client:1.0 .;
cd ..;
exit 0;