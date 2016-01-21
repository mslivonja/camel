# JMS & File Example

### Introduction
This example shows how to work with files and JMS. 

The example consumes messages from a queue and writes them to the file
system.

### Build
  
You will need to compile this example first:

	mvn compile

### Run  
  
The example should run if you type

	mvn exec:java -PActiveMQ - Example using ActiveMQ JMS message broker
	mvn exec:java -PWeblogic - Example using Weblogic JMS message broker

	java -cp camel-example-jms-file-2.17-SNAPSHOT.jar;"lib/*" org.apache.camel.example.jmstofile.WeblogicJmsToFileExample

After the example is complete, then there should be 10 files written
in the test directory.

### Documentation

This example is documented at [http://camel.apache.org/walk-through-an-example.html](http://camel.apache.org/walk-through-an-example.html)

### Forum, Help, etc 

If you hit an problems please let us know on the Camel Forums [http://camel.apache.org/discussion-forums.html](http://camel.apache.org/discussion-forums.html)

Please help us make Apache Camel better - we appreciate any feedback you may
have.  Enjoy!

### Troubleshooting

1)  If javax.management.MBeanTrustPermission register exception arises
    Change <JRE_HOME>/lib/security and add following line

        permission javax.management.MBeanTrustPermission "register";


The Camel riders!
