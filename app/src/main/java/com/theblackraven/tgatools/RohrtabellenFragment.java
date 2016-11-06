package com.theblackraven.tgatools;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import java.util.List;
import android.widget.Button;
import android.widget.Spinner;




public class RohrtabellenFragment extends Fragment {

    public TextView Ergebnis_1; //TextView Ergebnis erstellen damit man aus dem Asynctask darauf zugreifen kann!
    public TextView Ergebnis_2; //TextView Ergebnis erstellen damit man aus dem Asynctask darauf zugreifen kann!
    public TextView Ergebnis_3; //TextView Ergebnis erstellen damit man aus dem Asynctask darauf zugreifen kann!
    public EditText Leistung_text;
    public EditText Spreizung_text;
    public Spinner Spinner_text;
    public TextView Druckverlust_text;

    private class Berechnen extends AsyncTask<Double, Double, Double[]> {
        protected Double[] doInBackground(Double... value) {
            Double[] value_1 = new Double[2];
            value_1[0] = value[0]/(value[1]*1.163);
            value_1[1]=value[2];
            return value_1;


        }

        protected void onProgressUpdate(Double... progress) {

        }

        protected void onPostExecute(Double[] result) {
            Ergebnis_1.setText(String.format( "%.2f", result[0] )+" m³/h");
            Ergebnis_2.setText(String.format( "%.2f", result[0]/60*1000 )+" l/min");
            Ergebnis_3.setText(String.format( "%.2f", result[0]/3.6 )+" l/s");
            Druckverlust_text.setText(result[1].toString());
        }
    }

    private dbDataSource dataSource;
    public static final String LOG_TAG = RohrtabellenFragment.class.getSimpleName();

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rohrtabellen, container, false);

        dataSource = new dbDataSource(getContext());
        final Spinner spinner = (Spinner) rootView.findViewById(R.id.SpinnerRohrtabellen);

        Log.d(LOG_TAG, "Die Datenquelle wird geöffnet");
        dataSource.open();

        Data data = dataSource.createData("25", 20, 22, 3);
        Log.d(LOG_TAG, "Es wurde der folgende Eintrag in die Datenbank geschrieben:");
        Log.d(LOG_TAG, "ID: " + data.getId() + ", Inhalt: " + data.toString());

        Log.d(LOG_TAG, "Folgende Einträge sind in der Datenbank vorhanden:");
        showAllListEntries(spinner);



        Log.d(LOG_TAG, "Die Datenquelle wird geschlossen.");
        dataSource.close();

        final Button button = (Button) rootView.findViewById(R.id.Berechnen); //final=abgeschlossen, keine Veränderung mehr möglich
        Ergebnis_1 = (TextView) rootView.findViewById(R.id.Volumenstrom_1);
        Ergebnis_2 = (TextView) rootView.findViewById(R.id.Volumenstrom_2);
        Ergebnis_3 = (TextView) rootView.findViewById(R.id.Volumenstrom_3);
        Ergebnis_1.setText("");
        Ergebnis_2.setText("");
        Ergebnis_3.setText("");

        Leistung_text = (EditText) rootView.findViewById(R.id.Leistung_Text);
        Spreizung_text = (EditText) rootView.findViewById(R.id.Spreizung_Text);
        Spinner_text = (Spinner) rootView.findViewById(R.id.SpinnerRohrtabellen);
        Druckverlust_text = (TextView) rootView.findViewById(R.id.Druckverlust);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                double[] value_double = new double[3];

                value_double[0]= Double.parseDouble(Leistung_text.getText().toString());
                value_double[1]= Double.parseDouble(Spreizung_text.getText().toString());
                value_double[2]= Double.parseDouble(Spinner_text.getSelectedItem().toString());





                        new Berechnen().execute(value_double[0], value_double[1], value_double[2]);

            }
        });

        return rootView;




    }

    private void showAllListEntries(Spinner spinner){
        List<String> DataList = dataSource.getAllNames();

        ArrayAdapter<String> DataArrayAdapter = new ArrayAdapter<> (
                getContext(),
                android.R.layout.simple_spinner_item,
                DataList);

        DataArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(DataArrayAdapter);

        }



}

