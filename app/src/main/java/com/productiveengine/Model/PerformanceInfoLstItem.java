package com.productiveengine.Model;

/**
 * Created by Nikolaos on 11/04/2015.
 */
public class PerformanceInfoLstItem {
    private String metricName;
    private String metricResult;

    public PerformanceInfoLstItem() {
    }

    public PerformanceInfoLstItem(String metricName, String metricResult) {
        this.metricName = metricName;
        this.metricResult = metricResult;
    }

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public String getMetricResult() {
        return metricResult;
    }

    public void setMetricResult(String metricResult) {
        this.metricResult = metricResult;
    }
}
