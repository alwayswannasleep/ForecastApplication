package com.vladk.forecastapplication.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.vladk.forecastapplication.storage.Storage;

public class WelcomeActivity extends Activity {
    private static final String SHARED_PREFERENCES_NAME = "forecast_preferences";
    private static final String WAS_DB_INITIALIZED = "was_db_initialized";

    private Storage mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences =
                getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);

        mStorage = Storage.getInstance(this);

        if(sharedPreferences.contains(WAS_DB_INITIALIZED)) {
            mStorage.init();
        } else {
            mStorage.firstInit();
            sharedPreferences.edit().putBoolean(WAS_DB_INITIALIZED, true).apply();
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mStorage.register();
        Log.d(WelcomeActivity.class.getSimpleName(), "WelcomeActivity@onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        mStorage.unRegister();
    }
}
