package com.example.morten.overtidapp;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends android.support.v4.app.Fragment  {
private String antTimerOvertid;
TextView testText, visbase;
double mNum;
MyDbHandler dbHandler;

    public FragmentOne() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Legger disse data her slik at tlf oppdatere seg når man snur den
        onRestorInstanteState virker i fragmenter*/
        mNum = getArguments() != null ? getArguments().getDouble("num") : 0.0;
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

       dbHandler = new MyDbHandler(getActivity(),null,null,1);

        testText=(TextView)view.findViewById(R.id.testText);
        visbase=(TextView)view.findViewById(R.id.visbase);

        testText.setText( antTimerOvertid);
        if(MyDbHandler.doesDatabaseExist(getActivity(),MyDbHandler.DATABASE_NAME)){
       if(dbHandler.dataBaseToString()!=""||dbHandler.dataBaseToString()!=null) {
            //
           Toast.makeText(getActivity(), "db stringen er ikke blank", Toast.LENGTH_SHORT).show();
           visbase.setText(dbHandler.dataBaseToString());
        }
            Toast.makeText(getActivity(), "Basen finnes", Toast.LENGTH_SHORT).show();
        }

        return view;
    }//Slutt på onCreateView
    ///Lager data når man snur devicen
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("num", mNum);


    }









}

