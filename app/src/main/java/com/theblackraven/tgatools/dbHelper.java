package com.theblackraven.tgatools;

/**
 * Created by x220 on 11.06.2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class dbHelper extends SQLiteOpenHelper{
    //
    private static final String LOG_TAG = dbHelper.class.getSimpleName();

    public static final String DB_NAME = "rohrtabelle.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_STAHL = "Stahl";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "Bezeichnung";
    public static final String COLUMN_DURCHMESSER_A = "Aussendurchmesser";
    public static final String COLUMN_DURCHMESSER_I = "Innendurchmesser";
    public static final String COLUMN_K = "Rohrrauhigkeit";


    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_STAHL +
                    "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " STRING NOT NULL, " +
                    COLUMN_DURCHMESSER_A + " REAL NOT NULL, " +
                    COLUMN_DURCHMESSER_I + " REAL NOT NULL, " +
                    COLUMN_K + " REAL NOT NULL);";


    public dbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.d(LOG_TAG, "DbHelper hat die Datenbank: " + getDatabaseName() + " erzeugt.");
    }

    // Die onCreate-Methode wird nur aufgerufen, falls die Datenbank noch nicht existiert
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE + " angelegt.");
            db.execSQL(SQL_CREATE);
        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}