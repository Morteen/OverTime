package com.example.morten.overtidapp;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class Frag_Fire extends android.support.v4.app.Fragment {


    public Frag_Fire() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_frag__fire, container, false);

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, lagTimelistePrMnd());

        ListView listView = (ListView)view.findViewById(R.id.simpleListView);
        listView.setAdapter(itemsAdapter);


        return view;
    }
    private static ArrayList<String> lagTimelistePrMnd(){
        ArrayList<String>temp = new ArrayList<>();
        if(MainActivity.overtid.size()>0){
            int teller=1;
           int antTimer=0;
            String sjekk;
for(int i=0;i<MainActivity.overtid.size();i++){
            sjekk = MainActivity.overtid.get(teller-1).getDato();
            String[] date = sjekk.split(Pattern.quote("."));
            if(teller==Integer.parseInt(date[1])){
                antTimer+=MainActivity.overtid.get(i).getAntTimer();
            }else{
                temp.add(Integer.toString(antTimer));
                teller++;
            }
}


        }
            return temp;
    }

}
