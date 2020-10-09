package uk.gov.companieshouse.chd.order.api.logging;

import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

public class LoggingUtils {
    
    public static final String APPLICATION_NAMESPACE="chd-order-api";
    
    private static final Logger LOGGER = LoggerFactory.getLogger(APPLICATION_NAMESPACE);
    
    public LoggingUtils() { }
    
    public static Logger getLogger() {
        return LOGGER;
    }

}
