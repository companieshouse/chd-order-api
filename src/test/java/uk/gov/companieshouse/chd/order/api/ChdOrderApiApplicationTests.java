package uk.gov.companieshouse.chd.order.api;

import org.junit.Rule;
import org.junit.contrib.java.lang.system.EnvironmentVariables;
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

    @Test
    void checkEnvironmentVariablesAllPresentReturnsTrue() {
        setAllEnvironmentVariables();

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertTrue(isPresent);

        clearEnvironmentVariables();
    }

    @Test
    void checkEnvironmentVariablesMissingCHSAPIKeyReturnsFalse() {
        setAllEnvironmentVariables();
        environmentVariables.clear(CHS_API_KEY);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);

        clearEnvironmentVariables();
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDDataSourceURLReturnsFalse() {
        setAllEnvironmentVariables();
        environmentVariables.clear(CHPRD_DATASOURCE_URL);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);

        clearEnvironmentVariables();
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDSchemaNameReturnsFalse() {
        setAllEnvironmentVariables();
        environmentVariables.clear(CHPRD_SCHEMA_NAME);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);

        clearEnvironmentVariables();
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDPasswordReturnsFalse() {
        setAllEnvironmentVariables();
        environmentVariables.clear(CHPRD_PASSWORD);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);

        clearEnvironmentVariables();
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDCustomerIdReturnsFalse() {
        setAllEnvironmentVariables();
        environmentVariables.clear(CHPRD_CUSTOMER_ID);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);

        clearEnvironmentVariables();
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDPaymentMethodReturnsFalse() {
        setAllEnvironmentVariables();
        environmentVariables.clear(CHPRD_PAYMENT_METHOD);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);

        clearEnvironmentVariables();
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDHandCSRReturnsFalse() {
        setAllEnvironmentVariables();
        environmentVariables.clear(CHPRD_HANDCSR);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);

        clearEnvironmentVariables();
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDStatusReturnsFalse() {
        setAllEnvironmentVariables();
        environmentVariables.clear(CHPRD_STATUS);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);

        clearEnvironmentVariables();
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDFlagsReturnsFalse() {
        setAllEnvironmentVariables();
        environmentVariables.clear(CHPRD_FLAGS);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);

        clearEnvironmentVariables();
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDLanguageReturnsFalse() {
        setAllEnvironmentVariables();
        environmentVariables.clear(CHPRD_LANGUAGE);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);

        clearEnvironmentVariables();
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDDeliveryMethodReturnsFalse() {
        setAllEnvironmentVariables();
        environmentVariables.clear(CHPRD_DELIVMETH);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);

        clearEnvironmentVariables();
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDDeliveryLocationReturnsFalse() {
        setAllEnvironmentVariables();
        environmentVariables.clear(CHPRD_DELIVLOCATION);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);

        clearEnvironmentVariables();
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
