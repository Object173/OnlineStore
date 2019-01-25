package com.object173.shop.dao;

import com.object173.shop.model.Product;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ejb.access.LocalStatelessSessionProxyFactoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Queue;

@Repository
public class ProductDaoImpl implements ProductDao {
    private static final Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    @Override
    public void addProduct(Product product) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(product);
        logger.info("Product successfilly saved. "+product);
    }

    @Override
    public void updateProduct(Product product) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(product);
        logger.info("Product successfully update. "+product);
    }

    @Override
    public void removeProduct(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Product product = (Product)session.load(Product.class,new Integer(id));

        if(product!=null) {
            session.delete(product);
            logger.info("Product successfully removed. " + product);
        }
        logger.info("Product not found. " + product);
    }

    @Override
    public Product getProduct(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Product product = (Product) session.load(Product.class, new Integer(id));
        logger.info("Product successfully loaded. " + product);
        return product;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> listProduct() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Product> productList = session.createQuery("from Product").list();

        logger.info("load "+productList.size()+" products");
        return productList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> listProduct(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Product where category_id=:cat_id");
        query.setParameter("cat_id",id);

        List<Product> productList = query.list();

        logger.info("load "+productList.size()+" products");
        return productList;
    }
}
