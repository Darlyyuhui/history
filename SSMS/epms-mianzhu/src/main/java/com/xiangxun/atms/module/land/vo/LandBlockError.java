package com.xiangxun.atms.module.land.vo;

import java.math.BigDecimal;
import java.util.Date;

public class LandBlockError {
    private String id;

    private String landBlockId;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private Date errorTime;

    private String describe;

    private String createId;

    private Date createTime;

    private String updateId;

    private Date updateTime;

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
     * @return the value of LAND_BLOCK_ID
     */
    public String getLandBlockId() {
        return landBlockId;
    }

    /**
    
     *
     * @param landBlockId the value for LAND_BLOCK_ID
     */
    public void setLandBlockId(String landBlockId) {
        this.landBlockId = landBlockId == null ? null : landBlockId.trim();
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
     * @return the value of ERROR_TIME
     */
    public Date getErrorTime() {
        return errorTime;
    }

    /**
    
     *
     * @param errorTime the value for ERROR_TIME
     */
    public void setErrorTime(Date errorTime) {
        this.errorTime = errorTime;
    }

    /**
     * @return the value of DESCRIBE
     */
    public String getDescribe() {
        return describe;
    }

    /**
    
     *
     * @param describe the value for DESCRIBE
     */
    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
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
    
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", landBlockId=").append(landBlockId);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", errorTime=").append(errorTime);
        sb.append(", describe=").append(describe);
        sb.append(", createId=").append(createId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateId=").append(updateId);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}