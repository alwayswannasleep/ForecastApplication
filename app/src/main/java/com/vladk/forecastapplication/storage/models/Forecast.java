package com.vladk.forecastapplication.storage.models;

public class Forecast {

    private float mTemp;
    private float mMinTemp;
    private float mMaxTemp;
    private float mPressure;
    private float mHumidity;
    private float mCloudiness;
    private float mWindSpeed;
    private float mRainCount;
    private float mSnowCount;
    private String ForeCastDate;
    private WeatherCondition mCondition;

    public float getTemp() {
        return mTemp;
    }

    public void setTemp(float temp) {
        mTemp = temp;
    }

    public float getMinTemp() {
        return mMinTemp;
    }

    public void setMinTemp(float minTemp) {
        mMinTemp = minTemp;
    }

    public float getMaxTemp() {
        return mMaxTemp;
    }

    public void setMaxTemp(float maxTemp) {
        mMaxTemp = maxTemp;
    }

    public float getPressure() {
        return mPressure;
    }

    public void setPressure(float pressure) {
        mPressure = pressure;
    }

    public float getHumidity() {
        return mHumidity;
    }

    public void setHumidity(float humidity) {
        mHumidity = humidity;
    }

    public float getCloudiness() {
        return mCloudiness;
    }

    public void setCloudiness(float cloudiness) {
        mCloudiness = cloudiness;
    }

    public float getWindSpeed() {
        return mWindSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        mWindSpeed = windSpeed;
    }

    public float getRainCount() {
        return mRainCount;
    }

    public void setRainCount(float rainCount) {
        mRainCount = rainCount;
    }

    public float getSnowCount() {
        return mSnowCount;
    }

    public void setSnowCount(float snowCount) {
        mSnowCount = snowCount;
    }

    public String getForeCastDate() {
        return ForeCastDate;
    }

    public void setForeCastDate(String foreCastDate) {
        ForeCastDate = foreCastDate;
    }

    public WeatherCondition getCondition() {
        return mCondition;
    }

    public void setCondition(WeatherCondition condition) {
        mCondition = condition;
    }
}
