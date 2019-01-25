package com.object173.shop.service;

import com.object173.shop.dao.CategoryDao;
import com.object173.shop.dao.CategoryDaoImpl;
import com.object173.shop.model.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryDaoImpl.class);
    private CategoryDao categoryDao;

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    @Transactional
    public void addCategory(Category category) {
        try {
            if (!category.isValidate()) return;
            this.categoryDao.addCategory(category);
            System.out.println("add category "+category);
        }
        catch(Exception ex) {
            System.out.println("error add category " + ex);
        }
    }

    @Override
    @Transactional
    public void updateCategory(Category category) {
        if(!category.isValidate()) return;
        this.categoryDao.updateCategory(category);
    }

    @Override
    @Transactional
    public void removeCategory(int id) {
        this.categoryDao.removeCategory(id);
    }

    @Override
    @Transactional
    public Category getCategory(int id) {
        return this.categoryDao.getCategory(id);
    }

    @Override
    @Transactional
    public List<Category> listCategory() {
        return this.categoryDao.listCategory();
    }

    @Transactional
    public List<Category> listCategory(int id) {
        return this.categoryDao.listCategory(id);
    }
}
