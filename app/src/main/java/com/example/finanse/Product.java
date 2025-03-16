package com.example.finanse;

public class Product implements ProductInterface{
    private int User_id;
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
    public Product(int id,int user_id,String name, String category, double price, int amount)
    {
        Id=id;
        Name=name;
        Category=category;
        Price=price;
        Amount=amount;
        User_id=user_id;
    }
    public Product(int user_id,String name, String category, double price, int amount)
    {
        Name=name;
        Category=category;
        Price=price;
        Amount=amount;
        User_id=user_id;
    }
    public String getName() {return Name;}
    public String getCategory() {return Category;}
    public double getPrice() {return Price;}
    public int getAmount() {return Amount;}
    public int getId(){return Id;}

    @Override
    public int getUserID() {
        return User_id;
    }
}
