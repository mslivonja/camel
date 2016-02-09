package org.apache.camel.example.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by eslimat on 28.1.2016..
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Message {

    @XmlElement(required = true, nillable = false)
    private String path;

    @XmlElement(required = true, nillable = false)
    private MessageType type;

    @XmlElement(required = true, nillable = false)
    private DirectionType direction;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public DirectionType getDirection() {
        return direction;
    }

    public void setDirection(DirectionType direction) {
        this.direction = direction;
    }
}
