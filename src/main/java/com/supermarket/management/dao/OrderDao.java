package com.supermarket.management.dao;

import com.supermarket.management.entity.Order;
import com.supermarket.management.entity.OrderTemp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    @Modifying
    @Query(value = "UPDATE t_order set inventory_flag = 1 WHERE id = :id", nativeQuery = true)
    int updateInventory(@Param("id") Long id);

    @Query(value = "select product_id,product_name,product_category,product_unit,sum(qty) as qty from t_order where supermarket like %:supermarket% and to_days(create_date) = to_days(now()) group by product_id, product_name, product_category, product_unit limit :page,:size",
            nativeQuery = true)
    List<Object> getAll(@Param("supermarket") String supermarket,@Param("page") int page, @Param("size") int size);

}
