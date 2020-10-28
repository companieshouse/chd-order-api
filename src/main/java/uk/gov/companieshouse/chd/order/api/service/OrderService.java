package uk.gov.companieshouse.chd.order.api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uk.gov.companieshouse.chd.order.api.logging.LoggingUtils;
import uk.gov.companieshouse.chd.order.api.model.FilingHistoryCategory;
import uk.gov.companieshouse.chd.order.api.model.MissingImageDeliveriesRequest;
import uk.gov.companieshouse.chd.order.api.model.OrderDetails;
import uk.gov.companieshouse.chd.order.api.model.OrderHeader;
import uk.gov.companieshouse.chd.order.api.repository.OrderHeaderRepository;
import uk.gov.companieshouse.logging.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Map;

import static uk.gov.companieshouse.chd.order.api.logging.LoggingUtils.COMPANY_NUMBER_LOG_KEY;

@Service
public class OrderService {
    private final OrderHeaderRepository orderHeaderRepository;
    private static final Logger LOGGER = LoggingUtils.getLogger();

    private static final long NUM_ORDER_LINES = 1;
    private static final long CUSTOMER_VERSION = 1;
    private static final String PRODUCT_SUB_KEY = "SCUD";
    @Value("${chprd.customer-id}")
    private long CUSTOMER_ID;
    @Value("${chprd.payment-method}")
    private long PAYMENT_METHOD;
    @Value("${chprd.handcsr}")
    private String HANDCSR;
    @Value("${chprd.language}")
    private String LANGUAGE;
    @Value("${chprd.flags}")
    private long FLAGS;

    private static final long SEQUENCE_NUMBER = 1;
    private static final long STATUS = 1;
    private static final long QUANTITY = 1;
    @Value("${chprd.delivery-method}")
    private long DELIVERY_METHOD;
    @Value("${chprd.delivery-location}")
    private long DELIVERY_LOCATION;

    public OrderService(OrderHeaderRepository orderHeaderRepository) {
        this.orderHeaderRepository = orderHeaderRepository;
    }

    /**
     * Saves order details to CHPRD tables `ORDERHEADER` and `ORDERDETAIL` from data received in MID request.
     * @param midRequest order details to be persisted
     */
    public OrderHeader saveOrderDetails(MissingImageDeliveriesRequest midRequest) {
        Map<String, Object> logMap = LoggingUtils.createLoggingDataMap(COMPANY_NUMBER_LOG_KEY, midRequest.getCompanyNumber());

        OrderHeader orderHeader = createOrderHeader(midRequest);
        OrderDetails orderDetails = createOrderDetails(midRequest);
        orderHeader.setOrderDetails(Collections.singleton(orderDetails));

        LOGGER.info("Saving order details", logMap);
        return orderHeaderRepository.save(orderHeader);
    }

    private OrderHeader createOrderHeader(MissingImageDeliveriesRequest midRequest) {
        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setNumOrderLines(NUM_ORDER_LINES);
        orderHeader.setCustomerVersion(CUSTOMER_VERSION);
        orderHeader.setCustomerId(CUSTOMER_ID);
        orderHeader.setPsNumber(midRequest.getId());
        orderHeader.setPaymentMethod(PAYMENT_METHOD);
        orderHeader.setHandCsr(HANDCSR);
        orderHeader.setLanguage(LANGUAGE);
        orderHeader.setFlags(FLAGS);
        orderHeader.setPaymentMethod(PAYMENT_METHOD);
        orderHeader.setStatus(STATUS);
        orderHeader.setPaymentReference(midRequest.getPaymentReference());
        orderHeader.setOrderDateTime(midRequest.getOrderedAt());

        return orderHeader;
    }

    private OrderDetails createOrderDetails(MissingImageDeliveriesRequest midRequest) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setPsNumber(midRequest.getId());
        orderDetails.setSequenceNumber(SEQUENCE_NUMBER);
        orderDetails.setCompanyName(midRequest.getCompanyName());
        orderDetails.setCompanyNumber(midRequest.getCompanyNumber());
        FilingHistoryCategory fhCategory = FilingHistoryCategory.enumValueOf(midRequest.getFilingHistoryCategory());
        orderDetails.setProductCategory(fhCategory.getProductCategory().getValue());
        orderDetails.setProductCode(fhCategory.getProductCode());
        orderDetails.setProductKey(midRequest.getFilingHistoryType());
        orderDetails.setProductSubKey(PRODUCT_SUB_KEY);
        orderDetails.setProductDescription(midRequest.getFilingHistoryDescription());
        LocalDate productDate = LocalDate.parse(midRequest.getFilingHistoryDate(), DateTimeFormatter.ISO_LOCAL_DATE);
        orderDetails.setProductDate(productDate);
        orderDetails.setDeliveryMethod(DELIVERY_METHOD);
        orderDetails.setDeliveryLocation(DELIVERY_LOCATION);
        orderDetails.setItemCost(Long.parseLong(midRequest.getItemCost()));
        orderDetails.setQuantity(QUANTITY);
        orderDetails.setStatus(STATUS);

        return orderDetails;
    }
}
