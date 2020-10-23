package uk.gov.companieshouse.chd.order.api.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An instance of this represents the JSON serializable Missing Image Delivery
 * for use in REST requests and responses.
 */
public class MissingImageDeliveriesDTO {

	@NotNull
	@JsonProperty("id")
	private String id;

	@NotNull
	@JsonProperty("company_name")
	private String companyName;

	@NotNull
	@JsonProperty("company_number")
	private String companyNumber;

	@NotNull
	@JsonProperty("ordered_at")
	private LocalDateTime orderedAt;

	@NotNull
	@JsonProperty("payment_reference")
	private String paymentReference;

	@NotNull
	@JsonProperty("filing_history_category")
	private String filingHistoryCategory;

	@NotNull
	@JsonProperty("filing_history_description")
	private String filingHistoryDescription;

	@NotNull
	@JsonProperty("filing_history_date")
	private String filingHistoryDate;

	@NotNull
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

	public String getItemCost() {
		return itemCost;
	}

	public void setItemCost(String itemCost) {
		this.itemCost = itemCost;
	}
}
