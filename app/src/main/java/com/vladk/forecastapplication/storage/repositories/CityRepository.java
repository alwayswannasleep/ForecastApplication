package com.vladk.forecastapplication.storage.repositories;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.vladk.forecastapplication.storage.databases.DBSchema;
import com.vladk.forecastapplication.storage.models.City;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CityRepository {
    private SQLiteDatabase mDatabase;
    private Executor mExecutor = Executors.newSingleThreadExecutor();

    public CityRepository(SQLiteDatabase database) {
        mDatabase = database;
    }

    public void insert(final City city) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                ContentValues contentValues = new ContentValues();
                contentValues.put(DBSchema.Cities.ID, city.getId());
                contentValues.put(DBSchema.Cities.NAME, city.getName());
                contentValues.put(DBSchema.Cities.COUNTRY, city.getCountry());

                mDatabase.insert(DBSchema.Cities.TABLE_NAME, null, contentValues);
            }
        });

    }
}
