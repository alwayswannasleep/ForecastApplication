package com.vladk.forecastapplication.storage.models;

public class WeatherCondition {

    private int mId;
    private String mMeaning;
    private String mIconURI;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getMeaning() {
        return mMeaning;
    }

    public void setMeaning(String meaning) {
        mMeaning = meaning;
    }

    public String getIconURI() {
        return mIconURI;
    }

    public void setIconURI(String iconURI) {
        mIconURI = iconURI;
    }
}
