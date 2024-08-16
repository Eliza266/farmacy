package com.famacy.customer.domain;

public class Customer {
    private String idCust;
    private String name;
    private String lastName;
    private String email;
    private String birthDate;
    private float longe;
    private float lat;
    private String codCity;

    public Customer() {
    }

    public Customer(String idCust, String name, String lastName, String email, String birthDate, float longe, float lat,
            String codCity) {
        this.idCust = idCust;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.longe = longe;
        this.lat = lat;
        this.codCity = codCity;
    }

    public String getIdCust() {
        return this.idCust;
    }

    public void setIdCust(String idCust) {
        this.idCust = idCust;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public float getLonge() {
        return this.longe;
    }

    public void setLonge(float longe) {
        this.longe = longe;
    }

    public float getLat() {
        return this.lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public String getCodCity() {
        return this.codCity;
    }

    public void setCodCity(String codCity) {
        this.codCity = codCity;
    }

}
