package org.apache.camel.example.util;

import org.apache.camel.Converter;
import org.apache.camel.TypeConverters;
import org.apache.camel.example.model.Request;
import org.apache.camel.example.model.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by eslimat on 31.1.2016..
 */
@Converter
public class MessageTypeConverter implements TypeConverters{

    @Converter
    public InputStream convertRequestMessage(Request request) throws IOException {
        return new FileInputStream(request.getPath());
    }

    @Converter
    public InputStream convertResponseMessage(Response response) throws IOException {
        return new FileInputStream(response.getPath());
    }
}
