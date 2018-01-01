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
private String antTimerOvertid,totSum;
TextView antTimer, visbase,visTotalsum;
double mNum,mSum;
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
        mSum = getArguments() != null ? getArguments().getDouble("sum") : 0.0;
        antTimerOvertid=Double.toString(mNum);
        totSum=Double.toString(mSum);
        if(MainActivity.overtid.size()>0){
            antTimerOvertid=Overtid.visTotatl();
            totSum=Double.toString(Overtid.visTotatlIntjent());

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_fragment_one2, container, false);

       dbHandler = new MyDbHandler(getActivity(),null,null,1);

        antTimer=(TextView)view.findViewById(R.id.antTimer);
        visbase=(TextView)view.findViewById(R.id.visbase);
        visTotalsum=(TextView)view.findViewById(R.id.visTotalsum) ;

        antTimer.setText( antTimerOvertid);
        visTotalsum.setText(totSum);



        return view;
    }//Slutt på onCreateView
    ///Lager data når man snur devicen
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("num", mNum);
        outState.putDouble("sum", mSum);


    }









}

