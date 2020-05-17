package com.supermarket.management.service;

import com.supermarket.management.entity.User;

import java.util.List;

public interface UserService {

    User getUserByUserName(String username);

    User insertUser(User user);

    List<String> getAllSupermarket();
}
