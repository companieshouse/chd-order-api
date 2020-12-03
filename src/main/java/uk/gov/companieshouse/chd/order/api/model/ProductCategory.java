package uk.gov.companieshouse.chd.order.api.model;

public enum ProductCategory {
    MID_ACC(256),
    MID_363(257),
    MID_288(258),
    MID_287(259),
    MID_MORT(260),
    MID_LIQ(261),
    MID_NEWC(262),
    MID_CON(263),
    MID_CAP(264),
    MID_MISC(265);

    private long value;

    private ProductCategory(long v) {
        this.value = v;
    }

    public long getValue() { return this.value; }
}
