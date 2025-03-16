package com.example.finanse;

import android.content.Context;

import java.util.List;

public class ProductService {
    private DatabaseHelper db;
    public ProductService(Context context)
    {
        db=new DatabaseHelper(context);
    }
    public long addProduct(Product p) {
        return db.addProduct(p);
    }
    public List<Product> getProductsForUser(int userId)
    {
        return db.getProductsForUser(userId);
    }
    public void deleteProduct(int productId) {
        db.deleteProduct(productId);
    }
}
