package com.example.morten.overtidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by morten on 31.12.2017.
 */

public class MyDbHandler extends SQLiteOpenHelper {

    public static final int DATABASEVERSJON = 1;
    public static final String DATABASE_NAME = "Overtid.db";
    public static final String TABLE_NAME= Overtid.TABELL_NAVN;
    public static final String COL_ID= "id";
    public static final String DATO = Overtid.KOL_NAVN_Dato;
    public static final String TIMER =Overtid.KOL_NAVN_antTimer;
    public static final String INFO = Overtid.KOL_NAVN_Info;



    //Constructor
    public MyDbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASEVERSJON);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+TABLE_NAME+"(" + COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+DATO+" TEXT, "+TIMER+" TEXT, "+INFO+" TEXT "+")";
        //String query="CREATE TABLE "+TABLE_NAME+"(" + COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT "+DATO+" TEXT " +")";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);

    }

    //Legg til en ny rad i basen
    public  void addTid(Overtid tid){
        ContentValues values = new ContentValues();
        values.put(DATO,tid.getDato());
        values.put(TIMER,Double.toString(tid.getAntTimer()));
        values.put(INFO,tid.getInfo());


        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    //slett rad fra basen
    public void deleteTid(int id){
        SQLiteDatabase db = getWritableDatabase();
        //db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE "+COL_ID+"=\""+id+"\";");
        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE "+COL_ID+"="+id+";");
    }


    public double getTotalTid(){
        double tid=0.0;
        SQLiteDatabase db = getWritableDatabase();
        String query="SELECT * FROM "+TABLE_NAME;//+" WHERE 1";

        //Courser objektet peker til en plassering i resultatet
        Cursor c = db.rawQuery(query,null);
        if(  c.moveToFirst()){

            do{
                tid+=Double.parseDouble(c.getString(c.getColumnIndex(TIMER)));

            }while (c.moveToNext());

        }



        db.close();
        return tid;

    }

//Print ut basen som en string
        public String dataBaseToString(){
        String dbString="";
        SQLiteDatabase db = getWritableDatabase();
        String query="SELECT * FROM "+TABLE_NAME;//+" WHERE 1";

        //Courser objektet peker til en plassering i resultatet
        Cursor c = db.rawQuery(query,null);
       if(  c.moveToFirst()){

           do{
               dbString+=c.getString(c.getColumnIndex(DATO))+" "+c.getString(c.getColumnIndex(TIMER))+" "+c.getString(c.getColumnIndex(INFO));
               dbString+="\n";
           }while (c.moveToNext());

       }



        db.close();
        return dbString;

    }


    public Cursor CursorRes(){
        String dbString="";
        SQLiteDatabase db = getWritableDatabase();
        String query="SELECT * FROM "+TABLE_NAME;//+" WHERE 1";

        //Courser objektet peker til en plassering i resultatet
        Cursor c = db.rawQuery(query,null);
        db.close();
        return c;

    }



    public static boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }



    public  ArrayList<Overtid> getAllOvertid(MyDbHandler dop) {
        ArrayList<Overtid> tid = new ArrayList<Overtid>();
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] culoums = {
                COL_ID,DATO,TIMER,INFO

        };
        Cursor c = SQ.query(TABLE_NAME, culoums, null, null, null, null, null);//null verdien er forskjellig sorteringer having orderby osv

        // går gjennom listen og legger til objekter
        if (c.moveToFirst()) {
            do {
                Overtid t = new Overtid();
                t.setId(Integer.parseInt(c.getString((c.getColumnIndex(COL_ID)))));
                t.setDato(c.getString((c.getColumnIndex(DATO))));
                t.setAntTimer(Double.parseDouble(c.getString(c.getColumnIndex(TIMER))));
                t.setInfo(c.getString(c.getColumnIndex(INFO)));

                // Legget til tm listen
                tid.add(t);
            } while (c.moveToNext());
        }

        return tid;
    }






}
