package uk.gov.companieshouse.chd.order.api.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ORDERDETAIL")
public class OrderDetails {
    @Id
    @Basic
    @Column(name = "PSNUMBER")
    private String psNumber;
    @Basic
    @Column(name = "PRODCAT")
    private String productCategory;
    @Basic
    @Column(name = "PRODDESC")
    private String productDescription;
    @Basic
    @Column(name = "PRODDATE")
    private String productDate;
    @Basic
    @Column(name = "CONUMB")
    private String companyNumber;
    @Basic
    @Column(name = "CONAME")
    private String companyName;
    @Basic
    @Column(name = "ITEMCOST")
    private String itemCost;
    @Basic
    @Column(name = "QUANTITY")
    private String quantity;
    @Basic
    @Column(name = "STATUS")
    private String status;
    @Basic
    @Column(name = "DELIVMETH")
    private String deliveryMethod;
    @Basic
    @Column(name = "DELIVLOCATION")
    private String deliveryLocation;

    public String getPsNumber() {
        return psNumber;
    }

    public void setPsNumber(String psNumber) {
        this.psNumber = psNumber;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getItemCost() {
        return itemCost;
    }

    public void setItemCost(String itemCost) {
        this.itemCost = itemCost;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }
}
