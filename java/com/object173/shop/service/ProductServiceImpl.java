package com.object173.shop.service;

import com.object173.shop.dao.ProductDao;
import com.object173.shop.model.Product;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    @Transactional
    public void addProduct(Product product) {
        try {
            if (!product.isValidate()) return;
            this.productDao.addProduct(product);
        }
        catch(Exception ex) {}
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        if(!product.isValidate()) return;
        this.productDao.updateProduct(product);
    }

    @Override
    @Transactional
    public void removeProduct(int id) {
        this.productDao.removeProduct(id);
    }

    @Override
    @Transactional
    public Product getProduct(int id) {
        return this.productDao.getProduct(id);
    }

    @Override
    @Transactional
    public List<Product> listProduct() {
        return this.productDao.listProduct();
    }

    @Override
    @Transactional
    public List<Product> listProduct(int id) {
        return this.productDao.listProduct(id);
    }


}
