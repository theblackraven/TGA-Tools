package com.theblackraven.tgatools;

/**
 * Created by x220 on 29.05.2016.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.widget.ListView;

public class MainActivityFragment extends Fragment{

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ListView ToolsListView = (ListView) rootView.findViewById(R.id.listview_aktienliste);
        ToolsListView.setAdapter(ToolsListAdapter);

        return rootView;

    }

}

