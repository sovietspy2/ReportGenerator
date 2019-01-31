package hu.wortex.report.helpers;

public enum Field {
        MARKETPLACE ("marketplace"),
        ID ("id"),
        CURRENCY ("currency"),
        DESCRIPTION ("description"),
        LISTING_PRICE ("listing_price"),
        LISTING_OWNER_EMAIL ("owner_email_address"),
        UPLOAD_TIME ("upload_time"),
        LISTING_STATUS ("listing_status"),
        LOCATION_ID ("location_id"),
        QUANTITY ("quantity"),
        TITLE ("title");


        private final String name;

        Field(String field) {
            this.name = field;
        }

        public String getFieldName() {
            return this.name;
        }

//    @Override
//    public String toString() {
//        return this.name;
//    }
}
