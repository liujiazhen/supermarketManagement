package com.supermarket.management.service.impl;

import com.supermarket.management.dao.ApproveRecordDao;
import com.supermarket.management.entity.ApproveRecord;
import com.supermarket.management.service.ApproveRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApproveRecordServiceImpl implements ApproveRecordService {

    @Autowired
    private ApproveRecordDao approveRecordDao;

    @Override
    public ApproveRecord getOne(Long id) {
        return approveRecordDao.getById(id);
    }

    @Override
    public List<ApproveRecord> getApproveRecordlist(String createuser) {
        return approveRecordDao.getAllByCreateuser(createuser);
    }

    @Override
    public List<ApproveRecord> getApproveRecords(String status) {
        return approveRecordDao.getAllByApprovestatus(status);
    }

    @Override
    public List<ApproveRecord> getApproveRecordList(String username) {
        return approveRecordDao.getAllByApproveuser(username);
    }

    @Override
    public ApproveRecord saveApproveRecord(ApproveRecord approveRecord) {
        return approveRecordDao.save(approveRecord);
    }
}
