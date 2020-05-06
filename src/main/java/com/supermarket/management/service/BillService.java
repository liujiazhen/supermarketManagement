package com.supermarket.management.service;

import com.supermarket.management.entity.Bill;

import java.util.List;

public interface BillService {

    List<Bill> getBillList(String username, String name);

    List<Bill> getBillListByUser(String username);

    Bill addBill(Bill bill);

    int delBill(Long id);

    Bill getOne(Long id);

}
