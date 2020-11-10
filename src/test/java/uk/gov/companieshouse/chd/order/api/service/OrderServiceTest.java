package uk.gov.companieshouse.chd.order.api.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.companieshouse.chd.order.api.model.MissingImageDeliveriesRequest;
import uk.gov.companieshouse.chd.order.api.model.OrderDetails;
import uk.gov.companieshouse.chd.order.api.model.OrderHeader;
import uk.gov.companieshouse.chd.order.api.repository.OrderHeaderRepository;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static uk.gov.companieshouse.chd.order.api.testUtils.ItemSetup.setUpMissingImageDeliveriesRequest;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    private static final String FH_CATEGORY = "accounts";
    private static final String FH_DATE = "2018-04-22";
    private static final String ITEM_COST = "3";
    private static final String FILING_HISTORY_BARCODE = "1111111";
    private static final String ENTITY_ID = "222222";
    private static final String PAYMENT_REFERENCE_APPENED_BARCODE = "payment xyz--1111111";
    private static final String PAYMENT_REFERENCE_APPENED_ENTITY_ID = "payment xyz222222";

    @InjectMocks
    private OrderService serviceUnderTest;

    @Mock
    private OrderHeaderRepository repository;

    @Mock
    private MissingImageDeliveriesRequest midRequest;

    @Mock
    private OrderDetails orderDetails;

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

        doReturn(orderHeader).when(repository).save(any(OrderHeader.class));
        // when and then
        assertThat(serviceUnderTest.saveOrderDetails(midRequest), is(orderHeader));
    }

    @Test
    void paymentRefHasBarcodeAppended() {
        final MissingImageDeliveriesRequest midRequest = setUpMissingImageDeliveriesRequest();
        midRequest.setFilingHistoryBarcode(FILING_HISTORY_BARCODE);

        serviceUnderTest.saveOrderDetails(midRequest);

        Mockito.verify(repository).save(orderHeaderArgumentCaptor.capture());

        OrderHeader orderHeaderValue = orderHeaderArgumentCaptor.getValue();

        assertEquals(PAYMENT_REFERENCE_APPENED_BARCODE, orderHeaderValue.getPaymentReference());
    }

    @Test
    void paymentRefHasEntityIdAppended() {
        final MissingImageDeliveriesRequest midRequest = setUpMissingImageDeliveriesRequest();
        midRequest.setEntityID(ENTITY_ID);

        serviceUnderTest.saveOrderDetails(midRequest);

        Mockito.verify(repository).save(orderHeaderArgumentCaptor.capture());

        OrderHeader orderHeaderValue = orderHeaderArgumentCaptor.getValue();

        assertEquals(PAYMENT_REFERENCE_APPENED_ENTITY_ID, orderHeaderValue.getPaymentReference());
    }

    @Test
    void paymentRefHasEntityIdAppendedWhenBarcodeIsAlsoAvailable() {
        final MissingImageDeliveriesRequest midRequest = setUpMissingImageDeliveriesRequest();
        midRequest.setEntityID(ENTITY_ID);
        midRequest.setFilingHistoryBarcode(FILING_HISTORY_BARCODE);

        serviceUnderTest.saveOrderDetails(midRequest);

        Mockito.verify(repository).save(orderHeaderArgumentCaptor.capture());

        OrderHeader orderHeaderValue = orderHeaderArgumentCaptor.getValue();

        assertEquals(PAYMENT_REFERENCE_APPENED_ENTITY_ID, orderHeaderValue.getPaymentReference());
    }
}
