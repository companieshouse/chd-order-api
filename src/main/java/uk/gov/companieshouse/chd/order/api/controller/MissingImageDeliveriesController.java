package uk.gov.companieshouse.chd.order.api.controller;

import org.hibernate.exception.JDBCConnectionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.companieshouse.chd.order.api.dto.MissingImageDeliveriesDTO;
import uk.gov.companieshouse.chd.order.api.exception.OrderServiceException;
import uk.gov.companieshouse.chd.order.api.logging.LoggingUtils;
import uk.gov.companieshouse.chd.order.api.mapper.MissingImageDeliveriesRequestMapper;
import uk.gov.companieshouse.chd.order.api.model.MissingImageDeliveriesRequest;
import uk.gov.companieshouse.chd.order.api.service.OrderService;
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
    private final MissingImageDeliveriesRequestMapper mapper;
    private final OrderService orderService;
    private final CreateItemRequestValidator createMissingImageDeliveryItemRequestValidator;

    /**
     * Constructor.
     */
    public MissingImageDeliveriesController(final OrderService orderService, final MissingImageDeliveriesRequestMapper mapper,
        final CreateItemRequestValidator createMissingImageDeliveryItemRequestValidator) {
        this.orderService = orderService;
        this.mapper = mapper;
        this.createMissingImageDeliveryItemRequestValidator = createMissingImageDeliveryItemRequestValidator;
    }

    @PostMapping("${uk.gov.companieshouse.chd.order.api.mid}")
    public ResponseEntity<Object> createMissingImageDelivery(final @Valid @RequestBody MissingImageDeliveriesDTO midDTO,
                                                             HttpServletRequest request) throws OrderServiceException {
        Map<String, Object> logMap = LoggingUtils.createLoggingDataMap(COMPANY_NUMBER_LOG_KEY,
            midDTO.getCompanyNumber());

        LOGGER.infoRequest(request, "create mid item request", logMap);

        final List<String> errors = createMissingImageDeliveryItemRequestValidator.getValidationErrors(midDTO);
        if (!errors.isEmpty()) {
            logMap.put(ERRORS_LOG_KEY, errors);
            logMap.put(STATUS_LOG_KEY, BAD_REQUEST);
            LOGGER.errorRequest(request, "create missing image delivery item validation errors", logMap);
            return ResponseEntity.status(BAD_REQUEST).body(new ApiError(BAD_REQUEST, errors));
        }

        MissingImageDeliveriesRequest midRequest = mapper.mapMissingImageDeliveriesRequest(midDTO);

		try {
			orderService.saveOrderDetails(midRequest);

			// The duplicate attempt to insert a same record trigger a NullPointerException
			// for some reason. TBT!! 
		} catch (JDBCConnectionException | NullPointerException e) {
			LOGGER.error("Database Exception on Creating MID.", e);
			final String messageError = "Unable to insert Record";
			// Instead of throwing error we should response with a different 2** httpstatus
			// to avoid the infinite attempt on retry to save it.
			throw new OrderServiceException(messageError);

		} catch (Exception e) {
			LOGGER.error("Data format Exception on Creating MID.", e);
			throw (e);
		}

        logMap.put(STATUS_LOG_KEY, HttpStatus.CREATED);

        return ResponseEntity.status(HttpStatus.CREATED).body(midDTO);
    }
}
