package com.supermarket.management.service.impl;

import com.supermarket.management.dao.UserDao;
import com.supermarket.management.entity.User;
import com.supermarket.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByUserName(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public User insertUser(User user) {
        return userDao.save(user);
    }
}
