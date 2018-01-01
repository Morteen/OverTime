package com.example.morten.overtidapp;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by morten on 27.12.2017.
 */

public class Overtid implements Parcelable {
    private String dato;
    private double antTimer;
    private String info;

    private static final double TIMEBETALING =330.0;

///Dette er til SQLliteBasen
    static final String TABELL_NAVN = "Overtid";
    static final String KOL_NAVN_Dato = "Dato";
    static final String KOL_NAVN_antTimer ="antTimer";
    static final String KOL_NAVN_Info = "Info";



    public Overtid() {

    }
    public Overtid(int antTimer) {
        this.antTimer = antTimer;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public double getAntTimer() {
        return antTimer;
    }

    public void setAntTimer(double antTimer) {
        this.antTimer = antTimer;
    }



    //Mine metoder


    public static String visTotatl(){
       double teller=0.0;
        for(int i=0;i<MainActivity.overtid.size();i++) {
            teller+=MainActivity.overtid.get(i).getAntTimer();

        }

        return ""+teller;
    }
    public static double visTotatlIntjent(){
        double teller=0.0;
        for(int i=0;i<MainActivity.overtid.size();i++) {
            teller+=MainActivity.overtid.get(i).getAntTimer();

        }

        return teller*TIMEBETALING;
    }


    protected Overtid(Parcel in) {
        dato = in.readString();
        antTimer = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }



    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dato);
        dest.writeDouble(antTimer);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Overtid> CREATOR = new Parcelable.Creator<Overtid>() {
        @Override
        public Overtid createFromParcel(Parcel in) {
            return new Overtid(in);
        }

        @Override
        public Overtid[] newArray(int size) {
            return new Overtid[size];
        }
    };



    public static ArrayList<Overtid> lagOvertidListeFraSqlite(Cursor cursor) {
        ArrayList<Overtid> overtidListe = new ArrayList<Overtid>();
        while (cursor.moveToNext()) {
            Overtid tid = new Overtid();
            tid.setDato(cursor.getString(cursor.getColumnIndex("Dato")));
            tid.setAntTimer(cursor.getDouble(cursor.getColumnIndex("Timer")));
            tid.setInfo(cursor.getString(cursor.getColumnIndex("Info")));


            overtidListe .add(tid);
        }

        return overtidListe ;

    }

}