package uk.gov.companieshouse.chd.order.api.validator;

import uk.gov.companieshouse.chd.order.api.dto.MissingImageDeliveriesDTO;

import java.util.ArrayList;
import java.util.List;


public class CreateItemRequestValidator {

    /**
     * Validates the item provided, returning any errors found.
     * @param item the item to be validated
     * @return the errors found, which will be empty if the item is found to be valid
     */
    public List<String> getValidationErrors(final MissingImageDeliveriesDTO item) {
        final List<String> errors = new ArrayList<>();
        if (item.getId() == null) {
            errors.add("id: must not be null in a create item request");
        }
        if (item.getCompanyName() == null) {
            errors.add("company_name: must not be null in create item request");
        }
        if (item.getCompanyNumber() == null) {
            errors.add("company_number: must not be null in create item request");
        }
        if (item.getOrderedAt() == null) {
            errors.add("ordered_at: must not be null in create item request");
        }
        if (item.getPaymentReference() == null) {
            errors.add("payment_reference: must not be null in create item request");
        }
        if (item.getFilingHistoryCategory() == null) {
            errors.add("filing_history_category: must not be null in create item request");
        }
        if (item.getFilingHistoryDescription() == null) {
            errors.add("filing_history_description: must not be null in create item request");
        }
        if (item.getFilingHistoryDate() == null) {
            errors.add("filing_history_date: must not be null in create item request");
        }
        if (item.getItemCost() == null) {
            errors.add("item_cost: must not be null in create item request");
        }
        return errors;
    }
}
