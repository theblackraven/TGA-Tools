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


public class VolumenstromFragment extends Fragment {
    public TextView Ergebnis_1; //TextView Ergebnis erstellen damit man aus dem Asynctask darauf zugreifen kann!
    public TextView Ergebnis_2; //TextView Ergebnis erstellen damit man aus dem Asynctask darauf zugreifen kann!
    public TextView Ergebnis_3; //TextView Ergebnis erstellen damit man aus dem Asynctask darauf zugreifen kann!
    public EditText Leistung_text;
    public EditText Spreizung_text;

    private class Berechnen extends AsyncTask<Double, Double, Double> {
        protected Double doInBackground(Double... value) {
            double value_1;
            value_1 = value[0]/(value[1]*1.163);
            return value_1;

        }

        protected void onProgressUpdate(Double... progress) {

        }

        protected void onPostExecute(Double result) {
            Ergebnis_1.setText(String.format( "%.2f", result )+" m³/h");
            Ergebnis_2.setText(String.format( "%.2f", result/60*1000 )+" l/min");
            Ergebnis_3.setText(String.format( "%.2f", result/3.6 )+" l/s");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_volumenstrom, container, false);
        final Button button = (Button) rootView.findViewById(R.id.Berechnen); //final=abgeschlossen, keine Veränderung mehr möglich
        Ergebnis_1 = (TextView) rootView.findViewById(R.id.Volumenstrom_1);
        Ergebnis_2 = (TextView) rootView.findViewById(R.id.Volumenstrom_2);
        Ergebnis_3 = (TextView) rootView.findViewById(R.id.Volumenstrom_3);
        Ergebnis_1.setText("");
        Ergebnis_2.setText("");
        Ergebnis_3.setText("");

        Leistung_text = (EditText) rootView.findViewById(R.id.Leistung_Text);
        Spreizung_text = (EditText) rootView.findViewById(R.id.Spreizung_Text);





        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                double[] value_double = new double[2];
                value_double[0]= Double.parseDouble(Leistung_text.getText().toString());
                value_double[1]= Double.parseDouble(Spreizung_text.getText().toString());



                new Berechnen().execute(value_double[0],value_double[1]);

            }
        });


        return rootView;
    }

}










