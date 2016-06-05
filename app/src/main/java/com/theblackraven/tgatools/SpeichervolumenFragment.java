package com.theblackraven.tgatools;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class SpeichervolumenFragment extends Fragment {
    public TextView Ergebnis_1; //TextView Ergebnis erstellen damit man aus dem Asynctask darauf zugreifen kann!
    public TextView Ergebnis_2; //TextView Ergebnis erstellen damit man aus dem Asynctask darauf zugreifen kann!
    public EditText Leistung_text;
    public EditText Spreizung_text;
    public EditText Inhalt_text;

    private class Berechnen extends AsyncTask<Double, Double, Double[]> {
        protected Double[] doInBackground(Double... value) {
            Double[] value_1= new Double[2];
            value_1[0] = value[1]*value[2]/1000*1.163;
            value_1[1] = value_1[0]/value[0]*60;
            return value_1;

        }

        protected void onProgressUpdate(Double... progress) {

        }

        protected void onPostExecute(Double[] result) {
            Ergebnis_1.setText(String.format( "%.2f", result[0] )+" kWh");
            Ergebnis_2.setText(String.format( "%.2f", result[1] )+" min");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_speichervolumen, container, false);
        final Button button = (Button) rootView.findViewById(R.id.Berechnen); //final=abgeschlossen, keine Veränderung mehr möglich
        Ergebnis_1 = (TextView) rootView.findViewById(R.id.Speichervolumen_Ergebnis);
        Ergebnis_2 = (TextView) rootView.findViewById(R.id.Zeit_text);
        Ergebnis_1.setText("");
        Ergebnis_2.setText("");
        Inhalt_text = (EditText) rootView.findViewById(R.id.Speichervolumen_Text);
        Leistung_text = (EditText) rootView.findViewById(R.id.Leistung_1_text);
        Spreizung_text = (EditText) rootView.findViewById(R.id.Spreizung_1_text);





        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                double[] value_double = new double[3];
                value_double[0]= Double.parseDouble(Leistung_text.getText().toString());
                value_double[1]= Double.parseDouble(Spreizung_text.getText().toString());
                value_double[2]= Double.parseDouble(Inhalt_text.getText().toString());


                new Berechnen().execute(value_double[0],value_double[1],value_double[2]);

            }
        });


        return rootView;
    }

}









