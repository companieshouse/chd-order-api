package uk.gov.companieshouse.chd.order.api.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import uk.gov.companieshouse.chd.order.api.dto.MissingImageDeliveriesDTO;
import uk.gov.companieshouse.chd.order.api.model.MissingImageDeliveriesRequest;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(MissingImageDeliveriesRequestMapperTest.Config.class)
class MissingImageDeliveriesRequestMapperTest {
    @Configuration
    @ComponentScan(basePackageClasses = MissingImageDeliveriesRequestMapperTest.class)
    static class Config {
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

    @Autowired
    private MissingImageDeliveriesRequestMapper mapperUnderTest;

    @Test
    void successfullyMapsMissingImageDeliveriesDTOToMissingImageDeliveriesRequest() {
        final MissingImageDeliveriesDTO midDTO = new MissingImageDeliveriesDTO();
        midDTO.setId(ID);
        midDTO.setCompanyNumber(COMPANY_NUMBER);
        midDTO.setCompanyName(COMPANY_NAME);
        midDTO.setPaymentReference(PAYMENT_REFERENCE);
        midDTO.setOrderedAt(ORDERED_AT);
        midDTO.setItemCost(ITEM_COST);
        midDTO.setFilingHistoryCategory(FILING_HISTORY_CATEGORY);
        midDTO.setFilingHistoryDate(FILING_HISTORY_DATE);
        midDTO.setFilingHistoryDescription(FILING_HISTORY_DESCRIPTION);

        final MissingImageDeliveriesRequest midRequest = mapperUnderTest.mapMissingImageDeliveriesRequest(midDTO);

        assertThat(midRequest.getId(), is(ID));
        assertThat(midRequest.getCompanyNumber(), is(COMPANY_NUMBER));
        assertThat(midRequest.getCompanyName(), is(COMPANY_NAME));
        assertThat(midRequest.getPaymentReference(), is(PAYMENT_REFERENCE));
        assertThat(midRequest.getOrderedAt(), is(ORDERED_AT));
        assertThat(midRequest.getItemCost(), is(ITEM_COST));
        assertThat(midRequest.getFilingHistoryCategory(), is(FILING_HISTORY_CATEGORY));
        assertThat(midRequest.getFilingHistoryDate(), is(FILING_HISTORY_DATE));
        assertThat(midRequest.getFilingHistoryDescription(), is(FILING_HISTORY_DESCRIPTION));
    }
}
