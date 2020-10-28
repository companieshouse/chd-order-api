package uk.gov.companieshouse.chd.order.api.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.gov.companieshouse.chd.order.api.dto.MissingImageDeliveriesDTO;
import uk.gov.companieshouse.chd.order.api.testUtils.ItemSetup;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertTrue;

class CreateItemRequestValidatorTest {

    private CreateItemRequestValidator validatorUnderTest;
    private MissingImageDeliveriesDTO missingImageDeliveriesDTO;

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

    @BeforeEach
    void setUp() {
        validatorUnderTest = new CreateItemRequestValidator();
    }

    @Test
    @DisplayName("all fields are not null or empty")
    void allFieldsAreNotNullOrEmpty() {
        // Given
        missingImageDeliveriesDTO = ItemSetup.setUpMissingImageDeliveryDTO();
        // When
        final List<String> errors = validatorUnderTest.getValidationErrors(missingImageDeliveriesDTO);

        // Then
        assertTrue(errors.isEmpty());
    }

    @Test
    @DisplayName("ID is null all other fields are not null")
    void idIsMandatory() {
        // Given
        missingImageDeliveriesDTO = ItemSetup.setUpMissingImageDeliveryDTO();
        missingImageDeliveriesDTO.setId(null);
        // When
        final List<String> errors = validatorUnderTest.getValidationErrors(missingImageDeliveriesDTO);

        // Then
        assertThat(errors, contains(ID_ERROR));
    }

    @Test
    @DisplayName("payment reference is null all other fields are not null")
    void paymentReferenceIsMandatory() {
        // Given
        missingImageDeliveriesDTO = ItemSetup.setUpMissingImageDeliveryDTO();
        missingImageDeliveriesDTO.setPaymentReference(null);
        // When
        final List<String> errors = validatorUnderTest.getValidationErrors(missingImageDeliveriesDTO);

        // Then
        assertThat(errors, contains(PAYMENT_REFERENCE_ERROR));
    }

    @Test
    @DisplayName("All details are null and mandatory for creation of MID item")
    void collectionDetailsAreMandatoryForCollectionDeliveryMethod() {
        // Given
        missingImageDeliveriesDTO = ItemSetup.setUpMissingImageDeliveryDTO();
        missingImageDeliveriesDTO.setId(null);
        missingImageDeliveriesDTO.setCompanyName(null);
        missingImageDeliveriesDTO.setCompanyNumber(null);
        missingImageDeliveriesDTO.setFilingHistoryCategory(null);
        missingImageDeliveriesDTO.setFilingHistoryDate(null);
        missingImageDeliveriesDTO.setFilingHistoryDescription(null);
        missingImageDeliveriesDTO.setFilingHistoryType(null);
        missingImageDeliveriesDTO.setItemCost(null);
        missingImageDeliveriesDTO.setOrderedAt(null);
        missingImageDeliveriesDTO.setPaymentReference(null);

        // When
        final List<String> errors = validatorUnderTest.getValidationErrors(missingImageDeliveriesDTO);

        // Then
        assertThat(errors, containsInAnyOrder(
            ID_ERROR,
            COMPANY_NAME_ERROR,
            COMPANY_NUMBER_ERROR,
            ORDERED_AT_ERROR,
            PAYMENT_REFERENCE_ERROR,
            FILING_HISTORY_CATEGORY_ERROR,
            FILING_HISTORY_DESCRIPTION_ERROR,
            FILING_HISTORY_DATE_ERROR,
            FILING_HISTORY_TYPE_ERROR,
            ITEM_COST_ERROR));
    }

    @Test
    @DisplayName("ID is empty all other fields are populated")
    void idIsEmpty() {
        // Given
        missingImageDeliveriesDTO = ItemSetup.setUpMissingImageDeliveryDTO();
        missingImageDeliveriesDTO.setId("");
        // When
        final List<String> errors = validatorUnderTest.getValidationErrors(missingImageDeliveriesDTO);

        // Then
        assertThat(errors, contains("id: must not be null or empty in a create item request"));
    }

    @Test
    @DisplayName("payment reference is empty all other fields are populated")
    void paymentReferenceIsEmpty() {
        // Given
        missingImageDeliveriesDTO = ItemSetup.setUpMissingImageDeliveryDTO();
        missingImageDeliveriesDTO.setPaymentReference("");
        // When
        final List<String> errors = validatorUnderTest.getValidationErrors(missingImageDeliveriesDTO);

        // Then
        assertThat(errors, contains(PAYMENT_REFERENCE_ERROR));
    }

    @Test
    @DisplayName("All details are empty or null and mandatory for creation of MID item")
    void collectionDetailsEmptyOrNullAreMandatoryForCollectionDeliveryMethod() {
        // Given
        missingImageDeliveriesDTO = ItemSetup.setUpMissingImageDeliveryDTO();
        missingImageDeliveriesDTO.setId("");
        missingImageDeliveriesDTO.setCompanyName("");
        missingImageDeliveriesDTO.setCompanyNumber("");
        missingImageDeliveriesDTO.setFilingHistoryCategory("");
        missingImageDeliveriesDTO.setFilingHistoryDate("");
        missingImageDeliveriesDTO.setFilingHistoryDescription("");
        missingImageDeliveriesDTO.setFilingHistoryType("");
        missingImageDeliveriesDTO.setItemCost("");
        missingImageDeliveriesDTO.setOrderedAt(null);
        missingImageDeliveriesDTO.setPaymentReference("");

        // When
        final List<String> errors = validatorUnderTest.getValidationErrors(missingImageDeliveriesDTO);

        // Then
        assertThat(errors, containsInAnyOrder(
            ID_ERROR,
            COMPANY_NAME_ERROR,
            COMPANY_NUMBER_ERROR,
            ORDERED_AT_ERROR,
            PAYMENT_REFERENCE_ERROR,
            FILING_HISTORY_CATEGORY_ERROR,
            FILING_HISTORY_DESCRIPTION_ERROR,
            FILING_HISTORY_DATE_ERROR,
            FILING_HISTORY_TYPE_ERROR,
            ITEM_COST_ERROR));
    }
}
