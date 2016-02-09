package se.ericsson.ecmgui.model;

/**
 * Created by eslimat on 8.2.2016..
 */
public class VmVnics {

    private String name;

    private Vn vn;

    private boolean assignExternalIpAddress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vn getVn() {
        return vn;
    }

    public void setVn(Vn vn) {
        this.vn = vn;
    }

    public boolean isAssignExternalIpAddress() {
        return assignExternalIpAddress;
    }

    public void setAssignExternalIpAddress(boolean assignExternalIpAddress) {
        this.assignExternalIpAddress = assignExternalIpAddress;
    }
}
