package com.supermarket.management.dao;

import com.supermarket.management.entity.RoleMenus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMenusDao extends JpaRepository<RoleMenus,Long> {

    List<RoleMenus> getRoleMenusByRoleid(Long roleid);
}
