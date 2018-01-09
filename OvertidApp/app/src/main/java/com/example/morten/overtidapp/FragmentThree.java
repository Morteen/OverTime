package com.example.morten.overtidapp;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentThree extends android.support.v4.app.Fragment {


    TextView motivasjon, dager;
    String reiser;

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

        reiser = getResources().getString(R.string.reiser);

        dager.setText(MainActivity.antDagerTil());
        motivasjon.setText(reiser);

        return view;
    }




}
