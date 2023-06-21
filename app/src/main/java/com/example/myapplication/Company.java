package com.example.myapplication;

public class Company {
    private String name;
    private String adr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public Company(String name, String adr) {
        this.name = name;
        this.adr = adr;
    }

    public Company() {
        name = "";
        adr = "";
    }
}
