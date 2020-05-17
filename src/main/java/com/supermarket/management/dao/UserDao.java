package com.supermarket.management.dao;

import com.supermarket.management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {

    User getUserByUsername(String username);


    @Query(value = "select distinct supermarket from t_user where supermarket <> '管理员' and supermarket <> '采购员'",nativeQuery = true)
    List<String> getAllSupermarket();

    @Modifying
    @Query(value = "update t_user set password = :password where id = :id", nativeQuery = true)
    int updateUserPassword(@Param("id") Long id, @Param("password") String password);
}
