package com.vladk.forecastapplication.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ProgressBar;

import com.vladk.forecastapplication.Callback;
import com.vladk.forecastapplication.R;
import com.vladk.forecastapplication.storage.Storage;

public class WelcomeActivity extends AppCompatActivity {
    private Storage mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mStorage = Storage.getInstance(this);

        mStorage.init(new Callback() {
            @Override
            public void onResult() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(WelcomeActivity.class.getSimpleName(), "WelcomeActivity@onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
