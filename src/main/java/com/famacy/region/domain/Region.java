package com.famacy.region.domain;

public class Region {
    private String codReg;
    private String name;
    private String codeCountry;

    public Region() {
    }

    public Region(String codReg, String name, String codeCountry) {
        this.codReg = codReg;
        this.name = name;
        this.codeCountry = codeCountry;
    }

    public String getCodReg() {
        return this.codReg;
    }

    public void setCodReg(String codReg) {
        this.codReg = codReg;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeCountry() {
        return this.codeCountry;
    }

    public void setCodeCountry(String codeCountry) {
        this.codeCountry = codeCountry;
    }



}
