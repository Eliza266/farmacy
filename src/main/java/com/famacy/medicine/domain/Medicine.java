package com.famacy.medicine.domain;

public class Medicine {
    private int idMed;
    private String name;
    private String procedings;
    private String healthregister;
    private String description;
    private String desShort;
    private String nameRol;
    private int idMode;
    private int idum;
    private int idLab;
    private int idap;

    public Medicine() {
    }

    public Medicine(int idMed, String name, String procedings, String healthregister, String description, String desShort, String nameRol, int idMode, int idum, int idLab, int idap) {
        this.idMed = idMed;
        this.name = name;
        this.procedings = procedings;
        this.healthregister = healthregister;
        this.description = description;
        this.desShort = desShort;
        this.nameRol = nameRol;
        this.idMode = idMode;
        this.idum = idum;
        this.idLab = idLab;
        this.idap = idap;
    }

    public int getIdMed() {
        return this.idMed;
    }

    public void setIdMed(int idMed) {
        this.idMed = idMed;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProcedings() {
        return this.procedings;
    }

    public void setProcedings(String procedings) {
        this.procedings = procedings;
    }

    public String getHealthregister() {
        return this.healthregister;
    }

    public void setHealthregister(String healthregister) {
        this.healthregister = healthregister;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDesShort() {
        return this.desShort;
    }

    public void setDesShort(String desShort) {
        this.desShort = desShort;
    }

    public String getNameRol() {
        return this.nameRol;
    }

    public void setNameRol(String nameRol) {
        this.nameRol = nameRol;
    }

    public int getIdMode() {
        return this.idMode;
    }

    public void setIdMode(int idMode) {
        this.idMode = idMode;
    }

    public int getIdum() {
        return this.idum;
    }

    public void setIdum(int idum) {
        this.idum = idum;
    }

    public int getIdLab() {
        return this.idLab;
    }

    public void setIdLab(int idLab) {
        this.idLab = idLab;
    }

    public int getIdap() {
        return this.idap;
    }

    public void setIdap(int idap) {
        this.idap = idap;
    }


}
