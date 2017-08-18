package com.xiangxun.atms.module.reg.vo;

import java.util.Date;

public class WaterPollute {
    private String id;

    private String waterId;

    private String typeCode;

    private String name;

    private String features;

    private String scale;

    private String regionId;

    private String riverDistance;

    private Integer isOutfall;

    private String status;

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
     * @return the value of WATER_ID
     */
    public String getWaterId() {
        return waterId;
    }

    /**
    
     *
     * @param waterId the value for WATER_ID
     */
    public void setWaterId(String waterId) {
        this.waterId = waterId == null ? null : waterId.trim();
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

    /**
     * @return the value of NAME
     */
    public String getName() {
        return name;
    }

    /**
    
     *
     * @param name the value for NAME
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return the value of FEATURES
     */
    public String getFeatures() {
        return features;
    }

    /**
    
     *
     * @param features the value for FEATURES
     */
    public void setFeatures(String features) {
        this.features = features == null ? null : features.trim();
    }

    /**
     * @return the value of SCALE
     */
    public String getScale() {
        return scale;
    }

    /**
    
     *
     * @param scale the value for SCALE
     */
    public void setScale(String scale) {
        this.scale = scale == null ? null : scale.trim();
    }

    /**
     * @return the value of REGION_ID
     */
    public String getRegionId() {
        return regionId;
    }

    /**
    
     *
     * @param regionId the value for REGION_ID
     */
    public void setRegionId(String regionId) {
        this.regionId = regionId == null ? null : regionId.trim();
    }

    /**
     * @return the value of RIVER_DISTANCE
     */
    public String getRiverDistance() {
        return riverDistance;
    }

    /**
    
     *
     * @param riverDistance the value for RIVER_DISTANCE
     */
    public void setRiverDistance(String riverDistance) {
        this.riverDistance = riverDistance == null ? null : riverDistance.trim();
    }

    /**
     * @return the value of IS_OUTFALL
     */
    public Integer getIsOutfall() {
        return isOutfall;
    }

    /**
    
     *
     * @param isOutfall the value for IS_OUTFALL
     */
    public void setIsOutfall(Integer isOutfall) {
        this.isOutfall = isOutfall;
    }

    /**
     * @return the value of STATUS
     */
    public String getStatus() {
        return status;
    }

    /**
    
     *
     * @param status the value for STATUS
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
        sb.append(", waterId=").append(waterId);
        sb.append(", typeCode=").append(typeCode);
        sb.append(", name=").append(name);
        sb.append(", features=").append(features);
        sb.append(", scale=").append(scale);
        sb.append(", regionId=").append(regionId);
        sb.append(", riverDistance=").append(riverDistance);
        sb.append(", isOutfall=").append(isOutfall);
        sb.append(", status=").append(status);
        sb.append(", createId=").append(createId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateId=").append(updateId);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}