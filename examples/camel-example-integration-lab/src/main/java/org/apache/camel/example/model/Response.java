package org.apache.camel.example.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by eslimat on 28.1.2016..
 */
@XmlType(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class Response extends Message{

    private int delay;

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    @Override
    public MessageType getType() {
        return MessageType.RSP;
    }
}
