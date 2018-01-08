package com.example.morten.overtidapp;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentThree extends android.support.v4.app.Fragment {


    TextView motivasjon, dager;
    String text, reiser;

    public FragmentThree() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_three, container, false);


        motivasjon = (TextView) view.findViewById(R.id.motivasjon);
        dager = (TextView) view.findViewById(R.id.dager);
        text = getResources().getString(R.string.dager);
        reiser = getResources().getString(R.string.reiser);

        antDagerTil();

        return view;
    }


    private void antDagerTil() {
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
        dager.setText(Long.toString(diffDays));
        motivasjon.setText(reiser);
    }


}
