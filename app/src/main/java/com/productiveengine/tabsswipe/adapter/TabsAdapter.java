package com.productiveengine.tabsswipe.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.productiveengine.super_compare.BenchmarkSettingsUI;
import com.productiveengine.super_compare.BenchmarkUI;

public class TabsAdapter extends FragmentStatePagerAdapter{

    private int TOTAL_TABS = 2;

    public TabsAdapter(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Fragment getItem(int index) {
        Fragment fragment = null;

        switch (index) {
            case 0:
                fragment = new BenchmarkUI();
                break;
            case 1:
                fragment = new BenchmarkSettingsUI();
                break;
            default:
                fragment = new BenchmarkUI();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return TOTAL_TABS;
    }

}