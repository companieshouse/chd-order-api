package uk.gov.companieshouse.chd.order.api;

public enum RequiredEnvironmentVariables {

    CHPRD_DATASOURCE_URL("CHPRD_DATASOURCE_URL"),
    CHPRD_SCHEMA_NAME("CHPRD_SCHEMA_NAME"),
    CHPRD_PASSWORD("CHPRD_PASSWORD");

    private String name;

    RequiredEnvironmentVariables(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
