package com.rapplis.android.emecies;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FireOption extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option);

        FireList fire = new FireList();
        final String titleText = fire.title();
        int iconImage = fire.icon();
        int coverImage = fire.setCover();
        final String phoneNum = fire.setPhone();
        String finalTitle;

        TextView title = (TextView) findViewById(R.id.option_title);

        if(titleText.equals("Fire Brigade Enquiry"))finalTitle = titleText;
        else if(titleText.equals("Head Quarter"))finalTitle = "Fire Brigade "+titleText;
        else finalTitle = "Fire Brigade of\n" + titleText;

        title.setText(finalTitle);

        ImageView icon = (ImageView) findViewById(R.id.option_icon);
        icon.setImageResource(iconImage);

        TextView phone = (TextView) findViewById(R.id.phone);
        phone.setText(phoneNum);

        ImageView cover = (ImageView) findViewById(R.id.cover);
        cover.setImageResource(coverImage);

        LinearLayout mapTab = (LinearLayout) findViewById(R.id.maptab);
        mapTab.setVisibility(View.GONE);

        FloatingActionButton call = (FloatingActionButton) findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNum));
                startActivity(call);
            }
        });
    }
}
