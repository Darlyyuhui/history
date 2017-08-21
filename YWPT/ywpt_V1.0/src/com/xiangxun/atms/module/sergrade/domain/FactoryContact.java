package com.xiangxun.atms.module.sergrade.domain;

//@Document(collection="SERGRADE_FACTORY_CONTACT")
public class FactoryContact {
//    @Indexed
    private String id;

//    @Indexed
    private String deviceid;

//    @Indexed
    private String contactid;
    
    //责任设备
    private String allotProperty;
    
    //用户ID
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid == null ? null : deviceid.trim();
    }

    public String getContactid() {
        return contactid;
    }

    public void setContactid(String contactid) {
        this.contactid = contactid == null ? null : contactid.trim();
    }

	/**
	 * @return the allotProperty
	 */
	public String getAllotProperty() {
		return allotProperty;
	}

	/**
	 * @param allotProperty the allotProperty to set
	 */
	public void setAllotProperty(String allotProperty) {
		this.allotProperty = allotProperty;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
    
    
}