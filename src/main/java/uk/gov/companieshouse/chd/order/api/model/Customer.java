package uk.gov.companieshouse.chd.order.api.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER")
public class Customer {
    
    @Basic
    @Column(name="CUSTOMERID")
    private long customerId;
    
    @Id
    @Basic
    @GeneratedValue(generator="customer_version_generator")
    @SequenceGenerator(name="customer_version_generator", sequenceName="customer_version_seq", allocationSize=1)
    @Column(name="CUSTOMERVERSION")
    private long customerVersion;
    
    @Basic
    @Column(name="FORENAME")
    private String forename;
    
    @Basic
    @Column(name="SURNAME")
    private String surname;
    
    @Basic
    @Column(name="EMAIL")
    private String email;

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getCustomerVersion() {
        return customerVersion;
    }

    public void setCustomerVersion(long customerVersion) {
        this.customerVersion = customerVersion;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
