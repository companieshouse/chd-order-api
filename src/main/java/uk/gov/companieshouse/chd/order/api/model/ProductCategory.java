package uk.gov.companieshouse.chd.order.api.model;

public enum ProductCategory {
    SCUD_ACC(256),
    SCUD_363(257),
    SCUD_288(258),
    SCUD_287(259),
    SCUD_MORT(260),
    SCUD_LIQ(261),
    SCUD_NEWC(262),
    SCUD_CON(263),
    SCUD_CAP(264),
    SCUD_MISC(265);

    private long value;

    private ProductCategory(long v) {
        this.value = v;
    }

    public long getValue() { return this.value; }
}
