# Getting Started
# Spring Boot, Embedded ActiveMQ & H2
### This project is developed using Spring boot. Used H2 embedded datatabse to save the message and embedded activemq

## Step 1 - Download Embedded ActiveMQ & H2 project

Clone the **ActiveMQ H2** project to your IDE  
Git Clone: [https://github.com/rajs24/sbt-activemq-h2.git](https://github.com/rajs24/sbt-activemq-h2.git)  

## Step 2 - Maven Clean and Install
`mvn clean install`


## Step 3 - Maven Run
`mvn spring-boot:run`


## Step 4 - Endpoint Details
GET  
get all messages from db
```
http://localhost:8088/msg/messages
```
GET  
send and receive message 
```
http://localhost:8088/api/msg/publish
```
Input Data:
```
  {
        "id": 6,
        "msgQueueName": "inmemory.queue",
        "message": "6 - Test Message",
        "timestamp": "2020-02-26"
}

```
H2 Console
```
http://localhost:8088/h2
```
