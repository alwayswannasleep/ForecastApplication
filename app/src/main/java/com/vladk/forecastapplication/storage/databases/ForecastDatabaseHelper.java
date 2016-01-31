package com.vladk.forecastapplication.storage.databases;

import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.vladk.forecastapplication.storage.databases.DBSchema.*;

/**
 * Database helper for all databases in project.
 * Implemented onCreate and onUpgrade methods.
 * <p>
 * ------------------------------------------------------
 *
 * @author Vlad Kraevskiy
 */
public class ForecastDatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_PATH = "/data/data/com.vladk.forecastapplication/databases/"
            + DBSchema.DB_NAME;

    private static final Map<String, String> mColumnMap = buildColumnMap();

    public ForecastDatabaseHelper(Context context) {
        super(context, DBSchema.DB_NAME, null, DBSchema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(new ExecBuilder(Cities.TABLE_NAME).dropTableString());
        db.execSQL(new ExecBuilder(Forecast.TABLE_NAME).dropTableString());
        db.execSQL(new ExecBuilder(WeatherCondition.TABLE_NAME).dropTableString());

        onCreate(db);
    }

    public Cursor query(String tableName, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(tableName);
        builder.setProjectionMap(mColumnMap);

        Cursor cursor = builder
                .query(getReadableDatabase(), null, selection, selectionArgs, null, null, sortOrder);

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }

        return cursor;
    }

    private static Map<String, String> buildColumnMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put(Cities.ID, Cities.ID);
        map.put(Cities.NAME, Cities.NAME);
        map.put(SearchManager.SUGGEST_COLUMN_INTENT_DATA_ID, Cities.ID + " AS " +
                SearchManager.SUGGEST_COLUMN_INTENT_DATA_ID);

        return map;
    }
}
