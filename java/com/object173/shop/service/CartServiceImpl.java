package com.object173.shop.service;

import com.object173.shop.dao.CartDao;
import com.object173.shop.dao.CategoryDao;
import com.object173.shop.model.Cart;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private CartDao cartDao;

    public void setCartDao(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    @Override
    @Transactional
    public void addCart(Cart cart) {
        try {
            this.cartDao.addCart(cart);
        }
        catch(Exception ex) {}
    }

    @Override
    @Transactional
    public void updateCart(Cart cart) {
        this.cartDao.updateCart(cart);
    }

    @Override
    @Transactional
    public void removeCart(int id) {
        this.cartDao.removeCart(id);
    }

    @Override
    @Transactional
    public void removeAll(int user_id) {
        List<Cart> carts = listCart(user_id);
        for(Cart cart:carts) removeCart(cart.getId());
    }

    @Override
    @Transactional
    public Cart getCart(int id) {
        return this.cartDao.getCart(id);
    }

    @Override
    @Transactional
    public List<Cart> listCart() {
        return this.cartDao.listCart();
    }

    @Override
    @Transactional
    public List<Cart> listCart(int id) {
        return this.cartDao.listCart(id);
    }
}
