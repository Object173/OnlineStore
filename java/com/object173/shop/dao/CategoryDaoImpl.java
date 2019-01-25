package com.object173.shop.dao;

import com.object173.shop.model.Category;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao{
    private static final Logger logger = LoggerFactory.getLogger(CategoryDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    @Override
    public void addCategory(Category category) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(category);
        System.out.println("Product successfilly saved. "+category);
    }

    @Override
    public void updateCategory(Category category) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(category);
        logger.info("Product successfully update. "+category);
    }

    @Override
    public void removeCategory(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Category category = (Category)session.load(Category.class,new Integer(id));

        if(category!=null) {
            session.delete(category);
            logger.info("Product successfully removed. " + category);
        }
        logger.info("Product not found. " + category);
    }

    @Override
    public Category getCategory(int id) {
        try {
            Session session = this.sessionFactory.getCurrentSession();
            Category category = (Category) session.load(Category.class, new Integer(id));
            logger.info("Product successfully loaded. " + category);
            return category;
        }
        catch (Exception ex) {
            return null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Category> listCategory() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Category> categoryList = session.createQuery("from Category").list();

        logger.info("load "+categoryList.size()+" products");
        return categoryList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Category> listCategory(int id) {
        Session session = this.sessionFactory.getCurrentSession();

        Query query=session.createQuery("from Category where parent_id=:p_id and id!=parent_id");
        query.setParameter("p_id",id);

        List<Category> categoryList=query.list();

        logger.info("load "+categoryList.size()+" products");
        return categoryList;
    }


}
