package uk.gov.companieshouse.chd.order.api.model;

import uk.gov.companieshouse.chd.order.api.converter.EnumValueNameConverter;

public enum FilingHistoryCategory {
    ACCOUNTS(ProductCode.CODE_44010, ProductCategory.MID_ACC),
    ANNUAL_RETURN(ProductCode.CODE_44011, ProductCategory.MID_363),
    CONFIRMATION_STATEMENT(ProductCode.CODE_44011, ProductCategory.MID_363),
    RETURN(ProductCode.CODE_44011, ProductCategory.MID_363),
    OFFICERS(ProductCode.CODE_44012, ProductCategory.MID_288),
    OFFICER(ProductCode.CODE_44012, ProductCategory.MID_288),
    ADDRESS(ProductCode.CODE_44013, ProductCategory.MID_287),
    MORTGAGE(ProductCode.CODE_44014, ProductCategory.MID_MORT),
    INSOLVENCY(ProductCode.CODE_44015, ProductCategory.MID_LIQ),
    LIQUIDATION(ProductCode.CODE_44015, ProductCategory.MID_LIQ),
    INCORPORATION(ProductCode.CODE_44016, ProductCategory.MID_NEWC),
    CHANGE_OF_NAME(ProductCode.CODE_44017, ProductCategory.MID_CON),
    CAPITAL(ProductCode.CODE_44018, ProductCategory.MID_CAP),
    RESOLUTION(ProductCode.CODE_44019, ProductCategory.MID_MISC),
    MISCELLANEOUS(ProductCode.CODE_44019, ProductCategory.MID_MISC),
    AUDITORS(ProductCode.CODE_44019, ProductCategory.MID_MISC),
    GAZETTE(ProductCode.CODE_44019, ProductCategory.MID_MISC),
    OTHER(ProductCode.CODE_44019, ProductCategory.MID_MISC),
    CERTIFICATE(ProductCode.CODE_44019, ProductCategory.MID_MISC),
    DISSOLUTION(ProductCode.CODE_44019, ProductCategory.MID_MISC),
    REREGISTRATION(ProductCode.CODE_44019, ProductCategory.MID_MISC),
    HISTORICAL(ProductCode.CODE_44019, ProductCategory.MID_MISC),
    RESTORATION(ProductCode.CODE_44019, ProductCategory.MID_MISC),
    COURT_ORDER(ProductCode.CODE_44019, ProductCategory.MID_MISC),
    SOCIAL_LANDLORD(ProductCode.CODE_44019, ProductCategory.MID_MISC),
    CHANGE_OF_CONSTITUTION(ProductCode.CODE_44019, ProductCategory.MID_MISC),
    DOCUMENT_REPLACEMENT(ProductCode.CODE_44019, ProductCategory.MID_MISC),
    PERSONS_WITH_SIGNIFICANT_CONTROL(ProductCode.CODE_44019, ProductCategory.MID_MISC),
    ANNOTATION(ProductCode.CODE_44019, ProductCategory.MID_MISC),
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
