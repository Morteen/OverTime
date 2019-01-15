package com.example.morten.overtidapp;


import android.app.Fragment;
import android.os.AsyncTask;
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
static TextView antTimer, visbase,visTotalsum,timerDenneMnd,sumDenneMnd,prosentMND,prosentAar;
double mNum,mSum,curMnd,curMndSum,sumDMnd;
MyDbHandler dbHandler;
static ProgressBar progressBar,progressDenneMND;
static int progressStatus, progressStatusDenneMND;
    private ProgressBar spinner;


    MyDbHandler dbhandler;

    public FragmentOne() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Legger disse data her slik at tlf oppdatere seg når man snur den
        onRestorInstanteState virker i fragmenter*/
        mNum = getArguments() != null ? getArguments().getDouble("num") : 0.0;
        sumDMnd = getArguments() != null ? getArguments().getDouble("sumDenneMnd") : 0.0;
        mSum = getArguments() != null ? getArguments().getDouble("sum") : 0.0;
        curMnd = getArguments() != null ? getArguments().getDouble("curMnd") : 0.0;
        curMndSum = getArguments() != null ? getArguments().getDouble("curMndsum") : 0.0;
        progressStatus=getArguments()!=null?getArguments().getInt("progressStatus"):0;

        antTimerOvertid=Double.toString(mNum);
        totSum=Double.toString(mSum);
        timerCurrMnd=Double.toString(curMnd);
        sumCurrMnd=Double.toString(sumDMnd);

        if(MainActivity.overtid!=null&&MainActivity.overtid.size()>0){
            antTimerOvertid=Overtid.visTotatl();
            totSum=Double.toString(Overtid.visTotatlIntjent());
            timerCurrMnd=Double.toString(Overtid.timerDenneMnd());
            progressStatus=Overtid.avstandTilTargetSum();
            progressStatusDenneMND=Overtid.avstandDenneMND();
            sumCurrMnd=Double.toString(Overtid.lonnDenneMND());


        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_fragment_one2, container, false);
        new setlistAsynk().execute();
       dbHandler = new MyDbHandler(getActivity(),null,null,1);
        spinner=(ProgressBar)view.findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        antTimer=(TextView)view.findViewById(R.id.antTimer);
        visbase=(TextView)view.findViewById(R.id.visbase);
        visTotalsum=(TextView)view.findViewById(R.id.visTotalsum) ;
        sumDenneMnd=(TextView)view.findViewById(R.id.sumDenneMnd);
        progressBar=(ProgressBar)view.findViewById(R.id.MyprogressBar);
        progressDenneMND=(ProgressBar)view.findViewById(R.id.denneMNDprogressBar);
        prosentMND=(TextView)view.findViewById(R.id.prosentMND);
        prosentAar=(TextView)view.findViewById(R.id.prosentAar);



        timerDenneMnd=(TextView)view.findViewById(R.id.timerDenneMnd);

        antTimer.setText( antTimerOvertid);
        visTotalsum.setText(totSum);
        timerDenneMnd.setText(timerCurrMnd);
        sumDenneMnd.setText(sumCurrMnd);
        progressBar.setProgress(progressStatus);
        prosentAar.setText(progressStatus+" %");
        progressDenneMND.setProgress(progressStatusDenneMND);
        prosentMND.setText(progressStatusDenneMND+" %");




        return view;
    }//Slutt på onCreateView
    ///Lager data når man snur devicen
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("num", mNum);
        outState.putDouble("sumDenneMnd", sumDMnd);
        outState.putDouble("sum", mSum);
        outState.putDouble("curMnd",curMnd);
        outState.putDouble("curMndsum",curMndSum);
        outState.putInt("progressStatus",progressStatus);
        // TODO: 06.01.2018  Legg til progrss status slik at disse virker ved orienterings forandring


    }



   public static void oppdaterFragOne(){


            //Oppdaterer fragOne tekstene
            FragmentOne.antTimer.setText(Overtid.visTotatl());
            FragmentOne.visTotalsum.setText(Double.toString(Overtid.visTotatlIntjent()));
            FragmentOne.timerDenneMnd.setText(Double.toString(Overtid.timerDenneMnd()));
            FragmentOne.progressStatus = Overtid.avstandTilTargetSum();
            FragmentOne.progressBar.setProgress(FragmentOne.progressStatus);
            FragmentOne. prosentAar.setText(FragmentOne.progressStatus+" %");
            FragmentOne.timerCurrMnd = Double.toString(Overtid.timerDenneMnd());
            FragmentOne.sumDenneMnd.setText(FragmentOne.sumCurrMnd);
            FragmentOne.progressStatusDenneMND = Overtid.avstandDenneMND();
            FragmentOne. prosentMND.setText(FragmentOne.progressStatusDenneMND+" %");
            FragmentOne.progressDenneMND.setProgress(FragmentOne.progressStatusDenneMND);

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


            dbhandler = new MyDbHandler(getActivity(), null, null, MyDbHandler.DATABASEVERSJON);
            MainActivity.overtid = dbhandler.getAllOvertid(dbhandler);
            if(MainActivity.overtid.size()>0){
                return null;
            }else{
                return "Kunne ikke laste listen!";
            }


        }


        @Override
        protected void onPostExecute(String result) {
            spinner.setVisibility(View.GONE);

            if (result== null) {
                oppdaterFragOne();

            } else {

                Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();

            }

        }


    }//Slutt på asynk classen










}

