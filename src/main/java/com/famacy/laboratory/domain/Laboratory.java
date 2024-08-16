package com.famacy.laboratory.domain;

public class Laboratory {
    private int idLab;
    private String name;
    private String codCity;

    public Laboratory() {
    }

    public Laboratory(int idLab, String name, String codCity) {
        this.idLab = idLab;
        this.name = name;
        this.codCity = codCity;
    }

    public int getIdLab() {
        return this.idLab;
    }

    public void setIdLab(int idLab) {
        this.idLab = idLab;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodCity() {
        return this.codCity;
    }

    public void setCodCity(String codCity) {
        this.codCity = codCity;
    }

    
}
