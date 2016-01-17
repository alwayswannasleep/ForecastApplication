package com.vladk.forecastapplication.storage;

import android.content.SearchRecentSuggestionsProvider;
import android.database.Cursor;
import android.net.Uri;

public class SuggestionProvider extends SearchRecentSuggestionsProvider {
    public final static String AUTHORITY = "com.vladk.forecastapplication.storage.SuggestionProvider";
    public final static int MODE = DATABASE_MODE_QUERIES;

    public SuggestionProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return super.query(uri, projection, selection, selectionArgs, sortOrder);
    }
}
