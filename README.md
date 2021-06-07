# AutomaticBet

This application is used to automate the creation of coupons on the bookmaker's website.
At the beginning it connects to PredictionRestApi from where it gets the list of events,
then it sprinkles the page for these events and retrieves the tag needed to create http queries and sends a request to the bookmaker's page.
Created with Spring, RestAPI, jsoup, database PostgreSQL, hibernate, mockito, junit, lombok in Java15.
I made this project to learn and practice technologies mentioned above.

## Requirements
- jdk 15
- maven
- postgresql

### How to build
```bash
mvn clean install
```
### How to run
```bash
mvn spring-boot:run
```

### Configuration example
```bash
spring: 
  datasource: 
    username: "USERNAME" 
    password: "PASSWORD"  
    url: "JDBC_URL" 
    driverclass: "DRIVER"
```
