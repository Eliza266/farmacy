package com.famacy.pharmacymedicine.domain;

public class Pharmacymedicine {
    private int idPha;
    private int idMed;
    private Float price;

    public Pharmacymedicine() {
    }

    public Pharmacymedicine(int idPha, int idMed, Float price) {
        this.idPha = idPha;
        this.idMed = idMed;
        this.price = price;
    }

    public int getIdPha() {
        return this.idPha;
    }

    public void setIdPha(int idPha) {
        this.idPha = idPha;
    }

    public int getIdMed() {
        return this.idMed;
    }

    public void setIdMed(int idMed) {
        this.idMed = idMed;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    
}
