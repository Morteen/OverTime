package com.example.morten.overtidapp;


import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwo extends android.support.v4.app.Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    static ArrayList<Overtid> overtidListe;

    private ListView startListView;
    private MyDbHandler db;
    OvertidsAdapter adapter;
    Spinner spinner;
    Dialog dialog;
    String[] spinnerItems = new String[]{
            "Vis alle",
            "Januar",
            "Februar",
            "Mars",
            "April",
            "Mai",
            "Juni",
            "Juli",
            "Agust",
            "September",
            "Oktober",
            "November",
            "Desember",

    };

    public FragmentTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_two2, container, false);

        db = new MyDbHandler(getActivity(), null, null, MyDbHandler.DATABASEVERSJON);
        startListView = (ListView) view.findViewById(R.id.startList);
        startListView.setOnItemClickListener(this);
        startListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {


                //Lager en dialogBox for å gi brukeren en mulighet til å ombestemme seg
                dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.layout_alertdialog);
                Button ok = (Button) dialog.findViewById(R.id.OkButton);
                Button avbryt = (Button) dialog.findViewById(R.id.cansel);
                TextView tekst = (TextView) dialog.findViewById(R.id.alertdialogText);
                final int posison=pos;
               ok.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        slettOgOppdater(posison);
                        dialog.dismiss();
                    }
                });

                avbryt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(),"Hei fra LongClick! "+pos, Toast.LENGTH_SHORT).show();

                Log.v("long clicked","pos: " + pos);
                dialog.show();
                return true;
            }
        });
        adapter = new OvertidsAdapter(getContext(), MainActivity.overtid);
        startListView.setAdapter(adapter);

        spinner = (Spinner) view.findViewById(R.id.spinner);
       /* ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.mnd_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/
        ArrayAdapter<String> spinneradapter = new ArrayAdapter<String>(getActivity(),R.layout.spinner_textview_align,spinnerItems );
        // Apply the adapter to the spinner
        spinneradapter.setDropDownViewResource(R.layout.spinner_textview_align);
        spinner.setAdapter(spinneradapter);
        spinner.setOnItemSelectedListener(this);

        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


/*
        int minId = MainActivity.overtid.get(position).getId();
        if (position > -1) {
            db.deleteTid(minId);

            MainActivity.overtid.remove(position);
            adapter.notifyDataSetChanged();

            //Oppdaterer fragOne tekstene
            FragmentOne.antTimer.setText(Overtid.visTotatl());
            FragmentOne.visTotalsum.setText(Double.toString(Overtid.visTotatlIntjent()));
            FragmentOne.timerDenneMnd.setText(Double.toString(Overtid.timerDenneMnd()));
            FragmentOne.progressStatus = Overtid.avstandTilTargetSum();
            FragmentOne.progressBar.setProgress(FragmentOne.progressStatus);
            FragmentOne.timerCurrMnd = Double.toString(Overtid.timerDenneMnd());
            FragmentOne.sumDenneMnd.setText(FragmentOne.sumCurrMnd);
            FragmentOne.progressStatusDenneMND = Overtid.avstandDenneMND();
            FragmentOne.progressDenneMND.setProgress(FragmentOne.progressStatusDenneMND);


        }*/


    }

    public void onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Spinner position !:"+position , Toast.LENGTH_SHORT).show();
        ArrayList<Overtid> temp = Overtid.velgMnd(position);
        if (temp.size() > 0) {
            adapter = new OvertidsAdapter(getContext(), temp);
            startListView.setAdapter(adapter);
        } else {

            Toast.makeText(getActivity(), "Du jobbet ikke overtid den måneden!:" + position, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        adapter = new OvertidsAdapter(getContext(), MainActivity.overtid);
        startListView.setAdapter(adapter);

    }
    private void slettOgOppdater(int position){
        int minId = MainActivity.overtid.get(position).getId();
        if (position > -1) {
            db.deleteTid(minId);

            MainActivity.overtid.remove(position);
            adapter.notifyDataSetChanged();
            FragmentOne.oppdaterFragOne();
            //Oppdaterer fragOne tekstene
           /* FragmentOne.antTimer.setText(Overtid.visTotatl());
            FragmentOne.visTotalsum.setText(Double.toString(Overtid.visTotatlIntjent()));
            FragmentOne.timerDenneMnd.setText(Double.toString(Overtid.timerDenneMnd()));
            FragmentOne.progressStatus = Overtid.avstandTilTargetSum();
            FragmentOne.progressBar.setProgress(FragmentOne.progressStatus);
            FragmentOne. prosentAar.setText(FragmentOne.progressStatus+" %");
            FragmentOne.timerCurrMnd = Double.toString(Overtid.timerDenneMnd());
            FragmentOne.sumDenneMnd.setText(FragmentOne.sumCurrMnd);
            FragmentOne.progressStatusDenneMND = Overtid.avstandDenneMND();
            FragmentOne. prosentMND.setText(FragmentOne.progressStatusDenneMND+" %");
            FragmentOne.progressDenneMND.setProgress(FragmentOne.progressStatusDenneMND);*/

    }


}

}
