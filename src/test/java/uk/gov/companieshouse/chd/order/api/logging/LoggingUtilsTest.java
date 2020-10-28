package uk.gov.companieshouse.chd.order.api.logging;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.companieshouse.logging.Logger;

@ExtendWith(MockitoExtension.class)
public class LoggingUtilsTest {
    
    @Test
    @DisplayName("Calling getLogger should return a configured logger")
    public void getLoggerReturns() {
        Logger logger = LoggingUtils.getLogger();
        assertNotNull(logger);
    }
    
    @Test
    @DisplayName("Calling createLoggingDataMap should return a Hash map with one key-value mapped")
    public void createLoggingDataMapReturn() {
        String key = "key";
        String value = "value";
        Map<String, Object> logMap = LoggingUtils.createLoggingDataMap(key, value);

        assertNotNull(logMap);
        assertEquals(value, logMap.get(key));
    }
}
