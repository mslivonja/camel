package se.ericsson.ecmgui.model;

/**
 * Created by eslimat on 5.2.2016..
 */
public class Order {

    private int id;

    private String tenantName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }
}
