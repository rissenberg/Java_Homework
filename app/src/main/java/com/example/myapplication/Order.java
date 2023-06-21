package com.example.myapplication;

import java.util.ArrayList;

public class Order {
    private Company company;
    private Pakage pakage;
    private int cost;
    public static int total;
    private String dest;

    public Company getCorp() {
        return company;
    }

    public void setCorp(Company company) {
        this.company = company;
    }

    public Pakage getPac() {
        return pakage;
    }

    public void setPac(Pakage pakage) {
        this.pakage = pakage;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public Order(Company company, Pakage pakage, int cost, String dest) {
        this.company = company;
        this.pakage = pakage;
        this.cost = cost;
        this.dest = dest;
    }

    public Order() {
        company = new Company();
        pakage = new Pakage();
        cost = 0;
        dest = "";
    }

    public static ArrayList<Order> getSends() {
        ArrayList<Order> orders = new ArrayList<Order>();
        Order order1 = new Order(new Company("МГТУ им Баумана", "2-ая Бауманская"), new Pakage(1, false, "нет требований", PakType.L), 550, "Луна");
        Order order2 = new Order(new Company("ПрофСофт", "Весенний проезд"), new Pakage(3, true, "хрупкий груз", PakType.S), 1000, "куда хочешь");
        Order order3 = new Order(new Company("ООО \"Ремонт\"", "Измайловский проспект"), new Pakage(15, false, "нет требований", PakType.M), 3500, "2-ая Бауманская");

        orders.add(order1); orders.add(order2); orders.add(order3);
        orders.add(order1); orders.add(order2); orders.add(order3);
        orders.add(order1); orders.add(order2); orders.add(order3);

        return orders;
    }
}
