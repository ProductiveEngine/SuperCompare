package com.productiveengine.super_compare;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AboutUI extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_about);
        //View v = inflate(R.layout.fragment_about, container, false);
        // ((TextView) v.findViewById(R.id.txtEULA)).setMovementMethod(new ScrollingMovementMethod());
        //v.findViewById(R.id.txtEULA).setVerticalScrollBarEnabled(true);
        //v.findViewById(R.id.txtEULA).setScrollBarFadeDuration (0);
        //return v;
    }
}


