package com.xiangxun.atms.module.bs.vo;

import java.util.Date;

public class MobileApp {
    private String id;

    private String version;

    private String name;

    private String remark;

    private String saveUrl;

    private String createId;

    private Date createTime;

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
     * @return the value of VERSION
     */
    public String getVersion() {
        return version;
    }

    /**
    
     *
     * @param version the value for VERSION
     */
    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
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
     * @return the value of REMARK
     */
    public String getRemark() {
        return remark;
    }

    /**
    
     *
     * @param remark the value for REMARK
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * @return the value of SAVE_URL
     */
    public String getSaveUrl() {
        return saveUrl;
    }

    /**
    
     *
     * @param saveUrl the value for SAVE_URL
     */
    public void setSaveUrl(String saveUrl) {
        this.saveUrl = saveUrl == null ? null : saveUrl.trim();
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
    
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", version=").append(version);
        sb.append(", name=").append(name);
        sb.append(", remark=").append(remark);
        sb.append(", saveUrl=").append(saveUrl);
        sb.append(", createId=").append(createId);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}