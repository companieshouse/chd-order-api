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

public class CreateItemRequestValidatorTest {

    private CreateItemRequestValidator validatorUnderTest;
    private MissingImageDeliveriesDTO missingImageDeliveriesDTO;

    @BeforeEach
    void setUp() {
        validatorUnderTest = new CreateItemRequestValidator();
    }

    @Test
    @DisplayName("all fields are not null")
    void allFieldsAreNotNull() {
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
        assertThat(errors, contains("id: must not be null in a create item request"));
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
        assertThat(errors, contains("payment_reference: must not be null in create item request"));
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
        missingImageDeliveriesDTO.setItemCost(null);
        missingImageDeliveriesDTO.setOrderedAt(null);
        missingImageDeliveriesDTO.setPaymentReference(null);

        // When
        final List<String> errors = validatorUnderTest.getValidationErrors(missingImageDeliveriesDTO);

        // Then
        assertThat(errors, containsInAnyOrder(
            "id: must not be null in a create item request",
            "company_name: must not be null in create item request",
            "company_number: must not be null in create item request",
            "ordered_at: must not be null in create item request",
            "payment_reference: must not be null in create item request",
            "filing_history_category: must not be null in create item request",
            "filing_history_description: must not be null in create item request",
            "filing_history_date: must not be null in create item request",
            "item_cost: must not be null in create item request"));
    }
}
