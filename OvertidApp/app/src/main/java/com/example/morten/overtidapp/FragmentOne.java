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
public class FragmentOne extends android.support.v4.app.Fragment  {
private String antTimerOvertid;
TextView testText;
int mNum;
    public FragmentOne() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments() != null ? getArguments().getInt("num") : 0;
        antTimerOvertid=mNum+"";
        if(MainActivity.overtid.size()>0){
            antTimerOvertid=Overtid.visTotatl();


        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_fragment_one2, container, false);
        testText=(TextView)view.findViewById(R.id.testText);

        testText.setText( antTimerOvertid);


        return view;
    }//Slutt på onCreateView
    ///Lager data når man snur devicen
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("num", mNum);


    }









}

