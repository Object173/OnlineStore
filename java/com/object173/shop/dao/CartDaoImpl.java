package com.object173.shop.dao;

import com.object173.shop.model.Cart;
import com.object173.shop.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDaoImpl implements CartDao {
    private static final Logger logger = LoggerFactory.getLogger(CartDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    @Override
    public void addCart(Cart cart) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(cart);
    }

    @Override
    public void updateCart(Cart cart) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(cart);
    }

    @Override
    public void removeCart(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Cart cart = (Cart)session.load(Cart.class,new Integer(id));

        if(cart!=null) {
            session.delete(cart);
        }
    }

    @Override
    public Cart getCart(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Cart cart = (Cart) session.load(Cart.class, new Integer(id));
        return cart;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Cart> listCart() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Cart> cartList = session.createQuery("from Cart").list();
        return cartList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Cart> listCart(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Cart> cartList = session.createQuery("from Cart c where c.user_id="+Integer.toString(id)).list();
        return cartList;
    }
}
