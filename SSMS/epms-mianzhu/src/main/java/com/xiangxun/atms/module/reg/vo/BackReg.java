package com.xiangxun.atms.module.reg.vo;

import java.math.BigDecimal;
import java.util.Date;

public class BackReg extends BaseReg {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3252710922374311477L;

	private String id;

    private String code;

    private String typeCode;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private String ambient;

    private String years;

    private String wallSource;

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
    
    
    private String testItems;

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
     * @return the value of TYPE_CODE
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
    
     *
     * @param typeCode the value for TYPE_CODE
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
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
     * @return the value of AMBIENT
     */
    public String getAmbient() {
        return ambient;
    }

    /**
    
     *
     * @param ambient the value for AMBIENT
     */
    public void setAmbient(String ambient) {
        this.ambient = ambient == null ? null : ambient.trim();
    }

    /**
     * @return the value of YEARS
     */
    public String getYears() {
        return years;
    }

    /**
    
     *
     * @param years the value for YEARS
     */
    public void setYears(String years) {
        this.years = years == null ? null : years.trim();
    }

    /**
     * @return the value of WALL_SOURCE
     */
    public String getWallSource() {
        return wallSource;
    }

    /**
    
     *
     * @param wallSource the value for WALL_SOURCE
     */
    public void setWallSource(String wallSource) {
        this.wallSource = wallSource == null ? null : wallSource.trim();
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

    public String getTestItems() {
		return testItems;
	}

	public void setTestItems(String testItems) {
		this.testItems = testItems;
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
        sb.append(", typeCode=").append(typeCode);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", ambient=").append(ambient);
        sb.append(", years=").append(years);
        sb.append(", wallSource=").append(wallSource);
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