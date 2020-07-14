package com.rapplis.android.emecies.Data;

import android.provider.BaseColumns;

public class DataContract {
    private DataContract() {}

    /**
     * Inner class that defines constant values for the pets database table.
     * Each entry in the table represents a single pet.
     */
    public static final class DataEntry implements BaseColumns {

        /** Name of database table for pets */
        public final static String CALL_CENTER_TABLE_NAME = "call_center";
        public final static String AMBULANCE_TABLE_NAME = "ambulance";
        public final static String POLICE_STATION_TABLE_NAME = "police_station";
        public final static String FIRE_SERVICE_TABLE_NAME = "fire_service";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_NAME ="name";

        public final static String COLUMN_PHONE = "phone";

        public final static String COLUMN_LATITUDE = "latitude";

        public final static String COLUMN_LONGITUDE = "longitude";

        public final static String COLUMN_PROFILE_IMAGE = "profilePhotoUri";

        public final static String COLUMN_COVER_IMAGE = "coverPhotoUri";
    }
}
