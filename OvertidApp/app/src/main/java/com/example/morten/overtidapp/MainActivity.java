package com.example.morten.overtidapp;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {
    static ArrayList<Overtid> overtid;
    MyDbHandler dbhandler;
    Calendar calender;
    private String dato;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        calender = GregorianCalendar.getInstance();

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter pAdpter = new PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pAdpter);
        overtid = new ArrayList<Overtid>();

             new setlistAsynk().execute();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Antall dager til vi reiser er "+antDagerTil(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        final Dialog dialog = new Dialog(MainActivity.this);
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_add) {
            dialog.setContentView(R.layout.layout_dialogbox);


            final EditText mDato = (EditText) dialog.findViewById(R.id.editDato);
            final EditText mAntTimer = (EditText) dialog.findViewById(R.id.editAntTimer);
            final EditText info = (EditText) dialog.findViewById(R.id.info);
            Button reg = (Button) dialog.findViewById(R.id.reg_btn);
            Button avbryt = (Button) dialog.findViewById(R.id.avslutt_btn);
            TextView tekst = (TextView) dialog.findViewById(R.id.dialogBoxTekst);

            tekst.setText("Hei legg inn overtid");


            reg.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
                @Override
                public void onClick(View v) {
                    final int mnd = calender.get(Calendar.MONTH) + 1;
                    final int dag = calender.get(Calendar.DAY_OF_MONTH);
                    dato = dag + "." + mnd;
                    //Sjekker for blankt felt og like passord
                    if (mAntTimer.getText().toString().isEmpty()) {
                        Toast.makeText(MainActivity.this, "Du må legge inn antall timer overtid", Toast.LENGTH_SHORT).show();
                    } else {

                        Overtid tid = new Overtid(Double.parseDouble(mAntTimer.getText().toString()));
                        if ((mDato.getText().toString().isEmpty())) {
                            tid.setDato(dato);

                        } else {
                            tid.setDato(mDato.getText().toString());
                        }
                        if (!info.getText().toString().isEmpty()) {
                            tid.setInfo(info.getText().toString());
                        }


                        overtid.add(tid);
                        dbhandler.addTid(tid);
                        FragmentOne.oppdaterFragOne();



                        dialog.dismiss();
                    }


                }

            });
            avbryt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog.dismiss();
                }
            });

            dialog.show();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    ///Lager data når man snur devicen
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("OvertidListe", overtid);


    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        overtid = savedInstanceState.getParcelableArrayList("OvertidListe");

    }
    public static String antDagerTil() {
        // Creates two calendars instances
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        // Sett dato for  target dato i den ene calendar instance
        //get Curdate i mili sek i den andre

        cal2.set(2018, Calendar.JULY, 6);

        // Get the represented date in milliseconds
        long millis1 = cal1.getTimeInMillis();
        long millis2 = cal2.getTimeInMillis();

        // Calculate difference in milliseconds
        long diff = millis2 - millis1;

        // Calculate difference in seconds
        long diffSeconds = diff / 1000;

        // Calculate difference in minutes
        long diffMinutes = diff / (60 * 1000);

        // Calculate difference in hours
        long diffHours = diff / (60 * 60 * 1000);

        // Calculate difference in days
        long diffDays = diff / (24 * 60 * 60 * 1000);


        return Long.toString(diffDays);
    }







    class setlistAsynk extends AsyncTask<String, Void, String> {



        public setlistAsynk() {
            super();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected String doInBackground(String... params) {

            dbhandler = new MyDbHandler(MainActivity.this, null, null, MyDbHandler.DATABASEVERSJON);
            overtid = dbhandler.getAllOvertid(dbhandler);
            if(overtid.size()>0){
                return null;
            }else{
                return "Kunne ikke laste listen!";
            }


        }


        @Override
        protected void onPostExecute(String result) {


            if (result== null) {


            } else {

                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();

            }

        }


    }//Slutt på asynk classen


}
