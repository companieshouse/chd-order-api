package uk.gov.companieshouse.chd.order.api;

import org.junit.Rule;
import org.junit.contrib.java.lang.system.EnvironmentVariables;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ChdOrderApiApplicationTests {

    @Rule
    public EnvironmentVariables environmentVariables = new EnvironmentVariables();
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
        environmentVariables.clear(CHS_API_KEY);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDDataSourceURLReturnsFalse() {
        environmentVariables.clear(CHPRD_DATASOURCE_URL);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDSchemaNameReturnsFalse() {
        environmentVariables.clear(CHPRD_SCHEMA_NAME);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDPasswordReturnsFalse() {
        environmentVariables.clear(CHPRD_PASSWORD);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDCustomerIdReturnsFalse() {
        environmentVariables.clear(CHPRD_CUSTOMER_ID);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDPaymentMethodReturnsFalse() {
        environmentVariables.clear(CHPRD_PAYMENT_METHOD);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDHandCSRReturnsFalse() {
        environmentVariables.clear(CHPRD_HANDCSR);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDStatusReturnsFalse() {
        environmentVariables.clear(CHPRD_STATUS);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDFlagsReturnsFalse() {
        environmentVariables.clear(CHPRD_FLAGS);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDLanguageReturnsFalse() {
        environmentVariables.clear(CHPRD_LANGUAGE);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDDeliveryMethodReturnsFalse() {
        environmentVariables.clear(CHPRD_DELIVMETH);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDDeliveryLocationReturnsFalse() {
        environmentVariables.clear(CHPRD_DELIVLOCATION);

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
    }

    private void clearEnvironmentVariables() {
        environmentVariables.clear(CHS_API_KEY, CHPRD_DATASOURCE_URL, CHPRD_SCHEMA_NAME,
            CHPRD_PASSWORD, CHPRD_CUSTOMER_ID, CHPRD_PAYMENT_METHOD, CHPRD_HANDCSR, CHPRD_STATUS,
            CHPRD_FLAGS, CHPRD_LANGUAGE, CHPRD_DELIVMETH, CHPRD_DELIVLOCATION);
    }
}
