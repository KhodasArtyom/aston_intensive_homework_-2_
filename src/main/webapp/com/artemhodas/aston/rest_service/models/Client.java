package com.artemhodas.aston.rest_service.models;

public class Client {
    private int clientId;
    private String firstName;
    private String lastName;
    private Bank bank;

    public Client() {
    }

    public Client(int clientId, String firstName, String lastName, Bank bank) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bank = bank;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", bank=" + bank +
                '}';
    }
}
