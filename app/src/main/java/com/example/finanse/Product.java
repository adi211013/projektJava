package com.example.finanse;

public class Product {
    private int Id;
    private String Name,Category;
    private double Price;
    private int Amount;
    public Product(String name, String category, double price, int amount)
    {
        Name=name;
        Category=category;
        Price=price;
        Amount=amount;
    }
    public Product(int id,String name, String category, double price, int amount)
    {
        Id=id;
        Name=name;
        Category=category;
        Price=price;
        Amount=amount;
    }
    public String getName() {return Name;}
    public String getCategory() {return Category;}
    public double getPrice() {return Price;}
    public int getAmount() {return Amount;}
    public int getId(){return Id;}
}
