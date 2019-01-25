package com.object173.shop.service;

import com.object173.shop.model.Cart;
import com.object173.shop.model.Order;
import com.object173.shop.model.OrderItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


public interface OrderService {
    public void addOrder(Order order);
    public void addOrder(int user, List<Cart> carts);
    public void updateOrder(Order order);
    public void removeOrder(int id);
    public void removeOrderItem(int id);
    public Order getOrder(int id);
    public OrderItem getOrderItem(int id);
    public List<Order> listOrder();
    public List<Order> listOrder(int id);
    public List<OrderItem> listItem(int id);
}
