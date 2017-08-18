package com.xiangxun.atms.module.bs.vo;

import java.math.BigDecimal;
import java.util.Date;

public class StandardMonitor implements java.io.Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 2668448638170797997L;

	private String id;

    private String code;

    private String typeCode;

    private String name;

    private BigDecimal minVal;

    private BigDecimal maxVal;

    private BigDecimal alarmVal;

    private String unit;

    private String level;

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
     * @return the value of MIN_VAL
     */
    public BigDecimal getMinVal() {
        return minVal;
    }

    /**
    
     *
     * @param minVal the value for MIN_VAL
     */
    public void setMinVal(BigDecimal minVal) {
        this.minVal = minVal;
    }

    /**
     * @return the value of MAX_VAL
     */
    public BigDecimal getMaxVal() {
        return maxVal;
    }

    /**
    
     *
     * @param maxVal the value for MAX_VAL
     */
    public void setMaxVal(BigDecimal maxVal) {
        this.maxVal = maxVal;
    }

    /**
     * @return the value of ALARM_VAL
     */
    public BigDecimal getAlarmVal() {
        return alarmVal;
    }

    /**
    
     *
     * @param alarmVal the value for ALARM_VAL
     */
    public void setAlarmVal(BigDecimal alarmVal) {
        this.alarmVal = alarmVal;
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
     * @return the value of LEVEL
     */
    public String getLevel() {
        return level;
    }

    /**
    
     *
     * @param level the value for LEVEL
     */
    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
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
        sb.append(", code=").append(code);
        sb.append(", typeCode=").append(typeCode);
        sb.append(", name=").append(name);
        sb.append(", minVal=").append(minVal);
        sb.append(", maxVal=").append(maxVal);
        sb.append(", alarmVal=").append(alarmVal);
        sb.append(", unit=").append(unit);
        sb.append(", level=").append(level);
        sb.append(", describe=").append(describe);
        sb.append(", createId=").append(createId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateId=").append(updateId);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}