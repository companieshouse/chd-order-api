package uk.gov.companieshouse.chd.order.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import uk.gov.companieshouse.chd.order.api.dto.MissingImageDeliveriesDTO;
import uk.gov.companieshouse.chd.order.api.service.OrderService;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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

    @MockBean
    private OrderService orderService;

    @Test
    @DisplayName("Create Missing Image Delivery returns 201 on valid headers")
    void createMissingImageDeliverySucceedsOnValidHeaders() throws Exception {

        MissingImageDeliveriesDTO missingImageDeliveriesDTO = missingImageDeliveriesDTO();
        missingImageDeliveriesDTO.setCompanyName("Test");
        String content = objectMapper.writeValueAsString(missingImageDeliveriesDTO);

        mockMvc.perform(post(MISSING_IMAGE_DELIVERIES_URL)
                .header(REQUEST_ID_HEADER_NAME, REQUEST_ID_VALUE)
                .header(ERIC_IDENTITY, ERIC_IDENTITY_VALUE)
                .header(ERIC_IDENTITY_TYPE, API_KEY_IDENTITY_TYPE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isCreated())
                .andExpect(content().json(content, true));
    }

    @Test
    @DisplayName("Create Missing Image Delivery returns 415 on invalid headers")
    void createMissingImageDeliveryFailsOnInvalidHeader() throws Exception {
        mockMvc.perform(post(MISSING_IMAGE_DELIVERIES_URL)
                .header(REQUEST_ID_HEADER_NAME, REQUEST_ID_VALUE)
                .header(ERIC_IDENTITY, ERIC_IDENTITY_VALUE)
                .header(ERIC_IDENTITY_TYPE, API_KEY_IDENTITY_TYPE)
                .content(objectMapper.writeValueAsString(missingImageDeliveriesDTO())))
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    @DisplayName("Create Missing Image Delivery returns 400 on invalid headers")
    void createMissingImageDeliveryFailsOnMissingField() throws Exception {
        mockMvc.perform(post(MISSING_IMAGE_DELIVERIES_URL)
                        .header(REQUEST_ID_HEADER_NAME, REQUEST_ID_VALUE)
                        .header(ERIC_IDENTITY, ERIC_IDENTITY_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(ERIC_IDENTITY_TYPE, API_KEY_IDENTITY_TYPE)
                        .content(objectMapper.writeValueAsString(missingImageDeliveriesDTO())))
                .andExpect(status().isBadRequest());
    }


    private MissingImageDeliveriesDTO missingImageDeliveriesDTO(){
        MissingImageDeliveriesDTO  missingImageDeliveriesDTO = new MissingImageDeliveriesDTO();
        missingImageDeliveriesDTO.setCompanyNumber("123");
        missingImageDeliveriesDTO.setFilingHistoryType("TestType");
        missingImageDeliveriesDTO.setFilingHistoryCategory("Test");
        missingImageDeliveriesDTO.setFilingHistoryDate("25-10-2018");
        missingImageDeliveriesDTO.setFilingHistoryDescription("Test");
        missingImageDeliveriesDTO.setFilingHistoryBarcode("111111");
        missingImageDeliveriesDTO.setEntityID("222222");
        missingImageDeliveriesDTO.setId("Test");
        missingImageDeliveriesDTO.setItemCost("Test");
        missingImageDeliveriesDTO.setOrderedAt(LocalDateTime.now());
        missingImageDeliveriesDTO.setPaymentReference("Test");
        return missingImageDeliveriesDTO;
    }

}
