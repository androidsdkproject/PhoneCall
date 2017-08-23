package com.example.android1.phone;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

/**
 * Created by Android1 on 7/5/2017.
 */

public class Pager extends FragmentStatePagerAdapter {
    int tabCount;


    public Pager(FragmentManager fm, int tabCount) {
        super(fm);

        this.tabCount= tabCount;
    }


    @Override
    public Fragment getItem(int position) {



        switch (position) {
            case 0:
                Favourite tab1 = new Favourite();
                return tab1;
            case 1:
                History tab2 = new History();
                return tab2;
            case 2:
                Contact tab3 = new Contact();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }




}
