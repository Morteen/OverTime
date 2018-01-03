package com.example.morten.overtidapp;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends android.support.v4.app.Fragment  {
static String antTimerOvertid,totSum,timerCurrMnd,sumCurrMnd;
static TextView antTimer, visbase,visTotalsum,timerDenneMnd,sumDenneMnd;
double mNum,mSum,curMnd,curMndSum;
MyDbHandler dbHandler;
static ProgressBar progressBar,progressDenneMND;
static int progressStatus, progressStatusDenneMND;

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
        curMnd = getArguments() != null ? getArguments().getDouble("curMnd") : 0.0;
        curMndSum = getArguments() != null ? getArguments().getDouble("curMndsum") : 0.0;
        antTimerOvertid=Double.toString(mNum);
        totSum=Double.toString(mSum);
        timerCurrMnd=Double.toString(curMnd);
        sumCurrMnd=Double.toString(Overtid.lonnDenneMND());

        if(MainActivity.overtid.size()>0){
            antTimerOvertid=Overtid.visTotatl();
            totSum=Double.toString(Overtid.visTotatlIntjent());
            timerCurrMnd=Double.toString(Overtid.timerDenneMnd());
            progressStatus=Overtid.avstandTilTargetSum();
            progressStatusDenneMND=Overtid.avstandDenneMND();
            Toast.makeText(getActivity(), "Progress status"+progressStatus, Toast.LENGTH_SHORT).show();

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
        sumDenneMnd=(TextView)view.findViewById(R.id.sumDenneMnd);
        progressBar=(ProgressBar)view.findViewById(R.id.MyprogressBar);
        progressDenneMND=(ProgressBar)view.findViewById(R.id.denneMNDprogressBar);



        timerDenneMnd=(TextView)view.findViewById(R.id.timerDenneMnd);

        antTimer.setText( antTimerOvertid);
        visTotalsum.setText(totSum);
        timerDenneMnd.setText(timerCurrMnd);
        sumDenneMnd.setText(sumCurrMnd);
        progressBar.setProgress(progressStatus);
        progressDenneMND.setProgress(progressStatusDenneMND);




        return view;
    }//Slutt på onCreateView
    ///Lager data når man snur devicen
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("num", mNum);
        outState.putDouble("sum", mSum);
        outState.putDouble("curMnd",curMnd);
        outState.putDouble("curMndsum",curMndSum);


    }









}

