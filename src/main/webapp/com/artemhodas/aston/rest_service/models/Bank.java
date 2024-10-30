package com.artemhodas.aston.rest_service.models;

public class Bank {

    private int idBank;
    private String name;
    private String location;


    public Bank() {
    }

    public Bank(int idBank, String name, String location) {
        this.idBank = idBank;
        this.name = name;
        this.location = location;

    }

    public int getIdBank() {
        return idBank;
    }

    public void setIdBank(int idBank) {
        this.idBank = idBank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "idBank=" + idBank +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
