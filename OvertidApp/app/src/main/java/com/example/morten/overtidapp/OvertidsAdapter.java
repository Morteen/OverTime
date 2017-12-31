package com.example.morten.overtidapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by morten on 30.12.2017.
 */

public class OvertidsAdapter  extends BaseAdapter {


    Context mContext;
    ArrayList<Overtid> minOvertid;
    LayoutInflater mInflater;


    //Construtør for adapter
    public OvertidsAdapter(Context mContext, ArrayList<Overtid> minOvertid) {
        this.mContext = mContext;
        this.minOvertid = minOvertid;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return minOvertid.size();
    }

    @Override
    public Object getItem(int position) {
        return minOvertid.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {

            //XML fil-navnet
            convertView = mInflater.inflate(R.layout.overtid_liste, null);
            viewHolder = new ViewHolder();


            viewHolder.tvDato = (TextView) convertView.findViewById(R.id.tvDato);
            viewHolder.tvTimer = (TextView) convertView.findViewById(R.id.tvTimer);
            viewHolder.tvInfo = (TextView) convertView.findViewById(R.id.tvInfo);



            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


       Overtid currentTid = minOvertid.get(position);
        //Setter verdien til  Overtid objektet  i denne posisjonen i riktig textview
        viewHolder.tvDato.setText(currentTid.getDato());
        viewHolder.tvTimer.setText(Double.toString(currentTid.getAntTimer()));
        viewHolder.tvInfo.setText(currentTid.getInfo());



        return convertView;
    }



    private static class ViewHolder {
        public TextView tvDato;
        public TextView tvTimer;
        public TextView tvInfo;



    }


}
