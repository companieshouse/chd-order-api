package uk.gov.companieshouse.chd.order.api.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;

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
import uk.gov.companieshouse.chd.order.api.exception.DuplicateEntryException;
import uk.gov.companieshouse.chd.order.api.exception.OrderServiceException;
import uk.gov.companieshouse.chd.order.api.mapper.MissingImageDeliveriesRequestMapper;
import uk.gov.companieshouse.chd.order.api.model.MissingImageDeliveriesRequest;
import uk.gov.companieshouse.chd.order.api.service.OrderService;
import uk.gov.companieshouse.chd.order.api.validator.CreateItemRequestValidator;

@ExtendWith(MockitoExtension.class)
class MissingImageDeliveriesControllerTest {

    @InjectMocks
    private MissingImageDeliveriesController controllerUnderTest;

    @Mock
    private CreateItemRequestValidator createMissingImageDeliveryItemRequestValidator;

    @Mock
    private HttpServletRequest request;

    @Mock
    private MissingImageDeliveriesRequestMapper midRequestMapper;

    @Mock
    private MissingImageDeliveriesRequest midRequest;

    @Mock
    private OrderServiceException orderServiceException;

    @Mock
    private OrderService orderService;

    private static final MissingImageDeliveriesDTO MISSING_IMAGE_DELIVERIES_DTO;

    static {
        MISSING_IMAGE_DELIVERIES_DTO = new MissingImageDeliveriesDTO();
        MISSING_IMAGE_DELIVERIES_DTO.setCompanyName("Test");
        MISSING_IMAGE_DELIVERIES_DTO.setCompanyNumber("123");
        MISSING_IMAGE_DELIVERIES_DTO.setFilingHistoryType("TestType");
        MISSING_IMAGE_DELIVERIES_DTO.setFilingHistoryCategory("Test");
        MISSING_IMAGE_DELIVERIES_DTO.setFilingHistoryDate("25-10-2018");
        MISSING_IMAGE_DELIVERIES_DTO.setFilingHistoryDescription("Test");
        MISSING_IMAGE_DELIVERIES_DTO.setFilingHistoryBarcode("111111");
        MISSING_IMAGE_DELIVERIES_DTO.setEntityID("222222");
        MISSING_IMAGE_DELIVERIES_DTO.setId("Test");
        MISSING_IMAGE_DELIVERIES_DTO.setItemCost("Test");
        MISSING_IMAGE_DELIVERIES_DTO.setOrderedAt(LocalDateTime.now());
        MISSING_IMAGE_DELIVERIES_DTO.setPaymentReference("Test");
    }

    @Test
    @DisplayName("Create Missing image delivery successfully")
    void createMissingImageDeliverySuccessfully() {
        when(createMissingImageDeliveryItemRequestValidator
                .getValidationErrors(MISSING_IMAGE_DELIVERIES_DTO))
                        .thenReturn(new ArrayList<String>());
        final ResponseEntity<Object> response = controllerUnderTest
                .createMissingImageDelivery(MISSING_IMAGE_DELIVERIES_DTO, request);
        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
    }

    @Test
    @DisplayName("Create Missing image delivery failed")
    void createMissingImageDeliveryFailed() {
        ArrayList<String> errors = new ArrayList<String>();
        String message = "company_name: must not be null or empty in create item request";
        errors.add(message);
        when(createMissingImageDeliveryItemRequestValidator
                .getValidationErrors(MISSING_IMAGE_DELIVERIES_DTO)).thenReturn(errors);
        final ResponseEntity<Object> response = controllerUnderTest
                .createMissingImageDelivery(MISSING_IMAGE_DELIVERIES_DTO, request);
        assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
        assertThat(((ApiError) response.getBody()).getErrors().get(0), is(message));
    }

    @Test
    @DisplayName("Test Exception on Creating MID - Unable to save Request messsage sent")
    void createMissingImageDeliverTestExecutionException() {
        when(createMissingImageDeliveryItemRequestValidator
                .getValidationErrors(MISSING_IMAGE_DELIVERIES_DTO))
                        .thenReturn(new ArrayList<String>());
        when(midRequestMapper.mapMissingImageDeliveriesRequest(MISSING_IMAGE_DELIVERIES_DTO))
                .thenReturn(midRequest);
        when(orderService.saveOrderDetails(midRequest)).thenThrow(OrderServiceException.class);

        final ResponseEntity<Object> response = controllerUnderTest
                .createMissingImageDelivery(MISSING_IMAGE_DELIVERIES_DTO, request);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    @DisplayName("Test Exception on Creating MID - Duplicate Record")
    void createMissingImageDeliverTestOnDuplicateEntryException() {
        when(createMissingImageDeliveryItemRequestValidator
                .getValidationErrors(MISSING_IMAGE_DELIVERIES_DTO))
                        .thenReturn(new ArrayList<String>());
        when(midRequestMapper
                .mapMissingImageDeliveriesRequest(MISSING_IMAGE_DELIVERIES_DTO))
                        .thenReturn(midRequest);
        when(orderService.saveOrderDetails(midRequest))
                .thenThrow(DuplicateEntryException.class);

        final ResponseEntity<Object> response = controllerUnderTest
                .createMissingImageDelivery(MISSING_IMAGE_DELIVERIES_DTO,
                        request);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }
}
