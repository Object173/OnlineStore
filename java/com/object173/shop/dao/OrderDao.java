package com.object173.shop.dao;

import com.object173.shop.model.Cart;
import com.object173.shop.model.Order;
import com.object173.shop.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public interface OrderDao {
    public void addOrder(Order order);
    public void addItem(OrderItem item);
    public void updateOrder(Order order);
    public void removeOrder(int id);
    public void removeOrderItem(int id);
    public Order getOrder(int id);
    public OrderItem getOrderItem(int id);
    public List<Order> listOrder();
    public List<Order> listOrder(int id);
    public List<OrderItem> listItem(int id);
}
