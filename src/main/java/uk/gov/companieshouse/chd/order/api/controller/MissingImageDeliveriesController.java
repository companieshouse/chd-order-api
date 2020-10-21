package uk.gov.companieshouse.chd.order.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.companieshouse.chd.order.api.dto.MissingImageDeliveriesDTO;
import uk.gov.companieshouse.chd.order.api.logging.LoggingUtils;
import uk.gov.companieshouse.logging.Logger;

@RestController
public class MissingImageDeliveriesController {

    private static final Logger LOGGER = LoggingUtils.getLogger();

    @PostMapping("${uk.gov.companieshouse.chdorderapi.mid}")
    public ResponseEntity<Object> createMissingImageDeliveries(
            @RequestBody MissingImageDeliveriesDTO missingImageDeliviesDto) {
        LOGGER.info("This has reached the createMissingImageDeliveries method");
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}
