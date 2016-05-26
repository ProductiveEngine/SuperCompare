package com.productiveengine.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikolaos on 12/5/2015.
 */
public class BenchmarkSettings {

    private List<AlgorithmSettings> algoSettigns;
    private String algoName;

    public BenchmarkSettings() {
    }

    public BenchmarkSettings(List<AlgorithmSettings> algoSettigns, String algoName) {
        this.algoSettigns = algoSettigns;
        this.algoName = algoName;
    }

    public static List<BenchmarkSettings> loadSettings(){
        List<AlgorithmSettings> algoSettigns = AlgorithmSettings.find(AlgorithmSettings.class, null, null, null, "Algorithm",null );
        List<AlgorithmSettings> newAlgoSettigns = new ArrayList<>();
        List<BenchmarkSettings> result = new ArrayList<>();
        int lastCode = -1;
        String lastName = "";
        boolean isFirstTime = true;

        for(AlgorithmSettings algoSetting:algoSettigns){
            if(lastCode != algoSetting.algorithm.getCode()){
                if(!isFirstTime) {
                    result.add(new BenchmarkSettings(new ArrayList<AlgorithmSettings>(newAlgoSettigns),lastName));
                    newAlgoSettigns.clear();
                }
            }
            newAlgoSettigns.add( algoSetting);
            if(isFirstTime) {
                isFirstTime = false;
            }
            lastCode = algoSetting.algorithm.getCode();
            lastName = algoSetting.algorithm.getName();
        }

        result.add(new BenchmarkSettings(new ArrayList<AlgorithmSettings>(newAlgoSettigns),lastName));
        newAlgoSettigns.clear();

    return result;
    }

    public List<AlgorithmSettings> getAlgoSettigns() {
        return algoSettigns;
    }

    public void setAlgoSettigns(List<AlgorithmSettings> algoSettigns) {
        this.algoSettigns = algoSettigns;
    }

    public String getAlgoName() {
        return algoName;
    }

    public void setAlgoName(String algoName) {
        this.algoName = algoName;
    }
}
