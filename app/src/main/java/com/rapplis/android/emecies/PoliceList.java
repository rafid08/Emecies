package com.rapplis.android.emecies;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rapplis.android.emecies.Data.DataContract;
import com.rapplis.android.emecies.Data.DatabaseHelper;

import java.util.ArrayList;

public class PoliceList extends AppCompatActivity {
    private static String title;
    private static byte[] icon;
    private static byte[] cover;
    private static String phone;
    private static String lat;
    private static String lon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                DataContract.DataEntry.COLUMN_NAME,
                DataContract.DataEntry.COLUMN_PHONE,
                DataContract.DataEntry.COLUMN_LATITUDE,
                DataContract.DataEntry.COLUMN_LONGITUDE,
                DataContract.DataEntry.COLUMN_PROFILE_IMAGE,
                DataContract.DataEntry.COLUMN_COVER_IMAGE};

        Cursor cursor = db.query(
                DataContract.DataEntry.POLICE_STATION_TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        final ArrayList<List> lists = new ArrayList<>();

        try {
            lists.clear();

            // Figure out the index of each column
            int nameColumnIndex = cursor.getColumnIndex(DataContract.DataEntry.COLUMN_NAME);
            int phoneColumnIndex = cursor.getColumnIndex(DataContract.DataEntry.COLUMN_PHONE);
            int latitudeColumnIndex = cursor.getColumnIndex(DataContract.DataEntry.COLUMN_LATITUDE);
            int longitudeColumnIndex = cursor.getColumnIndex(DataContract.DataEntry.COLUMN_LONGITUDE);
            int profileImageColumnIndex = cursor.getColumnIndex(DataContract.DataEntry.COLUMN_PROFILE_IMAGE);
            int coverImageColumnIndex = cursor.getColumnIndex(DataContract.DataEntry.COLUMN_COVER_IMAGE);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {

                lists.add(new List(cursor.getString(nameColumnIndex),cursor.getBlob(profileImageColumnIndex),
                        cursor.getBlob(coverImageColumnIndex), cursor.getString(phoneColumnIndex),
                        cursor.getString(latitudeColumnIndex), cursor.getString(longitudeColumnIndex)));
            }
        } finally {
            cursor.close();
        }

        LinearLayout header = (LinearLayout) findViewById(R.id.header);
        header.setVisibility(View.VISIBLE);

        ImageView headIcon = (ImageView) findViewById(R.id.header_icon);
        headIcon.setImageResource(R.drawable.police_icon);

        TextView headerTitle = (TextView) findViewById(R.id.header_title);
        headerTitle.setText("Police Stations inside Dhaka City");

        ListAdapter adapter = new ListAdapter(this, lists);

        GridView gridView = (GridView) findViewById(R.id.list);

        gridView.setNumColumns(1);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final List list = lists.get(position);
                title = list.getText();
                icon = list.getIcon();
                cover = list.getCover();
                phone = list.getPhone();
                lat = list.getLat();
                lon = list.getLon();

                Intent option = new Intent(view.getContext(), PoliceOption.class);
                startActivity(option);
            }
        });
    }
    public String title(){
        return title;
    }
    public byte[] icon(){
        return icon;
    }
    public byte[] setCover(){
        return cover;
    }
    public String setPhone(){
        return phone;
    }
    public String setLat(){
        return lat;
    }
    public String setLon(){
        return lon;
    }
}