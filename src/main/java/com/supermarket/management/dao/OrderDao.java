package com.supermarket.management.dao;

import com.supermarket.management.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    @Modifying
    @Query(value = "UPDATE t_order set inventory_flag = 1 WHERE id = :id", nativeQuery = true)
    int updateInventory(@Param("id") Long id);

    @Query(value = "select product_id,product_name,product_category,product_unit,supermarket,sum(qty) qty, product_id id,'2020-01-01 00:00:00' create_date,'2020-01-01 00:00:00' update_date, '1' user_id, '1' inventory_flag from t_order group by product_id, product_name, product_category, product_unit, supermarket",
            nativeQuery = true,countQuery = "select count(*) from (select product_id,product_name,product_category,product_unit,supermarket,sum(qty) qty, product_id id,'2020-01-01 00:00:00' create_date,'2020-01-01 00:00:00' update_date, '1' user_id, '1' inventory_flag from t_order group by product_id, product_name, product_category, product_unit, supermarket) as t_order")
    Page<Order> getAll(Pageable pageable);
    @Query(value = "select t.product_id,t.product_name,t.product_category,t.product_unit,t.supermarket,sum(t.qty) qty, t.product_id id,'2020/01/01 00:00:00' create_date,'2020/01/01 00:00:00' update_date, '1' user_id, '1' inventory_flag from t_order t where t.supermarket = :supermarket group by t.product_id, t.product_name, t.product_category, t.product_unit, t.supermarket", nativeQuery = true)
    Page<Order> getAllBySupermarket(@Param("supermarket") String supermarket, Pageable pageable);
}
