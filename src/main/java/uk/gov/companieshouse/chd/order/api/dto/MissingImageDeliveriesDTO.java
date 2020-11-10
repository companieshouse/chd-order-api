package uk.gov.companieshouse.chd.order.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

/**
 * An instance of this represents the JSON serializable Missing Image Delivery
 * for use in REST requests and responses.
 */
public class MissingImageDeliveriesDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("company_name")
    private String companyName;

    @JsonProperty("company_number")
    private String companyNumber;

    @JsonProperty("ordered_at")
    private LocalDateTime orderedAt;

    @JsonProperty("payment_reference")
    private String paymentReference;

    @JsonProperty("filing_history_type")
    private String filingHistoryType;

    @JsonProperty("filing_history_category")
    private String filingHistoryCategory;

    @JsonProperty("filing_history_description")
    private String filingHistoryDescription;

    @JsonProperty("filing_history_date")
    private String filingHistoryDate;

    @JsonProperty("filing_history_barcode")
    private String filingHistoryBarcode;

    @JsonProperty("entity_id")
    private String entityID;

    @JsonProperty("item_cost")
    private String itemCost;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    public LocalDateTime getOrderedAt() {
        return orderedAt;
    }

    public void setOrderedAt(LocalDateTime orderedAt) {
        this.orderedAt = orderedAt;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public String getFilingHistoryType() {
        return filingHistoryType;
    }

    public void setFilingHistoryType(String filingHistoryType) {
        this.filingHistoryType = filingHistoryType;
    }

    public String getFilingHistoryCategory() {
        return filingHistoryCategory;
    }

    public void setFilingHistoryCategory(String filingHistoryCategory) {
        this.filingHistoryCategory = filingHistoryCategory;
    }

    public String getFilingHistoryDescription() {
        return filingHistoryDescription;
    }

    public void setFilingHistoryDescription(String filingHistoryDescription) {
        this.filingHistoryDescription = filingHistoryDescription;
    }

    public String getFilingHistoryDate() {
        return filingHistoryDate;
    }

    public void setFilingHistoryDate(String filingHistoryDate) {
        this.filingHistoryDate = filingHistoryDate;
    }

    public String getFilingHistoryBarcode() {
        return filingHistoryBarcode;
    }

    public void setFilingHistoryBarcode(String filingHistoryBarcode) {
        this.filingHistoryBarcode = filingHistoryBarcode;
    }

    public String getEntityID() {
        return entityID;
    }

    public void setEntityID(String entityID) {
        this.entityID = entityID;
    }

    public String getItemCost() {
        return itemCost;
    }

    public void setItemCost(String itemCost) {
        this.itemCost = itemCost;
    }
}
