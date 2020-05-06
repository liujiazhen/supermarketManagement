package com.supermarket.management.dao;

import com.supermarket.management.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillDao extends JpaRepository<Bill, Long> {

    List<Bill> getAllByCreatenameAndStatusAndNameLike(String username,String status,String name);

    List<Bill> getAllByCreatenameAndStatus(String username,String status);

    Bill getBillById(Long id);

}
