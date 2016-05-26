package com.productiveengine.super_compare;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.productiveengine.Common.FormatString;
import com.productiveengine.Common.cBenhmarkType;
import com.productiveengine.BL.BenchmarkAlgorithmsBL;
import com.productiveengine.Model.AlgorithmSettings;
import com.productiveengine.Model.Performance;
import com.productiveengine.Model.PerformanceInfo;

import java.util.ArrayList;
import java.util.List;

public class BenchmarkUI extends Fragment {

    TextView txtDeviceManufacturer;
    TextView txtDeviceModel;
    LinearLayout lstResults;

    Button btnStartBenchmarkStart,btnStartBenchmarkStop;
    ProgressBar progressBar;
    TextView txtInfo;
    private String sInfo;
    BenchmarkAlgorithmsBL benchmark = new BenchmarkAlgorithmsBL();
    //ArrayList<PerformanceInfoLstItem> performanceArray = new ArrayList<PerformanceInfoLstItem>(){{add(new PerformanceInfoLstItem("-","-"));}};

    ListView lstResultsView;
    List<Performance> results;
    ArrayAdapter<Performance> resLstAdapter;

    private int progressBarStatus = 0;
    private Handler progressBarHandler = new Handler();
    BenchmarkAsyncTask mainTask = null;

