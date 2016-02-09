package se.ericsson.ecmgui.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by eslimat on 5.2.2016..
 */
public class CreateVCpeResponse {

    private Status status;

    private Data data;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
