package uk.gov.companieshouse.chd.order.api.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.companieshouse.chd.order.api.model.MissingImageDeliveriesRequest;
import uk.gov.companieshouse.chd.order.api.model.OrderDetails;
import uk.gov.companieshouse.chd.order.api.model.OrderHeader;
import uk.gov.companieshouse.chd.order.api.repository.OrderHeaderRepository;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    private static final String FH_CATEGORY = "accounts";
    private static final String FH_DATE = "2018-04-22";
    private static final String ITEM_COST = "3";
    private static final String ID = "MID-462515-995726";
    private static final String COMPANY_NUMBER = "00006400";
    private static final String COMPANY_NAME = "THE GIRLS' DAY SCHOOL TRUST";
    private static final String PAYMENT_REFERENCE = "payment xyz";
    private static final LocalDateTime ORDERED_AT = LocalDateTime.parse("2020-10-26T10:20:46.058");
    private static final String FILING_HISTORY_CATEGORY = "officer";
    private static final String FILING_HISTORY_DATE = "2010-02-12";
    private static final String FILING_HISTORY_DESCRIPTION = "change-person-director-company-with-change-date";
    private static final String FILING_HISTORY_TYPE = "Filing History Test Type";
    private static final String FILING_HISTORY_BARCODE = "1111111";
    private static final String ENTITY_ID = "222222";

    @InjectMocks
    private OrderService serviceUnderTest;

    @Mock
    private OrderHeaderRepository repository;

    @Mock
    private MissingImageDeliveriesRequest midRequest;

    @Mock
    private OrderDetails orderDetails;

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

}
