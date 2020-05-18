package com.supermarket.management.service;

import com.supermarket.management.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    User getUserByUserName(String username);

    User insertUser(User user);

    List<String> getAllSupermarket();

    Page<User> getAllByPage(User user);

    int updateUserPassword(Long id, String password);

    int deleteSupermarket(Long id);
}
