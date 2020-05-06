package com.supermarket.management.dao;

import com.supermarket.management.entity.Menus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenusDao extends JpaRepository<Menus,Long> {

    Menus getMenusById(Long id);
}
