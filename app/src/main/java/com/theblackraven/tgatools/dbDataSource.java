package com.theblackraven.tgatools;

/**
 * Created by x220 on 11.06.2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class dbDataSource {

    private static final String LOG_TAG = dbDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private dbHelper dbHelper;


    public dbDataSource(Context context) {
        Log.d(LOG_TAG, "Unsere DataSource erzeugt jetzt den dbHelper.");
        dbHelper = new dbHelper(context);
    }

    public void open() {
        Log.d(LOG_TAG, "Eine Referenz auf die Datenbank wird jetzt angefragt.");
        database = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "Datenbank-Referenz erhalten. Pfad zur Datenbank: " + database.getPath());
    }

    public void close() {
        dbHelper.close();
        Log.d(LOG_TAG, "Datenbank mit Hilfe des DbHelpers geschlossen.");
    }
}