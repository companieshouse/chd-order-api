package uk.gov.companieshouse.chd.order.api.environment;

public enum RequiredEnvironmentVariables {

    CHS_API_KEY("CHS_API_KEY"),
    CHPRD_DATASOURCE_URL("CHPRD_DATASOURCE_URL"),
    CHPRD_SCHEMA_NAME("CHPRD_SCHEMA_NAME"),
    CHPRD_PASSWORD("CHPRD_PASSWORD"),
    CHPRD_CUSTOMER_ID("CHPRD_CUSTOMER_ID"),
    CHPRD_PAYMENT_METHOD("CHPRD_PAYMENT_METHOD"),
    CHPRD_HANDCSR("CHPRD_HANDCSR"),
    CHPRD_STATUS("CHPRD_STATUS"),
    CHPRD_FLAGS("CHPRD_FLAGS"),
    CHPRD_LANGUAGE("CHPRD_LANGUAGE"),
    CHPRD_DELIVMETH("CHPRD_DELIVMETH"),
    CHPRD_DELIVLOCATION("CHPRD_DELIVLOCATION"),
    CHPRD_FORENAME("CHPRD_FORENAME"),
    CHPRD_SURNAME("CHPRD_SURNAME");

    private String name;

    RequiredEnvironmentVariables(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
