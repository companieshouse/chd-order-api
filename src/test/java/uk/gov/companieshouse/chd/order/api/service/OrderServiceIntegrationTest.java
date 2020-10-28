package uk.gov.companieshouse.chd.order.api.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.gov.companieshouse.chd.order.api.model.FilingHistoryCategory;
import uk.gov.companieshouse.chd.order.api.model.MissingImageDeliveriesRequest;
import uk.gov.companieshouse.chd.order.api.model.OrderDetails;
import uk.gov.companieshouse.chd.order.api.model.OrderHeader;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = "/application.properties")
public class OrderServiceIntegrationTest {
    @Configuration
    @EnableAutoConfiguration
    @EnableJpaRepositories(basePackages = "uk.gov.companieshouse.chd.order.api.repository")
    @ComponentScan("uk.gov.companieshouse.chd.order.api")
    static class Config {
        @Autowired
        private Environment env;

        @Bean
        public DataSource dataSource() {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
            dataSource.setUrl(env.getProperty("spring.datasource.url"));
            dataSource.setUsername(env.getProperty("spring.datasource.username"));
            dataSource.setPassword(env.getProperty("spring.datasource.password"));

            return dataSource;
        }
    }

    private static final String ID = "MID-462515-995726";
    private static final String COMPANY_NUMBER = "00006400";
    private static final String COMPANY_NAME = "THE GIRLS' DAY SCHOOL TRUST";
    private static final String PAYMENT_REFERENCE = "payment xyz";
    private static final LocalDateTime ORDERED_AT = LocalDateTime.parse("2020-10-26T10:20:46.058");
    private static final String ITEM_COST = "3";
    private static final String FILING_HISTORY_CATEGORY = "officer";
    private static final String FILING_HISTORY_DATE = "2010-02-12";
    private static final String FILING_HISTORY_DESCRIPTION = "change-person-director-company-with-change-date";
    private static final String FILING_HISTORY_TYPE = "Filing History Test Type";

    @Autowired
    private OrderService orderService;

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
    @Value("${chprd.delivery-method}")
    private long deliveryMethod;
    @Value("${chprd.delivery-location}")
    private long deliveryLocation;
    private static final String SUBKEY = "SCUD";

    @Test
    public void saveOrderDetailsPersistsOrderHeaderSuccessfully(){
        final MissingImageDeliveriesRequest midRequest = new MissingImageDeliveriesRequest();
        midRequest.setId(ID);
        midRequest.setCompanyNumber(COMPANY_NUMBER);
        midRequest.setCompanyName(COMPANY_NAME);
        midRequest.setPaymentReference(PAYMENT_REFERENCE);
        midRequest.setOrderedAt(ORDERED_AT);
        midRequest.setItemCost(ITEM_COST);
        midRequest.setFilingHistoryCategory(FILING_HISTORY_CATEGORY);
        midRequest.setFilingHistoryDate(FILING_HISTORY_DATE);
        midRequest.setFilingHistoryDescription(FILING_HISTORY_DESCRIPTION);
        midRequest.setFilingHistoryType(FILING_HISTORY_TYPE);

        OrderHeader orderHeader = orderService.saveOrderDetails(midRequest);
        OrderDetails orderDetails = orderHeader.getOrderDetails().iterator().next();
        assertThat(orderHeader.getCustomerVersion(), is(1l));
        assertThat(orderHeader.getPaymentReference(), is(PAYMENT_REFERENCE));
        assertThat(orderHeader.getOrderDateTime(), is(ORDERED_AT));
        assertThat(orderHeader.getCustomerId(), is(customerId));
        assertThat(orderHeader.getHandCsr(), is(handcsr));
        assertThat(orderHeader.getLanguage(), is(language));
        assertThat(orderHeader.getFlags(), is(flags));
        assertThat(orderHeader.getPaymentMethod(), is(paymentMethod));
        assertThat(orderHeader.getPsNumber(), is(ID));
        assertThat(orderDetails.getCompanyName(), is(COMPANY_NAME));
        assertThat(orderDetails.getCompanyNumber(), is(COMPANY_NUMBER));
        assertThat(orderDetails.getItemCost(), is(Long.parseLong(ITEM_COST)));
        assertThat(orderDetails.getProductCategory(), is(FilingHistoryCategory.enumValueOf(FILING_HISTORY_CATEGORY).getProductCategory().getValue()));
        assertThat(orderDetails.getProductCode(), is(FilingHistoryCategory.enumValueOf(FILING_HISTORY_CATEGORY).getProductCode()));
        assertThat(orderDetails.getDeliveryMethod(), is(deliveryMethod));
        assertThat(orderDetails.getDeliveryLocation(), is(deliveryLocation));
        assertThat(orderDetails.getProductDate(), is(LocalDate.parse(FILING_HISTORY_DATE)));
        assertThat(orderDetails.getProductKey(), is(FILING_HISTORY_TYPE));
        assertThat(orderDetails.getProductSubKey(), is(SUBKEY));
        assertThat(orderDetails.getPsNumber(), is(ID));
    }
}
