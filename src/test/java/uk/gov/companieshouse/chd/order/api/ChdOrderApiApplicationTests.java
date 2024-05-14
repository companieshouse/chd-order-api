package uk.gov.companieshouse.chd.order.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import uk.org.webcompere.systemstubs.jupiter.SystemStub;
import uk.org.webcompere.systemstubs.environment.EnvironmentVariables;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SystemStubsExtension.class)
class ChdOrderApiApplicationTests {


    @SystemStub
    private EnvironmentVariables environmentVariables;
    private static final String CHS_API_KEY = "CHS_API_KEY";
    private static final String CHPRD_DATASOURCE_URL = "CHPRD_DATASOURCE_URL";
    private static final String CHPRD_SCHEMA_NAME = "CHPRD_SCHEMA_NAME";
    private static final String CHPRD_PASSWORD = "CHPRD_PASSWORD";
    private static final String CHPRD_CUSTOMER_ID = "CHPRD_CUSTOMER_ID";
    private static final String CHPRD_PAYMENT_METHOD = "CHPRD_PAYMENT_METHOD";
    private static final String CHPRD_HANDCSR = "CHPRD_HANDCSR";
    private static final String CHPRD_STATUS = "CHPRD_STATUS";
    private static final String CHPRD_FLAGS = "CHPRD_FLAGS";
    private static final String CHPRD_LANGUAGE = "CHPRD_LANGUAGE";
    private static final String CHPRD_DELIVMETH = "CHPRD_DELIVMETH";
    private static final String CHPRD_DELIVLOCATION = "CHPRD_DELIVLOCATION";
    private static final String CHPRD_FORENAME="CHPRD_FORENAME";
    private static final String CHPRD_SURNAME="CHPRD_SURNAME";

    @BeforeEach
    void init() {
        setAllEnvironmentVariables();
    }

    @AfterEach
    void clearTest() {
        clearEnvironmentVariables();
    }

    @Test
    void checkEnvironmentVariablesAllPresentReturnsTrue() {
        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertTrue(isPresent);
    }

    @Test
    void checkEnvironmentVariablesMissingCHSAPIKeyReturnsFalse() {
        environmentVariables.remove(CHS_API_KEY);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDDataSourceURLReturnsFalse() {
        environmentVariables.remove(CHPRD_DATASOURCE_URL);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDSchemaNameReturnsFalse() {
        environmentVariables.remove(CHPRD_SCHEMA_NAME);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDPasswordReturnsFalse() {
        environmentVariables.remove(CHPRD_PASSWORD);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDCustomerIdReturnsFalse() {
        environmentVariables.remove(CHPRD_CUSTOMER_ID);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDPaymentMethodReturnsFalse() {
        environmentVariables.remove(CHPRD_PAYMENT_METHOD);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDHandCSRReturnsFalse() {
        environmentVariables.remove(CHPRD_HANDCSR);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDStatusReturnsFalse() {
        environmentVariables.remove(CHPRD_STATUS);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDFlagsReturnsFalse() {
        environmentVariables.remove(CHPRD_FLAGS);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDLanguageReturnsFalse() {
        environmentVariables.remove(CHPRD_LANGUAGE);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDDeliveryMethodReturnsFalse() {
        environmentVariables.remove(CHPRD_DELIVMETH);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDDeliveryLocationReturnsFalse() {
        environmentVariables.remove(CHPRD_DELIVLOCATION);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);
    }
    
    @Test
    void checkEnvironmentVariablesMissingCHPRDForenameReturnsFalse() {
        environmentVariables.remove(CHPRD_FORENAME);
        
        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);
    }
    
    @Test
    void checkEnvironmentVariablesMissingCHPRDSurnameReturnsFalse() {
        environmentVariables.remove(CHPRD_SURNAME);
        
        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);
    }

    @Test
    void contextLoads() {
    }

    private void setAllEnvironmentVariables() {
        environmentVariables.set(CHS_API_KEY, CHS_API_KEY);
        environmentVariables.set(CHPRD_DATASOURCE_URL, CHPRD_DATASOURCE_URL);
        environmentVariables.set(CHPRD_SCHEMA_NAME, CHPRD_SCHEMA_NAME);
        environmentVariables.set(CHPRD_PASSWORD, CHPRD_PASSWORD);
        environmentVariables.set(CHPRD_CUSTOMER_ID, CHPRD_CUSTOMER_ID);
        environmentVariables.set(CHPRD_PAYMENT_METHOD, CHPRD_PAYMENT_METHOD);
        environmentVariables.set(CHPRD_HANDCSR, CHPRD_HANDCSR);
        environmentVariables.set(CHPRD_STATUS, CHPRD_STATUS);
        environmentVariables.set(CHPRD_FLAGS, CHPRD_FLAGS);
        environmentVariables.set(CHPRD_LANGUAGE, CHPRD_LANGUAGE);
        environmentVariables.set(CHPRD_DELIVMETH, CHPRD_DELIVMETH);
        environmentVariables.set(CHPRD_DELIVLOCATION, CHPRD_DELIVLOCATION);
        environmentVariables.set(CHPRD_FORENAME, CHPRD_FORENAME);
        environmentVariables.set(CHPRD_SURNAME, CHPRD_SURNAME);
    }

    private void clearEnvironmentVariables() {
        environmentVariables.remove(CHS_API_KEY);
        environmentVariables.remove(CHPRD_DATASOURCE_URL);
        environmentVariables.remove(CHPRD_SCHEMA_NAME);
        environmentVariables.remove(CHPRD_PASSWORD);
        environmentVariables.remove( CHPRD_CUSTOMER_ID);
        environmentVariables.remove(CHPRD_PAYMENT_METHOD);
        environmentVariables.remove(CHPRD_HANDCSR);
        environmentVariables.remove(CHPRD_STATUS);
        environmentVariables.remove(CHPRD_FLAGS);
        environmentVariables.remove(CHPRD_LANGUAGE);
        environmentVariables.remove(CHPRD_DELIVMETH);
        environmentVariables.remove(CHPRD_DELIVLOCATION);
        environmentVariables.remove(CHPRD_FORENAME);
        environmentVariables.remove(CHPRD_SURNAME);
    }
}
