package com.rapplis.android.emecies;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.rapplis.android.emecies.Data.Data;
import com.rapplis.android.emecies.Data.DataContract;
import com.rapplis.android.emecies.Data.DatabaseHelper;

public class UpdateDataActivity extends AppCompatActivity {

    private final DatabaseHelper dbHelper = new DatabaseHelper(this);

    FirebaseStorage storage;
    FirebaseDatabase database;

    DatabaseReference ambulanceRef;
    DatabaseReference callCenterRef;
    DatabaseReference policeRef;
    DatabaseReference fireServiceRef;

    StorageReference ambulanceStorageRef;

    ProgressBar ambulanceProgressBar;
    ProgressBar fireProgressBar;
    ProgressBar policeProgressBar;
    ProgressBar callProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        ambulanceRef = database.getReference().child("ambulance");
        callCenterRef = database.getReference().child("call_center");
        policeRef = database.getReference().child("police_station");
        fireServiceRef = database.getReference().child("fire_service");
        ambulanceStorageRef = storage.getReference().child("ambulance");

        ambulanceProgressBar = findViewById(R.id.ambulance_progress);
        fireProgressBar = findViewById(R.id.fire_progress);
        policeProgressBar = findViewById(R.id.police_progress);
        callProgressBar = findViewById(R.id.call_progress);

        checkNetworkConnection();

