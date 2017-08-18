package com.xiangxun.ywpt.mobile.business.domain;

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
    
    
//	联系人
	private String contactname;

//所属部门
    private String orgname;
    
//建设服务厂商
    private String companyname;
    
  //经度
    private String mapx;
    
  //纬度
    private String mapy;
    
  //图片1
    private String photo1;
    
  //图片2
    private String photo2;
    
  //图片3
    private String photo3;
    
    //图片1
    private byte[] picture1;
    
    //图片2
    private byte[] picture2;

	//图片3
    private byte[] picture3;
    
   

	
	public byte[] getPicture1() {
		return picture1;
	}

	public void setPicture1(byte[] picture1) {
		this.picture1 = picture1;
	}

	public byte[] getPicture2() {
		return picture2;
	}

	public void setPicture2(byte[] picture2) {
		this.picture2 = picture2;
	}

	public byte[] getPicture3() {
		return picture3;
	}

	public void setPicture3(byte[] picture3) {
		this.picture3 = picture3;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDevicename() {
		return devicename;
	}

	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}

	public String getDevicecode() {
		return devicecode;
	}

	public void setDevicecode(String devicecode) {
		this.devicecode = devicecode;
	}

	public String getDeviceip() {
		return deviceip;
	}

	public void setDeviceip(String deviceip) {
		this.deviceip = deviceip;
	}

	public String getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}
	
	public String getIsouter() {
		return isouter;
	}

	public void setIsouter(String isouter) {
		this.isouter = isouter;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public String getAssignaccount() {
		return assignaccount;
	}

	public void setAssignaccount(String assignaccount) {
		this.assignaccount = assignaccount;
	}

	public Date getAssigntime() {
		return assigntime;
	}

	public void setAssigntime(Date assigntime) {
		this.assigntime = assigntime;
	}



	public String getIsreassign() {
		return isreassign;
	}

	public void setIsreassign(String isreassign) {
		this.isreassign = isreassign;
	}

	public String getExceptionid() {
		return exceptionid;
	}

	public void setExceptionid(String exceptionid) {
		this.exceptionid = exceptionid;
	}

	public String getOffaccount() {
		return offaccount;
	}

	public void setOffaccount(String offaccount) {
		this.offaccount = offaccount;
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
		this.orgid = orgid;
	}

	public String getIsleave() {
		return isleave;
	}

	public void setIsleave(String isleave) {
		this.isleave = isleave;
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
		this.assetid = assetid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	public String getContactname() {
		return contactname;
	}

	public void setContactname(String contactname) {
		this.contactname = contactname;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getMapx() {
		return mapx;
	}

	public void setMapx(String mapx) {
		this.mapx = mapx;
	}

	public String getMapy() {
		return mapy;
	}

	public void setMapy(String mapy) {
		this.mapy = mapy;
	}

	public String getPhoto1() {
		return photo1;
	}

	public void setPhoto1(String photo1) {
		this.photo1 = photo1;
	}

	public String getPhoto2() {
		return photo2;
	}

	public void setPhoto2(String photo2) {
		this.photo2 = photo2;
	}

	public String getPhoto3() {
		return photo3;
	}

	public void setPhoto3(String photo3) {
		//this.photo3 =( photo3  == null ? "" : photo3);
        this.photo3 = photo3 == null ? null : photo3.trim();

	}
	
	
    
   /* //附加属性
    private String statusHtml;
    
    private String isappraised;*/

   /* public String getId() {
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
	}*/
}