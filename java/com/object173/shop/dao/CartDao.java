package com.object173.shop.dao;

import com.object173.shop.model.Cart;

import java.util.List;

public interface CartDao {
    public void addCart(Cart cart);
    public void updateCart(Cart cart);
    public void removeCart(int id);
    public Cart getCart(int id);
    public List<Cart> listCart();
    public List<Cart> listCart(int id);
}
