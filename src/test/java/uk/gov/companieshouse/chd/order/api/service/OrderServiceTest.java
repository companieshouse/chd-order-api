package uk.gov.companieshouse.chd.order.api.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import uk.gov.companieshouse.chd.order.api.exception.OrderServiceException;
import uk.gov.companieshouse.chd.order.api.model.MissingImageDeliveriesRequest;
import uk.gov.companieshouse.chd.order.api.model.OrderDetails;
import uk.gov.companieshouse.chd.order.api.model.OrderHeader;
import uk.gov.companieshouse.chd.order.api.repository.OrderHeaderRepository;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static uk.gov.companieshouse.chd.order.api.testUtils.ItemSetup.setUpMissingImageDeliveriesRequest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;


@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    private static final String FH_CATEGORY = "accounts";
    private static final String FH_DATE = "2018-04-22";
    private static final String ITEM_COST = "3";
    private static final String FILING_HISTORY_BARCODE = "1111111";
    private static final String ENTITY_ID = "222222";
    private static final String PAYMENT_REFERENCE_APPENED_BARCODE = "payment xyz--1111111";
    private static final String PAYMENT_REFERENCE_APPENED_ENTITY_ID = "payment xyz222222";
	private static final String FH_DESCRIPTION = "memorandum-articles";
	private static final String FH_TYPE = "MEM/ARTS";
	private static final String ID = "MID-898216-037074";
	private static final String COMPANY_NAME = "THE GIRLS' DAY SCHOOL TRUST";
	private static final String COMPANY_NUMBER = "00006400";
	private static final String PAYMENT_REF = "1234";

	private static final String MSG_ERROR = "Unable to save Request";


    @InjectMocks
    private OrderService serviceUnderTest;

    @Mock
    private OrderHeaderRepository orderHeaderRepository;

    @Mock
	private MissingImageDeliveriesRequest midRequest;

	@Mock
	private OrderDetails orderDetails;

	@Mock
	private OrderHeader orderHeaderTest;

	@Mock
	private DataAccessException dataAccessException;

	@Mock
    private OrderServiceException orderServiceException;

    @Captor
    ArgumentCaptor<OrderHeader> orderHeaderArgumentCaptor;

    @Test
    void saveOrderDetailsPersistsOrderHeader(){
        // given
        when(midRequest.getFilingHistoryCategory()).thenReturn(FH_CATEGORY);
        when(midRequest.getFilingHistoryDate()).thenReturn(FH_DATE);
        when(midRequest.getItemCost()).thenReturn(ITEM_COST);
        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setOrderDetails(Collections.singleton(orderDetails));

        doReturn(orderHeader).when(orderHeaderRepository).save(any(OrderHeader.class));
        // when and then
        assertThat(serviceUnderTest.saveOrderDetails(midRequest), is(orderHeader));
    }


    @Test
    void paymentRefHasBarcodeAppended() {
        final MissingImageDeliveriesRequest midRequest = setUpMissingImageDeliveriesRequest();
        midRequest.setFilingHistoryBarcode(FILING_HISTORY_BARCODE);

        serviceUnderTest.saveOrderDetails(midRequest);

        Mockito.verify(orderHeaderRepository).save(orderHeaderArgumentCaptor.capture());

        OrderHeader orderHeaderValue = orderHeaderArgumentCaptor.getValue();

        assertEquals(PAYMENT_REFERENCE_APPENED_BARCODE, orderHeaderValue.getPaymentReference());
    }

    @Test
    void paymentRefHasEntityIdAppended() {
        final MissingImageDeliveriesRequest midRequest = setUpMissingImageDeliveriesRequest();
        midRequest.setEntityID(ENTITY_ID);

        serviceUnderTest.saveOrderDetails(midRequest);

        Mockito.verify(orderHeaderRepository).save(orderHeaderArgumentCaptor.capture());

        OrderHeader orderHeaderValue = orderHeaderArgumentCaptor.getValue();

        assertEquals(PAYMENT_REFERENCE_APPENED_ENTITY_ID, orderHeaderValue.getPaymentReference());
    }

    @Test
    void paymentRefHasEntityIdAppendedWhenBarcodeIsAlsoAvailable() {
        final MissingImageDeliveriesRequest midRequest = setUpMissingImageDeliveriesRequest();
        midRequest.setEntityID(ENTITY_ID);
        midRequest.setFilingHistoryBarcode(FILING_HISTORY_BARCODE);

        serviceUnderTest.saveOrderDetails(midRequest);

        Mockito.verify(orderHeaderRepository).save(orderHeaderArgumentCaptor.capture());

        OrderHeader orderHeaderValue = orderHeaderArgumentCaptor.getValue();

        assertEquals(PAYMENT_REFERENCE_APPENED_ENTITY_ID, orderHeaderValue.getPaymentReference());
    }

	@Test
	void saveOrderDetailsPersistsOrderHeaderTestOrderServiceException() {
		// Given the request is Complete
		when(midRequest.getId()).thenReturn(ID);
		when(midRequest.getCompanyName()).thenReturn(COMPANY_NAME);
		when(midRequest.getCompanyNumber()).thenReturn(COMPANY_NUMBER);
		when(midRequest.getOrderedAt()).thenReturn(LocalDateTime.now());
		when(midRequest.getPaymentReference()).thenReturn(PAYMENT_REF);
		when(midRequest.getFilingHistoryCategory()).thenReturn(FH_CATEGORY);
		when(midRequest.getFilingHistoryDescription()).thenReturn(FH_DESCRIPTION);
		when(midRequest.getFilingHistoryDate()).thenReturn(FH_DATE);
		when(midRequest.getFilingHistoryType()).thenReturn(FH_TYPE);
		when(midRequest.getItemCost()).thenReturn(ITEM_COST);
		orderHeaderTest.setOrderDetails(Collections.singleton(orderDetails));

		// When
		doThrow(dataAccessException).when(orderHeaderRepository).save(any(OrderHeader.class));

		// Then we assert that we throw OrderServiceException inside the catch
		// and message is set correctly
		Exception exception = Assertions.assertThrows(OrderServiceException.class,
				() -> serviceUnderTest.saveOrderDetails(midRequest));
		assertTrue(exception.getMessage().contains(MSG_ERROR));
	}
}
