package org.apache.camel.example.util;

import org.apache.camel.Exchange;

/**
 * Created by eslimat on 26.1.2016..
 */
public interface FileConsumer {

    /**
     * Resolve file and read it's contents.
     *
     * @param exchange Apache Camel exchange, current context
     * @return File contents or <code>null</code> if file is not resolved
     */
    public String consumeFile(Exchange exchange);
}
