package com.object173.shop.service;

import com.object173.shop.dao.ProductDao;
import com.object173.shop.dao.UserDao;
import com.object173.shop.model.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public boolean addUser(User user) {
            if (!user.isValidate()) return false;
            if(user.getAuthority()==null) user.setAuthority("ROLE_USER");
            if(user.getAuthority().length()<2) user.setAuthority("ROLE_USER");
            this.userDao.addUser(user);
        return true;
    }

    @Override
    @Transactional
    public boolean updateUser(User user) {
        if(!user.isValidate()) return false;
        if(user.getAuthority()==null) user.setAuthority("ROLE_USER");
        if(user.getAuthority().length()<2) user.setAuthority("ROLE_USER");
        this.userDao.updateUser(user);
        return true;
    }

    @Override
    @Transactional
    public void removeUser(int id) {
        this.userDao.removeUser(id);
    }

    @Override
    @Transactional
    public User getUser(int id) {
        return this.userDao.getUser(id);
    }

    @Override
    @Transactional
    public User getUser(String username) {
        return this.userDao.getUser(username);
    }

    @Override
    @Transactional
    public List<User> listUser() {
        return this.userDao.listUser();
    }
}
