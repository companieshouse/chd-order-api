package uk.gov.companieshouse.chd.order.api.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import uk.gov.companieshouse.chd.order.api.exception.DuplicateEntryException;
import uk.gov.companieshouse.chd.order.api.exception.OrderServiceException;
import uk.gov.companieshouse.chd.order.api.model.MissingImageDeliveriesRequest;
import uk.gov.companieshouse.chd.order.api.model.OrderDetails;
import uk.gov.companieshouse.chd.order.api.model.OrderHeader;
import uk.gov.companieshouse.chd.order.api.repository.OrderHeaderRepository;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    private static final String FH_CATEGORY = "accounts";
    private static final String FH_DATE = "2018-04-22";
    private static final String ITEM_COST = "3";
    private static final String FH_DESCRIPTION = "memorandum-articles";
    private static final String FH_TYPE = "MEM/ARTS";
    private static final String ID = "MID-898216-037074";
    private static final String COMPANY_NAME = "THE GIRLS' DAY SCHOOL TRUST";
    private static final String COMPANY_NUMBER = "00006400";
    private static final String PAYMENT_REF = "1234";

    private static final String MSG_ERROR = "Unable to save Request";
    private static final String MSG_DUPLICATE_ERROR = "Duplicate Record";

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

    @Test
    void saveOrderDetailsPersistsOrderHeader() {
        // given
        givenMidRequestIsComplete();
        // when
        doReturn(orderHeaderTest).when(orderHeaderRepository)
                .save(any(OrderHeader.class));
        // then
        assertThat(serviceUnderTest.saveOrderDetails(midRequest),
                is(orderHeaderTest));
    }

    @Test
    void saveOrderDetailsPersistsOrderHeaderTestOrderServiceException() {
        // Given
        givenMidRequestIsComplete();

        // When
        doThrow(dataAccessException).when(orderHeaderRepository)
                .save(any(OrderHeader.class));

        // Then
        Exception exception = assertThrows(OrderServiceException.class,
                () -> serviceUnderTest.saveOrderDetails(midRequest));
        assertTrue(exception.getMessage().contains(MSG_ERROR));
    }

    @Test
    @DisplayName("Test Exception on Creating MID - Duplicate Record")
    void saveOrderDetailsPersistsOrderHeaderTestDuplicateException() {
        // Given
        givenMidRequestIsComplete();

        // When
        when(orderHeaderRepository.existsById(any(String.class)))
                .thenReturn(true);

        Exception exception = assertThrows(DuplicateEntryException.class,
                () -> serviceUnderTest.saveOrderDetails(midRequest));
        assertEquals(MSG_DUPLICATE_ERROR, exception.getMessage());
    }

    private void givenMidRequestIsComplete() {
        when(midRequest.getId()).thenReturn(ID);
        when(midRequest.getCompanyName()).thenReturn(COMPANY_NAME);
        when(midRequest.getCompanyNumber()).thenReturn(COMPANY_NUMBER);
        when(midRequest.getOrderedAt()).thenReturn(LocalDateTime.now());
        when(midRequest.getPaymentReference()).thenReturn(PAYMENT_REF);
        when(midRequest.getFilingHistoryCategory()).thenReturn(FH_CATEGORY);
        when(midRequest.getFilingHistoryDescription())
                .thenReturn(FH_DESCRIPTION);
        when(midRequest.getFilingHistoryDate()).thenReturn(FH_DATE);
        when(midRequest.getFilingHistoryType()).thenReturn(FH_TYPE);
        when(midRequest.getItemCost()).thenReturn(ITEM_COST);
        orderHeaderTest.setOrderDetails(Collections.singleton(orderDetails));
    }
}
