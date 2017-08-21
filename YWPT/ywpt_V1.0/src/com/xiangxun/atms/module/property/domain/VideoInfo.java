package com.xiangxun.atms.module.property.domain;

import java.io.Serializable;
import java.util.Date;

//@Document(collection="PROPERTY_VIDEO_INFO")
public class VideoInfo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8962478851156336284L;

//	@Id
    private String id;

//    @Indexed
    private String directionCode;

//    @Indexed
    private String videotypeCode;

//    @Indexed
    private String iscloud;

//    @Indexed
    private String roadinfoId;

//    @Indexed
    private String name;

//    @Indexed
    private String orgId;

//    @Indexed
    private String ip;

//    @Indexed
    private String port;

//    @Indexed
    private String tracode;

//    @Indexed
    private String username;

//    @Indexed
    private String password;

//    @Indexed
    private String iphis;

//    @Indexed
    private String porthis;

//    @Indexed
    private String tracodehis;

//    @Indexed
    private String usernamehis;

//    @Indexed
    private String passwordhis;

//    @Indexed
    private String companyid;

//    @Indexed
    private String photo1;

//    @Indexed
    private String photo2;

//    @Indexed
    private String photo3;

//    @Indexed
    private String note;

//    @Indexed
    private String usetemplate;

//    @Indexed
    private String realvideoaddressid;

//    @Indexed
    private String hisvideoaddressid;

//    @Indexed
    private String mapx;

//    @Indexed
    private String mapy;

//    @Indexed
    private String code;

//    @Indexed
    private String deviceIp;

//    @Indexed
    private String deviceUsername;

//    @Indexed
    private String devicePassword;

//    @Indexed
    private String devicePort;
    
//    @Indexed
    private String deviceShape;
    
//    @Indexed
    private Date inserttime;
    
//    @Indexed
    private String status;
    
//    @Indexed
    private String factoryId;
    
  //扩展属性
    private String orgNames;
    private String companyName;
    
    private String realvideoaddressName;
    
    private String hisvideoaddressName;
    //是否变更
    private boolean hasModified;
    
    //自定义分组时用
    private String customGroupFlag;
    
    //是否分配
    private boolean hasAllot;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDirectionCode() {
        return directionCode;
    }

    public void setDirectionCode(String directionCode) {
        this.directionCode = directionCode == null ? null : directionCode.trim();
    }

    public String getVideotypeCode() {
        return videotypeCode;
    }

    public void setVideotypeCode(String videotypeCode) {
        this.videotypeCode = videotypeCode == null ? null : videotypeCode.trim();
    }

    public String getIscloud() {
        return iscloud;
    }

    public void setIscloud(String iscloud) {
        this.iscloud = iscloud == null ? null : iscloud.trim();
    }

    public String getRoadinfoId() {
        return roadinfoId;
    }

    public void setRoadinfoId(String roadinfoId) {
        this.roadinfoId = roadinfoId == null ? null : roadinfoId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port == null ? null : port.trim();
    }

    public String getTracode() {
        return tracode;
    }

    public void setTracode(String tracode) {
        this.tracode = tracode == null ? null : tracode.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getIphis() {
        return iphis;
    }

    public void setIphis(String iphis) {
        this.iphis = iphis == null ? null : iphis.trim();
    }

    public String getPorthis() {
        return porthis;
    }

    public void setPorthis(String porthis) {
        this.porthis = porthis == null ? null : porthis.trim();
    }

    public String getTracodehis() {
        return tracodehis;
    }

    public void setTracodehis(String tracodehis) {
        this.tracodehis = tracodehis == null ? null : tracodehis.trim();
    }

    public String getUsernamehis() {
        return usernamehis;
    }

    public void setUsernamehis(String usernamehis) {
        this.usernamehis = usernamehis == null ? null : usernamehis.trim();
    }

    public String getPasswordhis() {
        return passwordhis;
    }

    public void setPasswordhis(String passwordhis) {
        this.passwordhis = passwordhis == null ? null : passwordhis.trim();
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid == null ? null : companyid.trim();
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1 == null ? null : photo1.trim();
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2 == null ? null : photo2.trim();
    }

    public String getPhoto3() {
        return photo3;
    }

    public void setPhoto3(String photo3) {
        this.photo3 = photo3 == null ? null : photo3.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getUsetemplate() {
        return usetemplate;
    }

    public void setUsetemplate(String usetemplate) {
        this.usetemplate = usetemplate == null ? null : usetemplate.trim();
    }

    public String getRealvideoaddressid() {
        return realvideoaddressid;
    }

    public void setRealvideoaddressid(String realvideoaddressid) {
        this.realvideoaddressid = realvideoaddressid == null ? null : realvideoaddressid.trim();
    }

    public String getHisvideoaddressid() {
        return hisvideoaddressid;
    }

    public void setHisvideoaddressid(String hisvideoaddressid) {
        this.hisvideoaddressid = hisvideoaddressid == null ? null : hisvideoaddressid.trim();
    }

    public String getMapx() {
        return mapx;
    }

    public void setMapx(String mapx) {
        this.mapx = mapx == null ? null : mapx.trim();
    }

    public String getMapy() {
        return mapy;
    }

    public void setMapy(String mapy) {
        this.mapy = mapy == null ? null : mapy.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getDeviceIp() {
        return deviceIp;
    }

    public void setDeviceIp(String deviceIp) {
        this.deviceIp = deviceIp == null ? null : deviceIp.trim();
    }

    public String getDeviceUsername() {
        return deviceUsername;
    }

    public void setDeviceUsername(String deviceUsername) {
        this.deviceUsername = deviceUsername == null ? null : deviceUsername.trim();
    }

    public String getDevicePassword() {
        return devicePassword;
    }

    public void setDevicePassword(String devicePassword) {
        this.devicePassword = devicePassword == null ? null : devicePassword.trim();
    }

    public String getDevicePort() {
        return devicePort;
    }

    public void setDevicePort(String devicePort) {
        this.devicePort = devicePort == null ? null : devicePort.trim();
    }

	public String getOrgNames() {
		return orgNames;
	}

	public void setOrgNames(String orgNames) {
		this.orgNames = orgNames;
	}

	public String getRealvideoaddressName() {
		return realvideoaddressName;
	}

	public void setRealvideoaddressName(String realvideoaddressName) {
		this.realvideoaddressName = realvideoaddressName;
	}

	public String getHisvideoaddressName() {
		return hisvideoaddressName;
	}

	public void setHisvideoaddressName(String hisvideoaddressName) {
		this.hisvideoaddressName = hisvideoaddressName;
	}

	public String getDeviceShape() {
		return deviceShape;
	}

	public void setDeviceShape(String deviceShape) {
		this.deviceShape = deviceShape;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getInserttime() {
		return inserttime;
	}

	public void setInserttime(Date inserttime) {
		this.inserttime = inserttime;
	}

	public String getCustomGroupFlag() {
		return customGroupFlag;
	}

	public void setCustomGroupFlag(String customGroupFlag) {
		this.customGroupFlag = customGroupFlag;
	}

	public boolean isHasModified() {
		return hasModified;
	}

	public void setHasModified(boolean hasModified) {
		this.hasModified = hasModified;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFactoryId() {
		return factoryId;
	}

	public void setFactoryId(String factoryId) {
		this.factoryId = factoryId;
	}

	/**
	 * @return the hasAllot
	 */
	public boolean isHasAllot() {
		return hasAllot;
	}

	/**
	 * @param hasAllot the hasAllot to set
	 */
	public void setHasAllot(boolean hasAllot) {
		this.hasAllot = hasAllot;
	}

	
    
}