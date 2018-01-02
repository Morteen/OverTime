package com.example.morten.overtidapp;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwo extends android.support.v4.app.Fragment implements AdapterView.OnItemClickListener{
    static ArrayList<Overtid> overtidListe;

    private ListView startListView;
private MyDbHandler db;
    OvertidsAdapter adapter;
    public FragmentTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_two2, container, false);

        db= new MyDbHandler(getActivity(),null,null,MyDbHandler.DATABASEVERSJON);
        startListView = (ListView) view.findViewById(R.id.startList);
       startListView.setOnItemClickListener(this);
        adapter = new OvertidsAdapter(getContext(), MainActivity.overtid);
        startListView.setAdapter(adapter);


        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), MainActivity.overtid.size()+" Før sletting", Toast.LENGTH_SHORT).show();
        int minId=MainActivity.overtid.get(position).getId();
if(position>-1){
    db.deleteTid(minId);
    ArrayList<Overtid> testlist= db.getAllOvertid(db);
    Toast.makeText(getActivity(), testlist.size()+" ETTER sletting", Toast.LENGTH_SHORT).show();
    MainActivity.overtid.remove(position);
    adapter.notifyDataSetChanged();

//Oppdaterer fragOne tekstene
    FragmentOne.antTimer.setText(Overtid.visTotatl());
    FragmentOne.visTotalsum.setText(Double.toString(Overtid.visTotatlIntjent()));



}





    }

    public void onItemLongClick(AdapterView<?> parent, View view, int position, long id){

    }
}
