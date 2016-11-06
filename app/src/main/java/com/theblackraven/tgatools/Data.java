package com.theblackraven.tgatools;

/**
 * Created by x220 on 11.06.2016.
 */
public class Data {

    private int idx;
    private String name;
    private double da;
    private double di;
    private double k;
    private long id;


    public Data(int idx, String name, double da, double di, double k) {
        this.idx = idx;
        this.name =name;
        this.da = da;
        this.di = di;
        this.k = k;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }






    @Override
    public String toString() {
        String output = idx + ";" + name + ";" + da + ";" + di + ";" + k + ";";

        return output;
    }

    public String toStringNames() {
        String output = "Einträge" + name;

        return output;
    }
}