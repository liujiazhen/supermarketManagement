package com.supermarket.management.dao;

import com.supermarket.management.entity.ApproveRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApproveRecordDao extends JpaRepository<ApproveRecord, Long> {

    List<ApproveRecord> getAllByApproveuser(String username);

    List<ApproveRecord> getAllByApprovestatus(String status);

    List<ApproveRecord> getAllByCreateuser(String createuser);

    ApproveRecord getById(Long id);
}
