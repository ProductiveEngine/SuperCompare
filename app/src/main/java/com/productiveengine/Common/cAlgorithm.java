package com.productiveengine.Common;

/**
 * Created by Nikolaos on 9/3/2015.
 */
public class cAlgorithm {

    public static String getName(eAlgorithm metric){
        String name = "";
        switch (metric){
            case CPU_PI:
                name = "PI Calculation";
            break;
            default:
                name = "N/A";
            break;
        }
        return name;
    }

    public static eAlgorithm fromByte(byte b) {
        for(eAlgorithm t : eAlgorithm.values()) {
            if(t.ordinal() == (int)b)
                return t;
        }
        return null;  //or throw exception
    }

    public static eAlgorithm fromInt(int b) {
        for(eAlgorithm t : eAlgorithm.values()) {
            if(t.ordinal() == (int)b)
                return t;
        }
        return null;  //or throw exception
    }
}
