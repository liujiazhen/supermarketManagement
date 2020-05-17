package com.supermarket.management.dao;

import com.supermarket.management.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryDao extends JpaRepository<Inventory, Long>, JpaSpecificationExecutor<Inventory> {

    @Modifying
    @Query(value = "update t_inventory set qty = qty - :inventoryQty where id = :inventoryId", nativeQuery = true)
    void updateInventory(@Param("inventoryId") Long inventory, @Param("inventoryQty") int qty);
}
