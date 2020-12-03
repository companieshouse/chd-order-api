package uk.gov.companieshouse.chd.order.api.model;

public enum ProductCode {
    CODE_44010("44010"),
    CODE_44011("44011"),
    CODE_44012("44012"),
    CODE_44013("44013"),
    CODE_44014("44014"),
    CODE_44015("44015"),
    CODE_44016("44016"),
    CODE_44017("44017"),
    CODE_44018("44018"),
    CODE_44019("44019");

    private String value;

    ProductCode(String v) {
        this.value = v;
    }

    public String getValue(){
        return this.value;
    }
}
