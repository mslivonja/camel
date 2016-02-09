package org.apache.camel.example.util;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by eslimat on 26.1.2016..
 */
public class TestFileConsumer extends TestBase{

    private DefaultFileConsumer consumer = new DefaultFileConsumer();

    @Override
    public void setup() throws IOException {
        super.setup();
        // setup root directoy
        consumer.setRootDir(this.getTmpDir());
    }

    @Test
    public void testConsumeOneFile() throws Exception {
        String expected = "Test content, 001";
        createNewFile("Test.txt", expected);

        Exchange mockedExchange = mock(Exchange.class);
        Message mockedMessage = mock(Message.class);

        when(mockedExchange.getIn()).thenReturn(mockedMessage);

        String actual = consumer.consumeFile(mockedExchange);

        assertEquals(expected, actual);
    }

    @Test
    public void testConsumeSpecifiFile() throws Exception {

        createNewFile("Test_001.txt", "File should not be read");

        String expected = "Test content, 001";
        createNewFile("Test_002.txt", expected);

        Exchange mockedExchange = mock(Exchange.class);
        Message  mockedMessage = mock(Message.class);

        when(mockedExchange.getIn()).thenReturn(mockedMessage);
        when(mockedMessage.getHeader(Exchange.FILE_NAME)).thenReturn("Test_002.txt");

        String actual = consumer.consumeFile(mockedExchange);

        assertEquals(expected, actual);
    }

    @Test
    public void testEmptyDirectory() throws Exception {

        Exchange mockedExchange = mock(Exchange.class);
        Message  mockedMessage = mock(Message.class);

        when(mockedExchange.getIn()).thenReturn(mockedMessage);

        String actual = consumer.consumeFile(mockedExchange);

        assertNull(actual);
    }

    @Test
    public void testConsumeMissingFile() throws IOException {

        String expected = "Test content, 001";
        createNewFile("Test.txt", expected);

        Exchange mockedExchange = mock(Exchange.class);
        Message mockedMessage = mock(Message.class);

        when(mockedExchange.getIn()).thenReturn(mockedMessage);

        consumer.setSingleFileAsDefault(false);
        String actual = consumer.consumeFile(mockedExchange);

        assertNull(actual);
    }
}
