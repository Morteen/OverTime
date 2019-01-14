package com.example.morten.overtidapp;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

/**
 * Created by morten on 27.12.2017.
 */

public class Overtid implements Parcelable {
    private String dato;
    private int id;
    private double antTimer;
    private String info;

    private static final double TIMEBETALING = 330.0;
    final static int TARGETSUM=20000;
    final static int TARGET_OF_MND=3300;

    ///Dette er til SQLliteBasen
    static final String TABELL_NAVN = "Overtid";
    static final String KOL_NAVN_Dato = "Dato";
    static final String KOL_NAVN_antTimer = "antTimer";
    static final String KOL_NAVN_Info = "Info";


    public Overtid() {

    }

    public Overtid(double antTimer) {
        this.antTimer = antTimer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    public static String visTotatl() {
        double teller = 0.0;
        for (int i = 0; i < MainActivity.overtid.size(); i++) {
            teller += MainActivity.overtid.get(i).getAntTimer();

        }

        return "" + teller;
    }

    public static double visTotatlIntjent() {
        double teller = 0.0;
        double temp;
        for (int i = 0; i < MainActivity.overtid.size(); i++) {
            teller += MainActivity.overtid.get(i).getAntTimer();

        }
        //temp variablen er fordi jeg ikke gidder å forandre alleveriene til int
        temp=(int) Math.round((double)teller*TIMEBETALING);
        return temp;
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


            overtidListe.add(tid);
        }

        return overtidListe;

    }

    public static Double timerDenneMnd() {
        Calendar calender = GregorianCalendar.getInstance();
        double sum = 0;
        String sjekk;
        String currMnd = Integer.toString(calender.get(Calendar.MONTH) + 1);

        if(MainActivity.overtid!=null) {
            for (int i = 0; i < MainActivity.overtid.size(); i++) {
                sjekk = MainActivity.overtid.get(i).getDato();
                String[] date = sjekk.split(Pattern.quote("."));

                if (currMnd.equals(date[1])) {

                    sum += MainActivity.overtid.get(i).getAntTimer();

                }
            }
        }

        return sum;

    }


    public static String timerMnd(int mnd) {

        String sum = "";
        String sjekk;
        String currMnd = Integer.toString(mnd + 1);

        for (int i = 0; i < MainActivity.overtid.size(); i++) {
            sjekk = MainActivity.overtid.get(i).getDato();
            String[] date = sjekk.split(Pattern.quote("."));

            if (currMnd.equals(date[1])) {

                sum += MainActivity.overtid.get(i).getAntTimer();

            }
        }


        return sum;

    }



    public  static int avstandTilTargetSum(){
       double inntjent=visTotatlIntjent();
       if((int) Math.round((double)inntjent/TARGETSUM *100)>1){
           //Dette er en måte å avrunde fra double til int på en korrekt måte
           return  (int) Math.round((double)inntjent/TARGETSUM *100);
       }else{
           return 1;
       }

    }

    public static double lonnDenneMND(){

        double timer=timerDenneMnd();
        double lonn=(int) Math.round((double)TIMEBETALING*timer);
        return lonn;
    }

    public static int avstandDenneMND(){
        double lonn=lonnDenneMND();
        if((int) Math.round((double)lonn/TARGET_OF_MND *100)>0){
            return (int) Math.round((double)lonn/TARGET_OF_MND *100);
        }
       else{
            return 0;
        }
    }
    public static ArrayList<Overtid> velgMnd( int sjekk) {
        ArrayList<Overtid>currMND= new ArrayList<Overtid>();

        if (MainActivity.overtid.size() > 0) {

            if(sjekk==0){
                return MainActivity.overtid;
            }
else {
                for (int i = 0; i < MainActivity.overtid.size(); i++) {

                    String[] date = MainActivity.overtid.get(i).getDato().split(Pattern.quote("."));

                    if (Integer.toString(sjekk).equals(date[1])) {

                        currMND.add(MainActivity.overtid.get(i));

                    }
                }
                return currMND;

            }


        }//slutt på If fra linje 195



return null;



    }

}