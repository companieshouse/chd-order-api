package uk.gov.companieshouse.chd.order.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.companieshouse.chd.order.api.dto.MissingImageDeliveriesDTO;
import uk.gov.companieshouse.chd.order.api.logging.LoggingUtils;
import uk.gov.companieshouse.chd.order.api.validator.CreateItemRequestValidator;
import uk.gov.companieshouse.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static uk.gov.companieshouse.chd.order.api.logging.LoggingUtils.COMPANY_NUMBER_LOG_KEY;
import static uk.gov.companieshouse.chd.order.api.logging.LoggingUtils.STATUS_LOG_KEY;
import static uk.gov.companieshouse.chd.order.api.logging.LoggingUtils.ERRORS_LOG_KEY;

@RestController
public class MissingImageDeliveriesController {

	private static final Logger LOGGER = LoggingUtils.getLogger();
	private final CreateItemRequestValidator createMissingImageDeliveryItemRequestValidator;

	/**
	 * Constructor.
	 */
	public MissingImageDeliveriesController(final CreateItemRequestValidator createMissingImageDeliveryItemRequestValidator) {
		this.createMissingImageDeliveryItemRequestValidator = createMissingImageDeliveryItemRequestValidator;
	}

	@PostMapping("${uk.gov.companieshouse.chd.order.api.mid}")
	public ResponseEntity<Object> createMissingImageDelivery(final @Valid @RequestBody MissingImageDeliveriesDTO midDTO,
			HttpServletRequest request) {
		Map<String, Object> logMap = LoggingUtils.createLoggingDataMap(COMPANY_NUMBER_LOG_KEY,
				midDTO.getCompanyNumber());

		LOGGER.infoRequest(request, "create mid item request", logMap);

		final List<String> errors = createMissingImageDeliveryItemRequestValidator.getValidationErrors(midDTO);
		if (!errors.isEmpty()) {
			logErrorsWithStatus(logMap, errors, BAD_REQUEST);
			LOGGER.errorRequest(request, "create missing image delivery item validation errors", logMap);;
			return ResponseEntity.status(BAD_REQUEST).body(new ApiError(BAD_REQUEST, errors));
		}

		logMap.put(STATUS_LOG_KEY, HttpStatus.CREATED);
		return ResponseEntity.status(HttpStatus.CREATED).body(midDTO);
	}

	/**
	 * method to add errors and a bad request status to a map for logging
	 * purposes
	 * @param logMap the map of logging data
	 * @param errors a list of errors
	 */
	private void logErrorsWithStatus(Map<String, Object> logMap,
									 final List<String> errors, HttpStatus status) {
		logMap.put(ERRORS_LOG_KEY, errors);
		logMap.put(STATUS_LOG_KEY, status);
	}
}
