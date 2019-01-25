package com.object173.shop.service;

import com.object173.shop.model.Cart;

import java.util.List;

public interface CartService {
    public void addCart(Cart cart);
    public void updateCart(Cart cart);
    public void removeCart(int id);
    public void removeAll(int user_id);
    public Cart getCart(int id);
    public List<Cart> listCart();
    public List<Cart> listCart(int id);
}
