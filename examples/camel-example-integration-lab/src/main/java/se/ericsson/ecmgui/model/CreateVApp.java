package se.ericsson.ecmgui.model;

/**
 * Created by eslimat on 1.2.2016..
 */
public class CreateVApp {

    private String name;

    private String id;

    private String type;

    private String description;

    private Vdc vdc;

    private ProductInfo productInfo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Vdc getVdc() {
        return vdc;
    }

    public void setVdc(Vdc vdc) {
        this.vdc = vdc;
    }

    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }
}
