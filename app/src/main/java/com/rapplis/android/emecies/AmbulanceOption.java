package com.rapplis.android.emecies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class AmbulanceOption extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    static double lat;
    static double lon;
    static String label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option);

        AmbulanceList ambulance = new AmbulanceList();
        final String titleText = ambulance.title();
        int iconImage = ambulance.icon();
        int coverImage = ambulance.setCover();
        final String phoneNum = ambulance.setPhone();
        lat = ambulance.setLat();
        lon = ambulance.setLon();
        label = ambulance.title();

        TextView title = (TextView) findViewById(R.id.option_title);
        title.setText(titleText);

        ImageView icon = (ImageView) findViewById(R.id.option_icon);
        icon.setImageResource(iconImage);

        TextView phone = (TextView) findViewById(R.id.phone);
        phone.setText(phoneNum);

        ImageView cover = (ImageView) findViewById(R.id.cover);
        cover.setImageResource(coverImage);

        Button openMaps = (Button) findViewById(R.id.map_launch);
        openMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uriAddress = ("http://maps.google.com/maps?q="+ lat  +"," + lon +"("+ label + ")&iwloc=A&hl=es");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriAddress));
                startActivity(mapIntent);
            }
        });

        //get fragment and put google maps in it
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getContext();
        mapFragment.getMapAsync(this);

        FloatingActionButton call = (FloatingActionButton) findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNum));
                startActivity(call);
            }
        });
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(sydney).title(label));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(15.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.moveCamera(cameraUpdate);
    }
}