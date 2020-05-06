package com.supermarket.management.service;

import com.supermarket.management.entity.ApproveRecord;

import java.util.List;

public interface ApproveRecordService {

    List<ApproveRecord> getApproveRecordList(String username);

    ApproveRecord saveApproveRecord(ApproveRecord approveRecord);

    List<ApproveRecord> getApproveRecords(String status);

    List<ApproveRecord> getApproveRecordlist(String createuser);

    ApproveRecord getOne(Long id);
}
