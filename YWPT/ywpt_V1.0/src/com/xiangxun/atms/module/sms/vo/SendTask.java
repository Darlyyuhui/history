package com.xiangxun.atms.module.sms.vo;

import java.math.BigDecimal;
import java.util.Date;
//@Document(collection="SMS_SENDTASK")
public class SendTask {
	//   @Indexed
    private BigDecimal taskid;

    //   @Indexed
    private String destnumber;

    //   @Indexed
    private String content;

    //   @Indexed
    private String signname;

    //  @Indexed
    private Short sendpriority;

    //   @Indexed
    private Date sendtime;

    //  @Indexed
    private Short statusreport;

    //  @Indexed
    private Short englishflag;

    //  @Indexed
    private Short msgtype;

    //  @Indexed
    private String pushurl;

    //   @Indexed
    private Short recaction;

    //   @Indexed
    private Short validminute;

    //  @Indexed
    private Short sendflag;

    //   @Indexed
    private Short commport;

    //  @Indexed
    private Short splitcount;

    //  @Indexed
    private String batchid;

    // @Indexed
    private Short ringflag;

    public BigDecimal getTaskid() {
        return taskid;
    }

    public void setTaskid(BigDecimal taskid) {
        this.taskid = taskid;
    }

    public String getDestnumber() {
        return destnumber;
    }

    public void setDestnumber(String destnumber) {
        this.destnumber = destnumber == null ? null : destnumber.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getSignname() {
        return signname;
    }

    public void setSignname(String signname) {
        this.signname = signname == null ? null : signname.trim();
    }

    public Short getSendpriority() {
        return sendpriority;
    }

    public void setSendpriority(Short sendpriority) {
        this.sendpriority = sendpriority;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public Short getStatusreport() {
        return statusreport;
    }

    public void setStatusreport(Short statusreport) {
        this.statusreport = statusreport;
    }

    public Short getEnglishflag() {
        return englishflag;
    }

    public void setEnglishflag(Short englishflag) {
        this.englishflag = englishflag;
    }

    public Short getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(Short msgtype) {
        this.msgtype = msgtype;
    }

    public String getPushurl() {
        return pushurl;
    }

    public void setPushurl(String pushurl) {
        this.pushurl = pushurl == null ? null : pushurl.trim();
    }

    public Short getRecaction() {
        return recaction;
    }

    public void setRecaction(Short recaction) {
        this.recaction = recaction;
    }

    public Short getValidminute() {
        return validminute;
    }

    public void setValidminute(Short validminute) {
        this.validminute = validminute;
    }

    public Short getSendflag() {
        return sendflag;
    }

    public void setSendflag(Short sendflag) {
        this.sendflag = sendflag;
    }

    public Short getCommport() {
        return commport;
    }

    public void setCommport(Short commport) {
        this.commport = commport;
    }

    public Short getSplitcount() {
        return splitcount;
    }

    public void setSplitcount(Short splitcount) {
        this.splitcount = splitcount;
    }

    public String getBatchid() {
        return batchid;
    }

    public void setBatchid(String batchid) {
        this.batchid = batchid == null ? null : batchid.trim();
    }

    public Short getRingflag() {
        return ringflag;
    }

    public void setRingflag(Short ringflag) {
        this.ringflag = ringflag;
    }
}