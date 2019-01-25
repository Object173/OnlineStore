package com.object173.shop.dao;

import com.object173.shop.model.Product;
import com.object173.shop.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    @Override
    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
        logger.info("User successfilly saved. "+user);
    }

    @Override
    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
        logger.info("User successfully update. "+user);
    }

    @Override
    public void removeUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User)session.load(User.class,new Integer(id));

        if(user!=null) {
            session.delete(user);
            logger.info("User successfully removed. " + user);
        }
        logger.info("User not found. " + user);
    }

    @Override
    public User getUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));

        logger.info("User successfully loaded. " + user);
        return user;
    }

    @Override
    public User getUser(String username) {
        Session session = this.sessionFactory.getCurrentSession();
        String query="from User u where u.username='"+username+"'";
        User user = (User) session.createQuery(query).list().get(0);
        logger.info("User successfully loaded. " + user);
        return user;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUser() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("from User").list();

        logger.info("load "+userList.size()+" user");
        return userList;
    }
}
