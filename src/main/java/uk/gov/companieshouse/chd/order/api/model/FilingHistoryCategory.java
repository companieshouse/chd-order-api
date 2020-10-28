package uk.gov.companieshouse.chd.order.api.model;

import uk.gov.companieshouse.chd.order.api.converter.EnumValueNameConverter;

public enum FilingHistoryCategory {
    ACCOUNTS(ProductCode.CODE_14010, ProductCategory.SCUD_ACC),
    ANNUAL_RETURN(ProductCode.CODE_14011, ProductCategory.SCUD_363),
    CONFIRMATION_STATEMENT(ProductCode.CODE_14011, ProductCategory.SCUD_363),
    RETURN(ProductCode.CODE_14011, ProductCategory.SCUD_363),
    OFFICERS(ProductCode.CODE_14012, ProductCategory.SCUD_288),
    OFFICER(ProductCode.CODE_14012, ProductCategory.SCUD_288),
    ADDRESS(ProductCode.CODE_14013, ProductCategory.SCUD_287),
    MORTGAGE(ProductCode.CODE_14014, ProductCategory.SCUD_MORT),
    INSOLVENCY(ProductCode.CODE_14015, ProductCategory.SCUD_LIQ),
    LIQUIDATION(ProductCode.CODE_14015, ProductCategory.SCUD_LIQ),
    INCORPORATION(ProductCode.CODE_14016, ProductCategory.SCUD_NEWC),
    CHANGE_OF_NAME(ProductCode.CODE_14017, ProductCategory.SCUD_CON),
    CAPITAL(ProductCode.CODE_14018, ProductCategory.SCUD_CAP),
    RESOLUTION(ProductCode.CODE_14019, ProductCategory.SCUD_MISC),
    MISCELLANEOUS(ProductCode.CODE_14019, ProductCategory.SCUD_MISC),
    AUDITORS(ProductCode.CODE_14019, ProductCategory.SCUD_MISC),
    GAZETTE(ProductCode.CODE_14019, ProductCategory.SCUD_MISC),
    OTHER(ProductCode.CODE_14019, ProductCategory.SCUD_MISC),
    CERTIFICATE(ProductCode.CODE_14019, ProductCategory.SCUD_MISC),
    DISSOLUTION(ProductCode.CODE_14019, ProductCategory.SCUD_MISC),
    REREGISTRATION(ProductCode.CODE_14019, ProductCategory.SCUD_MISC),
    HISTORICAL(ProductCode.CODE_14019, ProductCategory.SCUD_MISC),
    RESTORATION(ProductCode.CODE_14019, ProductCategory.SCUD_MISC),
    COURT_ORDER(ProductCode.CODE_14019, ProductCategory.SCUD_MISC),
    SOCIAL_LANDLORD(ProductCode.CODE_14019, ProductCategory.SCUD_MISC),
    CHANGE_OF_CONSTITUTION(ProductCode.CODE_14019, ProductCategory.SCUD_MISC),
    DOCUMENT_REPLACEMENT(ProductCode.CODE_14019, ProductCategory.SCUD_MISC),
    PERSONS_WITH_SIGNIFICANT_CONTROL(ProductCode.CODE_14019, ProductCategory.SCUD_MISC),
    ANNOTATION(ProductCode.CODE_14019, ProductCategory.SCUD_MISC),
    UNHANDLED_CATEGORY(null, null);

    private ProductCode productCode;
    private ProductCategory productCategory;

    private FilingHistoryCategory(ProductCode productCode, ProductCategory productCategory) {
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
        return this.productCode.getValue();
    }

    public ProductCategory getProductCategory() { return this.productCategory; }
}
