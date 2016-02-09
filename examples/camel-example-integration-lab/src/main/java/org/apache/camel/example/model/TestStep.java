package org.apache.camel.example.model;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by eslimat on 28.1.2016..
 */
@XmlType(name = "test-step", propOrder = {
        "id", "description", "request", "response", "dependencies"
})
@XmlAccessorType(XmlAccessType.FIELD)
public class TestStep {

    @XmlElement(required = true)
    private int id;

    @XmlElement(required = false, nillable = true)
    private String description;

    @XmlAttribute(required = false)
    private boolean start;

    @XmlAttribute(required = false)
    private boolean end;

    @XmlElement(nillable = true)
    private Request request;

    @XmlElement(nillable = true)
    private Response response;

    @XmlElementWrapper(name = "dependencies")
    private List<TestStep> dependencies = new LinkedList<TestStep>();

    public TestStep(){}

    public TestStep(int id, String description, boolean isStart, boolean isEnd){
        this.id = id;
        this.description = description;
        this.start = isStart;
        this.end = isEnd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public List<TestStep> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<TestStep> dependencies) {
        this.dependencies = dependencies;
    }
}
