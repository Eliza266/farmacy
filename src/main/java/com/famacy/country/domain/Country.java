package com.famacy.country.domain;

public class Country {
    private String codeCountry;
    private String name;


    public Country() {
    }

    public Country(String codeCountry, String name) {
        this.codeCountry = codeCountry;
        this.name = name;
    }

    public String getCodeCountry() {
        return this.codeCountry;
    }

    public void setCodeCountry(String codeCountry) {
        this.codeCountry = codeCountry;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
