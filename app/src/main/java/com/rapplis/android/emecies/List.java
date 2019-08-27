package com.rapplis.android.emecies;

import android.app.Activity;

public class List {
    private String mgetText;
    private int mgetIcon;
    private int mgetCover;
    private String mgetPhone;
    private double mgetLat;
    private double mgetLon;

    public List(String getText, int getIcon, int getCover, String getPhone, double getLat, double getLon){
        mgetText = getText;
        mgetIcon = getIcon;
        mgetCover = getCover;
        mgetPhone = getPhone;
        mgetLat = getLat;
        mgetLon = getLon;
    }
    public String getText() {
        return mgetText;
    }
    public int getIcon(){
        return mgetIcon;
    }
    public int getCover(){
        return mgetCover;
    }
    public String getPhone() {
        return mgetPhone;
    }
    public double getLat(){
        return mgetLat;
    }
    public double getLon(){
        return mgetLon;
    }
}
