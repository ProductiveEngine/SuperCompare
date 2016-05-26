package com.productiveengine.BL;

import android.os.CountDownTimer;
import android.util.Log;

import com.productiveengine.Model.Algorithm;
import com.productiveengine.Model.AlgorithmSettings;
import com.productiveengine.Model.Metric_Value;
import com.productiveengine.Model.Performance;
import com.productiveengine.Model.PerformanceInfo;
import com.productiveengine.Common.*;
import com.productiveengine.super_compare.BenchmarkUI;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Nikolaos on 9/3/2015.
 */
public class BenchmarkAlgorithmsBL {

    private static final String TAG = "BenchmarkAlgorithms";
    BenchmarkUI.BenchmarkAsyncTask benchmarkAsyncTask_Time;

    public List<Performance> evaluate(BenchmarkUI.BenchmarkAsyncTask benchmarkAsyncTask){
        Algorithm algorithm = null;
        IAlgorithmBL iAlgorithmBL = null;
        List<Performance> performanceList = new ArrayList<>();
        Performance performance = null;

        for(AlgorithmSettings algoSett:AlgorithmSettings.find(AlgorithmSettings.class, "IS_SELECTED = 1")){
            algorithm = algoSett.getAlgorithm();

            switch( cAlgorithm.fromInt(algorithm.getCode())){
                case CPU_PI:
                    iAlgorithmBL = new CpuPI_BL(benchmarkAsyncTask);
                break;
            }

            switch(cBenhmarkType.fromInt(algoSett.getBenchmarkType())){
                case TYPE_I:
                    performance = iAlgorithmBL.runType1(algoSett);
                break;
                case TYPE_II:
                    performance = iAlgorithmBL.runType2(algoSett);
                break;
            }
            if(performance != null){
                performanceList.add(performance);
            }
        }
        return performanceList;
    }
}
