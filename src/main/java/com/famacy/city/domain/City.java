package com.famacy.city.domain;

public class City {
    private String codCity;
    private String name;
    private String codReg;

    public City() {
    }

    public City(String codCity, String name, String codReg) {
        this.codCity = codCity;
        this.name = name;
        this.codReg = codReg;
    }

    public String getCodCity() {
        return this.codCity;
    }

    public void setCodCity(String codCity) {
        this.codCity = codCity;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodReg() {
        return this.codReg;
    }

    public void setCodReg(String codReg) {
        this.codReg = codReg;
    }
    
}
