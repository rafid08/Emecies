package com.rapplis.android.emecies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class CallCenterList extends AppCompatActivity {
    private static String title;
    private static int icon;
    private static int cover;
    private static String phone;
    private static int map;
    private static String gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        final ArrayList<List> lists = new ArrayList<>();
        lists.add(new List("দুর্নীতি দমন কমিশন",R.drawable.ddk_icon, R.drawable.ddk_cover, "106", 0, 0));
        lists.add(new List("কৃষি কল সেন্টার",R.drawable.krishi_icon, R.drawable.krishi_cover, "16123", 0, 0));
        lists.add(new List("BTCL",R.drawable.btcl_icon, R.drawable.btcl_cover, "16402", 0, 0));
        lists.add(new List("স্বাস্থ্য বাতায়ন",R.drawable.health_icon, R.drawable.health_cover, "16263", 0, 0));
        lists.add(new List("মহিলা সংস্থা/তথ্য আপা",R.drawable.female_icon, R.drawable.female_cover, "10922", 0, 0));
        lists.add(new List("দূর্যোগের আগাম বার্তা",R.drawable.weather_icon, R.drawable.weather_cover, "10941", 0, 0));
        lists.add(new List("চাইল্ড হেল্প লাইন",R.drawable.child_icon, R.drawable.child_cover, "1098", 0, 0));
        lists.add(new List("বাংলাদেশ ব্যাঙ্ক",R.drawable.bank_icon, R.drawable.bank_cover, "16236", 0, 0));
        lists.add(new List("নারী ও শিশু নির্যাতন প্রতিরোধ সেল",R.drawable.mohilashishu_icon, R.drawable.mohilashishu_cover, "109", 0, 0));
        lists.add(new List("প্রবাসবন্ধু কল সেন্টার",R.drawable.probash_icon, R.drawable.probash_cover, "09654333333", 0, 0));
        lists.add(new List("প্রবাসী কল্যাণ ও বৈদেশিক কর্মসংস্থান",R.drawable.bidesh_icon, R.drawable.bidesh_cover, "109", 0, 0));
        lists.add(new List("জাতীয় পরিচয় পত্র",R.drawable.nid_icon, R.drawable.nid_cover, "105", 0, 0));
        lists.add(new List("সরকারি আইন সেবা",R.drawable.law_icon, R.drawable.law_cover, "16430", 0, 0));
        lists.add(new List("ঢাকা ওয়াসা",R.drawable.wasa_icon, R.drawable.wasa_cover, "16162", 0, 0));
        lists.add(new List("ইউনিয়ন পরিষদ হেল্পলাইন",R.drawable.union_icon, R.drawable.union_cover, "16256", 0, 0));

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

                Intent option = new Intent(view.getContext(), CallCenterOption.class);
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
}
