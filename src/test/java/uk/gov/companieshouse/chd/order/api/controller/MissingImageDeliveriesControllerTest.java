package uk.gov.companieshouse.chd.order.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.companieshouse.chd.order.api.dto.MissingImageDeliveriesDTO;
import uk.gov.companieshouse.chd.order.api.validator.CreateItemRequestValidator;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MissingImageDeliveriesControllerTest {

	@InjectMocks
	private MissingImageDeliveriesController controllerUnderTest;

	@Mock
	private CreateItemRequestValidator createMissingImageDeliveryItemRequestValidator;

	@Mock
	private HttpServletRequest request;

	private static final MissingImageDeliveriesDTO MISSING_IMAGE_DELIVERIES_DTO;

	static {
		MISSING_IMAGE_DELIVERIES_DTO = new MissingImageDeliveriesDTO();
		MISSING_IMAGE_DELIVERIES_DTO.setCompanyName("Test");
		MISSING_IMAGE_DELIVERIES_DTO.setCompanyNumber("123");
		MISSING_IMAGE_DELIVERIES_DTO.setFilingHistoryType("TestType");
		MISSING_IMAGE_DELIVERIES_DTO.setFilingHistoryCategory("Test");
		MISSING_IMAGE_DELIVERIES_DTO.setFilingHistoryDate("25-10-2018");
		MISSING_IMAGE_DELIVERIES_DTO.setFilingHistoryDescription("Test");
		MISSING_IMAGE_DELIVERIES_DTO.setId("Test");
		MISSING_IMAGE_DELIVERIES_DTO.setItemCost("Test");
		MISSING_IMAGE_DELIVERIES_DTO.setOrderedAt(LocalDateTime.now());
		MISSING_IMAGE_DELIVERIES_DTO.setPaymentReference("Test");
	}

	@Test
	@DisplayName("Create Missing image delivery successfully")
	public void createMissingImageDeliverySuccessfully() {

		when(createMissingImageDeliveryItemRequestValidator.getValidationErrors(MISSING_IMAGE_DELIVERIES_DTO))
			.thenReturn(new ArrayList<String>());
		final ResponseEntity<Object> response = controllerUnderTest.createMissingImageDelivery(
				MISSING_IMAGE_DELIVERIES_DTO, request);
		assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
	}

	@Test
	@DisplayName("Create Missing image delivery failed")
	public void createMissingImageDeliveryFailed() {
		ArrayList<String> errors = new ArrayList<String>();
		errors.add("company_name: must not be null in create item request");
		when(createMissingImageDeliveryItemRequestValidator.getValidationErrors(MISSING_IMAGE_DELIVERIES_DTO))
			.thenReturn(errors);
		final ResponseEntity<Object> response = controllerUnderTest.createMissingImageDelivery(
			MISSING_IMAGE_DELIVERIES_DTO, request);
		assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
	}
}