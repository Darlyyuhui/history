package com.xiangxun.atms.module.bs.vo;

import java.util.Date;

public class AutoCodeRule {
    private String typeCode;

    private String headCode;

    private String dateType;

    private String centerCode;

    private Integer orderLength;

    private String createId;

    private Date createTime;

    private String updateId;

    private Date updateTime;

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
     * @return the value of HEAD_CODE
     */
    public String getHeadCode() {
        return headCode;
    }

    /**
    
     *
     * @param headCode the value for HEAD_CODE
     */
    public void setHeadCode(String headCode) {
        this.headCode = headCode == null ? null : headCode.trim();
    }

    /**
     * @return the value of DATE_TYPE
     */
    public String getDateType() {
        return dateType;
    }

    /**
    
     *
     * @param dateType the value for DATE_TYPE
     */
    public void setDateType(String dateType) {
        this.dateType = dateType == null ? null : dateType.trim();
    }

    /**
     * @return the value of CENTER_CODE
     */
    public String getCenterCode() {
        return centerCode;
    }

    /**
    
     *
     * @param centerCode the value for CENTER_CODE
     */
    public void setCenterCode(String centerCode) {
        this.centerCode = centerCode == null ? null : centerCode.trim();
    }

    /**
     * @return the value of ORDER_LENGTH
     */
    public Integer getOrderLength() {
        return orderLength;
    }

    /**
    
     *
     * @param orderLength the value for ORDER_LENGTH
     */
    public void setOrderLength(Integer orderLength) {
        this.orderLength = orderLength;
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
        sb.append(", typeCode=").append(typeCode);
        sb.append(", headCode=").append(headCode);
        sb.append(", dateType=").append(dateType);
        sb.append(", centerCode=").append(centerCode);
        sb.append(", orderLength=").append(orderLength);
        sb.append(", createId=").append(createId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateId=").append(updateId);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}