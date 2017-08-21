package com.xiangxun.atms.module.sms.vo;

import java.math.BigDecimal;
import java.util.Date;

//@Document(collection="SMS_SENTRECORD")
public class SentRecord {
	//   @Indexed
    private BigDecimal msgid;

    //  @Indexed
    private Short splitindex;

    //   @Indexed
    private String desttel;

    //   @Indexed
    private String content;

    //   @Indexed
    private Short sentstatus;

    //   @Indexed
    private Date senttime;

    //   @Indexed
    private Short commport;

    //   @Indexed
    private String batchid;

    public BigDecimal getMsgid() {
        return msgid;
    }

    public void setMsgid(BigDecimal msgid) {
        this.msgid = msgid;
    }

    public Short getSplitindex() {
        return splitindex;
    }

    public void setSplitindex(Short splitindex) {
        this.splitindex = splitindex;
    }

    public String getDesttel() {
        return desttel;
    }

    public void setDesttel(String desttel) {
        this.desttel = desttel == null ? null : desttel.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Short getSentstatus() {
        return sentstatus;
    }

    public void setSentstatus(Short sentstatus) {
        this.sentstatus = sentstatus;
    }

    public Date getSenttime() {
        return senttime;
    }

    public void setSenttime(Date senttime) {
        this.senttime = senttime;
    }

    public Short getCommport() {
        return commport;
    }

    public void setCommport(Short commport) {
        this.commport = commport;
    }

    public String getBatchid() {
        return batchid;
    }

    public void setBatchid(String batchid) {
        this.batchid = batchid == null ? null : batchid.trim();
    }
}