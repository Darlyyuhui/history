package com.xiangxun.atms.module.repair.vo;

import java.math.BigDecimal;
import java.util.Date;

public class RepairCapital {
    private String id;

    private String proId;

    private Date moneyTime;

    private BigDecimal total;

    private String source;

    private String competentUnit;

    private String useStatus;

    private String createId;

    private Date createTime;

    private String updateId;

    private Date updateTime;
    
    private String proCode;
    private String proName;

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
     * @return the value of MONEY_TIME
     */
    public Date getMoneyTime() {
        return moneyTime;
    }

    /**
    
     *
     * @param moneyTime the value for MONEY_TIME
     */
    public void setMoneyTime(Date moneyTime) {
        this.moneyTime = moneyTime;
    }

    /**
     * @return the value of TOTAL
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
    
     *
     * @param total the value for TOTAL
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * @return the value of SOURCE
     */
    public String getSource() {
        return source;
    }

    /**
    
     *
     * @param source the value for SOURCE
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    /**
     * @return the value of COMPETENT_UNIT
     */
    public String getCompetentUnit() {
        return competentUnit;
    }

    /**
    
     *
     * @param competentUnit the value for COMPETENT_UNIT
     */
    public void setCompetentUnit(String competentUnit) {
        this.competentUnit = competentUnit == null ? null : competentUnit.trim();
    }

    /**
     * @return the value of USE_STATUS
     */
    public String getUseStatus() {
        return useStatus;
    }

    /**
    
     *
     * @param useStatus the value for USE_STATUS
     */
    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus == null ? null : useStatus.trim();
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

    public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
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
        sb.append(", moneyTime=").append(moneyTime);
        sb.append(", total=").append(total);
        sb.append(", source=").append(source);
        sb.append(", competentUnit=").append(competentUnit);
        sb.append(", useStatus=").append(useStatus);
        sb.append(", createId=").append(createId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateId=").append(updateId);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}