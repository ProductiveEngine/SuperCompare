package com.productiveengine.Model;

import com.orm.SugarRecord;

/**
 * Created by Nikolaos on 9/5/2015.
 */
public class Performance extends SugarRecord<Performance> {
    private double result;
    private double initialCPUTemp;
    private double finalCPUTemp;
    private double initialBatteryCharge;
    private double finalBatteryCharge;
    private AlgorithmSettings algorithmSettings;

    public Performance() {
    }

    public Performance(double result, double initialCPUTemp, double finalCPUTemp, double initialBatteryCharge, double finalBatteryCharge) {
        this.result = result;
        this.initialCPUTemp = initialCPUTemp;
        this.finalCPUTemp = finalCPUTemp;
        this.initialBatteryCharge = initialBatteryCharge;
        this.finalBatteryCharge = finalBatteryCharge;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public double getInitialCPUTemp() {
        return initialCPUTemp;
    }

    public void setInitialCPUTemp(double initialCPUTemp) {
        this.initialCPUTemp = initialCPUTemp;
    }

    public double getFinalCPUTemp() {
        return finalCPUTemp;
    }

    public void setFinalCPUTemp(double finalCPUTemp) {
        this.finalCPUTemp = finalCPUTemp;
    }

    public double getInitialBatteryCharge() {
        return initialBatteryCharge;
    }

    public void setInitialBatteryCharge(double initialBatteryCharge) {
        this.initialBatteryCharge = initialBatteryCharge;
    }

    public double getFinalBatteryCharge() {
        return finalBatteryCharge;
    }

    public void setFinalBatteryCharge(double finalBatteryCharge) {
        this.finalBatteryCharge = finalBatteryCharge;
    }

    public AlgorithmSettings getAlgorithmSettings() {
        return algorithmSettings;
    }

    public void setAlgorithmSettings(AlgorithmSettings algorithmSettings) {
        this.algorithmSettings = algorithmSettings;
    }
}
