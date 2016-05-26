package com.productiveengine.Model;

import com.productiveengine.Common.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Nikolaos on 9/3/2015.
 */
public class PerformanceInfo {

    private static final String TAG = "PerformanceInfo";

    private HashMap<eAlgorithm, Metric_Value> performance;
    private double totalTime = 0;

    public PerformanceInfo() {
        performance = new HashMap<eAlgorithm, Metric_Value>();
    }

    public PerformanceInfo(HashMap<eAlgorithm, Metric_Value> performance) {
        this.performance = performance;
    }

    public Metric_Value getMextricValue(eAlgorithm metric){
        return performance.get(metric);
    }

    public Metric_Value addMextricValue(eAlgorithm metric, Metric_Value metricValue){
        return performance.put(metric, metricValue);
    }

    public HashMap<eAlgorithm, Metric_Value> getPerformance() {
        return performance;
    }

    public void setPerformance(HashMap<eAlgorithm, Metric_Value> performance) {
        this.performance = performance;
    }

    public double calculateTotalTime(){
        totalTime = 0;
        Metric_Value metricValue = null;

        Iterator it = performance.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            metricValue = (Metric_Value) pair.getValue();
            //Log.d("Hash Map "+pair.getKey()+" R = "+metricValue.getTime(),TAG);
            totalTime += metricValue.getTime();
            //it.remove(); // avoids a ConcurrentModificationException
        }
        //Log.d("Total score " +  String.format("%.4f", getTotalTimeInSeconds()),TAG);
        return totalTime;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public double getTotalTimeInSeconds() {
        return totalTime*Math.pow(10.0,-9.0);
    }

    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }
}
