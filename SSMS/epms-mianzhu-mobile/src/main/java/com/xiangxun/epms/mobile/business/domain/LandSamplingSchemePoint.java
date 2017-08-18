package com.xiangxun.epms.mobile.business.domain;

import java.math.BigDecimal;
import java.util.Date;

public class LandSamplingSchemePoint {
	private String id;

    private String schemeId;
    
    private String name;
    
    private String code;

    private  BigDecimal   longitude;

    private  BigDecimal   latitude;

    private String rangeX;

    private String rangeY;

    private String areaCode;

    private Integer isSamplingPoint;

    private Integer checkStatus;

    private Integer isRelease;

    private Integer isSampling;

    private String createId;

    private Date createTime;

    private String updateId;

    private Date updateTime;
    
    private String blockId;
    private String regionId;

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
     * @return the value of SCHEME_ID
     */
    public String getSchemeId() {
        return schemeId;
    }

    /**
    
     *
     * @param schemeId the value for SCHEME_ID
     */
    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId == null ? null : schemeId.trim();
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
     * @return the value of LONGITUDE
     */
    public BigDecimal getLongitude() {
        return longitude;
    }

    /**
    
     *
     * @param longitude the value for LONGITUDE
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude == null ? null : longitude;
    }

    /**
     * @return the value of LATITUDE
     */
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
    
     *
     * @param latitude the value for LATITUDE
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude == null ? null : latitude;
    }

    /**
     * @return the value of RANGE_X
     */
    public String getRangeX() {
        return rangeX;
    }

    /**
    
     *
     * @param rangeX the value for RANGE_X
     */
    public void setRangeX(String rangeX) {
        this.rangeX = rangeX == null ? null : rangeX.trim();
    }

    /**
     * @return the value of RANGE_Y
     */
    public String getRangeY() {
        return rangeY;
    }

    /**
    
     *
     * @param rangeY the value for RANGE_Y
     */
    public void setRangeY(String rangeY) {
        this.rangeY = rangeY == null ? null : rangeY.trim();
    }

    /**
     * @return the value of AREA_CODE
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
    
     *
     * @param areaCode the value for AREA_CODE
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    /**
     * @return the value of IS_SAMPLING_POINT
     */
    public Integer getIsSamplingPoint() {
        return isSamplingPoint;
    }

    /**
    
     *
     * @param isSamplingPoint the value for IS_SAMPLING_POINT
     */
    public void setIsSamplingPoint(Integer isSamplingPoint) {
        this.isSamplingPoint = isSamplingPoint;
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
     * @return the value of IS_RELEASE
     */
    public Integer getIsRelease() {
        return isRelease;
    }

    /**
    
     *
     * @param isRelease the value for IS_RELEASE
     */
    public void setIsRelease(Integer isRelease) {
        this.isRelease = isRelease;
    }

    /**
     * @return the value of IS_SAMPLING
     */
    public Integer getIsSampling() {
        return isSampling;
    }

    /**
    
     *
     * @param isSampling the value for IS_SAMPLING
     */
    public void setIsSampling(Integer isSampling) {
        this.isSampling = isSampling;
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

    public String getBlockId() {
		return blockId;
	}

	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name==null?"":name;
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
        sb.append(", schemeId=").append(schemeId);
        sb.append(", code=").append(code);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", rangeX=").append(rangeX);
        sb.append(", rangeY=").append(rangeY);
        sb.append(", areaCode=").append(areaCode);
        sb.append(", isSamplingPoint=").append(isSamplingPoint);
        sb.append(", checkStatus=").append(checkStatus);
        sb.append(", isRelease=").append(isRelease);
        sb.append(", isSampling=").append(isSampling);
        sb.append(", createId=").append(createId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateId=").append(updateId);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }


}
