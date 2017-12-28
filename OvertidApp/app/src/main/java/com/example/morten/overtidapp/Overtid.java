package com.example.morten.overtidapp;

/**
 * Created by morten on 27.12.2017.
 */

public class Overtid {
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

}
