package uk.gov.companieshouse.chd.order.api.model;

public enum ProductCode {
    CODE_14010("14010"),
    CODE_14011("14011"),
    CODE_14012("14012"),
    CODE_14013("14013"),
    CODE_14014("14014"),
    CODE_14015("14015"),
    CODE_14016("14016"),
    CODE_14017("14017"),
    CODE_14018("14018"),
    CODE_14019("14019");

    private String value;

    ProductCode(String v) {
        this.value = v;
    }

    public String getValue(){
        return this.value;
    }
}
