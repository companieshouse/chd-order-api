package uk.gov.companieshouse.chd.order.api.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static uk.gov.companieshouse.chd.order.api.testUtils.ItemSetup.setUpMissingImageDeliveriesRequest;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import uk.gov.companieshouse.chd.order.api.exception.DuplicateEntryException;
import uk.gov.companieshouse.chd.order.api.exception.OrderServiceException;
import uk.gov.companieshouse.chd.order.api.model.Customer;
import uk.gov.companieshouse.chd.order.api.model.MissingImageDeliveriesRequest;
import uk.gov.companieshouse.chd.order.api.model.OrderDetails;
import uk.gov.companieshouse.chd.order.api.model.OrderHeader;
import uk.gov.companieshouse.chd.order.api.repository.CustomerRepository;
import uk.gov.companieshouse.chd.order.api.repository.OrderHeaderRepository;


@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    
    private static final String FH_CATEGORY = "accounts";
    private static final String FH_DATE = "2018-04-22";
    private static final String ITEM_COST = "3";
    private static final String FILING_HISTORY_BARCODE = "1111111";
    private static final String ENTITY_ID = "222222";
    private static final String PAYMENT_REFERENCE_APPENDED_BARCODE = "payment xyz--1111111";
    private static final String PAYMENT_REFERENCE_APPENDED_ENTITY_ID = "payment xyz222222";

    private static final String MSG_ERROR = "Unable to save Request";
    private static final String MSG_DUPLICATE_ERROR = "Duplicate Record";
    private static Customer CUSTOMER;
    private static final String EMAIL = "email";
    private static final String EMAIL_OVER_40_CHARS = "this-email-address-is-longer-than-fourty-characters-in-length";
    private static long CUSTOMER_ID=0L;


    @InjectMocks
    private OrderService serviceUnderTest;

    @Mock
    private OrderHeaderRepository orderHeaderRepository;
    
    @Mock
    private CustomerRepository customerRepository;

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
    
    @Captor
    ArgumentCaptor<Customer> customerArgumentCaptor;
    
    @BeforeEach
    void setup() {
        CUSTOMER = new Customer();
        CUSTOMER.setCustomerId(CUSTOMER_ID);
        CUSTOMER.setCustomerVersion(2L);
        CUSTOMER.setForename("forename");
        CUSTOMER.setSurname("surname");
        CUSTOMER.setEmail(EMAIL);
        
    }

    @Test
    @DisplayName("Test that an order is saved with an existing customer")
    void saveOrderDetailsPersistsOrderHeader() {
        // given
        when(midRequest.getFilingHistoryCategory()).thenReturn(FH_CATEGORY);
        when(midRequest.getFilingHistoryDate()).thenReturn(FH_DATE);
        when(midRequest.getItemCost()).thenReturn(ITEM_COST);
        when(midRequest.getEmail()).thenReturn(EMAIL);
        when(customerRepository.findCustomerByCustomerIdAndEmail(CUSTOMER_ID, EMAIL)).thenReturn(CUSTOMER);
        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setOrderDetails(Collections.singleton(orderDetails));

        doReturn(orderHeader).when(orderHeaderRepository).save(any(OrderHeader.class));
        // when and then
        assertThat(serviceUnderTest.saveOrderDetails(midRequest), is(orderHeader));
    }
    
    @Test
    @DisplayName("Test that an order is saved with a new customer")
    void saveOrderDetailsPersistsOrderHeaderNewCustomer() {
        // given
        when(midRequest.getFilingHistoryCategory()).thenReturn(FH_CATEGORY);
        when(midRequest.getFilingHistoryDate()).thenReturn(FH_DATE);
        when(midRequest.getItemCost()).thenReturn(ITEM_COST);
        when(midRequest.getEmail()).thenReturn(EMAIL);
        when(customerRepository.findCustomerByCustomerIdAndEmail(CUSTOMER_ID, EMAIL)).thenReturn(null);
        when(customerRepository.save(any(Customer.class))).thenReturn(CUSTOMER);
        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setOrderDetails(Collections.singleton(orderDetails));

        doReturn(orderHeader).when(orderHeaderRepository).save(any(OrderHeader.class));
        // when and then
        serviceUnderTest.saveOrderDetails(midRequest);
        Mockito.verify(customerRepository, times(1)).save(customerArgumentCaptor.capture());
        Customer createdCustomer = customerArgumentCaptor.getValue();
        assertEquals(CUSTOMER_ID, createdCustomer.getCustomerId());
        assertEquals(EMAIL, createdCustomer.getEmail());
    }
    
    @Test
    @DisplayName("Test that an order is saved correctly with a new customer with an email address longer than 40 characters")
    void saveOrderDetailsPersistsOrderHeaderNewCustomerWithLongEmailAddress() {
        // given
        when(midRequest.getFilingHistoryCategory()).thenReturn(FH_CATEGORY);
        when(midRequest.getFilingHistoryDate()).thenReturn(FH_DATE);
        when(midRequest.getItemCost()).thenReturn(ITEM_COST);
        when(midRequest.getEmail()).thenReturn(EMAIL_OVER_40_CHARS);
        when(customerRepository.findCustomerByCustomerIdAndEmail(CUSTOMER_ID, EMAIL_OVER_40_CHARS)).thenReturn(null);
        when(customerRepository.save(any(Customer.class))).thenReturn(CUSTOMER);
        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setOrderDetails(Collections.singleton(orderDetails));

        doReturn(orderHeader).when(orderHeaderRepository).save(any(OrderHeader.class));
        // when and then
        serviceUnderTest.saveOrderDetails(midRequest);
        Mockito.verify(customerRepository, times(1)).save(customerArgumentCaptor.capture());
        Customer createdCustomer = customerArgumentCaptor.getValue();
        assertEquals(CUSTOMER_ID, createdCustomer.getCustomerId());
        assertEquals(EMAIL_OVER_40_CHARS, createdCustomer.getEmail());
        String first40Chars = EMAIL_OVER_40_CHARS.substring(0, 40);
        assertEquals(first40Chars, createdCustomer.getPremises());
        assertEquals(first40Chars, createdCustomer.getAddrline1());
    }

    @Test
    @DisplayName("test that the payment reference field includes the barcode")
    void paymentRefHasBarcodeAppended() {
        final MissingImageDeliveriesRequest midRequest = setUpMissingImageDeliveriesRequest();
        midRequest.setFilingHistoryBarcode(FILING_HISTORY_BARCODE);

        serviceUnderTest.saveOrderDetails(midRequest);

        Mockito.verify(orderHeaderRepository).save(orderHeaderArgumentCaptor.capture());

        OrderHeader orderHeaderValue = orderHeaderArgumentCaptor.getValue();

        assertEquals(PAYMENT_REFERENCE_APPENDED_BARCODE, orderHeaderValue.getPaymentReference());
    }

    @Test
    @DisplayName("Test that the payment reference field includes the entity id")
    void paymentRefHasEntityIdAppended() {
        final MissingImageDeliveriesRequest midRequest = setUpMissingImageDeliveriesRequest();
        midRequest.setEntityID(ENTITY_ID);

        serviceUnderTest.saveOrderDetails(midRequest);

        Mockito.verify(orderHeaderRepository).save(orderHeaderArgumentCaptor.capture());

        OrderHeader orderHeaderValue = orderHeaderArgumentCaptor.getValue();

        assertEquals(PAYMENT_REFERENCE_APPENDED_ENTITY_ID, orderHeaderValue.getPaymentReference());
    }

    @Test
    @DisplayName("Test that the payment reference field includes the entity id when both entity id and barcode are available")
    void paymentRefHasEntityIdAppendedWhenBarcodeIsAlsoAvailable() {
        final MissingImageDeliveriesRequest midRequest = setUpMissingImageDeliveriesRequest();
        midRequest.setEntityID(ENTITY_ID);
        midRequest.setFilingHistoryBarcode(FILING_HISTORY_BARCODE);

        serviceUnderTest.saveOrderDetails(midRequest);

        Mockito.verify(orderHeaderRepository).save(orderHeaderArgumentCaptor.capture());

        OrderHeader orderHeaderValue = orderHeaderArgumentCaptor.getValue();

        assertEquals(PAYMENT_REFERENCE_APPENDED_ENTITY_ID, orderHeaderValue.getPaymentReference());
    }

    @Test
    @DisplayName("Test Exception on Creating MID - Order Service Exception")
    void saveOrderDetailsPersistsOrderHeaderTestOrderServiceException() {
        // given
        final MissingImageDeliveriesRequest midRequest = setUpMissingImageDeliveriesRequest();

        // When
        doThrow(dataAccessException).when(orderHeaderRepository)
                .save(any(OrderHeader.class));

        // Then
        Exception exception = assertThrows(
                OrderServiceException.class,
                () -> serviceUnderTest.saveOrderDetails(midRequest));
        assertTrue(exception.getMessage().contains(MSG_ERROR));
    }

    @Test
    @DisplayName("Test Exception on Creating MID - Duplicate Record Exception")
    void saveOrderDetailsPersistsOrderHeaderTestDuplicateException() {
        // given
        final MissingImageDeliveriesRequest midRequest = setUpMissingImageDeliveriesRequest();

        // When
        when(orderHeaderRepository.existsById(any(String.class)))
                .thenReturn(true);

        Exception exception = assertThrows(DuplicateEntryException.class,
                () -> serviceUnderTest.saveOrderDetails(midRequest));
        assertEquals(MSG_DUPLICATE_ERROR, exception.getMessage());
    }
}
