package com.vladk.forecastapplication.storage;

import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.vladk.forecastapplication.storage.databases.DBSchema;
import com.vladk.forecastapplication.storage.databases.ForecastDatabaseHelper;

import java.util.Arrays;

public class SuggestionProvider extends ContentProvider {
    private final static String TAG = "SuggestionProvider";

    private ForecastDatabaseHelper mHelper;

    public SuggestionProvider() {
        Log.d(TAG, "SuggestionProvider()");
    }

    @Override
    public boolean onCreate() {
        mHelper = new ForecastDatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection,
                        String selection, String[] selectionArgs, String sortOrder) {
        Log.d(TAG, "SuggestionProvider@query()" + Arrays.toString(selectionArgs));

        selection = SearchManager.SUGGEST_COLUMN_TEXT_1 + " " + selection;
        selectionArgs[0] = selectionArgs[0] + "%";

        return mHelper.query(DBSchema.Cities.TABLE_NAME,
                selection, selectionArgs, DBSchema.Cities.NAME);
    }

    @Override
    public String getType(@NonNull Uri uri) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values,
                      String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException();
    }
}
