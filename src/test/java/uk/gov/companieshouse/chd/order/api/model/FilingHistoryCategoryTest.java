package uk.gov.companieshouse.chd.order.api.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

class FilingHistoryCategoryTest {
    private static final String ACCOUNTS = "accounts";
    private static final String[] ANNUAL_RETURN = {"annual-return", "confirmation-statement", "return"};
    private static final String[] APPOINTMENT = {"officers", "officer"};
    private static final String ADDRESS = "address";
    private static final String MORTGAGE = "mortgage";
    private static final String[] LIQUIDATION = {"insolvency", "liquidation"};
    private static final String INCORPORATION = "incorporation";
    private static final String CHANGE_OF_NAME = "change-of-name";
    private static final String CAPITAL = "capital";
    private static final String[] MISC = {"resolution", "miscellaneous", "auditors", "gazette", "other", "certificate",
            "dissolution", "reregistration", "historical", "restoration", "court-order", "social-landlord",
            "change-of-constitution", "document-replacement", "persons-with-significant-control", "annotation"};
    private static final String UNHANDLED_CATEGORY = "unhandled-category";

    @Test
    void returnsNullForIncorrectUnknownOrUnhandledCategory(){
        assertThat(FilingHistoryCategory.enumValueOf(UNHANDLED_CATEGORY).getProductCategory(), is(nullValue()));
    }

    @Test
    void mapsAccountsCategorySuccessfully(){
        assertThat(FilingHistoryCategory.enumValueOf(ACCOUNTS).getProductCategory(), is(ProductCategory.SCUD_ACC));
        assertThat(FilingHistoryCategory.enumValueOf(ACCOUNTS).getProductCode(), is(ProductCode.CODE_14010.getValue()));
        assertThat(FilingHistoryCategory.ACCOUNTS.toString(), is("accounts"));
    }

    @Test
    void mapsAnnualReturnsCategoriesSuccessfully(){
        Arrays.stream(ANNUAL_RETURN).forEach(c -> assertThat(FilingHistoryCategory.enumValueOf(c).getProductCategory(), is(ProductCategory.SCUD_363)));
        Arrays.stream(ANNUAL_RETURN).forEach(c -> assertThat(FilingHistoryCategory.enumValueOf(c).getProductCode(), is(ProductCode.CODE_14011.getValue())));
        assertThat(FilingHistoryCategory.ANNUAL_RETURN.toString(), is("annual-return"));
        assertThat(FilingHistoryCategory.CONFIRMATION_STATEMENT.toString(), is("confirmation-statement"));
        assertThat(FilingHistoryCategory.RETURN.toString(), is("return"));
    }

    @Test
    void mapsAppointmentCategoriesSuccessfully(){
        Arrays.stream(APPOINTMENT).forEach(c -> assertThat(FilingHistoryCategory.enumValueOf(c).getProductCategory(), is(ProductCategory.SCUD_288)));
        Arrays.stream(APPOINTMENT).forEach(c -> assertThat(FilingHistoryCategory.enumValueOf(c).getProductCode(), is(ProductCode.CODE_14012.getValue())));
        assertThat(FilingHistoryCategory.OFFICER.toString(), is("officer"));
        assertThat(FilingHistoryCategory.OFFICERS.toString(), is("officers"));
    }

    @Test
    void mapsAddressCategorySuccessfully(){
        assertThat(FilingHistoryCategory.enumValueOf(ADDRESS).getProductCategory(), is(ProductCategory.SCUD_287));
        assertThat(FilingHistoryCategory.enumValueOf(ADDRESS).getProductCode(), is(ProductCode.CODE_14013.getValue()));
        assertThat(FilingHistoryCategory.ADDRESS.toString(), is("address"));
    }

    @Test
    void mapsMortgageCategorySuccessfully(){
        assertThat(FilingHistoryCategory.enumValueOf(MORTGAGE).getProductCategory(), is(ProductCategory.SCUD_MORT));
        assertThat(FilingHistoryCategory.enumValueOf(MORTGAGE).getProductCode(), is(ProductCode.CODE_14014.getValue()));
        assertThat(FilingHistoryCategory.MORTGAGE.toString(), is("mortgage"));
    }

