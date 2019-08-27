package com.rapplis.android.emecies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class FireList extends AppCompatActivity {
    private static String title;
    private static int icon;
    private static int cover;
    private static String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        final ArrayList<List> lists = new ArrayList<>();
        lists.add(new List("Fire Brigade Enquiry", R.drawable.firebrigade_icon, R.drawable.firebrigade_cover, "199", 0, 0));
        lists.add(new List("Head Quarter", R.drawable.firebrigade_icon, R.drawable.firebrigade_cover, "02-9555555", 0, 0));
        lists.add(new List("Demra", R.drawable.firebrigade_icon, R.drawable.firebrigade_cover, "02-9715575", 0, 0));
        lists.add(new List("Dhaka Cantt.(Kurmitola)", R.drawable.firebrigade_icon, R.drawable.firebrigade_cover, "02-605168", 0, 0));
        lists.add(new List("Hajigonj", R.drawable.firebrigade_icon, R.drawable.firebrigade_cover, "02-9715531", 0, 0));
        lists.add(new List("Khilgaon", R.drawable.firebrigade_icon, R.drawable.firebrigade_cover, "02-7218329", 0, 0));
        lists.add(new List("Mirpur-10 (Circle)", R.drawable.firebrigade_icon, R.drawable.firebrigade_cover, "02-9001055", 0, 0));
        lists.add(new List("Mirpur Training Complex", R.drawable.firebrigade_icon, R.drawable.firebrigade_cover, "02-9001189", 0, 0));
        lists.add(new List("Mohammadpur", R.drawable.firebrigade_icon, R.drawable.firebrigade_cover, "02-9112078", 0, 0));
        lists.add(new List("Polashi", R.drawable.firebrigade_icon, R.drawable.firebrigade_cover, "02-509670", 0, 0));
        lists.add(new List("Sadarghat", R.drawable.firebrigade_icon, R.drawable.firebrigade_cover, "02-7119759", 0, 0));
        lists.add(new List("Tejgaon", R.drawable.firebrigade_icon, R.drawable.firebrigade_cover, "02-9898187", 0, 0));
        lists.add(new List("Tongi", R.drawable.firebrigade_icon, R.drawable.firebrigade_cover, "02-9801070", 0, 0));

        LinearLayout header = (LinearLayout) findViewById(R.id.header);
        header.setVisibility(View.VISIBLE);

        ImageView headIcon = (ImageView) findViewById(R.id.header_icon);
        headIcon.setImageResource(R.drawable.firebrigade_icon);

        TextView headerTitle = (TextView) findViewById(R.id.header_title);
        headerTitle.setText("Fire Brigade Services");

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

                Intent option = new Intent(view.getContext(), FireOption.class);
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
