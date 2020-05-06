package com.supermarket.management.controller;

import com.supermarket.management.config.security.SecurityUserDetails;
import com.supermarket.management.entity.*;
import com.supermarket.management.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.DateUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BillControllrt {

    @Autowired
    private BillService billService;

    @Autowired
    private ApproveRecordService approveRecordService;

    /**
     * 获取一个空对象
     *
     * @return
     */
    @RequestMapping("/newBill")
    public Bill newBill() {
        return new Bill();
    }

    @RequestMapping("/getBill")
    public Bill getBill(Long id) {
        return billService.getOne(id);
    }

    /**
     * 增加
     *
     * @param bill
     * @return
     */
    @RequestMapping("/addBill")
    public int addBill(Bill bill) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUserDetails securityUserDetails = (SecurityUserDetails) authentication.getPrincipal();
        bill.setCreatename(securityUserDetails.getUsername());
        bill.setStatus("0");
        billService.addBill(bill);
        return 1;
    }

    /**
     * @param bill
     * @return
     */
    @RequestMapping("/updataBill")
    public int updataBill(Bill bill) {
        billService.addBill(bill);
        return 1;
    }

    /**
     * 提交
     *
     * @param
     * @return
     */
    @RequestMapping("/approveBill")
    public int approveBill(Long id, String name) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUserDetails securityUserDetails = (SecurityUserDetails) authentication.getPrincipal();
        Bill bill = new Bill();
        bill.setId(id);
        bill = billService.getOne(id);
        bill.setStatus("1");
        billService.addBill(bill);
        ApproveRecord approveRecord = new ApproveRecord();
        approveRecord.setBillid(id);
        approveRecord.setBillname(name);
        approveRecord.setBillcount(bill.getAmountmoney());
        approveRecord.setCreateuser(securityUserDetails.getUsername());
        approveRecord.setApprovestatus("待审核");
        approveRecord.setApprovetime(DateUtils.createNow().getTime().toString());
        approveRecordService.saveApproveRecord(approveRecord);
        return 1;
    }


    /**
     * 删除
     *
     * @param billid
     * @return
     */
    @RequestMapping("/delBill")
    public int delBill(Long billid) {
        return billService.delBill(billid);
    }

    /**
     * 查询
     *
     * @param
     * @return
     */
    @RequestMapping("/getBillList")
    public Map<String, Object> getBillList(String name) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUserDetails securityUserDetails = (SecurityUserDetails) authentication.getPrincipal();
        String username = securityUserDetails.getUsername();
        List<Bill> bills = new ArrayList<>();
        if (name == null || name.equals("")) {
            bills = billService.getBillListByUser(securityUserDetails.getUsername());
        } else {
            bills = billService.getBillList(securityUserDetails.getUsername(), name);
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", "0");
        resultMap.put("msg", "");
        resultMap.put("data", bills);
        resultMap.put("count", bills.size());
        return resultMap;
    }

    /**
     * 根据状态获取
     *
     * @param status
     * @return
     */
    @RequestMapping("/getApproveRecord")
    public Map<String, Object> getApproveRecord(String status) {
        List<ApproveRecord> approveRecords = approveRecordService.getApproveRecords(status);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", "0");
        resultMap.put("msg", "");
        resultMap.put("data", approveRecords);
        resultMap.put("count", approveRecords.size());
        return resultMap;
    }

    /**
     * 根据创建用户获取
     *
     * @param
     * @return
     */
    @RequestMapping("/getApproveRecordByUser")
    public Map<String, Object> getApproveRecordByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUserDetails securityUserDetails = (SecurityUserDetails) authentication.getPrincipal();

        List<ApproveRecord> approveRecords = approveRecordService.getApproveRecordlist(securityUserDetails.getUsername());
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", "0");
        resultMap.put("msg", "");
        resultMap.put("data", approveRecords);
        resultMap.put("count", approveRecords.size());
        return resultMap;
    }


    /**
     * 已驳回
     *
     * @param billid
     * @return
     */
    @RequestMapping("/reject")
    public int reject(Long id, Long billid, String propose) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUserDetails securityUserDetails = (SecurityUserDetails) authentication.getPrincipal();

        Bill bill = new Bill();
        bill.setId(billid);
        bill = billService.getOne(billid);
        bill.setStatus("0");
        billService.addBill(bill);
        ApproveRecord approveRecord = new ApproveRecord();
        approveRecord.setId(id);
        approveRecord = approveRecordService.getOne(id);
        approveRecord.setApproveuser(securityUserDetails.getUsername());
        approveRecord.setApprovetime(DateUtils.createNow().getTime().toString());
        approveRecord.setApprovestatus("已驳回");
        approveRecord.setPropose(propose);
        approveRecordService.saveApproveRecord(approveRecord);
        return 1;
    }

    /**
     * 修改状态
     *
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/approveStatus")
    public int approveStatus(Long id, String status) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUserDetails securityUserDetails = (SecurityUserDetails) authentication.getPrincipal();
        ApproveRecord approveRecord = new ApproveRecord();
        approveRecord.setId(id);
        approveRecord = approveRecordService.getOne(id);
        approveRecord.setApproveuser(securityUserDetails.getUsername());
        approveRecord.setApprovetime(DateUtils.createNow().getTime().toString());
        approveRecord.setApprovestatus(status);
        approveRecord.setPropose("通过");
        approveRecordService.saveApproveRecord(approveRecord);
        return 1;
    }


    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @RequestMapping("/regeritUser")
    public int regeritUser(String username, String password, Long roleid) {
        User user = new User();
        if (userService.getUserByUserName(username) != null) {
            return 0;
        }
        user.setUsername(username);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        User user1 = userService.insertUser(user);
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleid(roleid);
        rolePermission.setUserid(user1.getId());
        rolePermissionService.save(rolePermission);
        return 1;
    }

    @RequestMapping("/getAllRole")
    public List<Role> getAllRole() {
        return roleService.getAll();
    }
}
