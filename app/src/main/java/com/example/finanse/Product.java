package com.example.finanse;

public class Product implements ProductInterface{
    private int user_id;
    private int id;
    private String name, category;
    private double price;
    private int amount;
    public Product(int id,int user_id,String name, String category, double price, int amount)
    {
        this.id =id;
        this.name =name;
        this.category =category;
        this.price =price;
        this.amount =amount;
        this.user_id =user_id;
    }
    public Product(int user_id,String name, String category, double price, int amount)
    {
        this.name =name;
        this.category =category;
        this.price =price;
        this.amount =amount;
        this.user_id =user_id;
    }
    public String getName() {return name;}
    public String getCategory() {return category;}
    public double getPrice() {return price;}
    public int getAmount() {return amount;}
    public int getId(){return id;}
    public int getUserID() {
        return user_id;
    }
}
