package uk.gov.companieshouse.chd.order.api.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERHEADER")
public class OrderHeader {
    @Id
    @Basic
    @Column(name = "PSNUMBER")
    private String psNumber;
    @Basic
    @Column(name = "CUSTVERSION")
    private String custVersion;
    @Basic
    @Column(name = "PAYMENTMETHOD")
    private String paymentMethod;
    @Basic
    @Column(name = "NUMORDERLINES")
    private String numOrderLine;
    @Basic
    @Column(name = "REFERENCE")
    private String reference;
    @Basic
    @Column(name = "ORDDTETME")
    private String orderDateTime;
    @Basic
    @Column(name = "HANDCSR")
    private String handCsr;
    @Basic
    @Column(name = "STATUS")
    private String status;
    @Basic
    @Column(name = "FLAGS")
    private String flags;
    @Basic
    @Column(name = "LANGUAGE")
    private String language;

    public String getPsNumber() {
        return psNumber;
    }

    public void setPsNumber(String psNumber) {
        this.psNumber = psNumber;
    }

    public String getCustVersion() {
        return custVersion;
    }

    public void setCustVersion(String custVersion) {
        this.custVersion = custVersion;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getNumOrderLine() {
        return numOrderLine;
    }

    public void setNumOrderLine(String numOrderLine) {
        this.numOrderLine = numOrderLine;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(String orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public String getHandCsr() {
        return handCsr;
    }

    public void setHandCsr(String handCsr) {
        this.handCsr = handCsr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderHeader that = (OrderHeader) o;
        if (psNumber != that.psNumber
                || custVersion != that.custVersion
                || paymentMethod != that.paymentMethod
                || numOrderLine != )
    }
}
