package com.xiangxun.atms.module.bs.vo;

import java.math.BigDecimal;
import java.util.Date;

public class StandardIndustry implements java.io.Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 3000843675886643239L;

	private String id;

    private String code;

    private String name;

    private String sample;

    private String params;

    private String factor;

    private BigDecimal standardVal;

    private String unit;

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
     * @return the value of SAMPLE
     */
    public String getSample() {
        return sample;
    }

    /**
    
     *
     * @param sample the value for SAMPLE
     */
    public void setSample(String sample) {
        this.sample = sample == null ? null : sample.trim();
    }

    /**
     * @return the value of PARAMS
     */
    public String getParams() {
        return params;
    }

    /**
    
     *
     * @param params the value for PARAMS
     */
    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    /**
     * @return the value of FACTOR
     */
    public String getFactor() {
        return factor;
    }

    /**
    
     *
     * @param factor the value for FACTOR
     */
    public void setFactor(String factor) {
        this.factor = factor == null ? null : factor.trim();
    }

    /**
     * @return the value of STANDARD_VAL
     */
    public BigDecimal getStandardVal() {
        return standardVal;
    }

    /**
    
     *
     * @param standardVal the value for STANDARD_VAL
     */
    public void setStandardVal(BigDecimal standardVal) {
        this.standardVal = standardVal;
    }

    /**
     * @return the value of UNIT
     */
    public String getUnit() {
        return unit;
    }

    /**
    
     *
     * @param unit the value for UNIT
     */
    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
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
        sb.append(", sample=").append(sample);
        sb.append(", params=").append(params);
        sb.append(", factor=").append(factor);
        sb.append(", standardVal=").append(standardVal);
        sb.append(", unit=").append(unit);
        sb.append(", createId=").append(createId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateId=").append(updateId);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}