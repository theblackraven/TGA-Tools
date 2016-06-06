package com.theblackraven.tgatools;


import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.graphics.Paint.Align;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;


public class VentilFragment extends Fragment {
    public TextView Ergebnis_1; //TextView Ergebnis erstellen damit man aus dem Asynctask darauf zugreifen kann!
    public TextView Ergebnis_2; //TextView Ergebnis erstellen damit man aus dem Asynctask darauf zugreifen kann!
    public EditText Leistung_text;
    public EditText Spreizung_text;
    public EditText Volumenstrom_text;
    public EditText Kvs_text;
    public Button bNewSeries;
    public EditText sv;







    public void openChart(double[] kv){
        int[] x = {0,10,20,30,40,50,60,70,80,90,100 };




        // Creating an  XYSeries for Income
        XYSeries VentilSeries = new XYSeries("Ventilkennlinie");

        // Adding data to Income and Expense Series
        for(int i=0;i<x.length;i++){
            VentilSeries.add(x[i], kv[i]);
        }


        // Creating a dataset to hold each series
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        // Adding Income Series to the dataset
        dataset.clear();
        dataset.addSeries(VentilSeries);

        // Adding Expense Series to dataset


        // Creating XYSeriesRenderer to customize incomeSeries
        XYSeriesRenderer VentilRenderer = new XYSeriesRenderer();
        VentilRenderer.setColor(Color.BLUE);
        VentilRenderer.setPointStyle(PointStyle.CIRCLE);
        VentilRenderer.setFillPoints(true);
        VentilRenderer.setLineWidth(10);
        VentilRenderer.setChartValuesTextSize(50);
        VentilRenderer.setDisplayChartValues(true);




        // Creating a XYMultipleSeriesRenderer to customize the whole chart
        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer(2);
        multiRenderer.setXLabels(0);
        multiRenderer.setChartTitle("Ventilkennlinie");
        multiRenderer.setChartTitleTextSize(80);
        multiRenderer.setXTitle("Hub in %");
        multiRenderer.setYTitle("kv/kv100 in %");
        multiRenderer.setAxisTitleTextSize(50);
        multiRenderer.setZoomButtonsVisible(false);
        multiRenderer.setShowGrid(true);
        multiRenderer.setShowGridX(true);
        multiRenderer.setShowGridY(true);
        multiRenderer.setZoomEnabled(true);
        multiRenderer.setLegendTextSize(50);
        multiRenderer.setGridColor(Color.RED);
        multiRenderer.setXLabels(10);
        multiRenderer.setYLabels(10);
        multiRenderer.setLabelsTextSize(40);
        multiRenderer.setMargins(new int[] {30, 120, 80, 0});
        multiRenderer.setXLabelsColor(Color.RED);
        multiRenderer.setYLabelsColor(0, Color.RED);
        multiRenderer.setLabelsColor(Color.RED);
        multiRenderer.setYLabelsAngle(40);
        multiRenderer.setLegendHeight(200);


        for(int i=0;i<x.length;i++){
            //multiRenderer.addXTextLabel(i+1, x[i]);
        }



        // Adding incomeRenderer and expenseRenderer to multipleRenderer
        // Note: The order of adding dataseries to dataset and renderers to multipleRenderer
        // should be same
        multiRenderer.addSeriesRenderer(VentilRenderer);


        // Creating an intent to plot line chart using dataset and multipleRenderer
        String title = "Kennlinie";
        Intent intent = ChartFactory.getLineChartIntent(getActivity(), dataset, multiRenderer, title);

        // Start Activity
        startActivity(intent);
    }








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
        bNewSeries = (Button) rootView.findViewById(R.id.Kennlinie_Ventil);
        sv=(EditText) rootView.findViewById(R.id.sv_text);


        bNewSeries.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Ventil berechnen
                //n Berechnen
                double n = Math.log(Double.parseDouble(sv.getText().toString()));
                double[] kvs = {1,2,3,4,5,6,7,8,9,10,11};
                for(int i=0; i<11; i++)
                {
                    kvs[i]=Math.exp(n*(i/10.0-1))*100;
                }

                openChart(kvs);
            }
        });









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









