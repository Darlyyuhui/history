package com.xiangxun.atms.module.land.vo;

import java.util.Date;

public class LandSamplingPlan {
    private String id;

    private String code;

    private String name;

    private String explain;

    private String dept;

    private String sampleCodes;

    private Date startTime;

    private Date finishTime;

    private Integer isFinish;

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
     * @return the value of EXPLAIN
     */
    public String getExplain() {
        return explain;
    }

    /**
    
     *
     * @param explain the value for EXPLAIN
     */
    public void setExplain(String explain) {
        this.explain = explain == null ? null : explain.trim();
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
     * @return the value of SAMPLE_CODES
     */
    public String getSampleCodes() {
        return sampleCodes;
    }

    /**
    
     *
     * @param sampleCodes the value for SAMPLE_CODES
     */
    public void setSampleCodes(String sampleCodes) {
        this.sampleCodes = sampleCodes == null ? null : sampleCodes.trim();
    }

    /**
     * @return the value of START_TIME
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
    
     *
     * @param startTime the value for START_TIME
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the value of FINISH_TIME
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
    
     *
     * @param finishTime the value for FINISH_TIME
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * @return the value of IS_FINISH
     */
    public Integer getIsFinish() {
        return isFinish;
    }

    /**
    
     *
     * @param isFinish the value for IS_FINISH
     */
    public void setIsFinish(Integer isFinish) {
        this.isFinish = isFinish;
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
        sb.append(", explain=").append(explain);
        sb.append(", dept=").append(dept);
        sb.append(", sampleCodes=").append(sampleCodes);
        sb.append(", startTime=").append(startTime);
        sb.append(", finishTime=").append(finishTime);
        sb.append(", isFinish=").append(isFinish);
        sb.append(", createId=").append(createId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateId=").append(updateId);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}