package com.xiangxun.ywpt.mobile.business.domain;

import java.io.Serializable;
import java.util.Date;

//@Document(collection="ALARM_WORKORDER_LOG")
public class WorkorderLog implements Serializable {
//    @Indexed
    private String id;

//    @Indexed
    private String workid;

//    @Indexed
    private String account;

//    @Indexed
    private String operator;

//    @Indexed
    private Date opertime;

//    @Indexed
    private String opercontent;

//    @Indexed
    private String workstatus;

//    @Indexed
    private String note;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getWorkid() {
        return workid;
    }

    public void setWorkid(String workid) {
        this.workid = workid == null ? null : workid.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getOpertime() {
        return opertime;
    }

    public void setOpertime(Date opertime) {
        this.opertime = opertime;
    }

    public String getOpercontent() {
        return opercontent;
    }

    public void setOpercontent(String opercontent) {
        this.opercontent = opercontent == null ? null : opercontent.trim();
    }

    public String getWorkstatus() {
        return workstatus;
    }

    public void setWorkstatus(String workstatus) {
        this.workstatus = workstatus == null ? null : workstatus.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}