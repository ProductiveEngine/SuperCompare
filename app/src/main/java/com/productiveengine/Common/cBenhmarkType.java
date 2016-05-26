package com.productiveengine.Common;

/**
 * Created by Nikolaos on 20/5/2015.
 */
public class cBenhmarkType {

    public static String getName(int b){
        return getName(fromInt(b));
    }

    public static String getName(eBenchmarkType metric){
        String name = "";
        switch (metric){
            case TYPE_I:
                name = "Type I";
                break;
            default:
                name = "Type II";
                break;
        }
        return name;
    }

    public static String getParameterName(int b){
        return getParameterName(fromInt(b));
    }

    public static String getParameterName(eBenchmarkType metric){
        String name = "";
        switch (metric){
            case TYPE_I:
                name = "Weight";
                break;
            default:
                name = "Time (secs)";
                break;
        }
        return name;
    }

    public static String getResultName(int b){
        return getResultName(fromInt(b));
    }

    public static String getResultName(eBenchmarkType metric){
        String name = "";
        switch (metric){
            case TYPE_I:
                name = "Time (secs)";
                break;
            default:
                name = "Repetitions";
                break;
        }
        return name;
    }

    public static eBenchmarkType fromInt(int b) {
        for(eBenchmarkType t : eBenchmarkType.values()) {
            if(t.ordinal() == (int)b)
                return t;
        }
        return null;  //or throw exception
    }
}
