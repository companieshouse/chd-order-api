package uk.gov.companieshouse.chd.order.api.logging;

import static org.junit.Assert.assertNotNull;
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

}
