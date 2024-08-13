package com.famacy.modeadmin.domain;

public class Modeadmi {
    private int idap;
    private String name;

    public Modeadmi() {
    }

    public Modeadmi(int idap, String name) {
        this.idap = idap;
        this.name = name;
    }

    public int getIdap() {
        return this.idap;
    }

    public void setIdap(int idap) {
        this.idap = idap;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
