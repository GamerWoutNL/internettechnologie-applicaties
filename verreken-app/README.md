# Verreken app

The verreken app is an webapplication to equal out the expenses of your friend group!

## Run using Docker

These steps are for Windows users. If you're on Mac/Linux, these steps may be different.

1. Make sure you have Docker installed.

2. Make an executable jar file with Maven. Do:
```
cd verreken-app/backend
mvn clean package
```

3. Run Docker compose. Do:
```
cd verreken-app
docker-compose up -d
```

This will spin up all containers. The frontend will listen to port 5632. For the best experience, open up the frontend in Google Chrome and do:

1. Right mouse click and press `inspect element`.
2. Click on `Toggle device toolbar`.
3. Set the ratio to the ratio of an iPhone X.
