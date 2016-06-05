package com.theblackraven.tgatools;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class VentilFragment extends Fragment {
    public TextView Ergebnis_1; //TextView Ergebnis erstellen damit man aus dem Asynctask darauf zugreifen kann!
    public TextView Ergebnis_2; //TextView Ergebnis erstellen damit man aus dem Asynctask darauf zugreifen kann!
    public EditText Leistung_text;
    public EditText Spreizung_text;
    public EditText Volumenstrom_text;
    public EditText Kvs_text;

    private class Berechnen extends AsyncTask<Double, Double, Double[]> {
        protected Double[] doInBackground(Double... value) {
            Double[] value_1= new Double[2];
            value_1[0] = (value[2]*value[2])/(value[3]*value[3])*1000;
            value_1[1] = value_1[0]*100;
            return value_1;

        }

        protected void onProgressUpdate(Double... progress) {

        }

        protected void onPostExecute(Double[] result) {
            Ergebnis_1.setText(String.format( "%.2f", result[0] )+" mbar");
            Ergebnis_2.setText(String.format( "%.2f", result[1] )+" Pascal");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ventil, container, false);
        final Button button = (Button) rootView.findViewById(R.id.Berechnen); //final=abgeschlossen, keine Veränderung mehr möglich
        Ergebnis_1 = (TextView) rootView.findViewById(R.id.Druckverlust_Ergebnis_mbar);
        Ergebnis_2 = (TextView) rootView.findViewById(R.id.Druckverlust_Ergebnis_pascal);
        Ergebnis_1.setText("");
        Ergebnis_2.setText("");
        Volumenstrom_text = (EditText) rootView.findViewById(R.id.Volumenstrom_2_text);
        Leistung_text = (EditText) rootView.findViewById(R.id.Leistung_2_text);
        Spreizung_text = (EditText) rootView.findViewById(R.id.Spreizung_2_text);
        Kvs_text=(EditText) rootView.findViewById(R.id.kvs_text);





        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                double[] value_double = new double[4];
                String Leistung;
                String Spreizung;
                Leistung = Leistung_text.getText().toString();
                Spreizung = Spreizung_text.getText().toString();
                if(!Spreizung.matches("")){
                    value_double[1]= Double.parseDouble(Spreizung_text.getText().toString());
                }
                if(!Leistung.matches("")){
                    value_double[0]= Double.parseDouble(Leistung_text.getText().toString());
                }
                String Volumenstrom;
                Volumenstrom = Volumenstrom_text.getText().toString();
                if (Volumenstrom.matches("")) {
                    value_double[2]=value_double[0]/(value_double[1]*1.163);
                    Volumenstrom_text.setText(String.format( "%.2f",value_double[2]));
                }
                else {
                    value_double[2] = Double.parseDouble(Volumenstrom_text.getText().toString());
                }
                value_double[3]= Double.parseDouble(Kvs_text.getText().toString());



                new Berechnen().execute(value_double[0],value_double[1],value_double[2], value_double[3]);

            }
        });


        return rootView;
    }

}









