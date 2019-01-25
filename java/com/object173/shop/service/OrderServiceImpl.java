package com.object173.shop.service;

import com.object173.shop.dao.OrderDao;
import com.object173.shop.model.Cart;
import com.object173.shop.model.Order;
import com.object173.shop.model.OrderItem;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao;
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
    @Override
    @Transactional
    public void addOrder(Order order) {
        try {
            this.orderDao.addOrder(order);
        }
        catch(Exception ex) {}
    }

    @Override
    @Transactional
    public void addOrder(int user, List<Cart> carts) {

        ArrayList<OrderItem> items = new ArrayList<OrderItem>();
        float sum=0;
        for(Cart cart:carts)
        {
            items.add(new OrderItem(cart));
            sum+=cart.getCost();
        }

        addOrder(new Order(user,sum));
        List<Order> orders=this.orderDao.listOrder(user);
        int order_id=orders.get(orders.size()-1).getId();
        for(OrderItem item:items)
        {
            item.setOrder_id(order_id);
            this.orderDao.addItem(item);
        }
    }

    @Override
    @Transactional
    public void updateOrder(Order order) {
        this.orderDao.updateOrder(order);
    }

    @Override
    @Transactional
    public void removeOrder(int id) {
        this.orderDao.removeOrder(id);
    }

    @Override
    @Transactional
    public void removeOrderItem(int id) {
        this.orderDao.removeOrderItem(id);
    }

    @Override
    @Transactional
    public Order getOrder(int id) {
        return this.orderDao.getOrder(id);
    }

    @Override
    @Transactional
    public OrderItem getOrderItem(int id) {
        return this.orderDao.getOrderItem(id);
    }

    @Override
    @Transactional
    public List<Order> listOrder() {
        return this.orderDao.listOrder();
    }

    @Override
    @Transactional
    public List<Order> listOrder(int id) {
        return this.orderDao.listOrder(id);
    }

    @Override
    @Transactional
    public List<OrderItem> listItem(int id) {
        return this.orderDao.listItem(id);
    }
}
