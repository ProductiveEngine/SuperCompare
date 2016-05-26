package com.productiveengine.Model;

/**
 * Created by Nikolaos on 9/3/2015.
 */
public class Metric_Value {
    private int A = 1000000;
    private int B = 1000000;
    private int X = 1000000;
    private int Y = 1000000;
    private int Z = 1000000;
    private double weight = 1;
    private long time;
    private double maxReps;

    public double calculateResult(){
        //R = time*(Math.pow(10,-9))*(0.5*(0.8*A+0.2*B)+0.5*(0.2*X+0.3*Y+0.5*Z));
        return time;
    }

    public String showResult(){
        //return String.format("%,4f",getTimeInSeconds()) + " sec";
        return getTimeInSeconds() + " sec";
    }

    //----------------------------------------------------------------------------
    public int getA() {
        return A;
    }

    public void setA(int a) {
        A = a;
    }

    public int getB() {
        return B;
    }

    public void setB(int b) {
        B = b;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getZ() {
        return Z;
    }

    public void setZ(int z) {
        Z = z;
    }

    public long getTime() {
        return time;
    }

    public double getTimeInSeconds() {
        return time*Math.pow(10.0,-9.0);
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getMaxReps() {
        return maxReps;
    }

    public void setMaxReps(double maxReps) {
        this.maxReps = maxReps;
    }
}
