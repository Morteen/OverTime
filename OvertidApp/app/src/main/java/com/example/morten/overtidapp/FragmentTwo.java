package com.example.morten.overtidapp;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwo extends android.support.v4.app.Fragment {
    static ArrayList<Overtid> overtidListe;

    private ListView startListView;

    public FragmentTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_two2, container, false);


        startListView = (ListView) view.findViewById(R.id.startList);


        OvertidsAdapter adapter = new OvertidsAdapter(getContext(), MainActivity.overtid);
        startListView.setAdapter(adapter);


        return view;
    }

}
