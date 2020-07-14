package com.rapplis.android.emecies;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class GridAdapter extends ArrayAdapter<List> {
    public GridAdapter(Activity context, ArrayList<List> words) {
        super(context, 0, words);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_icon, parent, false);
        }

        List currentWord = getItem(position);

        TextView textview = (TextView) listItemView.findViewById(R.id.text);
        textview.setText(currentWord.getText());

        ImageView icon = (ImageView) listItemView.findViewById(R.id.icon);
        icon.setImageBitmap(getImage(currentWord.getIcon()));

        return listItemView;
    }

    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}