package com.object173.shop.service;

import com.object173.shop.model.Product;

import java.util.List;

public interface ProductService {
    public void addProduct(Product product);
    public void updateProduct(Product product);
    public void removeProduct(int id);
    public Product getProduct(int id);
    public List<Product> listProduct();
    public List<Product> listProduct(int id);
}
