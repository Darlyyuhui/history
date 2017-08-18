package com.xiangxun.atms.module.pollute.vo;

import java.util.Date;

public class ReservePlan {
    private String id;

    private String code;

    private String name;

    private String accidentType;

    private String dept;

    private Date madeTime;

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
     * @return the value of ACCIDENT_TYPE
     */
    public String getAccidentType() {
        return accidentType;
    }

    /**
    
     *
     * @param accidentType the value for ACCIDENT_TYPE
     */
    public void setAccidentType(String accidentType) {
        this.accidentType = accidentType == null ? null : accidentType.trim();
    }

    /**
     * @return the value of DEPT
     */
    public String getDept() {
        return dept;
    }

    /**
    
     *
     * @param dept the value for DEPT
     */
    public void setDept(String dept) {
        this.dept = dept == null ? null : dept.trim();
    }

    /**
     * @return the value of MADE_TIME
     */
    public Date getMadeTime() {
        return madeTime;
    }

    /**
    
     *
     * @param madeTime the value for MADE_TIME
     */
    public void setMadeTime(Date madeTime) {
        this.madeTime = madeTime;
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
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", accidentType=").append(accidentType);
        sb.append(", dept=").append(dept);
        sb.append(", madeTime=").append(madeTime);
        sb.append(", createId=").append(createId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateId=").append(updateId);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}