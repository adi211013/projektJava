package com.example.finanse;

import java.util.List;

public interface ProductLogic  {
    long addProduct(Product p);
    List<Product> getProductsForUser(int userId);
    void deleteProduct(int id);
}
