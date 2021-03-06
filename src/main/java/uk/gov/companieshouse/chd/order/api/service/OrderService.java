package uk.gov.companieshouse.chd.order.api.service;

import static uk.gov.companieshouse.chd.order.api.logging.LoggingUtils.COMPANY_NUMBER_LOG_KEY;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import uk.gov.companieshouse.chd.order.api.exception.DuplicateEntryException;
import uk.gov.companieshouse.chd.order.api.exception.OrderServiceException;
import uk.gov.companieshouse.chd.order.api.logging.LoggingUtils;
import uk.gov.companieshouse.chd.order.api.model.Customer;
import uk.gov.companieshouse.chd.order.api.model.FilingHistoryCategory;
import uk.gov.companieshouse.chd.order.api.model.MissingImageDeliveriesRequest;
import uk.gov.companieshouse.chd.order.api.model.OrderDetails;
import uk.gov.companieshouse.chd.order.api.model.OrderHeader;
import uk.gov.companieshouse.chd.order.api.repository.CustomerRepository;
import uk.gov.companieshouse.chd.order.api.repository.OrderHeaderRepository;
import uk.gov.companieshouse.logging.Logger;

@Service
public class OrderService {
    private final OrderHeaderRepository orderHeaderRepository;
    private final CustomerRepository customerRepository;
    private static final Logger LOGGER = LoggingUtils.getLogger();

    private static final long NUM_ORDER_LINES = 1;
    private static final String PRODUCT_SUB_KEY = "SCUD";
    private static final long REORDERED = 0;
    @Value("${chprd.customer-id}")
    private long customerId;
    @Value("${chprd.payment-method}")
    private long paymentMethod;
    @Value("${chprd.handcsr}")
    private String handcsr;
    @Value("${chprd.language}")
    private String language;
    @Value("${chprd.flags}")
    private long flags;
    @Value("${chprd.forename}")
    private String forename;
    @Value("${chprd.surname}")
    private String surname;

    private static final long SEQUENCE_NUMBER = 1;
    private static final long STATUS = 1;
    private static final long QUANTITY = 1;
    @Value("${chprd.delivery-method}")
    private long deliveryMethod;
    @Value("${chprd.delivery-location}")
    private long deliveryLocation;

    public OrderService(OrderHeaderRepository orderHeaderRepository, CustomerRepository customerRepository) {
        this.orderHeaderRepository = orderHeaderRepository;
        this.customerRepository = customerRepository;
    }

    /**
     * Saves order details to CHPRD tables `ORDERHEADER` and `ORDERDETAIL` from data received in MID request.
     *
     * @param midRequest order details to be persisted
     */
    public OrderHeader saveOrderDetails(MissingImageDeliveriesRequest midRequest) {
        Map<String, Object> logMap = LoggingUtils.createLoggingDataMap(COMPANY_NUMBER_LOG_KEY, midRequest.getCompanyNumber());

        OrderHeader orderHeader = createOrderHeader(midRequest);
        OrderDetails orderDetails = createOrderDetails(midRequest);
        orderHeader.setOrderDetails(Collections.singleton(orderDetails));

		LOGGER.info("Saving order details", logMap);

        try {
            if (!orderHeaderRepository.existsById(orderHeader.getPsNumber())) {
                return orderHeaderRepository.save(orderHeader);
            } else {
                final String messageError = "Duplicate Record";
                throw new DuplicateEntryException(messageError);
            }
        } catch (DataAccessException e) {
            final String messageError = "Unable to save Request";
            throw new OrderServiceException(messageError);
        }
	}

    private OrderHeader createOrderHeader(MissingImageDeliveriesRequest midRequest) {
        OrderHeader orderHeader = new OrderHeader();

        String paymentReference = midRequest.getPaymentReference();
        String barcode = midRequest.getFilingHistoryBarcode();
        String entityId = midRequest.getEntityID();
        String finalPaymentReference;

        if (entityId != null) {
            finalPaymentReference = paymentReference + entityId;
        } else if (barcode != null) {
            finalPaymentReference = paymentReference + "--" + barcode;
        } else {
            finalPaymentReference = paymentReference;
        }

        orderHeader.setNumOrderLines(NUM_ORDER_LINES);
        orderHeader.setCustomerVersion(getCustomerForOrder(midRequest.getEmail()).getCustomerVersion());
        orderHeader.setCustomerId(customerId);
        orderHeader.setPsNumber(midRequest.getId());
        orderHeader.setOrderValue(Long.parseLong(midRequest.getItemCost()));
        orderHeader.setReordered(REORDERED);
        orderHeader.setHandCsr(handcsr);
        orderHeader.setLanguage(language);
        orderHeader.setFlags(flags);
        orderHeader.setPaymentMethod(paymentMethod);
        orderHeader.setStatus(STATUS);
        orderHeader.setPaymentReference(finalPaymentReference);
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
        orderDetails.setDeliveryMethod(deliveryMethod);
        orderDetails.setDeliveryLocation(deliveryLocation);
        orderDetails.setItemCost(Long.parseLong(midRequest.getItemCost()));
        orderDetails.setQuantity(QUANTITY);
        orderDetails.setStatus(STATUS);

        return orderDetails;
    }
    
    private Customer getCustomerForOrder(String email) {
        Customer customer = customerRepository.findCustomerByCustomerIdAndEmail(customerId, email);
        if(customer == null) {
            customer = new Customer();
            customer.setCustomerId(customerId);
            customer.setForename(forename);
            customer.setSurname(surname);
            customer.setPremises(substring40Chars(email));
            customer.setAddrline1(substring40Chars(email));
            customer.setEmail(email);
            customerRepository.save(customer);
        }
        return customer;
    }
    
    /**
     * method to check if a string is 40 characters and return :
     * 1. null if the provided string is null, 
     * 2. the first 40 character if the string is longer than 40 characters
     * 3. the string is less than 40 characters
     * @param s
     * @return
     */
    private String substring40Chars(String s) {
        return (s == null)? s : (s.length() > 40)? s.substring(0, 40) : s;
    }
}
