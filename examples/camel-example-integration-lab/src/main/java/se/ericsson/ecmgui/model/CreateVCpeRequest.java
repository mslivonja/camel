package se.ericsson.ecmgui.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by eslimat on 1.2.2016..
 */
@ApiModel(description = "vApp API vCPE create request ")
public class CreateVCpeRequest {

    private String tenantName;

    private List<OrderItem> orderItems;

    @ApiModelProperty(value = "Tenant name", required = true)
    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    @ApiModelProperty(value = "Order items", required = false)
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
