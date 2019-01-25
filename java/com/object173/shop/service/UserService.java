package com.object173.shop.service;

import com.object173.shop.model.User;

import java.util.List;

public interface UserService {
    public boolean addUser(User user);
    public boolean updateUser(User user);
    public void removeUser(int id);
    public User getUser(int id);
    public User getUser(String username);
    public List<User> listUser();
}
