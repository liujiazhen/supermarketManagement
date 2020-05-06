package com.supermarket.management.service.impl;

import com.supermarket.management.dao.BillDao;
import com.supermarket.management.entity.Bill;
import com.supermarket.management.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillDao billDao;

    @Override
    public List<Bill> getBillList(String username, String name) {
        return billDao.getAllByCreatenameAndStatusAndNameLike(username, "0",name);
    }

    @Override
    public List<Bill> getBillListByUser(String username) {
        return billDao.getAllByCreatenameAndStatus(username, "0");
    }

    @Override
    public Bill addBill(Bill bill) {
        return billDao.save(bill);
    }

    @Override
    public Bill getOne(Long id) {
        return billDao.getBillById(id);
    }

    @Override
    public int delBill(Long id) {
        billDao.deleteById(id);
        return 1;
    }

}
