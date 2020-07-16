package com.rapplis.android.emecies;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class ListAdapter extends ArrayAdapter<List> {

    public Context context;
    public ArrayList<List> dataList;
    public ArrayList<List> orig;

    public ListAdapter(Activity context, ArrayList<List> lists) {
        super(context, 0, lists);
        this.dataList = lists;
        this.context = context;
        this.orig = new ArrayList<>();
        this.orig.addAll(dataList);
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        dataList.clear();
        if (charText.length()==0){
            dataList.addAll(orig);
        }
        else {
            for (List list : orig){
                if (list.getText().toLowerCase(Locale.getDefault())
                        .contains(charText)){
                    dataList.add(list);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
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
