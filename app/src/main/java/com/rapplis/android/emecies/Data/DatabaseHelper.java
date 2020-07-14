package com.rapplis.android.emecies.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    /** Name of the database file */
    private static final String DATABASE_NAME = "data.db";

    private static final int DATABASE_VERSION = 1;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_CALL_CENTER_TABLE =  "CREATE TABLE " + DataContract.DataEntry.CALL_CENTER_TABLE_NAME + " ("
                + DataContract.DataEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DataContract.DataEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + DataContract.DataEntry.COLUMN_PHONE + " INTEGER NOT NULL, "
                + DataContract.DataEntry.COLUMN_PROFILE_IMAGE + " BLOB, "
                + DataContract.DataEntry.COLUMN_COVER_IMAGE + " BLOB );";

        String SQL_CREATE_AMBULANCE_TABLE =  "CREATE TABLE " + DataContract.DataEntry.AMBULANCE_TABLE_NAME + " ("
                + DataContract.DataEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DataContract.DataEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + DataContract.DataEntry.COLUMN_PHONE + " INTEGER NOT NULL, "
                + DataContract.DataEntry.COLUMN_LATITUDE + " TEXT, "
                + DataContract.DataEntry.COLUMN_LONGITUDE + " TEXT, "
                + DataContract.DataEntry.COLUMN_PROFILE_IMAGE + " BLOB, "
                + DataContract.DataEntry.COLUMN_COVER_IMAGE + " BLOB );";

        String SQL_CREATE_POLICE_STATION_TABLE =  "CREATE TABLE " + DataContract.DataEntry.POLICE_STATION_TABLE_NAME + " ("
                + DataContract.DataEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DataContract.DataEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + DataContract.DataEntry.COLUMN_PHONE + " INTEGER NOT NULL, "
                + DataContract.DataEntry.COLUMN_LATITUDE + " TEXT, "
                + DataContract.DataEntry.COLUMN_LONGITUDE + " TEXT, "
                + DataContract.DataEntry.COLUMN_PROFILE_IMAGE + " BLOB, "
                + DataContract.DataEntry.COLUMN_COVER_IMAGE + " BLOB );";

        String SQL_CREATE_FIRE_SERVICE_TABLE =  "CREATE TABLE " + DataContract.DataEntry.FIRE_SERVICE_TABLE_NAME + " ("
                + DataContract.DataEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DataContract.DataEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + DataContract.DataEntry.COLUMN_PHONE + " INTEGER NOT NULL, "
                + DataContract.DataEntry.COLUMN_LATITUDE + " TEXT, "
                + DataContract.DataEntry.COLUMN_LONGITUDE + " TEXT, "
                + DataContract.DataEntry.COLUMN_PROFILE_IMAGE + " BLOB, "
                + DataContract.DataEntry.COLUMN_COVER_IMAGE + " BLOB );";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_CALL_CENTER_TABLE);
        db.execSQL(SQL_CREATE_AMBULANCE_TABLE);
        db.execSQL(SQL_CREATE_POLICE_STATION_TABLE);
        db.execSQL(SQL_CREATE_FIRE_SERVICE_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}