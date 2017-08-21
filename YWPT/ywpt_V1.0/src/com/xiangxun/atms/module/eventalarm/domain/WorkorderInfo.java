package com.xiangxun.atms.module.eventalarm.domain;

import java.io.Serializable;
import java.util.Date;

//@Document(collection="ALARM_WORKORDER_INFO")
public class WorkorderInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 377895144214037680L;

//	@Indexed
    private String id;

//    @Indexed
    private String devicename;

//    @Indexed
    private String devicecode;

//    @Indexed
    private String deviceip;

//    @Indexed
    private String devicetype;

//    @Indexed
    private String isouter;

//    @Indexed
    private String position;

//    @Indexed
    private String companyid;

//    @Indexed
    private String contact;

//    @Indexed
    private String telephone;

//    @Indexed
    private String messages;

//    @Indexed
    private String assignaccount;

//    @Indexed
    private Date assigntime;

//    @Indexed
    private String status;

//    @Indexed
    private String isreassign;

//    @Indexed
    private String exceptionid;

//    @Indexed
    private String offaccount;

//    @Indexed
    private Date offtime;

//    @Indexed
    private String orgid;

//    @Indexed
    private String isleave;
    
//    @Indexed
    private String reason;

//    @Indexed
    private String note;
    
//  @Indexed
    private String assetid;
    
    //附加属性
    private String statusHtml;
    
    private String isappraised;
//增加巡更状态
//    @Indexed
    private String xungeng;

//    @Indexed
    private Date xungengtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename == null ? null : devicename.trim();
    }

    public String getDevicecode() {
        return devicecode;
    }

    public void setDevicecode(String devicecode) {
        this.devicecode = devicecode == null ? null : devicecode.trim();
    }

    public String getDeviceip() {
        return deviceip;
    }

    public void setDeviceip(String deviceip) {
        this.deviceip = deviceip == null ? null : deviceip.trim();
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype == null ? null : devicetype.trim();
    }

    public String getIsouter() {
        return isouter;
    }

    public void setIsouter(String isouter) {
        this.isouter = isouter == null ? null : isouter.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid == null ? null : companyid.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages == null ? null : messages.trim();
    }

    public String getAssignaccount() {
        return assignaccount;
    }

    public void setAssignaccount(String assignaccount) {
        this.assignaccount = assignaccount == null ? null : assignaccount.trim();
    }

    public Date getAssigntime() {
        return assigntime;
    }

    public void setAssigntime(Date assigntime) {
        this.assigntime = assigntime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getIsreassign() {
        return isreassign;
    }

    public void setIsreassign(String isreassign) {
        this.isreassign = isreassign == null ? null : isreassign.trim();
    }

    public String getExceptionid() {
        return exceptionid;
    }

    public void setExceptionid(String exceptionid) {
        this.exceptionid = exceptionid == null ? null : exceptionid.trim();
    }

    public String getOffaccount() {
        return offaccount;
    }

    public void setOffaccount(String offaccount) {
        this.offaccount = offaccount == null ? null : offaccount.trim();
    }

    public Date getOfftime() {
        return offtime;
    }

    public void setOfftime(Date offtime) {
        this.offtime = offtime;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid == null ? null : orgid.trim();
    }

    public String getIsleave() {
        return isleave;
    }

    public void setIsleave(String isleave) {
        this.isleave = isleave == null ? null : isleave.trim();
    }

	public String getStatusHtml() {
		return statusHtml;
	}

	public void setStatusHtml(String statusHtml) {
		this.statusHtml = statusHtml;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public String getAssetid() {
        return assetid;
    }

    public void setAssetid(String assetid) {
        this.assetid = assetid == null ? null : assetid.trim();
    }

	public String getIsappraised() {
		return isappraised;
	}

	public void setIsappraised(String isappraised) {
		this.isappraised = isappraised;
	}
	
	 public String getXungeng() {
	    return xungeng;
	 }

	 public void setXungeng(String xungeng) {
        this.xungeng = xungeng == null ? null : xungeng.trim();
    }

    public Date getXungengtime() {
        return xungengtime;
    }

    public void setXungengtime(Date xungengtime) {
        this.xungengtime = xungengtime;
    }
}