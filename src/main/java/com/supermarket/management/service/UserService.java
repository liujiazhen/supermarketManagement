package com.supermarket.management.service;

import com.supermarket.management.entity.User;

public interface UserService {

    User getUserByUserName(String username);

    User insertUser(User user);
}
