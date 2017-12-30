package com.example.morten.overtidapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by morten on 27.12.2017.
 */

public class Overtid implements Parcelable {
    private String dato;
    private int antTimer;

    public Overtid(int antTimer) {
        this.antTimer = antTimer;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public int getAntTimer() {
        return antTimer;
    }

    public void setAntTimer(int antTimer) {
        this.antTimer = antTimer;
    }



    //Mine metoder


    public static String visTotatl(){
        int teller=0;
        for(int i=0;i<MainActivity.overtid.size();i++) {
            teller+=MainActivity.overtid.get(i).getAntTimer();

        }

        return ""+teller;
    }


    protected Overtid(Parcel in) {
        dato = in.readString();
        antTimer = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }



    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dato);
        dest.writeInt(antTimer);
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
}