package com.example.morten.overtidapp;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwo extends android.support.v4.app.Fragment implements AdapterView.OnItemClickListener {
    static ArrayList<Overtid> overtidListe;

    private ListView startListView;
    private MyDbHandler db;
    OvertidsAdapter adapter;
    Spinner spinner;

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
        adapter = new OvertidsAdapter(getContext(), MainActivity.overtid);
        startListView.setAdapter(adapter);

        spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.mnd_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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


        }


    }

    public void onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

    }

//Spinner onClick metoder
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        // An item was selected. You can retrieve the selected item using
        //int res= (int) parent.getItemAtPosition(pos);
        Toast.makeText(getActivity(), "Hei fra spinner: " + pos, Toast.LENGTH_SHORT).show();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


}
