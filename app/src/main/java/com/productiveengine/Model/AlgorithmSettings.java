package com.productiveengine.Model;

import com.orm.SugarRecord;

/**
 * Created by Nikolaos on 9/5/2015.
 */
public class AlgorithmSettings extends SugarRecord<AlgorithmSettings> {
    int benchmarkType;
    double parameter;
    boolean isSelected;
    //String lblParameter;
    //String lblResult;

    Algorithm algorithm;

    public AlgorithmSettings(){

    }

    public AlgorithmSettings(AlgorithmSettings settings) {
        this.benchmarkType = settings.benchmarkType;
        this.parameter = settings.parameter;
        this.isSelected = settings.isSelected;
        this.algorithm = new Algorithm(settings.algorithm);
    }

    public AlgorithmSettings(byte benchmarkType, byte parameter, boolean isSelected) {
        this.benchmarkType = benchmarkType;
        this.parameter = parameter;
        this.isSelected = isSelected;
    }

    public int getBenchmarkType() {
        return benchmarkType;
    }

    public void setBenchmarkType(int benchmarkType) {
        this.benchmarkType = benchmarkType;
    }

    public double getParameter() {
        return parameter;
    }

    public void setParameter(double parameter) {
        this.parameter = parameter;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    /*
    public String getLblResult() { return lblResult; }

    public void setLblResult(String lblResult) { this.lblResult = lblResult; }

    public String getLblParameter() { return lblParameter; }

    public void setLblParameter(String lblParameter) { this.lblParameter = lblParameter; }
    */
}
