package uk.gov.companieshouse.chd.order.api.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

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
	private HttpServletRequest request;

	private static final MissingImageDeliveriesDTO MISSING_IMAGE_DELIVERIES_DTO;

	static {
		MISSING_IMAGE_DELIVERIES_DTO = new MissingImageDeliveriesDTO();
		MISSING_IMAGE_DELIVERIES_DTO.setCompanyName("Test");
		MISSING_IMAGE_DELIVERIES_DTO.setCompanyNumber("123");
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
		final ResponseEntity<MissingImageDeliveriesDTO> response = controllerUnderTest.createMissingImageDelivery(
				MISSING_IMAGE_DELIVERIES_DTO, request);
		
		assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
		assertEquals(MISSING_IMAGE_DELIVERIES_DTO.getCompanyName(), response.getBody().getCompanyName());
		assertEquals(MISSING_IMAGE_DELIVERIES_DTO.getCompanyNumber(), response.getBody().getCompanyNumber());
		assertEquals(MISSING_IMAGE_DELIVERIES_DTO.getFilingHistoryCategory(), response.getBody().getFilingHistoryCategory());
		assertEquals(MISSING_IMAGE_DELIVERIES_DTO.getFilingHistoryDate(), response.getBody().getFilingHistoryDate());
		assertEquals(MISSING_IMAGE_DELIVERIES_DTO.getFilingHistoryDescription(), response.getBody().getFilingHistoryDescription());
		assertEquals(MISSING_IMAGE_DELIVERIES_DTO.getId(), response.getBody().getId());
		assertEquals(MISSING_IMAGE_DELIVERIES_DTO.getItemCost(), response.getBody().getItemCost());
		assertEquals(MISSING_IMAGE_DELIVERIES_DTO.getOrderedAt(), response.getBody().getOrderedAt());
		assertEquals(MISSING_IMAGE_DELIVERIES_DTO.getPaymentReference(), response.getBody().getPaymentReference());
	}

}