    @Test
    void mapsLiquidationCategoriesSuccessfully(){
        Arrays.stream(LIQUIDATION).forEach(c -> assertThat(FilingHistoryCategory.enumValueOf(c).getProductCategory(), is(ProductCategory.SCUD_LIQ)));
        Arrays.stream(LIQUIDATION).forEach(c -> assertThat(FilingHistoryCategory.enumValueOf(c).getProductCode(), is(ProductCode.CODE_14015.getValue())));
        assertThat(FilingHistoryCategory.LIQUIDATION.toString(), is("liquidation"));
        assertThat(FilingHistoryCategory.INSOLVENCY.toString(), is("insolvency"));
    }

    @Test
    void mapsIncorporationCategorySuccessfully(){
        assertThat(FilingHistoryCategory.enumValueOf(INCORPORATION).getProductCategory(), is(ProductCategory.SCUD_NEWC));
        assertThat(FilingHistoryCategory.enumValueOf(INCORPORATION).getProductCode(), is(ProductCode.CODE_14016.getValue()));
        assertThat(FilingHistoryCategory.INCORPORATION.toString(), is("incorporation"));
    }

    @Test
    void mapsChangeOfNameCategorySuccessfully(){
        assertThat(FilingHistoryCategory.enumValueOf(CHANGE_OF_NAME).getProductCategory(), is(ProductCategory.SCUD_CON));
        assertThat(FilingHistoryCategory.enumValueOf(CHANGE_OF_NAME).getProductCode(), is(ProductCode.CODE_14017.getValue()));
        assertThat(FilingHistoryCategory.CHANGE_OF_NAME.toString(), is("change-of-name"));
    }

    @Test
    void mapsCapitalCategorySuccessfully(){
        assertThat(FilingHistoryCategory.enumValueOf(CAPITAL).getProductCategory(), is(ProductCategory.SCUD_CAP));
        assertThat(FilingHistoryCategory.enumValueOf(CAPITAL).getProductCode(), is(ProductCode.CODE_14018.getValue()));
        assertThat(FilingHistoryCategory.CAPITAL.toString(), is("capital"));
    }

    @Test
    void mapsMiscellaneousCategoriesSuccessfully(){
        Arrays.stream(MISC).forEach(c -> assertThat(FilingHistoryCategory.enumValueOf(c).getProductCategory(), is(ProductCategory.SCUD_MISC)));
        Arrays.stream(MISC).forEach(c -> assertThat(FilingHistoryCategory.enumValueOf(c).getProductCode(), is(ProductCode.CODE_14019.getValue())));
        assertThat(FilingHistoryCategory.RESOLUTION.toString(), is("resolution"));
        assertThat(FilingHistoryCategory.MISCELLANEOUS.toString(), is("miscellaneous"));
        assertThat(FilingHistoryCategory.AUDITORS.toString(), is("auditors"));
        assertThat(FilingHistoryCategory.GAZETTE.toString(), is("gazette"));
        assertThat(FilingHistoryCategory.OTHER.toString(), is("other"));
        assertThat(FilingHistoryCategory.CERTIFICATE.toString(), is("certificate"));
        assertThat(FilingHistoryCategory.DISSOLUTION.toString(), is("dissolution"));
        assertThat(FilingHistoryCategory.REREGISTRATION.toString(), is("reregistration"));
        assertThat(FilingHistoryCategory.HISTORICAL.toString(), is("historical"));
        assertThat(FilingHistoryCategory.RESTORATION.toString(), is("restoration"));
        assertThat(FilingHistoryCategory.COURT_ORDER.toString(), is("court-order"));
        assertThat(FilingHistoryCategory.SOCIAL_LANDLORD.toString(), is("social-landlord"));
        assertThat(FilingHistoryCategory.CHANGE_OF_CONSTITUTION.toString(), is("change-of-constitution"));
        assertThat(FilingHistoryCategory.DOCUMENT_REPLACEMENT.toString(), is("document-replacement"));
        assertThat(FilingHistoryCategory.PERSONS_WITH_SIGNIFICANT_CONTROL.toString(), is("persons-with-significant-control"));
        assertThat(FilingHistoryCategory.ANNOTATION.toString(), is("annotation"));
    }
}
