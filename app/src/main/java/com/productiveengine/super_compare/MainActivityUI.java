package com.productiveengine.super_compare;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;

import com.productiveengine.Common.FormatString;
import com.productiveengine.Model.AlgorithmSettings;
import com.productiveengine.Model.BenchmarkSettings;
import com.productiveengine.Model.DBSetup;
import com.productiveengine.tabsswipe.adapter.TabsAdapter;

import java.util.List;

public class MainActivityUI extends ActionBarActivity implements android.support.v7.app.ActionBar.TabListener{

    private static final String TAG = "Main Activity";
    private ViewPager tabsviewPager;
    private ActionBar mActionBar;
    private TabsAdapter mTabsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        DBSetup dbSetup = new DBSetup();
        dbSetup.InitialSetup();

        List<AlgorithmSettings> books = AlgorithmSettings.listAll(AlgorithmSettings.class);

        tabsviewPager = (ViewPager) findViewById(R.id.tabspager);

        mTabsAdapter = new TabsAdapter(getSupportFragmentManager());

        tabsviewPager.setAdapter(mTabsAdapter);

        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        Tab benchmarkTab = getSupportActionBar().newTab().setText("Benchmark").setTabListener(this);
        Tab benchmarkSettings = getSupportActionBar().newTab().setText("Settings").setTabListener(this);
        //Tab aboutTab = getSupportActionBar().newTab().setText("About").setTabListener(this);

        getSupportActionBar().addTab(benchmarkTab);
        getSupportActionBar().addTab(benchmarkSettings);

        //getSupportActionBar().setSelectedNavigationItem(1);
        //Log.d(TAG,android.os.Build.DEVICE + " " +android.os.Build.MODEL + " " + android.os.Build.PRODUCT+ " " + Build.HARDWARE+ " " + Build.MANUFACTURER);

        //This helps in providing swiping effect for v7 compat library
        tabsviewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                getSupportActionBar().setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });

    }

    @Override
    public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTabSelected(Tab selectedtab, FragmentTransaction arg1) {
        // TODO Auto-generated method stub
        tabsviewPager.setCurrentItem(selectedtab.getPosition()); //update tab position on tap
    }

    @Override
    public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
        // TODO Auto-generated method stub

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_algo_info:

                //open Activity,Fragments or other action
                return true;
            case R.id.action_about:
                setContentView(R.layout.fragment_about);
                //open Activity,Fragments or other action
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}