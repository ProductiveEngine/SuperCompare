package com.productiveengine.Model;

import com.productiveengine.Common.cAlgorithm;
import com.productiveengine.Common.eBenchmarkType;
import com.productiveengine.Common.eAlgorithm;

import java.util.List;

/**
 * Created by Nikolaos on 9/5/2015.
 */
public class DBSetup {

    private static final String TAG = "DB Setup";

    public void InitialSetup() {
        cleanDB();
        initialPopulate();
    }

    public boolean hasIntegrity(){
        return false;
    }

    private void cleanDB(){
        try { Performance.deleteAll(Performance.class);}catch(Exception e){ }
        try { AlgorithmSettings.deleteAll(AlgorithmSettings.class);}catch(Exception e){ }
        try { Algorithm.deleteAll(Algorithm.class);}catch(Exception e){ }
    }

    private void initialPopulate(){
        algorithmPopulate();
        settingsPopulate();

    }

    private void algorithmPopulate(){
        //--Algorithm Setup
        Algorithm algorithm = null;
        //--CPU_PI
        algorithm = new Algorithm();
        algorithm.setName(cAlgorithm.getName(eAlgorithm.CPU_PI));
        algorithm.setCode(eAlgorithm.CPU_PI.ordinal());
        algorithm.setComponent("CPU");
        algorithm.setInfo("");
        algorithm.save();
    }

    private void settingsPopulate(){
        AlgorithmSettings algorithmSettings = null;
        List<Algorithm> algorithms = Algorithm.listAll(Algorithm.class);

        for(Algorithm algorithm : algorithms){
            algorithmSettings = new AlgorithmSettings();
            algorithmSettings.benchmarkType = (byte) eBenchmarkType.TYPE_I.ordinal();
            algorithmSettings.parameter = 1;
            algorithmSettings.isSelected = false;
            algorithmSettings.algorithm = algorithm;
            algorithmSettings.save();
            //------------------------------------------------------------------
            algorithmSettings = new AlgorithmSettings();
            algorithmSettings.benchmarkType = (byte) eBenchmarkType.TYPE_II.ordinal();
            algorithmSettings.parameter = 30;
            algorithmSettings.isSelected = false;
            algorithmSettings.algorithm = algorithm;
            algorithmSettings.save();
        }
        List<AlgorithmSettings> s = AlgorithmSettings.listAll(AlgorithmSettings.class);

    }
}
