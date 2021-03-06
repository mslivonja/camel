# Spring Java Config Example

### Introduction
This example shows how to work with files and JMS, using Spring Java Config 
to boot up Camel and configure the routes. 

The example consumes messages from a queue and writes them to the file
system.

### Build
You will need to compile this example first:

	mvn compile


### Run
To run the example type

	mvn camel:run

To stop the example hit `ctrl + c`


### Configuration
You can see the routing rules by looking at the java code in the
`src/main/java directory` and the Spring XML configuration lives in
`src/main/resources/META-INF/spring`

### Documentation
This example is documented at <http://camel.apache.org/spring-java-config-example.html>

### Forum, Help, etc 

If you hit an problems please let us know on the Camel Forums <http://camel.apache.org/discussion-forums.html>

Please help us make Apache Camel better - we appreciate any feedback you may
have.  Enjoy!



The Camel riders!
