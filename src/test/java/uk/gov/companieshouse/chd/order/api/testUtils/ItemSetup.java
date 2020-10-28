package uk.gov.companieshouse.chd.order.api.testUtils;

import uk.gov.companieshouse.chd.order.api.dto.MissingImageDeliveriesDTO;

import java.time.LocalDateTime;

public class ItemSetup {

    public static MissingImageDeliveriesDTO setUpMissingImageDeliveryDTO() {
        MissingImageDeliveriesDTO missingImageDeliveriesDTO = new MissingImageDeliveriesDTO();
        missingImageDeliveriesDTO.setCompanyName("Company Name");
        missingImageDeliveriesDTO.setCompanyNumber("123");
        missingImageDeliveriesDTO.setFilingHistoryCategory("Category");
        missingImageDeliveriesDTO.setFilingHistoryDate("25-10-2018");
        missingImageDeliveriesDTO.setFilingHistoryDescription("Test");
        missingImageDeliveriesDTO.setFilingHistoryType("filing history type");
        missingImageDeliveriesDTO.setId("1");
        missingImageDeliveriesDTO.setItemCost("Item cost");
        missingImageDeliveriesDTO.setOrderedAt(LocalDateTime.now());
        missingImageDeliveriesDTO.setPaymentReference("Payment reference");
        return missingImageDeliveriesDTO;
    };
}
