package uk.gov.companieshouse.chd.order.api.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import uk.gov.companieshouse.chd.order.api.dto.MissingImageDeliveriesDTO;

@ExtendWith(MockitoExtension.class)
public class MissingImageDeliveriesControllerTest {

	@InjectMocks
	private MissingImageDeliveriesController controllerUnderTest;

	@Mock
	private MissingImageDeliveriesDTO missingImageDeliveryDTO;

	@Mock
	private HttpServletRequest request;

	@Test
	@DisplayName("Create Missing image delivery successfully")
	public void createMissingImageDeliverySuccessfully() {
		final ResponseEntity<Object> response = controllerUnderTest.createMissingImageDelivery(
				missingImageDeliveryDTO, request);

		assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
	}
}
