package com.supermarket.management.dao;

import com.supermarket.management.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePermissionDao extends JpaRepository<RolePermission,Long> {

    RolePermission getDistinctByUserid(Long userid);
}
