package se.ericsson.ecmgui.model;

import java.util.List;

/**
 * Created by eslimat on 1.2.2016..
 */
public class OrderItem {

    private String orderItemId;

    private CreateVApp createVapp;

    private List<Vm> vm;

    private List<VmVnics> vmVnics;

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public CreateVApp getCreateVapp() {
        return createVapp;
    }

    public void setCreateVapp(CreateVApp createVApp) {
        this.createVapp = createVApp;
    }

    public List<Vm> getVm() {
        return vm;
    }

    public void setVm(List<Vm> vm) {
        this.vm = vm;
    }

    public List<VmVnics> getVmVnics() {
        return vmVnics;
    }

    public void setVmVnics(List<VmVnics> vmVnics) {
        this.vmVnics = vmVnics;
    }
}
