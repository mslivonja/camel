# Spring & JMS & Weblogic Example

### Introduction
This example shows how to work with the Camel-JMS Component using Oracle Weblogic JMS server.

The example consumes messages from a queue and invokes the bean with the received message.
Message is output to log.

There are three routes:
    1) Log received message to console
    2) Forward received message to response queue
    3) Receive message on request queue and answer with message read from file on response queue

### Prerequisites
Oracle Weblogic thin client jar is needed for JMS communication with Oracle Weblogic JMS queue.
Weblogic client can be generated:

        cd WL_HOME/server/lib
        java -jar wljarbuilder.jar

        After build current directory should contain wlfullclient.jar


In order to install 3rd party JARs use following commands:

    - Oracle Weblogic full client JAR
        mvn install:install-file    -Dfile=<path-to-file> -DgroupId=oracle  \
                                    -DartifactId=weblogic                   \
                                    -Dversion=12.1.2.0                      \
                                    -Dpackaging=jar                         \

The Server is required to be running when you try the clients.

And for the Client we have HermesJMS:
- Normal use of Hermes client for sending JMS messages to queue
- Normal use the ProducerTemplate ala Spring Template style

### Build
You will need to compile this example first:

	mvn compile

### Run
The example should run if you type:
	
#### Step 1: Run Server	
	mvn exec:java -PCamelServer
	
#### Step 2: Run Clients
	mvn exec:java -PCamelClient	

To stop the example hit `ctrl + c`

### Documentation

### Forum, Help, etc

### Troubleshooting

While building Oracle weblogic full client JAR following exception is received do following:
    - cp wlclient.jar  weblogic-classes.jar
    - rerun jar builder
    - rm weblogic-classes.jar





The Camel riders!

