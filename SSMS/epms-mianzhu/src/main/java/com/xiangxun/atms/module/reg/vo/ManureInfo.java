package com.xiangxun.atms.module.reg.vo;

import java.util.Date;

public class ManureInfo {
    private String id;

    private String manureRegId;

    private String typeCode;

    private String sale;

    private String suggest;

    private String salesYear;

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
     * @return the value of MANURE_REG_ID
     */
    public String getManureRegId() {
        return manureRegId;
    }

    /**
    
     *
     * @param manureRegId the value for MANURE_REG_ID
     */
    public void setManureRegId(String manureRegId) {
        this.manureRegId = manureRegId == null ? null : manureRegId.trim();
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
     * @return the value of SALE
     */
    public String getSale() {
        return sale;
    }

    /**
    
     *
     * @param sale the value for SALE
     */
    public void setSale(String sale) {
        this.sale = sale == null ? null : sale.trim();
    }

    /**
     * @return the value of SUGGEST
     */
    public String getSuggest() {
        return suggest;
    }

    /**
    
     *
     * @param suggest the value for SUGGEST
     */
    public void setSuggest(String suggest) {
        this.suggest = suggest == null ? null : suggest.trim();
    }

    /**
     * @return the value of SALES_YEAR
     */
    public String getSalesYear() {
        return salesYear;
    }

    /**
    
     *
     * @param salesYear the value for SALES_YEAR
     */
    public void setSalesYear(String salesYear) {
        this.salesYear = salesYear == null ? null : salesYear.trim();
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
        sb.append(", manureRegId=").append(manureRegId);
        sb.append(", typeCode=").append(typeCode);
        sb.append(", sale=").append(sale);
        sb.append(", suggest=").append(suggest);
        sb.append(", salesYear=").append(salesYear);
        sb.append(", createId=").append(createId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateId=").append(updateId);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}