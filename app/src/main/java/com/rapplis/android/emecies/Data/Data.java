package com.rapplis.android.emecies.Data;

public class Data {
    private String name;
    private String phone;
    private String latitude;
    private String longitude;
    private String profilePhotoUri;
    private String coverPhotoUri;

    public Data() {
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getProfilePhotoUri() {
        return profilePhotoUri;
    }

    public String getCoverPhotoUri() {
        return coverPhotoUri;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setProfilePhotoUri(String profilePhotoUri) {
        this.profilePhotoUri = profilePhotoUri;
    }

    public void setCoverPhotoUri(String coverPhotoUri) {
        this.coverPhotoUri = coverPhotoUri;
    }
}
