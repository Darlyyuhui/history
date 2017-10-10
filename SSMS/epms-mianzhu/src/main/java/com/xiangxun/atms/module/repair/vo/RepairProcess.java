package com.xiangxun.atms.module.repair.vo;

import java.util.Date;

public class RepairProcess {
    private String id;

    private String proId;

    private String stageId;

    private String workDate;

    private String workDept;

    private String workContent;

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
     * @return the value of PRO_ID
     */
    public String getProId() {
        return proId;
    }

    /**
    
     *
     * @param proId the value for PRO_ID
     */
    public void setProId(String proId) {
        this.proId = proId == null ? null : proId.trim();
    }

    /**
     * @return the value of STAGE_ID
     */
    public String getStageId() {
        return stageId;
    }

    /**
    
     *
     * @param stageId the value for STAGE_ID
     */
    public void setStageId(String stageId) {
        this.stageId = stageId == null ? null : stageId.trim();
    }

    /**
     * @return the value of WORK_DATE
     */
    public String getWorkDate() {
        return workDate;
    }

    /**
    
     *
     * @param workDate the value for WORK_DATE
     */
    public void setWorkDate(String workDate) {
        this.workDate = workDate == null ? null : workDate.trim();
    }

    /**
     * @return the value of WORK_DEPT
     */
    public String getWorkDept() {
        return workDept;
    }

    /**
    
     *
     * @param workDept the value for WORK_DEPT
     */
    public void setWorkDept(String workDept) {
        this.workDept = workDept == null ? null : workDept.trim();
    }

    /**
     * @return the value of WORK_CONTENT
     */
    public String getWorkContent() {
        return workContent;
    }

    /**
    
     *
     * @param workContent the value for WORK_CONTENT
     */
    public void setWorkContent(String workContent) {
        this.workContent = workContent == null ? null : workContent.trim();
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
        sb.append(", proId=").append(proId);
        sb.append(", stageId=").append(stageId);
        sb.append(", workDate=").append(workDate);
        sb.append(", workDept=").append(workDept);
        sb.append(", workContent=").append(workContent);
        sb.append(", createId=").append(createId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateId=").append(updateId);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}