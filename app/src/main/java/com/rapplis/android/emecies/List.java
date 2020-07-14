package com.rapplis.android.emecies;

import android.app.Activity;

public class List {
    private String mgetText;
    private byte[] mgetIcon;
    private byte[] mgetCover;
    private String mgetPhone;
    private String mgetLat;
    private String mgetLon;

    public List(String getText, byte[] getIcon, byte[] getCover, String getPhone, String getLat, String getLon){
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
    public byte[] getIcon(){
        return mgetIcon;
    }
    public byte[] getCover(){
        return mgetCover;
    }
    public String getPhone() {
        return mgetPhone;
    }
    public String getLat(){
        return mgetLat;
    }
    public String getLon(){
        return mgetLon;
    }
}
