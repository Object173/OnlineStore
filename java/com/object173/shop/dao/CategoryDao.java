package com.object173.shop.dao;

import com.object173.shop.model.Category;

import java.util.List;

public interface CategoryDao {
    public void addCategory(Category category);
    public void updateCategory(Category category);
    public void removeCategory(int id);
    public Category getCategory(int id);
    public List<Category> listCategory();
    public List<Category> listCategory(int id);
}
