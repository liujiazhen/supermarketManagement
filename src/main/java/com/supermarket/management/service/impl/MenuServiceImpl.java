package com.supermarket.management.service.impl;

import com.supermarket.management.dao.MenusDao;
import com.supermarket.management.entity.Menus;
import com.supermarket.management.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenusDao menusDao;

    @Override
    public Menus getMenus(Long id) {
        return menusDao.getMenusById(id);
    }
}
