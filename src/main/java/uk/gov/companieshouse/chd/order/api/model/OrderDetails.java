package uk.gov.companieshouse.chd.order.api.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="ORDERDETAIL")
public class OrderDetails {
    @Id
    @Basic
    @Column(name = "PSNUMBER")
    private String psNumber;
    @Column(name = "SEQNUMBER")
    private long sequenceNumber;
    @Basic
    @Column(name = "PRODCAT")
    private long productCategory;
    @Basic
    @Column(name = "PRODCODE")
    private String productCode;
    @Basic
    @Column(name = "PRODKEY")
    private String productKey;
    @Basic
    @Column(name = "PRODSUBKEY")
    private String productSubKey;
    @Basic
    @Column(name = "PRODDESC")
    private String productDescription;
    @Basic
    @Column(name = "PRODDATE")
    private LocalDate productDate;
    @Basic
    @Column(name = "CONUMB")
    private String companyNumber;
    @Basic
    @Column(name = "CONAME")
    private String companyName;
    @Basic
    @Column(name = "ITEMCOST")
    private long itemCost;
    @Basic
    @Column(name = "QUANTITY")
    private long quantity;
    @Basic
    @Column(name = "STATUS")
    private long status;
    @Basic
    @Column(name = "DELIVMETH")
    private long deliveryMethod;
    @Basic
    @Column(name = "DELIVLOCATION", columnDefinition = "NUMBER")
    private long deliveryLocation;
    @ManyToOne
    @JoinColumn(name = "PSNUMBER",
            referencedColumnName = "PSNUMBER",
            nullable = false,
            insertable = false,
            updatable = false)
    private OrderHeader orderHeader;

    public String getPsNumber() {
        return psNumber;
    }

    public void setPsNumber(String psNumber) { this.psNumber = psNumber; }

    public long getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(long sequenceNumber) { this.sequenceNumber = sequenceNumber; }

    public long getProductCategory() { return productCategory; }

    public void setProductCategory(long productCategory) { this.productCategory = productCategory; }

    public String getProductCode() { return productCode; }

    public void setProductCode(String productCode) { this.productCode = productCode; }

    public String getProductKey() { return productKey; }

    public void setProductKey(String productKey) { this.productKey = productKey; }

    public String getProductSubKey() { return productSubKey; }

    public void setProductSubKey(String productSubKey) { this.productSubKey = productSubKey; }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) { this.productDescription = productDescription; }

    public LocalDate getProductDate() {
        return productDate;
    }

    public void setProductDate(LocalDate productDate) { this.productDate = productDate; }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber) { this.companyNumber = companyNumber; }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public long getItemCost() {
        return itemCost;
    }

    public void setItemCost(long itemCost) { this.itemCost = itemCost; }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) { this.quantity = quantity; }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) { this.status = status; }

    public long getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(long deliveryMethod) { this.deliveryMethod = deliveryMethod; }

    public long getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(long deliveryLocation) { this.deliveryLocation = deliveryLocation; }

    public OrderHeader getOrderHeader() {
        return orderHeader;
    }

    public void setOrderHeader(OrderHeader header) {
        this.orderHeader = header;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetails that = (OrderDetails) o;
        return productCategory == that.productCategory &&
                itemCost == that.itemCost &&
                quantity == that.quantity &&
                status == that.status &&
                deliveryMethod == that.deliveryMethod &&
                psNumber.equals(that.psNumber) &&
                sequenceNumber == that.sequenceNumber &&
                productCode.equals(that.productCode) &&
                productKey.equals(that.productKey) &&
                productSubKey.equals(that.productSubKey) &&
                productDescription.equals(that.productDescription) &&
                productDate.equals(that.productDate) &&
                companyNumber.equals(that.companyNumber) &&
                companyName.equals(that.companyName) &&
                deliveryLocation == that.deliveryLocation &&
                orderHeader.equals(that.orderHeader);
    }

    @Override
    public int hashCode() {
        return Objects.hash(psNumber, sequenceNumber, productCategory, productCode, productKey, productSubKey, productDescription,
                productDate, companyNumber, companyName, itemCost, quantity, status, deliveryMethod, deliveryLocation);
    }
}