package uk.gov.companieshouse.chd.order.api.validator;

import org.springframework.stereotype.Component;
import uk.gov.companieshouse.chd.order.api.dto.MissingImageDeliveriesDTO;

import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Component
public class CreateItemRequestValidator {

    private static final String ID_ERROR = "id: must not be null or empty in a create item request";
    private static final String COMPANY_NUMBER_ERROR = "company_number: must not be null or empty in create item request";
    private static final String COMPANY_NAME_ERROR = "company_name: must not be null or empty in create item request";
    private static final String ORDERED_AT_ERROR = "ordered_at: must not be null in create item request";
    private static final String PAYMENT_REFERENCE_ERROR = "payment_reference: must not be null or empty in create item request";
    private static final String FILING_HISTORY_CATEGORY_ERROR = "filing_history_category: must not be null or empty in create item request";
    private static final String FILING_HISTORY_DESCRIPTION_ERROR = "filing_history_description: must not be null or empty in create item request";
    private static final String FILING_HISTORY_DATE_ERROR = "filing_history_date: must not be null or empty in create item request";
    private static final String FILING_HISTORY_TYPE_ERROR = "filing_history_type: must not be null or empty in create item request";
    private static final String ITEM_COST_ERROR = "item_cost: must not be null or empty in create item request";

    /**
     * Validates the item provided, returning any errors found.
     * @param item the item to be validated
     * @return the errors found, which will be empty if the item is found to be valid
     */
    public List<String> getValidationErrors(final MissingImageDeliveriesDTO item) {
        final List<String> errors = new ArrayList<>();
        checkString(item.getId(), errors, ID_ERROR);
        checkString(item.getCompanyNumber(), errors, COMPANY_NUMBER_ERROR);
        checkString(item.getCompanyName(), errors, COMPANY_NAME_ERROR);
        checkString(item.getPaymentReference(), errors, PAYMENT_REFERENCE_ERROR);
        checkString(item.getFilingHistoryCategory(), errors, FILING_HISTORY_CATEGORY_ERROR);
        checkString(item.getFilingHistoryDescription(), errors, FILING_HISTORY_DESCRIPTION_ERROR);
        checkString(item.getFilingHistoryDate(), errors, FILING_HISTORY_DATE_ERROR);
        checkString(item.getFilingHistoryType(), errors, FILING_HISTORY_TYPE_ERROR);
        checkString(item.getItemCost(), errors, ITEM_COST_ERROR);
        if (item.getOrderedAt() == null) {
            errors.add(ORDERED_AT_ERROR);
        }
        return errors;
    }

    private void checkString(String string, List<String> errors, String errorMessage) {
        if(isBlank(string)){
            errors.add(errorMessage);
        }
    }
}
