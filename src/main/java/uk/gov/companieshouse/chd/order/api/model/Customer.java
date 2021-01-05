package uk.gov.companieshouse.chd.order.api.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public class Customer {
    
    @Column(name="CUSTOMERID")
    private long customerId;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="customer_version_generator")
    @SequenceGenerator(name="customer_version_generator", sequenceName="CUSTOMER_VERSION_SEQ")
    @Column(name="CUSTOMERVERSION", updatable=false, nullable=false)
    private long customerVersion;
    
    @Column(name="FORENAME")
    private String forename;
    
    @Column(name="SURNAME")
    private String surname;
    
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
