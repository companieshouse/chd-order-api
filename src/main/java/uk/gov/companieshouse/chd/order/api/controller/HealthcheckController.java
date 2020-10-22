package uk.gov.companieshouse.chd.order.api.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Returns HTTP OK response to indicate a healthy service is running
 */
@RestController
public class HealthcheckController {
    @GetMapping("${uk.gov.companieshouse.chdorderapi.health}")
    public ResponseEntity<Void> getHealthCheck (){
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
