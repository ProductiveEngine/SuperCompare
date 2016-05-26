package com.productiveengine.Common;

/**
 * Created by Nikolaos on 7/6/2015.
 */
public class FormatString {

    public static String formatBigNumber(Double number){
        String result = "";
        String sNumber = "";
        long lNumber = (long) ((number * 1e5) / 1e5);
        StringBuilder sb = new StringBuilder();

        int i = 0,y=-3;
        sNumber = ""+lNumber;

        for(i=sNumber.length();i>0;i-=3){
            result = sNumber.substring(Math.max(i-3,0),i)+numberPrefix(y+=3) +" "+result;
        }

        return result;
    }

    public static String numberPrefix(int index){
        String prefix = null;

        switch(index){
            case 3:
                prefix = "K";
            break;
            case 6:
                prefix = "M";
            break;
            case 9:
                prefix = "G";
            break;
            case 12:
                prefix = "T";
            break;
            case 15:
                prefix = "P";
            break;
            case 18:
                prefix = "E";
            break;
            case 21:
                prefix = "Z";
            break;
            case 24:
                prefix = "Y";
            break;
            default:
                prefix="";
        }
        return prefix;
    }

}
