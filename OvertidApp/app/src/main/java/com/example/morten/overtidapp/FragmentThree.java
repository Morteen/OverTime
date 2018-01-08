package com.example.morten.overtidapp;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentThree extends android.support.v4.app.Fragment {

ImageView seilboat;
TextView motivasjon;
    public FragmentThree() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_fragment_three, container, false);

        seilboat=(ImageView)view.findViewById(R.id.seilboat);
        motivasjon=(TextView)view.findViewById(R.id.motivasjon);

        antDagerTil();

        return view;
    }


private void antDagerTil(){
    // Creates two calendars instances
    Calendar cal1 = Calendar.getInstance();
    Calendar cal2 = Calendar.getInstance();

    // Set the date for both of the calendar instance
    //cal1.set(2006, Calendar.DECEMBER, 30);
    //cal1.
    cal2.set(2018, Calendar.JULY, 7);

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

    motivasjon.setText(diffDays+"");
}


}
