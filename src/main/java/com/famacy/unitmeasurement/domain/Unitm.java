package com.famacy.unitmeasurement.domain;

public class Unitm {
    private int idum;
    private String name;

    public Unitm() {
    }

    public Unitm(int idum, String name) {
        this.idum = idum;
        this.name = name;
    }

    public int getIdum() {
        return this.idum;
    }

    public void setIdum(int idum) {
        this.idum = idum;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
