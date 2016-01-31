package com.vladk.forecastapplication.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.vladk.forecastapplication.Callback;
import com.vladk.forecastapplication.storage.databases.DBSchema;
import com.vladk.forecastapplication.storage.databases.ForecastDatabaseHelper;
import com.vladk.forecastapplication.storage.repositories.CityRepository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Main Storage for access to general data.
 * Singleton. Before usage do registration.
 * <p>
 * ------------------------------------------------------
 *
 * @author Vlad Kraevskiy
 */
public class Storage {
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private CityRepository mCityRepository;
    private ForecastDatabaseHelper mHelper;

    private final int BUFFER_SIZE = 32000;

    private Executor mExecutor = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors() - 1);

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

    public void init(Callback callback) {
        boolean dbExist = databaseExist();

        openDataBase();
        mCityRepository = new CityRepository(mDatabase);

        if (dbExist) {
            callback.onResult();
        } else {
            copyDatabase(callback);
        }
    }

    private void copyDatabase(final Callback callback) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream inputStream = mContext.getAssets().open(DBSchema.DB_NAME + ".db");
                    OutputStream outputStream = new FileOutputStream(ForecastDatabaseHelper.DB_PATH);

                    byte[] buffer = new byte[BUFFER_SIZE];

                    while (inputStream.read(buffer) > 0) {
                        outputStream.write(buffer);
                    }

                    inputStream.close();
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.d(Storage.class.getSimpleName(), "Load finished");

                callback.onResult();
            }
        });
    }

    private boolean databaseExist() {
        boolean dbExist;

        try {
            SQLiteDatabase database = SQLiteDatabase
                    .openDatabase(ForecastDatabaseHelper.DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);

            database.close();
            dbExist = true;
        } catch (SQLiteException e) {
            dbExist = false;
        }

        return dbExist;
    }

    private Storage(Context context) {
        mContext = context;
    }

    private void openDataBase() {
        mHelper = new ForecastDatabaseHelper(mContext);
        mDatabase = mHelper.getWritableDatabase();
        mDatabase.setLocale(Locale.ENGLISH);
    }
}