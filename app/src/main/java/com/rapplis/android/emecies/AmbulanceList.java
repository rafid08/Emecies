package com.rapplis.android.emecies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class AmbulanceList extends AppCompatActivity {
    private static String title;
    private static int icon;
    private static int cover;
    private static String phone;
    private static double lat;
    private static double lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        final ArrayList<List> lists = new ArrayList<>();
        lists.add(new List("Al-Markazul Islam Ambulance Service",R.drawable.markazul_islam, R.drawable.markazul_islam_cover,
                "02-8125549", 23.770847, 90.366664));
        lists.add(new List("Anju- Man- E- Mafidul Islam", R.drawable.anjuman_icon, R.drawable.anjuman_cover,
                "02-8626812", 23.731622, 90.429916));
        lists.add(new List("Apollo Hospitals Dhaka", R.drawable.apollo_icon, R.drawable.apollo_cover,
                "01714090000", 23.809913, 90.431056));
        lists.add(new List("Alif Ambulance Service", R.drawable.alif_icon, R.drawable.alif_cover,
                "01713205555", 23.751985, 90.385564));
        lists.add(new List("BIRDEM Hospital", R.drawable.birdem_icon, R.drawable.birdem_cover,
                "02-9661551", 23.738846, 90.396482));
        lists.add(new List("BNSB, Dhaka Eye Hospital", R.drawable.bsnb_icon, R.drawable.bsnb_cover,
                "02-8014476", 23.799181, 90.355239));
        lists.add(new List("BSMMU (PG Hospital)", R.drawable.bsmmu_icon, R.drawable.bsmmu_cover,
                "02-8614001", 23.738624, 90.393631));
        lists.add(new List("CMH (24 Hour Ambulance)", R.drawable.cmh_icon, R.drawable.cmh_cover,
                "02-9870011", 23.814464, 90.398866));
        lists.add(new List("Day-Night Ambulance service", R.drawable.d_icon, R.drawable.daynight_cover,
                "02-9123073", 23.770211,90.41382));
        lists.add(new List("Dhaka Medical College Hospital", R.drawable.dhaka_medical, R.drawable.dhaka_medical_cover,
                "02-8626812", 23.725610, 90.398058));
        lists.add(new List("Dhaka Shishu Hospital", R.drawable.shishu_icon, R.drawable.shishu_cover,
                "02-81145712", 23.773029, 90.369309));
        lists.add(new List("Dip Clinic & Diagnostic Centre", R.drawable.d_icon, R.drawable.dip_cover,
                "0171682583", 23.839117, 90.257585));
        lists.add(new List("Holy Family Medical College & Hospital", R.drawable.holy_icon, R.drawable.holy_cover,
                "02-8311721-5", 23.746704, 90.403111));
        lists.add(new List("Ibrahim Cardiac Hospital & Research Insititute(24 Hours Ambulance)", R.drawable.ibrahim_icon, R.drawable.ibrahim_cover,
                "01713003004", 23.738824, 90.396526));
        /**lists.add(new List("", , ,
                "", , ""));**/

        LinearLayout header = (LinearLayout) findViewById(R.id.header);
        header.setVisibility(View.GONE);

        GridAdapter adapter = new GridAdapter(this, lists);

        GridView gridView = (GridView) findViewById(R.id.list);

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

                Intent option = new Intent(view.getContext(), AmbulanceOption.class);
                startActivity(option);
            }
        });
    }
    public String title(){
        return title;
    }
    public int icon(){
        return icon;
    }
    public int setCover(){
        return cover;
    }
    public String setPhone(){
        return phone;
    }
    public double setLat(){
        return lat;
    }
    public double setLon(){
        return lon;
    }
}
