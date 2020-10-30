package uk.gov.companieshouse.chd.order.api.model;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ORDERHEADER")
public class OrderHeader {
    @Id
    @Basic
    @Column(name = "PSNUMBER")
    private String psNumber;
    @Basic
    @Column(name = "CUSTID")
    private long customerId;
    @Basic
    @Column(name = "CUSTVERSION")
    private long customerVersion;
    @Basic
    @Column(name = "ORDERVALUE")
    private long orderValue;
    @Basic
    @Column(name = "PAYMENTMETHOD")
    private long paymentMethod;
    @Basic
    @Column(name = "NUMORDERLINES")
    private long numOrderLines;
    @Basic
    @Column(name = "REFERENCE")
    private String paymentReference;
    @Basic
    @Column(name = "ORDDTETME")
    private LocalDateTime orderDateTime;
    @Basic
    @Column(name = "HANDCSR")
    private String handCsr;
    @Basic
    @Column(name = "STATUS")
    private long status;
    @Basic
    @Column(name = "FLAGS")
    private long flags;
    @Basic
    @Column(name = "LANGUAGE")
    private String language;
    @OneToMany(mappedBy = "orderHeader", cascade = CascadeType.ALL)
    private Set<OrderDetails> orderDetails;

    public String getPsNumber() {
        return psNumber;
    }

    public void setPsNumber(String psNumber) { this.psNumber = psNumber; }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) { this.customerId = customerId; }

    public long getCustomerVersion() {
        return customerVersion;
    }

    public void setCustomerVersion(long customerVersion) { this.customerVersion = customerVersion; }

    public long getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(long orderValue) { this.orderValue = orderValue; }

    public long getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(long paymentMethod) { this.paymentMethod = paymentMethod; }

    public long getNumOrderLines() {
        return numOrderLines;
    }

    public void setNumOrderLines(long numOrderLines) { this.numOrderLines = numOrderLines; }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) { this.paymentReference = paymentReference; }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) { this.orderDateTime = orderDateTime; }

    public String getHandCsr() {
        return handCsr;
    }

    public void setHandCsr(String handCsr) { this.handCsr = handCsr; }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) { this.status = status; }

    public long getFlags() {
        return flags;
    }

    public void setFlags(long flags) { this.flags = flags; }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) { this.language = language; }

    public Set<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetails> details) {
        this.orderDetails = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderHeader that = (OrderHeader) o;
        return customerId == that.customerId &&
                customerVersion == that.customerVersion &&
                orderValue == that.orderValue &&
                paymentMethod == that.paymentMethod &&
                status == that.status &&
                flags == that.flags &&
                psNumber.equals(that.psNumber) &&
                numOrderLines == that.numOrderLines &&
                paymentReference.equals(that.paymentReference) &&
                orderDateTime.equals(that.orderDateTime) &&
                handCsr.equals(that.handCsr) &&
                language.equals(that.language) &&
                orderDetails.equals(that.orderDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(psNumber, customerId, customerVersion, orderValue, paymentMethod, numOrderLines, paymentReference,
                orderDateTime, handCsr, status, flags, language, orderDetails);
    }
}
