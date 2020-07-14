package com.rapplis.android.emecies;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

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

import java.util.ArrayList;

public class UpdateDataActivity extends AppCompatActivity {

    private final DatabaseHelper dbHelper = new DatabaseHelper(this);

    FirebaseStorage storage;
    FirebaseDatabase database;

    DatabaseReference ambulanceRef;
    DatabaseReference callCenterRef;
    DatabaseReference policeRef;
    DatabaseReference fireServiceRef;

    StorageReference ambulanceStorageRef;

    ProgressBar progressBar;

    private ArrayList<Data> datas;

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

        progressBar = findViewById(R.id.progress);

        datas = new ArrayList<>();

        ambulanceRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                datas.clear();

                TextView progressText = findViewById(R.id.progressText);
                progressText.setText("\nUpdating Ambulance Datas");
                int progress = 1;
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    try{
                        if(postSnapshot.exists()){
                            progressBar.setMax((int) dataSnapshot.getChildrenCount());
                            final Data data = postSnapshot.getValue(Data.class);

                            final SQLiteDatabase db = dbHelper.getWritableDatabase();

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
                                            }
                                        }
                                    });
                                }
                            });
                            progress++;
                            progressBar.setProgress(progress);
                        }

                    } catch (NullPointerException e){
                        e.printStackTrace();
                    }
                }
                Intent i = new Intent(getApplicationContext(), Home.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        policeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                datas.clear();

                TextView progressText = findViewById(R.id.progressText);
                progressText.setText("\nUpdating Police Station Datas");
                int progress = 1;
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    try{
                        if(postSnapshot.exists()){
                            progressBar.setMax((int) dataSnapshot.getChildrenCount());
                            final Data data = postSnapshot.getValue(Data.class);

                            final SQLiteDatabase db = dbHelper.getWritableDatabase();

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
                                            }
                                        }
                                    });
                                }
                            });
                            progress++;
                            progressBar.setProgress(progress);
                        }

                    } catch (NullPointerException e){
                        e.printStackTrace();
                    }
                }
                Intent i = new Intent(getApplicationContext(), Home.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        callCenterRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                datas.clear();

                TextView progressText = findViewById(R.id.progressText);
                progressText.setText("\nUpdating Call Center Datas");
                int progress = 1;
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    try{
                        if(postSnapshot.exists()){
                            progressBar.setMax((int) dataSnapshot.getChildrenCount());
                            final Data data = postSnapshot.getValue(Data.class);

                            final SQLiteDatabase db = dbHelper.getWritableDatabase();

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
                                            }
                                        }
                                    });
                                }
                            });
                            progress++;
                            progressBar.setProgress(progress);
                        }

                    } catch (NullPointerException e){
                        e.printStackTrace();
                    }
                }
                Intent i = new Intent(getApplicationContext(), Home.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        fireServiceRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                datas.clear();

                TextView progressText = findViewById(R.id.progressText);
                progressText.setText("\nUpdating Fire service Datas");
                int progress = 1;
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    try{
                        if(postSnapshot.exists()){
                            progressBar.setMax((int) dataSnapshot.getChildrenCount());
                            final Data data = postSnapshot.getValue(Data.class);

                            final SQLiteDatabase db = dbHelper.getWritableDatabase();

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
                                                    DataContract.DataEntry.FIRE_SERVICE_TABLE_NAME)){
                                                db.insert(DataContract.DataEntry.FIRE_SERVICE_TABLE_NAME, null, values);
                                            }
                                        }
                                    });
                                }
                            });
                            progress++;
                            progressBar.setProgress(progress);
                        }

                    } catch (NullPointerException e){
                        e.printStackTrace();
                    }
                }
                Intent i = new Intent(getApplicationContext(), Home.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
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
