package uk.gov.companieshouse.chd.order.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import uk.gov.companieshouse.chd.order.api.dto.MissingImageDeliveriesDTO;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.gov.companieshouse.api.util.security.EricConstants.ERIC_IDENTITY;
import static uk.gov.companieshouse.api.util.security.EricConstants.ERIC_IDENTITY_TYPE;
import static uk.gov.companieshouse.api.util.security.SecurityConstants.API_KEY_IDENTITY_TYPE;
import static uk.gov.companieshouse.chd.order.api.util.TestConstants.ERIC_IDENTITY_VALUE;
import static uk.gov.companieshouse.chd.order.api.util.TestConstants.MISSING_IMAGE_DELIVERIES_URL;
import static uk.gov.companieshouse.chd.order.api.util.TestConstants.REQUEST_ID_VALUE;
import static uk.gov.companieshouse.chd.order.api.util.TestConstants.REQUEST_ID_HEADER_NAME;

@AutoConfigureMockMvc
@SpringBootTest
class MissingImageDeliveriesControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final MissingImageDeliveriesDTO MISSING_IMAGE_DELIVERIES_DTO;

    static {
        MISSING_IMAGE_DELIVERIES_DTO = new MissingImageDeliveriesDTO();
        MISSING_IMAGE_DELIVERIES_DTO.setCompanyName("Test");
        MISSING_IMAGE_DELIVERIES_DTO.setCompanyNumber("123");
        MISSING_IMAGE_DELIVERIES_DTO.setFilingHistoryType("TestType");
        MISSING_IMAGE_DELIVERIES_DTO.setFilingHistoryCategory("Test");
        MISSING_IMAGE_DELIVERIES_DTO.setFilingHistoryDate("25-10-2018");
        MISSING_IMAGE_DELIVERIES_DTO.setFilingHistoryDescription("Test");
        MISSING_IMAGE_DELIVERIES_DTO.setId("Test");
        MISSING_IMAGE_DELIVERIES_DTO.setItemCost("Test");
        MISSING_IMAGE_DELIVERIES_DTO.setOrderedAt(LocalDateTime.now());
        MISSING_IMAGE_DELIVERIES_DTO.setPaymentReference("Test");
    }

    @Test
    @DisplayName("Create Missing Image Delivery returns 201 on valid headers")
    void createMissingImageDeliverySucceedsOnValidHeaders() throws Exception {
        mockMvc.perform(post(MISSING_IMAGE_DELIVERIES_URL)
                .header(REQUEST_ID_HEADER_NAME, REQUEST_ID_VALUE)
                .header(ERIC_IDENTITY, ERIC_IDENTITY_VALUE)
                .header(ERIC_IDENTITY_TYPE, API_KEY_IDENTITY_TYPE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(MISSING_IMAGE_DELIVERIES_DTO)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Create Missing Image Delivery returns 403 on invalid ERIC-Identity-Type")
    void createMissingImageDeliveryFailsOnInvalidIdentityType() throws Exception {
        mockMvc.perform(post(MISSING_IMAGE_DELIVERIES_URL)
                .header(REQUEST_ID_HEADER_NAME, REQUEST_ID_VALUE)
                .header(ERIC_IDENTITY, ERIC_IDENTITY_VALUE)
                .header(ERIC_IDENTITY_TYPE, "invalid")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(MISSING_IMAGE_DELIVERIES_DTO)))
                .andExpect(status().isForbidden());
    }
}
