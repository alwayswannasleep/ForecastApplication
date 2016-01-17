package com.vladk.forecastapplication.storage.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.vladk.forecastapplication.storage.databases.DBSchema.*;

/**
 * Database helper for all databases in project.
 * Implemented onCreate and onUpgrade methods.
 *
 * ------------------------------------------------------
 *
 * @author Vlad Kraevskiy
 */
public class ForecastDatabaseHelper extends SQLiteOpenHelper {

    public ForecastDatabaseHelper(Context context) {
        super(context, DBSchema.DB_NAME, null, DBSchema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ExecBuilder builder = new ExecBuilder(Cities.TABLE_NAME);
        builder.addValue(Cities.ID, ExecBuilder.Types.Integer)
                .addValue(Cities.NAME, ExecBuilder.Types.Text)
                .addValue(Cities.COUNTRY, ExecBuilder.Types.Text)
                .setPrimaryKey(Cities.ID);

        db.execSQL(builder.build());

        builder = new ExecBuilder(WeatherCondition.TABLE_NAME);
        builder.addValue(WeatherCondition.ID, ExecBuilder.Types.Integer)
                .addValue(WeatherCondition.MEANING, ExecBuilder.Types.Text)
                .addValue(WeatherCondition.ICON_REF, ExecBuilder.Types.Text)
                .setPrimaryKey(WeatherCondition.ID);

        db.execSQL(builder.build());

        builder = new ExecBuilder(Forecast.TABLE_NAME);
        builder.addValue(Forecast.CITY_ID, ExecBuilder.Types.Text)
                .addValue(Forecast.TEMP, ExecBuilder.Types.Real)
                .addValue(Forecast.TEMP_MIN, ExecBuilder.Types.Real)
                .addValue(Forecast.TEMP_MAX, ExecBuilder.Types.Real)
                .addValue(Forecast.PRESSURE, ExecBuilder.Types.Real)
                .addValue(Forecast.HUMIDITY, ExecBuilder.Types.Real)
                .addValue(Forecast.CONDITION_ID, ExecBuilder.Types.Integer)
                .addValue(Forecast.CLOUDINESS, ExecBuilder.Types.Real)
                .addValue(Forecast.WIND_SPEED, ExecBuilder.Types.Real)
                .addValue(Forecast.RAIN_COUNT, ExecBuilder.Types.Real)
                .addValue(Forecast.SNOW_COUNT, ExecBuilder.Types.Real)
                .addValue(Forecast.FORECAST_DATE, ExecBuilder.Types.Text)
                .addForeignKey(Forecast.CITY_ID, Cities.TABLE_NAME, Cities.ID)
                .addForeignKey(Forecast.CONDITION_ID, WeatherCondition.TABLE_NAME, WeatherCondition.ID);

        db.execSQL(builder.build());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(new ExecBuilder(Cities.TABLE_NAME).dropTableString());
        db.execSQL(new ExecBuilder(Forecast.TABLE_NAME).dropTableString());
        db.execSQL(new ExecBuilder(WeatherCondition.TABLE_NAME).dropTableString());

        onCreate(db);
    }
}
