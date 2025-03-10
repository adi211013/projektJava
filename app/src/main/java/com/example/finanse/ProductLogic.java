package com.example.finanse;

import java.util.List;

public interface ProductLogic  {
    long addProduct(Product p);
    List<Product> getAllProducts();
    void deleteProduct(int id);
}
