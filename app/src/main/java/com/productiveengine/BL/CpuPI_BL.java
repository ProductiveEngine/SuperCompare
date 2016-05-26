package com.productiveengine.BL;

import android.util.Log;

import com.productiveengine.Common.cAlgorithm;
import com.productiveengine.Common.eAlgorithm;
import com.productiveengine.Model.AlgorithmSettings;
import com.productiveengine.Model.Performance;
import com.productiveengine.super_compare.BenchmarkUI;

/**
 * Created by Nikolaos on 9/3/2015.
 */
public class CpuPI_BL implements IAlgorithmBL {

    private static final String TAG = "CpuPI";
    private double currentReps;
    private double weight = 1;
    private double timeLimit = 1;

    BenchmarkUI.BenchmarkAsyncTask benchmarkAsyncTask_Time;

    public CpuPI_BL(BenchmarkUI.BenchmarkAsyncTask benchmarkAsyncTask_Time) {
        this.benchmarkAsyncTask_Time = benchmarkAsyncTask_Time;
    }

    @Override
    public Performance runType1(AlgorithmSettings algoSett) {
        Performance performance = new Performance();
        performance.setAlgorithmSettings(algoSett);
        weight = algoSett.getParameter();

        double pi = 0;
        double denominator = 1;
        double localReps = 100000000L;
        double totalReps = weight * localReps;
        int smallCounter = 0;
        int completionProgress = 0;
        //Test
        //totalReps = 10000;

        currentReps = 0;

        long myTime;
        //Start timer
        myTime = System.nanoTime();

        for (int x = 0; x < totalReps; x++) {

            if (x % 2 == 0) {
                pi = pi + (1 / denominator);
            } else {
                pi = pi - (1 / denominator);
            }
            denominator = denominator + 2;

            currentReps++;
            smallCounter++;

            if (smallCounter == 1000000) {
                //Log.d("pi " + (int)( 100*(currentReps/totalReps))+ " "+currentReps+" "+totalReps, TAG);
                double s = currentReps / totalReps;
                completionProgress = (int) (100 * (currentReps / totalReps));
                benchmarkAsyncTask_Time.notifyProgress(completionProgress);
                smallCounter = 0;
            }
            if(benchmarkAsyncTask_Time.isCancelled()){
                return null;
            }
        }
        myTime = System.nanoTime() - myTime;
        performance.setResult(myTime*Math.pow(10,-9));
        //pi = pi * 4;
        //Log.d("pi " + pi, TAG);
        return performance;
    }

    @Override
    public Performance runType2(AlgorithmSettings algoSett) {

        Performance performance = new Performance();
        performance.setAlgorithmSettings(algoSett);
        timeLimit = algoSett.getParameter();
        double currTime;

        double pi = 0;
        double denominator = 1;
        double totalReps = Double.MAX_VALUE;
        int smallCounter = 0;

        currentReps = 0;

        long myTime;
        //Start timer
        myTime = System.nanoTime();

        for (int x = 0; x < totalReps; x++) {

            if (x % 2 == 0) {
                pi = pi + (1 / denominator);
            } else {
                pi = pi - (1 / denominator);
            }
            denominator = denominator + 2;

            currentReps++;
            smallCounter++;

            currTime = (System.nanoTime() - myTime)*Math.pow(10,-9);
            if (smallCounter == 100000) {
                //Log.d("pi " + (int)( 100*(currentReps/totalReps))+ " "+currentReps+" "+totalReps, TAG);
                benchmarkAsyncTask_Time.notifyProgress((int) (Math.min(100 * (currTime / timeLimit),100)));
                smallCounter = 0;
            }
            //Log.d((System.nanoTime() - myTime)*Math.pow(10,-9) + "Test" , TAG);
            if( currTime > timeLimit){
                break;
            }
            if(benchmarkAsyncTask_Time.isCancelled()){
                return null;
            }
        }
        performance.setResult(currentReps);
        //pi = pi * 4;
        //Log.d("pi " + pi, TAG);
        return performance;
    }

    @Override
    public void testMaxReps() {

    }

    @Override
    public String algorithmName() {

        return cAlgorithm.getName(eAlgorithm.CPU_PI);
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public double getCurrentReps() {
        return currentReps;
    }


}

