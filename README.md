## Requirements
This project was built with the following tools:
    * Gradle 7.4.2
    * Java openjdk 15.0.1
    * npm 8.3.1
    * Docker for Windows 20.10.16
    * node 16.4.0

## Build Steps
The entire app, containers and all, can be built with the script below:
```bash
./buildContainers.sh
```

To build the backend individually:
```bash
cd server
gradle build
```

To build the frontend individually:
```bash
cd client
npm install
npm build
```

## Running the App
The app is run via docker compose.
```bash
cd docker
docker-compose up -d
```
Webpage is accessible via `localhost:3000`