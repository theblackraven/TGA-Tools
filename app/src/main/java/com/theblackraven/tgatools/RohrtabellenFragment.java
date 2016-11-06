package com.theblackraven.tgatools;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.List;




public class RohrtabellenFragment extends Fragment {

    private dbDataSource dataSource;
    public static final String LOG_TAG = RohrtabellenFragment.class.getSimpleName();

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rohrtabellen, container, false);

        dataSource = new dbDataSource(getContext());

        Log.d(LOG_TAG, "Die Datenquelle wird geöffnet");
        dataSource.open();

        Data data = dataSource.createData("Stahl DN 20", 20, 22, 3);
        Log.d(LOG_TAG, "Es wurde der folgende Eintrag in die Datenbank geschrieben:");
        Log.d(LOG_TAG, "ID: " + data.getId() + ", Inhalt: " + data.toString());

        Log.d(LOG_TAG, "Folgende Einträge sind in der Datenbank vorhanden:");
        showAllListEntries(rootView);



        Log.d(LOG_TAG, "Die Datenquelle wird geschlossen.");
        dataSource.close();
        return rootView;



    }

    private void showAllListEntries(View rootView){
        List<Data> DataList = dataSource.getAllData();

        ArrayAdapter<Data> DataArrayAdapter = new ArrayAdapter<> (
                getContext(),
                android.R.layout.simple_list_item_multiple_choice,
                DataList);

        ListView DataListView = (ListView) rootView.findViewById(R.id.listView);
        DataListView.setAdapter(DataArrayAdapter);

        }

}

