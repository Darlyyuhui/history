package com.xiangxun.atms.module.reg.vo;

import java.math.BigDecimal;
import java.util.Date;

public class ManureReg extends BaseReg {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6030703007560746999L;

	private String id;

    private String code;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private String shopName;

    private String shopkeeper;

    private String tel;

    private String dealManure;

    private Date samplingTime;

    private String samplingUser;

    private String samplingSource;

    private Integer checkStatus;

    private String checkUser;

    private Date checkTime;

    private String createId;

    private Date createTime;

    private String updateId;

    private Date updateTime;

    private Integer status;

    private String missionId;

    /**
     * @return the value of ID
     */
    public String getId() {
        return id;
    }

    /**
    
     *
     * @param id the value for ID
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * @return the value of CODE
     */
    public String getCode() {
        return code;
    }

    /**
    
     *
     * @param code the value for CODE
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * @return the value of SHOP_NAME
     */
    public String getShopName() {
        return shopName;
    }

    public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	/**
    
     *
     * @param shopName the value for SHOP_NAME
     */
    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    /**
     * @return the value of SHOPKEEPER
     */
    public String getShopkeeper() {
        return shopkeeper;
    }

    /**
    
     *
     * @param shopkeeper the value for SHOPKEEPER
     */
    public void setShopkeeper(String shopkeeper) {
        this.shopkeeper = shopkeeper == null ? null : shopkeeper.trim();
    }

    /**
     * @return the value of TEL
     */
    public String getTel() {
        return tel;
    }

    /**
    
     *
     * @param tel the value for TEL
     */
    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    /**
     * @return the value of DEAL_MANURE
     */
    public String getDealManure() {
        return dealManure;
    }

    /**
    
     *
     * @param dealManure the value for DEAL_MANURE
     */
    public void setDealManure(String dealManure) {
        this.dealManure = dealManure == null ? null : dealManure.trim();
    }

    /**
     * @return the value of SAMPLING_TIME
     */
    public Date getSamplingTime() {
        return samplingTime;
    }

    /**
    
     *
     * @param samplingTime the value for SAMPLING_TIME
     */
    public void setSamplingTime(Date samplingTime) {
        this.samplingTime = samplingTime;
    }

    /**
     * @return the value of SAMPLING_USER
     */
    public String getSamplingUser() {
        return samplingUser;
    }

    /**
    
     *
     * @param samplingUser the value for SAMPLING_USER
     */
    public void setSamplingUser(String samplingUser) {
        this.samplingUser = samplingUser == null ? null : samplingUser.trim();
    }

    /**
     * @return the value of SAMPLING_SOURCE
     */
    public String getSamplingSource() {
        return samplingSource;
    }

    /**
    
     *
     * @param samplingSource the value for SAMPLING_SOURCE
     */
    public void setSamplingSource(String samplingSource) {
        this.samplingSource = samplingSource == null ? null : samplingSource.trim();
    }

    /**
     * @return the value of CHECK_STATUS
     */
    public Integer getCheckStatus() {
        return checkStatus;
    }

    /**
    
     *
     * @param checkStatus the value for CHECK_STATUS
     */
    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    /**
     * @return the value of CHECK_USER
     */
    public String getCheckUser() {
        return checkUser;
    }

    /**
    
     *
     * @param checkUser the value for CHECK_USER
     */
    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser == null ? null : checkUser.trim();
    }

    /**
     * @return the value of CHECK_TIME
     */
    public Date getCheckTime() {
        return checkTime;
    }

    /**
    
     *
     * @param checkTime the value for CHECK_TIME
     */
    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    /**
     * @return the value of CREATE_ID
     */
    public String getCreateId() {
        return createId;
    }

    /**
    
     *
     * @param createId the value for CREATE_ID
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    /**
     * @return the value of CREATE_TIME
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
    
     *
     * @param createTime the value for CREATE_TIME
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the value of UPDATE_ID
     */
    public String getUpdateId() {
        return updateId;
    }

    /**
    
     *
     * @param updateId the value for UPDATE_ID
     */
    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    /**
     * @return the value of UPDATE_TIME
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
    
     *
     * @param updateTime the value for UPDATE_TIME
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return the value of STATUS
     */
    public Integer getStatus() {
        return status;
    }

    /**
    
     *
     * @param status the value for STATUS
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the value of MISSION_ID
     */
    public String getMissionId() {
        return missionId;
    }

    /**
    
     *
     * @param missionId the value for MISSION_ID
     */
    public void setMissionId(String missionId) {
        this.missionId = missionId == null ? null : missionId.trim();
    }

    /**
    
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", code=").append(code);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", shopName=").append(shopName);
        sb.append(", shopkeeper=").append(shopkeeper);
        sb.append(", tel=").append(tel);
        sb.append(", dealManure=").append(dealManure);
        sb.append(", samplingTime=").append(samplingTime);
        sb.append(", samplingUser=").append(samplingUser);
        sb.append(", samplingSource=").append(samplingSource);
        sb.append(", checkStatus=").append(checkStatus);
        sb.append(", checkUser=").append(checkUser);
        sb.append(", checkTime=").append(checkTime);
        sb.append(", createId=").append(createId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateId=").append(updateId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", status=").append(status);
        sb.append(", missionId=").append(missionId);
        sb.append("]");
        return sb.toString();
    }
}