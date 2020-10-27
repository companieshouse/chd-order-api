package uk.gov.companieshouse.chd.order.api.model;

import uk.gov.companieshouse.chd.order.api.converter.EnumValueNameConverter;

public enum FilingHistoryCategory {
    ACCOUNTS("14010", ProductCategory.SCUD_ACC),
    ANNUAL_RETURN("14011", ProductCategory.SCUD_363),
    CONFIRMATION_STATEMENT("14011", ProductCategory.SCUD_363),
    RETURN("14011", ProductCategory.SCUD_363),
    OFFICERS("14012", ProductCategory.SCUD_288),
    OFFICER("14012", ProductCategory.SCUD_288),
    ADDRESS("14013", ProductCategory.SCUD_287),
    MORTGAGE("14014", ProductCategory.SCUD_MORT),
    INSOLVENCY("14015", ProductCategory.SCUD_LIQ),
    LIQUIDATION("14015", ProductCategory.SCUD_LIQ),
    INCORPORATION("14016", ProductCategory.SCUD_NEWC),
    CHANGE_OF_NAME("14017", ProductCategory.SCUD_CON),
    CAPITAL("14018", ProductCategory.SCUD_CAP),
    RESOLUTION("14019", ProductCategory.SCUD_MISC),
    MISCELLANEOUS("14019", ProductCategory.SCUD_MISC),
    AUDITORS("14019", ProductCategory.SCUD_MISC),
    GAZETTE("14019", ProductCategory.SCUD_MISC),
    OTHER("14019", ProductCategory.SCUD_MISC),
    CERTIFICATE("14019", ProductCategory.SCUD_MISC),
    DISSOLUTION("14019", ProductCategory.SCUD_MISC),
    REREGISTRATION("14019", ProductCategory.SCUD_MISC),
    HISTORICAL("14019", ProductCategory.SCUD_MISC),
    RESTORATION("14019", ProductCategory.SCUD_MISC),
    COURT_ORDER("14019", ProductCategory.SCUD_MISC),
    SOCIAL_LANDLORD("14019", ProductCategory.SCUD_MISC),
    CHANGE_OF_CONSTITUTION("14019", ProductCategory.SCUD_MISC),
    DOCUMENT_REPLACEMENT("14019", ProductCategory.SCUD_MISC),
    PERSONS_WITH_SIGNIFICANT_CONTROL("14019", ProductCategory.SCUD_MISC),
    ANNOTATION("14019", ProductCategory.SCUD_MISC),
    UNHANDLED_CATEGORY(null, null);

    private String productCode;
    private ProductCategory productCategory;

    private FilingHistoryCategory(String productCode, ProductCategory productCategory) {
        this.productCode = productCode;
        this.productCategory = productCategory;
    }

    public static FilingHistoryCategory enumValueOf(String v) {
        try {
            return FilingHistoryCategory.valueOf(EnumValueNameConverter.convertEnumValueJsonToName(v));
        } catch (IllegalArgumentException ex) {
            return UNHANDLED_CATEGORY;
        }
    }

    @Override
    public String toString() {
        return EnumValueNameConverter.convertEnumValueNameToJson(this);
    }

    public String getProductCode(){
        return this.productCode;
    }

    public ProductCategory getProductCategory() { return this.productCategory; }
}
