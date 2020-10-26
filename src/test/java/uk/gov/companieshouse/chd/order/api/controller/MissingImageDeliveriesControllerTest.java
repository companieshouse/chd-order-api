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
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MissingImageDeliveriesControllerTest {

	@InjectMocks
	private MissingImageDeliveriesController controllerUnderTest;

	@Mock
	private MissingImageDeliveriesDTO missingImageDeliveryDTO;

	@Mock
	private HttpServletRequest request;

	@Mock
	private CreateItemRequestValidator createMissingImageDeliveryItemRequestValidator;

	@Test
	@DisplayName("Create Missing image delivery successfully")
	public void createMissingImageDeliverySuccessfully() {
		when(createMissingImageDeliveryItemRequestValidator.getValidationErrors(missingImageDeliveryDTO))
			.thenReturn(new ArrayList<String>());
		final ResponseEntity<Object> response = controllerUnderTest.createMissingImageDelivery(
				missingImageDeliveryDTO, request);
		assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
	}

	@Test
	@DisplayName("Create Missing image delivery failed")
	public void createMissingImageDeliveryFailed() {
		ArrayList<String> errors = new ArrayList<String>();
		errors.add("company_name: must not be null in create item request");
		when(createMissingImageDeliveryItemRequestValidator.getValidationErrors(missingImageDeliveryDTO))
			.thenReturn(errors);
		final ResponseEntity<Object> response = controllerUnderTest.createMissingImageDelivery(
			missingImageDeliveryDTO, request);
		assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
	}
}