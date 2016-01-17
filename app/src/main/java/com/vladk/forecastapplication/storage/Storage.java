package com.vladk.forecastapplication.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.vladk.forecastapplication.R;
import com.vladk.forecastapplication.storage.databases.ForecastDatabaseHelper;
import com.vladk.forecastapplication.storage.models.City;
import com.vladk.forecastapplication.storage.repositories.CityRepository;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Main Storage for access to general data.
 * Singleton. Before usage do registration.
 * <p/>
 * ------------------------------------------------------
 *
 * @author Vlad Kraevskiy
 */
public class Storage {
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private CityRepository mCityRepository;

    private Executor mExecutor = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors() - 1);

    private int mRegisteredCount = 0;

    private static volatile Storage mInstance;

    public static Storage getInstance(Context context) {
        Storage localInstance = mInstance;

        if (localInstance == null) {
            localInstance = mInstance;
            synchronized (Storage.class) {
                if (localInstance == null) {
                    localInstance = mInstance = new Storage(context);
                }
            }
        }

        return localInstance;
    }

    public void firstInit() {
        init();

        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Gson gson = new Gson();

                    InputStream in = mContext.getResources().openRawResource(R.raw.city_list);
                    JsonReader jsonReader = new JsonReader(new InputStreamReader(in, "UTF-8"));

                    jsonReader.setLenient(true);

                    while (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                        City city = gson.fromJson(jsonReader, City.class);
                        mCityRepository.insert(city);
                    }

                    jsonReader.close();
                    Log.d(Storage.class.getSimpleName(), "Load finished");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void init() {
        openDataBase();
        mCityRepository = new CityRepository(mDatabase);
    }

    public void register() {
        mRegisteredCount++;
    }

    public void unRegister() {
        mRegisteredCount--;

        if (mRegisteredCount == 0) {
            closeDataBase();
        }
    }

    private Storage(Context context) {
        mContext = context;
    }

    private void openDataBase() {
        ForecastDatabaseHelper helper = new ForecastDatabaseHelper(mContext);
        mDatabase = helper.getWritableDatabase();
    }

    private void closeDataBase() {
        mDatabase.close();
    }

}