package org.apache.camel.example.util;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.example.model.Response;
import org.apache.camel.example.model.TestStep;

import java.io.File;

/**
 * Created by eslimat on 1.2.2016..
 */
public class ResponseValidation implements Processor {

    public void process(Exchange exchange) throws Exception {

        TestStep ts = (TestStep) exchange.getIn().getHeader("TestStep");

        if(ts!=null){
            Response response = ts.getResponse();

            File f = new File(response.getPath());
            if(f.exists() && f.canRead())
                return;
        }

        throw new Exception("Inference exception, unable to resolve next step or response message");
    }
}
