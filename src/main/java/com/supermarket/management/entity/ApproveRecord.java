package com.supermarket.management.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "t_approve_record")
public class ApproveRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private Long billid;

    private String billname;

    private Integer billcount;

    private String approveuser;

    private String approvetime;

    private String approvestatus;

    private String createuser;

    private String propose;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBillid() {
        return billid;
    }

    public void setBillid(Long billid) {
        this.billid = billid;
    }

    public String getApproveuser() {
        return approveuser;
    }

    public void setApproveuser(String approveuser) {
        this.approveuser = approveuser;
    }

    public String getApprovetime() {
        return approvetime;
    }

    public void setApprovetime(String approvetime) {
        this.approvetime = approvetime;
    }

    public String getApprovestatus() {
        return approvestatus;
    }

    public void setApprovestatus(String approvestatus) {
        this.approvestatus = approvestatus;
    }

    public String getPropose() {
        return propose;
    }

    public void setPropose(String propose) {
        this.propose = propose;
    }

    public String getBillname() {
        return billname;
    }

    public void setBillname(String billname) {
        this.billname = billname;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public Integer getBillcount() {
        return billcount;
    }

    public void setBillcount(Integer billcount) {
        this.billcount = billcount;
    }
}
