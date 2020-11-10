package uk.gov.companieshouse.chd.order.api.testUtils;

import uk.gov.companieshouse.chd.order.api.dto.MissingImageDeliveriesDTO;
import uk.gov.companieshouse.chd.order.api.model.MissingImageDeliveriesRequest;

import java.time.LocalDateTime;

public class ItemSetup {

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

    public static MissingImageDeliveriesDTO setUpMissingImageDeliveryDTO() {
        MissingImageDeliveriesDTO missingImageDeliveriesDTO = new MissingImageDeliveriesDTO();
        missingImageDeliveriesDTO.setCompanyName(COMPANY_NAME);
        missingImageDeliveriesDTO.setCompanyNumber(COMPANY_NUMBER);
        missingImageDeliveriesDTO.setFilingHistoryCategory(FILING_HISTORY_CATEGORY);
        missingImageDeliveriesDTO.setFilingHistoryDate(FILING_HISTORY_DATE);
        missingImageDeliveriesDTO.setFilingHistoryDescription(FILING_HISTORY_DESCRIPTION);
        missingImageDeliveriesDTO.setFilingHistoryType(FILING_HISTORY_TYPE);
        missingImageDeliveriesDTO.setId(ID);
        missingImageDeliveriesDTO.setItemCost(ITEM_COST);
        missingImageDeliveriesDTO.setOrderedAt(LocalDateTime.now());
        missingImageDeliveriesDTO.setPaymentReference(PAYMENT_REFERENCE);
        return missingImageDeliveriesDTO;
    };

    public static MissingImageDeliveriesRequest setUpMissingImageDeliveriesRequest() {
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
        midRequest.setFilingHistoryBarcode(null);
        midRequest.setEntityID(null);
        return midRequest;
    };
}
