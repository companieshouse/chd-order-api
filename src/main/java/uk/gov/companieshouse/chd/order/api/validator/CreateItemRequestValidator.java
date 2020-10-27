package uk.gov.companieshouse.chd.order.api.validator;

import org.springframework.stereotype.Component;
import uk.gov.companieshouse.chd.order.api.dto.MissingImageDeliveriesDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateItemRequestValidator {

    /**
     * Validates the item provided, returning any errors found.
     * @param item the item to be validated
     * @return the errors found, which will be empty if the item is found to be valid
     */
    public List<String> getValidationErrors(final MissingImageDeliveriesDTO item) {
        final List<String> errors = new ArrayList<>();
        if (item.getId() == null || item.getId().isEmpty()) {
            errors.add("id: must not be null or empty in a create item request");
        }
        if (item.getCompanyName() == null || item.getCompanyName().isEmpty()) {
            errors.add("company_name: must not be null or empty in create item request");
        }
        if (item.getCompanyNumber() == null || item.getCompanyNumber().isEmpty()) {
            errors.add("company_number: must not be null or empty in create item request");
        }
        if (item.getOrderedAt() == null) {
            errors.add("ordered_at: must not be null in create item request");
        }
        if (item.getPaymentReference() == null || item.getPaymentReference().isEmpty()) {
            errors.add("payment_reference: must not be null or empty in create item request");
        }
        if (item.getFilingHistoryCategory() == null || item.getFilingHistoryCategory().isEmpty()) {
            errors.add("filing_history_category: must not be null or empty in create item request");
        }
        if (item.getFilingHistoryDescription() == null || item.getFilingHistoryDescription().isEmpty()) {
            errors.add("filing_history_description: must not be null or empty in create item request");
        }
        if (item.getFilingHistoryDate() == null || item.getFilingHistoryDate().isEmpty()) {
            errors.add("filing_history_date: must not be null or empty in create item request");
        }
        if (item.getItemCost() == null || item.getItemCost().isEmpty()) {
            errors.add("item_cost: must not be null or empty in create item request");
        }
        return errors;
    }
}
