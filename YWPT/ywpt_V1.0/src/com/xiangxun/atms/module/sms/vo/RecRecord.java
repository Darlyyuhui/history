package com.xiangxun.atms.module.sms.vo;

import java.math.BigDecimal;
import java.util.Date;

//@Document(collection="SMS_RECRECORD")
public class RecRecord {
	//    @Indexed
    private BigDecimal smsindex;

    //    @Indexed
    private String sourcenumber;

    //    @Indexed
    private String content;

    //    @Indexed
    private Date senttime;

    //    @Indexed
    private Short commport;

    //   @Indexed
    private Short msgtype;

    public BigDecimal getSmsindex() {
        return smsindex;
    }

    public void setSmsindex(BigDecimal smsindex) {
        this.smsindex = smsindex;
    }

    public String getSourcenumber() {
        return sourcenumber;
    }

    public void setSourcenumber(String sourcenumber) {
        this.sourcenumber = sourcenumber == null ? null : sourcenumber.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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

    public Short getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(Short msgtype) {
        this.msgtype = msgtype;
    }
}