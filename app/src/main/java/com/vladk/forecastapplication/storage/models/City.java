package com.vladk.forecastapplication.storage.models;

import com.google.gson.annotations.SerializedName;
import com.vladk.forecastapplication.storage.databases.DBSchema;

public class City {

    @SerializedName("_id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("country")
    private String mCountry;

    private Forecast mForecast;

    public Forecast getForecast() {
        return mForecast;
    }

    public void setForecast(Forecast forecast) {
        mForecast = forecast;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }
}
