package com.object173.shop.dao;

import com.object173.shop.model.Order;
import com.object173.shop.model.OrderItem;
import com.object173.shop.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    private static final Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    @Override
    public void addOrder(Order order) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(order);
        logger.info("Order successfilly saved. "+order);
    }

    @Override
    public void addItem(OrderItem item) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(item);
    }

    @Override
    public void updateOrder(Order order) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(order);
        logger.info("Order successfully update. "+order);
    }

    @Override
    public void removeOrder(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Order order = (Order)session.load(Order.class, new Integer(id));

        if(order!=null) {
            session.delete(order);
            logger.info("Product successfully removed. " + order);
        }
        logger.info("Product not found. " + order);
    }

    @Override
    public void removeOrderItem(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        OrderItem item = (OrderItem)session.load(OrderItem.class, new Integer(id));

        if(item!=null) {
            session.delete(item);
        }
    }

    @Override
    public Order getOrder(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Order order = (Order) session.load(Order.class, new Integer(id));
        logger.info("Product successfully loaded. " + order);
        return order;
    }

    @Override
    public OrderItem getOrderItem(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        OrderItem item = (OrderItem) session.load(OrderItem.class, new Integer(id));
        return item;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Order> listOrder() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Order> ordersList = session.createQuery("from Order").list();

        logger.info("load "+ordersList.size()+" orders");
        return ordersList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Order> listOrder(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Order> ordersList = session.createQuery("from Order o where o.user_id="+Integer.toString(id)).list();

        logger.info("load "+ordersList.size()+" orders");
        return ordersList;
    }

    @Override
    public List<OrderItem> listItem(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        List<OrderItem> itemsList = session.createQuery("from OrderItem o where o.order_id="+id).list();
        return itemsList;
    }
}
