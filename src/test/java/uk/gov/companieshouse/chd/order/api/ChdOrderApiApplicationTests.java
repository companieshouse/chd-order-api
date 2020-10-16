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
    private static final String CHPRD_DATASOURCE_URL = "CHPRD_DATASOURCE_URL";
    private static final String CHPRD_SCHEMA_NAME = "CHPRD_SCHEMA_NAME";
    private static final String CHPRD_PASSWORD = "CHPRD_PASSWORD";

    @Test
    void checkEnvironmentVariablesAllPresentReturnsTrue() {
        environmentVariables.set(CHPRD_DATASOURCE_URL, CHPRD_DATASOURCE_URL);
        environmentVariables.set(CHPRD_SCHEMA_NAME, CHPRD_SCHEMA_NAME);
        environmentVariables.set(CHPRD_PASSWORD, CHPRD_PASSWORD);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertTrue(isPresent);

        clearEnvironmentVariables();
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDDataSourceURLReturnsFalse() {
        environmentVariables.set(CHPRD_SCHEMA_NAME, CHPRD_SCHEMA_NAME);
        environmentVariables.set(CHPRD_PASSWORD, CHPRD_PASSWORD);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);

        clearEnvironmentVariables();
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDSchemaNameReturnsFalse() {
        environmentVariables.set(CHPRD_DATASOURCE_URL, CHPRD_DATASOURCE_URL);
        environmentVariables.set(CHPRD_PASSWORD, CHPRD_PASSWORD);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);

        clearEnvironmentVariables();
    }

    @Test
    void checkEnvironmentVariablesMissingCHPRDPasswordReturnsFalse() {
        environmentVariables.set(CHPRD_DATASOURCE_URL, CHPRD_DATASOURCE_URL);
        environmentVariables.set(CHPRD_SCHEMA_NAME, CHPRD_SCHEMA_NAME);

        boolean isPresent = ChdOrderApiApplication.checkEnvironmentVariables();
        assertFalse(isPresent);

        clearEnvironmentVariables();
    }

    @Test
    void contextLoads() {
    }

    private void clearEnvironmentVariables() {
        environmentVariables.clear(CHPRD_DATASOURCE_URL, CHPRD_SCHEMA_NAME, CHPRD_PASSWORD);
    }

}
