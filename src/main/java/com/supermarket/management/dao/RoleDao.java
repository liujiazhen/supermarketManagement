package com.supermarket.management.dao;

import com.supermarket.management.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {

    Role getRoleById(Long id);
}
