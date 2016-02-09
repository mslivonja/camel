package org.apache.camel.example;

import org.junit.Test;
import org.springframework.jms.support.destination.JndiDestinationResolver;
import org.springframework.jndi.JndiTemplate;

import javax.jms.Destination;
import javax.jms.JMSException;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by eslimat on 26.1.2016..
 */
public class TestJndiDestinationResolver {

    @Test
    public void testJndiResolver() throws JMSException {

        JndiTemplate jndiTemplate = new JndiTemplate();
        Properties props = new Properties();
        props.setProperty("java.naming.factory.initial", "weblogic.jndi.WLInitialContextFactory");
        props.setProperty("java.naming.provider.url", "t3://localhost:7001");
        props.setProperty("java.naming.security.principal", "system");
        props.setProperty("java.naming.security.credentials", "weblogic12");
        jndiTemplate.setEnvironment(props);

        JndiDestinationResolver jndiDestinationResolver = new JndiDestinationResolver();
        jndiDestinationResolver.setJndiTemplate(jndiTemplate);

        Destination destination = jndiDestinationResolver.resolveDestinationName(null, "jms.test.request.queue", false);

        assertNotNull(destination);
        //assertEquals("myJmsModule!Test - Request Queue", ((DestinationImpl)destination).getName());
    }
}
