package uk.gov.companieshouse.chd.order.api.logging;

import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LoggingUtils {

	public static final String COMPANY_NUMBER_LOG_KEY = "company_number";
	public static final String STATUS_LOG_KEY = "status";
	public static final String ERRORS_LOG_KEY = "errors";

	private static final String APPLICATION_NAMESPACE = "chd-order-api";

	private static final Logger LOGGER = LoggerFactory.getLogger(APPLICATION_NAMESPACE);

	public LoggingUtils() {
	}

	public static Logger getLogger() {
		return LOGGER;
	}

	/**
	 * method to set up a map for logging purposes and add a value for the key
	 * 
	 * @param key
	 * @param value
	 * @return the Hash map with one key-value mapped
	 */
	public static Map<String, Object> createLoggingDataMap(final String key, final String value) {
		Map<String, Object> logMap = new HashMap<>();
		logMap.put(key, value);

		return logMap;
	}
}
