package com.xiangxun.atms.module.check.vo;

import java.util.Date;

public class DataCheckRule implements java.io.Serializable {
	
	private static final long serialVersionUID = -5280975912816237757L;

	private String id;

    private String name;

    private String checkType;

    private String checkObj;

    private String checkDimension;

    private Integer outlierParameter;

    private String outlierRemark;

    private Integer invalidParameter;

    private String invalidRemark;

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
     * @return the value of CHECK_TYPE
     */
    public String getCheckType() {
        return checkType;
    }

    /**
    
     *
     * @param checkType the value for CHECK_TYPE
     */
    public void setCheckType(String checkType) {
        this.checkType = checkType == null ? null : checkType.trim();
    }

    /**
     * @return the value of CHECK_OBJ
     */
    public String getCheckObj() {
        return checkObj;
    }

    /**
    
     *
     * @param checkObj the value for CHECK_OBJ
     */
    public void setCheckObj(String checkObj) {
        this.checkObj = checkObj == null ? null : checkObj.trim();
    }

    /**
     * @return the value of CHECK_DIMENSION
     */
    public String getCheckDimension() {
        return checkDimension;
    }

    /**
    
     *
     * @param checkDimension the value for CHECK_DIMENSION
     */
    public void setCheckDimension(String checkDimension) {
        this.checkDimension = checkDimension == null ? null : checkDimension.trim();
    }

    /**
     * @return the value of OUTLIER_PARAMETER
     */
    public Integer getOutlierParameter() {
        return outlierParameter;
    }

    /**
    
     *
     * @param outlierParameter the value for OUTLIER_PARAMETER
     */
    public void setOutlierParameter(Integer outlierParameter) {
        this.outlierParameter = outlierParameter;
    }

    /**
     * @return the value of OUTLIER_REMARK
     */
    public String getOutlierRemark() {
        return outlierRemark;
    }

    /**
    
     *
     * @param outlierRemark the value for OUTLIER_REMARK
     */
    public void setOutlierRemark(String outlierRemark) {
        this.outlierRemark = outlierRemark == null ? null : outlierRemark.trim();
    }

    /**
     * @return the value of INVALID_PARAMETER
     */
    public Integer getInvalidParameter() {
        return invalidParameter;
    }

    /**
    
     *
     * @param invalidParameter the value for INVALID_PARAMETER
     */
    public void setInvalidParameter(Integer invalidParameter) {
        this.invalidParameter = invalidParameter;
    }

    /**
     * @return the value of INVALID_REMARK
     */
    public String getInvalidRemark() {
        return invalidRemark;
    }

    /**
    
     *
     * @param invalidRemark the value for INVALID_REMARK
     */
    public void setInvalidRemark(String invalidRemark) {
        this.invalidRemark = invalidRemark == null ? null : invalidRemark.trim();
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
        sb.append(", name=").append(name);
        sb.append(", checkType=").append(checkType);
        sb.append(", checkObj=").append(checkObj);
        sb.append(", checkDimension=").append(checkDimension);
        sb.append(", outlierParameter=").append(outlierParameter);
        sb.append(", outlierRemark=").append(outlierRemark);
        sb.append(", invalidParameter=").append(invalidParameter);
        sb.append(", invalidRemark=").append(invalidRemark);
        sb.append(", createId=").append(createId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateId=").append(updateId);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}