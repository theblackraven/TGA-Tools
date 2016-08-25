package com.theblackraven.tgatools;

/**
 * Created by x220 on 11.06.2016.
 */
public class Data {

    private double ra;
    private double ri;
    private double k;
    private long id;


    public Data(double ra, double ri, double k) {
        this.ra = ra;
        this.ri = ri;
        this.k = k;
    }


    public double getra() {
        return ra;
    }

    public void setra(double ra) {
        this.ra = ra;
    }


    public double getri() {
        return ri;
    }

    public void setri(double ri) {
        this.ri = ri;
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
        String output = id + ";" + ra + ";" + ri + ";" + k + ";";

        return output;
    }
}