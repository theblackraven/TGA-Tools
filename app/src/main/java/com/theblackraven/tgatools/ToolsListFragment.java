package com.theblackraven.tgatools;

/**
 * Created by x220 on 29.05.2016.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.widget.ListView;

public class ToolsListFragment extends Fragment{

    public ToolsListFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Menü bekannt geben, dadurch kann unser Fragment Menü-Events verarbeiten
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_toolslistefragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Wir prüfen, ob Menü-Element mit der ID "action_daten_aktualisieren"
        // ausgewählt wurde und geben eine Meldung aus
        int id = item.getItemId();
        if (id == R.id.action_daten_aktualisieren) {
            Toast.makeText(getActivity(), "Aktualisieren gedrückt!", Toast.LENGTH_LONG).show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String LOG_TAG = ToolsListFragment.class.getSimpleName();

        Log.v(LOG_TAG, "verbose     - Meldung");
        Log.d(LOG_TAG, "debug       - Meldung");
        Log.i(LOG_TAG, "information - Meldung");
        Log.w(LOG_TAG, "warning     - Meldung");
        Log.e(LOG_TAG, "error       - Meldung");

        String [] ToolsListeArray = {
                "Wärmeleistung",
                "Druckverluste"

        }; //array mit Daten

        List <String> ToolsListe = new ArrayList<>(Arrays.asList(ToolsListeArray));

        ArrayAdapter <String> ToolsListAdapter =
                new ArrayAdapter<>(
                        getActivity(), // Die aktuelle Umgebung (diese Activity)
                        R.layout.list_item_tools, // ID der XML-Layout Datei
                        R.id.list_item_tools_textview, // ID des TextViews
                        ToolsListe); // Beispieldaten in einer ArrayList
        View rootView = inflater.inflate(R.layout.fragment_toolslist, container, false);

        ListView ToolsListView = (ListView) rootView.findViewById(R.id.listview_aktienliste);
        ToolsListView.setAdapter(ToolsListAdapter);

        ToolsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String aktienInfo = (String) adapterView.getItemAtPosition(position);
                Toast.makeText(getActivity(), aktienInfo, Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;

    }

}

