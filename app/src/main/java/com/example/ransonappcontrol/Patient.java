package com.example.ransonappcontrol;

public class Patient {

    private String name;
    private int age;
    private double leukocytesNumber;
    private double bloodGlucoseValue;
    private double astAndTgoValue;
    private double ldhValue;
    private boolean hasBiliaryLithiasis;
    private int points;
    private double death;

    public Patient(String name, int age, double leukocytesNumber, double bloodGlucoseValue, double astAndTgoValue, double ldhValue, boolean hasBiliaryLithiasis, int points, double death) {
        this.name = name;
        this.age = age;
        this.leukocytesNumber = leukocytesNumber;
        this.bloodGlucoseValue = bloodGlucoseValue;
        this.astAndTgoValue = astAndTgoValue;
        this.ldhValue = ldhValue;
        this.hasBiliaryLithiasis = hasBiliaryLithiasis;
        this.points = points;
        this.death = death;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public double getDeath() {
        return death;
    }

    public void setDeath(double death) {
        this.death = death;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getLeukocytesNumber() {
        return leukocytesNumber;
    }

    public void setLeukocytesNumber(double leukocytesNumber) {
        this.leukocytesNumber = leukocytesNumber;
    }

    public double getBloodGlucoseValue() {
        return bloodGlucoseValue;
    }

    public void setBloodGlucoseValue(double bloodGlucoseValue) {
        this.bloodGlucoseValue = bloodGlucoseValue;
    }

    public double getAstAndTgoValue() {
        return astAndTgoValue;
    }

    public void setAstAndTgoValue(double astAndTgoValue) {
        this.astAndTgoValue = astAndTgoValue;
    }

    public double getLdhValue() {
        return ldhValue;
    }

    public void setLdhValue(double ldhValue) {
        this.ldhValue = ldhValue;
    }

    public boolean isHasBiliaryLithiasis() {
        return hasBiliaryLithiasis;
    }

    public void setHasBiliaryLithiasis(boolean hasBiliaryLithiasis) {
        this.hasBiliaryLithiasis = hasBiliaryLithiasis;
    }
}
