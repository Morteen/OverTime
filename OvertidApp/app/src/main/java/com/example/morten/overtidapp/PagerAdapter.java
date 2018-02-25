package com.example.morten.overtidapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by morten on 27.12.2017.
 */

public class PagerAdapter extends FragmentPagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch ( i){
            case 0: return new FragmentOne();
            case 1: return  new FragmentTwo();
            case 2: return  new FragmentThree();

             default:break;

        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
