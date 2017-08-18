package com.xiangxun.atms.module.bs.vo;

import java.util.Date;

public class MobileDevice {
    private String id;

    private String mobelNo;

    private String imeiNo;

    private String userId;

    private String userPhone;

    private String createId;

    private Date createTime;

    private String updateId;

    private Date updateTime;

    private Integer status;

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
     * @return the value of MOBEL_NO
     */
    public String getMobelNo() {
        return mobelNo;
    }

    /**
    
     *
     * @param mobelNo the value for MOBEL_NO
     */
    public void setMobelNo(String mobelNo) {
        this.mobelNo = mobelNo == null ? null : mobelNo.trim();
    }

    /**
     * @return the value of IMEI_NO
     */
    public String getImeiNo() {
        return imeiNo;
    }

    /**
    
     *
     * @param imeiNo the value for IMEI_NO
     */
    public void setImeiNo(String imeiNo) {
        this.imeiNo = imeiNo == null ? null : imeiNo.trim();
    }

    /**
     * @return the value of USER_ID
     */
    public String getUserId() {
        return userId;
    }

    /**
    
     *
     * @param userId the value for USER_ID
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * @return the value of USER_PHONE
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
    
     *
     * @param userPhone the value for USER_PHONE
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
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
    
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", mobelNo=").append(mobelNo);
        sb.append(", imeiNo=").append(imeiNo);
        sb.append(", userId=").append(userId);
        sb.append(", userPhone=").append(userPhone);
        sb.append(", createId=").append(createId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateId=").append(updateId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}