package org.apache.camel.example.model;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by eslimat on 28.1.2016..
 */
@XmlRootElement(name = "test-case")
@XmlAccessorType(XmlAccessType.FIELD)
public class TestCase {

    @XmlElement(required = true, nillable = false)
    private String code;

    @XmlElement(required = false, nillable = true)
    private String description;

    @XmlElementWrapper(name = "list")
    private List<TestStep> testSteps = new LinkedList<TestStep>();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TestStep> getTestSteps() {
        return testSteps;
    }

    public void setTestSteps(List<TestStep> testSteps) {
        this.testSteps = testSteps;
    }
}
