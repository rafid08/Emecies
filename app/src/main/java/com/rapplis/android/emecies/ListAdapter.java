package com.rapplis.android.emecies;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<List> {

    public ListAdapter(Activity context, ArrayList<List> words) {
        super(context, 0, words);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_icon, parent, false);
        }

        List currentWord = getItem(position);

        TextView textview = (TextView) listItemView.findViewById(R.id.text);
        textview.setText(currentWord.getText());
        textview.setMinLines(1);
        textview.setMaxLines(1);
        textview.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        textview.setPadding(0,0, 0, 8);

        ImageView icon = (ImageView) listItemView.findViewById(R.id.icon);
        icon.setVisibility(View.GONE);

        return listItemView;
    }
}
