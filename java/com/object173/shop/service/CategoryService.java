package com.object173.shop.service;

import com.object173.shop.model.Category;
import com.object173.shop.model.Product;

import java.util.List;

public interface CategoryService {
    public void addCategory(Category category);
    public void updateCategory(Category category);
    public void removeCategory(int id);
    public Category getCategory(int id);
    public List<Category> listCategory();
    public List<Category> listCategory(int id);
}
