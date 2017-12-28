package com.example.morten.overtidapp;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
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
import java.util.List;

public class MainActivity extends AppCompatActivity {
static ArrayList<Overtid> overtid;
    private List<DataUpdateListener> mListeners;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ViewPager pager=(ViewPager)findViewById(R.id.pager);
        PagerAdapter pAdpter = new PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pAdpter);
       overtid= new ArrayList<Overtid>();
        mListeners = new ArrayList<>();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
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
            Button reg = (Button) dialog.findViewById(R.id.reg_btn);
            Button avbryt = (Button) dialog.findViewById(R.id.avslutt_btn);
            TextView tekst = (TextView) dialog.findViewById(R.id.dialogBoxTekst);

            tekst.setText("Hei legg inn overtid");


            mDato.setEnabled(false);


            reg.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
                @Override
                public void onClick(View v) {

                    //Sjekker for blankt felt og like passord
                    if(mAntTimer.getText().toString().isEmpty() ){
                        Toast.makeText(MainActivity.this, "Du må legge inn antall timer overtid", Toast.LENGTH_SHORT).show();
                    }
                    else{

                        Overtid tid= new Overtid(Integer.parseInt(mAntTimer.getText().toString()));
                        overtid.add(tid);


                       FragmentOne fragment = new FragmentOne();
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.one, fragment);
                        transaction.commit();







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
    public interface DataUpdateListener {
        void onDataUpdate();
    }
    public synchronized void registerDataUpdateListener(DataUpdateListener listener) {
        mListeners.add(listener);
    }

    public synchronized void unregisterDataUpdateListener(DataUpdateListener listener) {
        mListeners.remove(listener);
    }
    public synchronized void dataUpdated() {
        for (DataUpdateListener listener : mListeners) {
            listener.onDataUpdate();
        }
    }
}
