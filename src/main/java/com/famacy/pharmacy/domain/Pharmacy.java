package com.famacy.pharmacy.domain;

public class Pharmacy {
    private int idPha;
    private String name;
    private String addres;
    private float longi;
    private float lat;
    private String logo;
    private String codCity;

    public Pharmacy() {
    }

    public Pharmacy(int idPha, String name, String addres, float longi, float lat, String logo, String codCity) {
        this.idPha = idPha;
        this.name = name;
        this.addres = addres;
        this.longi = longi;
        this.lat = lat;
        this.logo = logo;
        this.codCity = codCity;
    }

    public int getIdPha() {
        return this.idPha;
    }

    public void setIdPha(int idPha) {
        this.idPha = idPha;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddres() {
        return this.addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public float getLongi() {
        return this.longi;
    }

    public void setLongi(float longi) {
        this.longi = longi;
    }

    public float getLat() {
        return this.lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCodCity() {
        return this.codCity;
    }

    public void setCodCity(String codCity) {
        this.codCity = codCity;
    }

    

}
