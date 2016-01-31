package com.vladk.forecastapplication.storage.databases;

import android.app.SearchManager;
import android.provider.BaseColumns;

/**
 *  Constants for ForecastDatabaseHelper class.
 *
 * ------------------------------------------------------
 *
 * @author Vlad Kraevskiy
 *
 */
public class DBSchema {

    public static final String DB_NAME = "forecast_data";
    public static final int VERSION = 1;

    public static class Cities {
        public static final String TABLE_NAME = "cities";
        public static final String ID = BaseColumns._ID;
        public static final String NAME = SearchManager.SUGGEST_COLUMN_TEXT_1;
        public static final String COUNTRY = "country";
    }

    public static class Forecast {
        public static final String TABLE_NAME = "forecast";
        public static final String CITY_ID = "city_id";
        public static final String TEMP = "temp";
        public static final String TEMP_MIN = "temp_min";
        public static final String TEMP_MAX = "temp_max";
        public static final String PRESSURE = "pressure";
        public static final String HUMIDITY = "humidity";
        public static final String CONDITION_ID = "condition_id";
        public static final String CLOUDINESS = "cloudiness";
        public static final String WIND_SPEED = "wind_speed";
        public static final String RAIN_COUNT = "rain_count";
        public static final String SNOW_COUNT = "snow_count";
        public static final String FORECAST_DATE = "forecast_date";
    }

    public static class WeatherCondition {
        public static final String TABLE_NAME = "weather_condition";
        public static final String ID = "id";
        public static final String MEANING = "meaning";
        public static final String ICON_REF = "icon_ref";
    }
}
