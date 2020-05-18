package com.supermarket.management.service.impl;

import com.supermarket.management.dao.UserDao;
import com.supermarket.management.entity.User;
import com.supermarket.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
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

    @Override
    public List<String> getAllSupermarket() {
        return userDao.getAllSupermarket();
    }

    @Override
    public Page<User> getAllByPage(User user) {
        Pageable pageable = PageRequest.of(user.getPage() - 1, user.getLimit());
        Page<User> users = userDao.findAll((Specification<User>) (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (user.getSupermarket() != null && !"".equals(user.getSupermarket())) {
                predicateList.add(cb.like(root.get("supermarket").as(String.class), user.getSupermarket() + "%"));
            }
            if (user.getUsername() != null && !"".equals(user.getUsername())) {
                predicateList.add(cb.like(root.get("username").as(String.class), user.getUsername() + "%"));
            }
            return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }, pageable);
        return users;
    }

    @Override
    public int updateUserPassword(Long id, String password) {
        return userDao.updateUserPassword(id,password);

    }

    @Override
    public int deleteSupermarket(Long id) {
        userDao.deleteById(id);
        return 0;
    }
}
