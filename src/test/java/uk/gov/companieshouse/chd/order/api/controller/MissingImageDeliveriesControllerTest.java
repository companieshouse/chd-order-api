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

	private MissingImageDeliveriesDTO missingImageDeliveryDTO;

	@Mock
	private HttpServletRequest request;

	@Test
	@DisplayName("Create Missing image delivery successfully")
	public void createMissingImageDeliverySuccessfully() {
		setUpMissingImageDeliveryDTO();
		final ResponseEntity<MissingImageDeliveriesDTO> response = controllerUnderTest.createMissingImageDelivery(
				missingImageDeliveryDTO, request);
		
		assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
		assertEquals(missingImageDeliveryDTO.getCompanyName(), response.getBody().getCompanyName());
		assertEquals(missingImageDeliveryDTO.getCompanyNumber(), response.getBody().getCompanyNumber());
		assertEquals(missingImageDeliveryDTO.getFilingHistoryCategory(), response.getBody().getFilingHistoryCategory());
		assertEquals(missingImageDeliveryDTO.getFilingHistoryDate(), response.getBody().getFilingHistoryDate());
		assertEquals(missingImageDeliveryDTO.getFilingHistoryDescription(), response.getBody().getFilingHistoryDescription());
		assertEquals(missingImageDeliveryDTO.getId(), response.getBody().getId());
		assertEquals(missingImageDeliveryDTO.getItemCost(), response.getBody().getItemCost());
		assertEquals(missingImageDeliveryDTO.getOrderedAt(), response.getBody().getOrderedAt());
		assertEquals(missingImageDeliveryDTO.getPaymentReference(), response.getBody().getPaymentReference());
	}
	
	// Mock test covering for sonar 
	private void setUpMissingImageDeliveryDTO() {
		missingImageDeliveryDTO = new MissingImageDeliveriesDTO();
		missingImageDeliveryDTO.setCompanyName("Test");
		missingImageDeliveryDTO.setCompanyNumber("123");
		missingImageDeliveryDTO.setFilingHistoryCategory("Test");
		missingImageDeliveryDTO.setFilingHistoryDate("25-10-2018");
		missingImageDeliveryDTO.setFilingHistoryDescription("Test");
		missingImageDeliveryDTO.setId("Test");
		missingImageDeliveryDTO.setItemCost("Test");
		missingImageDeliveryDTO.setOrderedAt(LocalDateTime.now());	
		missingImageDeliveryDTO.setPaymentReference("Test");
	}
	

}
