package org.apache.camel.example.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by eslimat on 28.1.2016..
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Request extends Message {


    public boolean isValid() {
        return false;
    }

    @Override
    public MessageType getType() {
        return MessageType.REQ;
    }
}