    PerformanceInfo performanceInfo = new PerformanceInfo();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_benchmark, container, false);
        //------------------------------------------------------------------------
        txtDeviceManufacturer = (TextView) v.findViewById(R.id.txtDeviceManufacturer);
        txtDeviceModel = (TextView) v.findViewById(R.id.txtDeviceModel);

        txtDeviceManufacturer.setText("Manufacturer:\t"+ Build.MANUFACTURER);
        txtDeviceModel.setText("Model:\t"+ Build.MODEL);
        //------------------------------------------------------------------------
        progressBar = (ProgressBar) v.findViewById(R.id.pBarBenchmark);
        progressBar.setIndeterminate(false);
        txtInfo = (TextView) v.findViewById(R.id.txtInfo);
        sInfo = "";

        lstResults = (LinearLayout) v.findViewById(R.id.layoutLstResults);
        //resLstAdapter = new ResLstAdapter(this.getActivity(), R.id.layoutLstResults, performanceArray);
        lstResultsView = (ListView) v.findViewById(R.id.lstResults);
        lstResultsView.setAdapter(resLstAdapter);

        btnStartBenchmarkStart = (Button) v.findViewById(R.id.btnBenchmarkStart);
        btnStartBenchmarkStop = (Button) v.findViewById(R.id.btnBenchmarkStop);

        btnStartBenchmarkStart.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Check if an algorithm is selected
                        if (AlgorithmSettings.find(AlgorithmSettings.class, "IS_SELECTED = 1").size() == 0) {
                            Toast.makeText(v.getContext(), "Please select an algorithm from the settings tab!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        //-----------------------------------
                        if (mainTask != null) {
                            mainTask.cancel(true);
                        }
                        mainTask = new BenchmarkAsyncTask();
                        mainTask.execute();

                        btnStartBenchmarkStart.setEnabled(false);
                        btnStartBenchmarkStop.setEnabled(true);
                    }
                }
        );

        btnStartBenchmarkStop.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(mainTask != null) {
                            mainTask.cancel(true);
                            mainTask = null;
                            txtInfo.setText("");
                            progressBar.setProgress(0);
                        }
                        btnStartBenchmarkStart.setEnabled(true);
                        btnStartBenchmarkStop.setEnabled(false);
                    }
                }
        );
        //-------------------------------------------
        //results = (List<Performance>) getActivity().getLastNonConfigurationInstance() ;

        ArrayList<Performance> resultsLst = new ArrayList<Performance>();

        // Set our custom array adapter as the ListView's adapter.
        resLstAdapter = new ResultsArrayAdapter(this.getActivity(), resultsLst);
        lstResultsView.setAdapter(resLstAdapter);

        // Inflate the layout for this fragment
        return v;
    }

    //----------------------------------------------------------------------------------------
    public void populateResults(List<Performance> results){

        if ( results != null ) {
            ArrayList<Performance> performanceArrayList = new ArrayList<Performance>();
            performanceArrayList.addAll(results);

            // Set our custom array adapter as the ListView's adapter.
            resLstAdapter = new ResultsArrayAdapter(this.getActivity(), performanceArrayList);
            lstResultsView.setAdapter(resLstAdapter);
        }
    }
    /** Holds child views for one row. */
    private static class ResultsViewHolder {
        private TextView lblAlgoname, lblType, lblParameter, lblResult ;

        public ResultsViewHolder() {}

        public ResultsViewHolder(TextView lblAlgoname, TextView lblType, TextView lblParameter, TextView lblResult) {
            this.lblAlgoname = lblAlgoname;
            this.lblType = lblType;
            this.lblParameter = lblParameter;
            this.lblResult = lblResult;
        }

        public TextView getLblAlgoname() {
            return lblAlgoname;
        }
        public void setLblAlgoname(TextView lblAlgoname) {
            this.lblAlgoname = lblAlgoname;
        }
        public TextView getLblType() {
            return lblType;
        }
        public void setLblType(TextView lblType) {
            this.lblType = lblType;
        }
        public TextView getLblParameter() {
            return lblParameter;
        }
        public void setLblParameter(TextView lblParameter) {
            this.lblParameter = lblParameter;
        }
        public TextView getLblResult() {
            return lblResult;
        }
        public void setLblResult(TextView lblResult) {
            this.lblResult = lblResult;
        }
    }

    /** Custom adapter for displaying an array of BenchmarkSettings objects. */
    private static class ResultsArrayAdapter extends ArrayAdapter<Performance> {

        private LayoutInflater inflater;

        public ResultsArrayAdapter(Context context, List<Performance> resultsList) {
            super(context, R.layout.results_row, R.id.rowTextView, resultsList);
            // Cache the LayoutInflate to avoid asking for a new one each time.
            inflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // results to display
            Performance results = (Performance) this.getItem(position);

            // The child views in each row.
            TextView lblAlgoname, lblType, lblPatameter, lblResult;

            // Create a new row view
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.results_row, null);

                // Find the child views.
                lblAlgoname = (TextView) convertView.findViewById(R.id.lblAlgoname);
                lblType = (TextView) convertView.findViewById(R.id.lblType);
                lblPatameter = (TextView) convertView.findViewById(R.id.lblParameter);
                lblResult = (TextView) convertView.findViewById(R.id.lblResult);
                //-----------------------------------------------------------------------
                // Optimization: Tag the row with it's child views, so we don't have to
                // call findViewById() later when we reuse the row.
                convertView.setTag(new ResultsViewHolder(lblAlgoname, lblType, lblPatameter, lblResult));
                //-------------------------------------------------------------------
            }
            // Reuse existing row view
            else {
                // Because we use a ViewHolder, we avoid having to call findViewById().
                ResultsViewHolder viewHolder = (ResultsViewHolder) convertView.getTag();
                lblAlgoname = viewHolder.getLblAlgoname();
                lblType = viewHolder.getLblType();
                lblPatameter = viewHolder.getLblParameter();
                lblResult = viewHolder.getLblResult();
            }

            int algotype = results.getAlgorithmSettings().getBenchmarkType();
            // Display results data
            lblAlgoname.setText(results.getAlgorithmSettings().getAlgorithm().getName());
            lblType.setText(cBenhmarkType.getName(algotype));
            lblPatameter.setText(cBenhmarkType.getParameterName(algotype) + ":" + results.getAlgorithmSettings().getParameter());
            lblResult.setText(cBenhmarkType.getResultName(algotype) +":" + FormatString.formatBigNumber(results.getResult()));

            return convertView;
        }
    }

    public  class BenchmarkAsyncTask extends AsyncTask<Void, Integer, Void> {
        int myProgress;

        @Override
        protected void onPreExecute() {
            //btnStartBenchmarkStart.setText("Stop");
            txtInfo.setText("Calculating...");
            myProgress = 0;
        }

        @Override
        protected Void doInBackground(Void... params) {
            results = benchmark.evaluate(this);
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
            //progressBar.setSecondaryProgress(values[0] + 5);
        }

        @Override
        protected void onPostExecute(Void result) {
            publishProgress(100);
            txtInfo.setText("");
            populateResults(results);
            btnStartBenchmarkStart.setEnabled(true);
            btnStartBenchmarkStop.setEnabled(false);
        }

        @Override
        protected void onCancelled(Void aVoid) {
            super.onCancelled(aVoid);
            progressBar.setProgress(0);
            txtInfo.setText("");
        }

        public void notifyProgress(int completionProgress) {
            publishProgress(completionProgress);
        }
    }
}

