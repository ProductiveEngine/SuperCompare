package com.productiveengine.BL;

import com.productiveengine.Model.AlgorithmSettings;
import com.productiveengine.Model.Metric_Value;
import com.productiveengine.Model.Performance;

/**
 * Created by Nikolaos on 16/3/2015.
 */
public interface IAlgorithmBL {
    public void testMaxReps();
    public String algorithmName();
    public double getWeight();
    public void setWeight(double weight);
    public double getCurrentReps();
    public Performance runType1(AlgorithmSettings algoSett);
    public Performance runType2(AlgorithmSettings algoSett);
}
