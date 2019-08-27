package com.rapplis.android.emecies;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class PoliceOption extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    static double lat;
    static double lon;
    static String label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option);

        PoliceList police = new PoliceList();
        final String titleText = police.title();
        int iconImage = police.icon();
        int coverImage = police.setCover();
        final String phoneNum = police.setPhone();
        lat = police.setLat();
        lon = police.setLon();
        label = police.title();

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

                if(isGoogleMapsInstalled()) {
                    startActivity(mapIntent);
                }
                else {
                    Context context = getApplicationContext();
                    CharSequence text = "Google Maps is not installed";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });

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

    public boolean isGoogleMapsInstalled()
    {
        try
        {
            ApplicationInfo info = getPackageManager().getApplicationInfo("com.google.android.apps.maps", 0 );
            return true;
        }
        catch(PackageManager.NameNotFoundException e)
        {
            return false;
        }
    }
}