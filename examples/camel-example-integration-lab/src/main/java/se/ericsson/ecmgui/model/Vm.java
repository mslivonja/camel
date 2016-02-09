package se.ericsson.ecmgui.model;

/**
 * Created by eslimat on 1.2.2016..
 */
public class Vm {

    private String id;

    private Vdc vdc;

    private String vmhdName;

    private BootSource bootsource;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Vdc getVdc() {
        return vdc;
    }

    public void setVdc(Vdc vdc) {
        this.vdc = vdc;
    }

    public String getVmhdName() {
        return vmhdName;
    }

    public void setVmhdName(String vmhdName) {
        this.vmhdName = vmhdName;
    }

    public BootSource getBootsource() {
        return bootsource;
    }

    public void setBootsource(BootSource bootsource) {
        this.bootsource = bootsource;
    }
}
