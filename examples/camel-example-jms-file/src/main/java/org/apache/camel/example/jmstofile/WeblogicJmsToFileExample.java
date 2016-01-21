package org.apache.camel.example.jmstofile;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.jms.JmsConfiguration;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.log4j.Logger;
import org.springframework.jms.support.destination.JndiDestinationResolver;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.jndi.JndiTemplate;

import javax.jms.ConnectionFactory;
import java.util.Properties;

/**
 * Created by eslimat on 20.1.2016..
 */
public class WeblogicJmsToFileExample {

    private static Logger log = Logger.getLogger(WeblogicJmsToFileExample.class);

    private static final String hostname = "localhost";

    private static final int port = 7001;

    private static final String connFactNm = "test.ConnectionFactory";

    private static final String queueNm = "test.queue";

    private static int timeout = 60000; // default timeout

    private WeblogicJmsToFileExample() {
    }

    public static void main(String args[]) throws Exception {

        log.debug("Starting Example '" + WeblogicJmsToFileExample.class.getName() + "'");

        // START SNIPPET: e1
        CamelContext context = new DefaultCamelContext();
        // END SNIPPET: e1
        // Set up the Weblogic JMS Components
        // START SNIPPET: e2

        // Setup JNDI Template
        JndiTemplate jndiTemplate = new JndiTemplate();
        Properties props = new Properties();
        props.setProperty("java.naming.factory.initial", "weblogic.jndi.WLInitialContextFactory");
        props.setProperty("java.naming.provider.url", "t3://" + hostname + ":" + port);
        props.setProperty("java.naming.security.principal", "system");
        props.setProperty("java.naming.security.credentials", "weblogic12");
        jndiTemplate.setEnvironment(props);

        JndiDestinationResolver jndiDestinationResolver = new JndiDestinationResolver();
        jndiDestinationResolver.setJndiTemplate(jndiTemplate);

        JmsConfiguration jmsConfiguration = new JmsConfiguration();
        jmsConfiguration.setConnectionFactory((ConnectionFactory) jndiTemplate.lookup(connFactNm));
        jmsConfiguration.setDestinationResolver(jndiDestinationResolver);

        JmsComponent jmsComponent = new JmsComponent();
        jmsComponent.setConfiguration(jmsConfiguration);

        // Note we can explicit name the component
        context.addComponent("wmq", jmsComponent);
        // END SNIPPET: e2
        // Add some configuration by hand ...
        // START SNIPPET: e3
        context.addRoutes(new RouteBuilder() {
            public void configure() {
                from("wmq:queue:" + queueNm).to("file://test?autoCreate=true&fileName=test-${random(10000)}.txt");
            }
        });

        // END SNIPPET: e3
        // Camel template - a handy class for kicking off exchanges
        // START SNIPPET: e4
        ProducerTemplate template = context.createProducerTemplate();
        // END SNIPPET: e4
        // Now everything is set up - lets start the context
        context.start();
        // Now send some test text to a component - for this case a JMS Queue
        // The text get converted to JMS messages - and sent to the Queue
        // test.queue
        // The file component is listening for messages from the Queue
        // test.queue, consumes
        // them and stores them to disk. The content of each file will be the
        // test we sent here.
        // The listener on the file component gets notified when new files are
        // found ... that's it!
        // START SNIPPET: e5
        for (int i = 0; i < 10; i++) {
            template.sendBody("wmq:queue:" + queueNm, "Test Message: " + i);
        }
        // END SNIPPET: e5

        // wait a bit and then stop
        Thread.sleep(timeout);
        context.stop();
    }
}
