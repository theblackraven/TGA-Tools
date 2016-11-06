package com.theblackraven.tgatools;

/**
 * Created by x220 on 11.06.2016.
 */
public class Data {

    private String name;
    private double da;
    private double di;
    private double k;
    private long id;


    public Data(String name, double da, double di, double k) {
        this.name =name;
        this.da = da;
        this.di = di;
        this.k = k;
    }


    public double getda() {
        return da;
    }

    public void setda(double da) {
        this.da = da;
    }


    public double getdi() {
        return di;
    }

    public void setdi(double di) {
        this.di = di;
    }

    public double getk() {
        return k;
    }

    public void setk(double k) {
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
        String output = id + ";" + name + ";" + da + ";" + di + ";" + k + ";";

        return output;
    }
}