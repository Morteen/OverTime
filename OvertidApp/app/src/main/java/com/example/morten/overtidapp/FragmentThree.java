package com.example.morten.overtidapp;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


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



        return view;
    }

}
