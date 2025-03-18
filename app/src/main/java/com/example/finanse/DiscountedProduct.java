package com.example.finanse;

public class DiscountedProduct extends Product {
    private double discontedProcentage;
    public DiscountedProduct(int userId, String name, String category, double price, int amount, double discontedProcentage)
    {
        super(userId,name,category,price,amount);
        this.discontedProcentage=discontedProcentage;
    }
    public double getDiscontedProcentage(){return discontedProcentage;}
    public void modifyPrice()
    {
        this.price *= (1 - discontedProcentage / 100);
    }

}