        ambulanceRef.addValueEventListener(new ValueEventListener() {
            int progress = 1;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("DELETE FROM "+ DataContract.DataEntry.AMBULANCE_TABLE_NAME);
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    try{
                        if(postSnapshot.exists()){
                            ambulanceProgressBar.setMax((int) dataSnapshot.getChildrenCount());
                            final Data data = postSnapshot.getValue(Data.class);

                            data.getName();
                            StorageReference profileUri = storage.getReferenceFromUrl(data.getProfilePhotoUri());
                            final StorageReference coverUri = storage.getReferenceFromUrl(data.getCoverPhotoUri());
                            final long SIZE = 1024 * 1024 * 5;

                            profileUri.getBytes(SIZE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                @Override
                                public void onSuccess(byte[] profile) {
                                    final byte[] profilePhoto = profile;

                                    coverUri.getBytes(SIZE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                        @Override
                                        public void onSuccess(byte[] cover) {
                                            byte[] coverPhoto = cover;

                                            ContentValues values = new ContentValues();
                                            values.put(DataContract.DataEntry.COLUMN_NAME, data.getName());
                                            values.put(DataContract.DataEntry.COLUMN_PHONE, data.getPhone());
                                            values.put(DataContract.DataEntry.COLUMN_LATITUDE, data.getLatitude());
                                            values.put(DataContract.DataEntry.COLUMN_LONGITUDE, data.getLongitude());
                                            values.put(DataContract.DataEntry.COLUMN_PROFILE_IMAGE, profilePhoto);
                                            values.put(DataContract.DataEntry.COLUMN_COVER_IMAGE, coverPhoto);

                                            if (!checkDuplicate(data.getName(), data.getPhone(), data.getLatitude(),
                                                    data.getLongitude(), data.getProfilePhotoUri(), data.getCoverPhotoUri(),
                                                    DataContract.DataEntry.AMBULANCE_TABLE_NAME)){
                                                db.insert(DataContract.DataEntry.AMBULANCE_TABLE_NAME, null, values);
                                                progress++;
                                                ambulanceProgressBar.setProgress(progress);
                                                if(ambulanceProgressBar.getProgress()==ambulanceProgressBar.getMax()){
                                                    Intent i = new Intent(getApplicationContext(), Home.class);
                                                    startActivity(i);
                                                    finish();
                                                }
                                            }
                                        }
                                    });
                                }
                            });
                        }

                    } catch (NullPointerException e){
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        policeRef.addValueEventListener(new ValueEventListener() {
            int progress = 1;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("DELETE FROM "+ DataContract.DataEntry.POLICE_STATION_TABLE_NAME);
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    try{
                        if(postSnapshot.exists()){
                            policeProgressBar.setMax((int) dataSnapshot.getChildrenCount());
                            final Data data = postSnapshot.getValue(Data.class);

                            data.getName();
                            StorageReference profileUri = storage.getReferenceFromUrl(data.getProfilePhotoUri());
                            final StorageReference coverUri = storage.getReferenceFromUrl(data.getCoverPhotoUri());
                            final long SIZE = 1024 * 1024 * 5;

                            profileUri.getBytes(SIZE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                @Override
                                public void onSuccess(byte[] profile) {
                                    final byte[] profilePhoto = profile;

                                    coverUri.getBytes(SIZE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                        @Override
                                        public void onSuccess(byte[] cover) {

                                            ContentValues values = new ContentValues();
                                            values.put(DataContract.DataEntry.COLUMN_NAME, data.getName());
                                            values.put(DataContract.DataEntry.COLUMN_PHONE, data.getPhone());
                                            values.put(DataContract.DataEntry.COLUMN_LATITUDE, data.getLatitude());
                                            values.put(DataContract.DataEntry.COLUMN_LONGITUDE, data.getLongitude());
                                            values.put(DataContract.DataEntry.COLUMN_PROFILE_IMAGE, profilePhoto);
                                            values.put(DataContract.DataEntry.COLUMN_COVER_IMAGE, cover);

                                            if (!checkDuplicate(data.getName(), data.getPhone(),  data.getLatitude(),
                                                    data.getLongitude(), data.getProfilePhotoUri(), data.getCoverPhotoUri(),
                                                    DataContract.DataEntry.POLICE_STATION_TABLE_NAME)){
                                                db.insert(DataContract.DataEntry.POLICE_STATION_TABLE_NAME, null, values);
                                                progress++;
                                                policeProgressBar.setProgress(progress);
                                            }
                                        }
                                    });
                                }
                            });
                        }

                    } catch (NullPointerException e){
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        callCenterRef.addValueEventListener(new ValueEventListener() {
            int progress = 1;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("DELETE FROM "+ DataContract.DataEntry.CALL_CENTER_TABLE_NAME);
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    try{
                        if(postSnapshot.exists()){
                            callProgressBar.setMax((int) dataSnapshot.getChildrenCount());
                            final Data data = postSnapshot.getValue(Data.class);

                            data.getName();
                            StorageReference profileUri = storage.getReferenceFromUrl(data.getProfilePhotoUri());
                            final StorageReference coverUri = storage.getReferenceFromUrl(data.getCoverPhotoUri());
                            final long SIZE = 1024 * 1024 * 5;

                            profileUri.getBytes(SIZE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                @Override
                                public void onSuccess(byte[] profile) {
                                    final byte[] profilePhoto = profile;

                                    coverUri.getBytes(SIZE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                        @Override
                                        public void onSuccess(byte[] cover) {

                                            ContentValues values = new ContentValues();
                                            values.put(DataContract.DataEntry.COLUMN_NAME, data.getName());
                                            values.put(DataContract.DataEntry.COLUMN_PHONE, data.getPhone());
                                            values.put(DataContract.DataEntry.COLUMN_PROFILE_IMAGE, profilePhoto);
                                            values.put(DataContract.DataEntry.COLUMN_COVER_IMAGE, cover);

                                            if (!checkDuplicate(data.getName(), data.getPhone(), null,
                                                    null, data.getProfilePhotoUri(), data.getCoverPhotoUri(),
                                                    DataContract.DataEntry.CALL_CENTER_TABLE_NAME)){
                                                db.insert(DataContract.DataEntry.CALL_CENTER_TABLE_NAME, null, values);
                                                progress++;
                                                callProgressBar.setProgress(progress);
                                            }
                                        }
                                    });
                                }
                            });
                        }

                    } catch (NullPointerException e){
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        fireServiceRef.addValueEventListener(new ValueEventListener() {
            int progress = 1;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("DELETE FROM "+ DataContract.DataEntry.FIRE_SERVICE_TABLE_NAME);
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    try{
                        if(postSnapshot.exists()){
                            fireProgressBar.setMax((int) dataSnapshot.getChildrenCount());
                            final Data data = postSnapshot.getValue(Data.class);

                            data.getName();
                            StorageReference profileUri = storage.getReferenceFromUrl(data.getProfilePhotoUri());
                            final StorageReference coverUri = storage.getReferenceFromUrl(data.getCoverPhotoUri());
                            final long SIZE = 1024 * 1024 * 5;

                            profileUri.getBytes(SIZE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                @Override
                                public void onSuccess(byte[] profile) {
                                    final byte[] profilePhoto = profile;

                                    coverUri.getBytes(SIZE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                        @Override
                                        public void onSuccess(byte[] cover) {

                                            ContentValues values = new ContentValues();
                                            values.put(DataContract.DataEntry.COLUMN_NAME, data.getName());
                                            values.put(DataContract.DataEntry.COLUMN_PHONE, data.getPhone());
                                            values.put(DataContract.DataEntry.COLUMN_LATITUDE, data.getLatitude());
                                            values.put(DataContract.DataEntry.COLUMN_LONGITUDE, data.getLongitude());
                                            values.put(DataContract.DataEntry.COLUMN_PROFILE_IMAGE, profilePhoto);
                                            values.put(DataContract.DataEntry.COLUMN_COVER_IMAGE, cover);

                                            if (!checkDuplicate(data.getName(), data.getPhone(), data.getLatitude(),
                                                    data.getLongitude(), data.getProfilePhotoUri(), data.getCoverPhotoUri(),
                                                    DataContract.DataEntry.FIRE_SERVICE_TABLE_NAME)){
                                                db.insert(DataContract.DataEntry.FIRE_SERVICE_TABLE_NAME, null, values);
                                                progress++;
                                                fireProgressBar.setProgress(progress);
                                            }
                                        }
                                    });
                                }
                            });
                        }

                        if(ambulanceProgressBar.getProgress()==ambulanceProgressBar.getMax() &&
                                policeProgressBar.getProgress()==policeProgressBar.getMax() &&
                                fireProgressBar.getProgress()==fireProgressBar.getMax() &&
                                callProgressBar.getProgress()==callProgressBar.getMax()){
                            finish();
                        }

                    } catch (NullPointerException e){
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new AlertDialog.Builder(getApplicationContext())
                .setTitle("Are sure you want to exit?")
                .setMessage("Please wait until the one time download is done. The app will be closed if you exit")
                .setPositiveButton("wait", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setNegativeButton("exit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void checkNetworkConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        assert cm != null;
        boolean connected = cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
        if(!connected){
            new AlertDialog.Builder(UpdateDataActivity.this)
                    .setTitle("No Internet Access")
                    .setMessage("This one time download requires internet connection. Please connect to the Internet")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            checkNetworkConnection();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }


    public boolean checkDuplicate(String name, String phone, String latitude, String longitude, String profileImageUri,
                                  String coverImageUri, String tableName){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        if(latitude==null && longitude==null){
            Cursor cur = db.query(tableName, null,
                    DataContract.DataEntry.COLUMN_NAME +" = ? AND "+ DataContract.DataEntry.COLUMN_PHONE +" = ? AND "+
                            DataContract.DataEntry.COLUMN_PROFILE_IMAGE +" = ? AND "+DataContract.DataEntry.COLUMN_COVER_IMAGE +" = ? ",
                    new String[] {name, phone, profileImageUri, coverImageUri},
                    null, null, null, null);
            if (cur != null && cur.getCount()>0) {
                cur.close();
                return true;
            }
        } else if(latitude!=null && longitude!=null){
            Cursor cur = db.query(tableName, null,
                    DataContract.DataEntry.COLUMN_NAME +" = ? AND "+ DataContract.DataEntry.COLUMN_PHONE +" = ? AND "+
                            DataContract.DataEntry.COLUMN_LATITUDE +" = ? AND "+DataContract.DataEntry.COLUMN_LONGITUDE +" = ? AND "+
                            DataContract.DataEntry.COLUMN_PROFILE_IMAGE +" = ? AND "+DataContract.DataEntry.COLUMN_COVER_IMAGE +" = ? ",
                    new String[] {name, phone, latitude, longitude, profileImageUri, coverImageUri},
                    null, null, null, null);
            if (cur != null && cur.getCount()>0) {
                cur.close();
                return true;
            }
        }
        return false;
    }
}
