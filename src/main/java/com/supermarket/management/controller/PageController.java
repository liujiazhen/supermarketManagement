package com.supermarket.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    /**
     * 测试界面
     *
     * @return
     */
    @RequestMapping("/money")
    public String moneyPage() {
        return "money";
    }
    @RequestMapping("/test")
    public String test() {
        return "test";
    }
    /**
     * 员工管理界面
     *
     * @return
     */
    @RequestMapping("/billmanagement")
    public String gobillmanagement() {
        return "billmanagement";
    }

    /**
     * 历史数据查询
     *
     * @return
     */
    @RequestMapping("/historicalquery")
    public String gohistoricalquery() {
        return "historicalquery";
    }

    /**
     * 财务审核界面
     *
     * @return
     */
    @RequestMapping("/billmanagementtop")
    public String gobillmanagementtop() {
        return "billmanagementtop";
    }

    /**
     * 领导审核界面
     * @return
     */
    @RequestMapping("/approvalmanagement")
    public String goapprovalmanagement() {
        return "approvalmanagement";
    }

    @RequestMapping("/regerit")
    public String goregerit() {
        return "regerit";
    }

    @RequestMapping("/productmanage")
    public String productmanage() {
        return "productmanage";
    }
    @RequestMapping("/todayOrderManage")
    public String todayOrderManage() {
        return "todayOrderManage";
    }
    @RequestMapping("/historyordermanage")
    public String historyordermanage() {
        return "historyordermanage";
    }
    @RequestMapping("/inventorymanage")
    public String inventorymanage() {
        return "inventorymanage";
    }
    @RequestMapping("/supermarketOrderManage")
    public String supermarketOrderManage() {
        return "supermarketOrderManage";
    }
    @RequestMapping("/supermarketManagement")
    public String supermarketManagement() {
        return "supermarketManagement";
    }

}
