# Ticket booking app
## Assumptions
* User must pay within next 15 minutes since booking.
## Build
Firstly, you need to have maven installed.
```shell
sudo apt-get install maven
```

Nextly, build the project. Target will be placed at target directory.
```shell
mvn package
```

## Run demo
Firstly, you need to run backend app:
```shell
java -jar ./target/ticket_booking_app-0.0.1-SNAPSHOT.jar
```
After boot, try to run demo script:
```shell
sh demo.sh
```

## Tests
Integration tests are in tests.sh. To run them, run:
```shell
sh tests.sh
```
Remember that you have to restart the backend app until running tests if you runned demo before.