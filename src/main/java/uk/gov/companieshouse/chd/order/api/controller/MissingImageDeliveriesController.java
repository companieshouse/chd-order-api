package uk.gov.companieshouse.chd.order.api.controller;

import static uk.gov.companieshouse.chd.order.api.logging.LoggingUtils.COMPANY_NUMBER_LOG_KEY;
import static uk.gov.companieshouse.chd.order.api.logging.LoggingUtils.STATUS_LOG_KEY;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uk.gov.companieshouse.chd.order.api.dto.MissingImageDeliveriesDTO;
import uk.gov.companieshouse.chd.order.api.logging.LoggingUtils;
import uk.gov.companieshouse.chd.order.api.mapper.MissingImageDeliveriesRequestMapper;
import uk.gov.companieshouse.chd.order.api.model.MissingImageDeliveriesRequest;
import uk.gov.companieshouse.chd.order.api.service.OrderService;
import uk.gov.companieshouse.logging.Logger;

@RestController
public class MissingImageDeliveriesController {

	private static final Logger LOGGER = LoggingUtils.getLogger();
	private final MissingImageDeliveriesRequestMapper mapper;
	private final OrderService orderService;

	/**
	 * Constructor.
	 */
	public MissingImageDeliveriesController(OrderService orderService, MissingImageDeliveriesRequestMapper mapper) {
		this.orderService = orderService;
		this.mapper = mapper;
	}

    @PostMapping("${uk.gov.companieshouse.chd.order.api.mid}")
    public ResponseEntity<MissingImageDeliveriesDTO>
		createMissingImageDelivery(final @Valid @RequestBody MissingImageDeliveriesDTO midDTO,
            HttpServletRequest request) {
		Map<String, Object> logMap = LoggingUtils.createLoggingDataMap(COMPANY_NUMBER_LOG_KEY, midDTO.getCompanyNumber());

		LOGGER.infoRequest(request, "create mid item request", logMap);
		logMap.put(STATUS_LOG_KEY, HttpStatus.CREATED);

		MissingImageDeliveriesRequest midRequest = mapper.mapMissingImageDeliveriesRequest(midDTO);
		orderService.saveOrderDetails(midRequest);
		LOGGER.infoRequest(request, "order details saved", logMap);

		return ResponseEntity.status(HttpStatus.CREATED).body(midDTO);
    }
}
