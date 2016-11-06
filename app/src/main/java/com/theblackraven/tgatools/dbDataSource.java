package com.theblackraven.tgatools;

/**
 * Created by x220 on 11.06.2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

public class dbDataSource {

    private static final String LOG_TAG = dbDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private dbHelper dbHelper;

    private String[] columns = {
            dbHelper.COLUMN_ID,
            dbHelper.COLUMN_NAME,
            dbHelper.COLUMN_DURCHMESSER_A,
            dbHelper.COLUMN_DURCHMESSER_I,
            dbHelper.COLUMN_K
    };



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
    public Data createData(String name, double da, double di, double k) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COLUMN_DURCHMESSER_A, da);
        values.put(dbHelper.COLUMN_DURCHMESSER_I, di);
        values.put(dbHelper.COLUMN_K, k);
        values.put(dbHelper.COLUMN_NAME, name);

        long insertId = database.insert(dbHelper.TABLE_STAHL, null, values);

        Cursor cursor = database.query(dbHelper.TABLE_STAHL,
                columns, dbHelper.COLUMN_ID + "=" + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Data data = cursorToData(cursor);
        cursor.close();

        return data;
    }

    private Data cursorToData(Cursor cursor) {
        int IdIndex = cursor.getColumnIndex(dbHelper.COLUMN_ID);
        int IdName = cursor.getColumnIndex(dbHelper.COLUMN_NAME);
        int IdDa = cursor.getColumnIndex(dbHelper.COLUMN_DURCHMESSER_A);
        int IdDi = cursor.getColumnIndex(dbHelper.COLUMN_DURCHMESSER_I);
        int IdK = cursor.getColumnIndex(dbHelper.COLUMN_K);

        int idx = cursor.getInt(IdIndex);
        String name = cursor.getString(IdName);
        double da = cursor.getInt(IdDa);
        double di = cursor.getInt(IdDi);
        double k = cursor.getInt(IdK);



        Data data = new Data(idx, name, da, di, k);

        return data;
    }

    public List<Data> getAllData() {
        List<Data> DataList = new ArrayList<>();

        Cursor cursor = database.query(dbHelper.TABLE_STAHL,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Data data;

        while(!cursor.isAfterLast()) {
            data = cursorToData(cursor);
            DataList.add(data);
            Log.d(LOG_TAG, "ID: " + data.getId() + ", Inhalt: " + data.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return DataList;
    }

}