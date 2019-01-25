package com.object173.shop.dao;

import com.object173.shop.model.User;

import java.util.List;

public interface UserDao {
    public void addUser(User user);
    public void updateUser(User user);
    public void removeUser(int id);
    public User getUser(int id);
    public User getUser(String username);
    public List<User> listUser();
}
