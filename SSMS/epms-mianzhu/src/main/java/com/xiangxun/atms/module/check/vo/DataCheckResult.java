package com.xiangxun.atms.module.check.vo;

public class DataCheckResult {
    private String id;

    private String infoId;

    private String regId;

    private String regCode;

    private String errItems;

    private String errTypes;

    private Integer isInvalid;

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
     * @return the value of INFO_ID
     */
    public String getInfoId() {
        return infoId;
    }

    /**
    
     *
     * @param infoId the value for INFO_ID
     */
    public void setInfoId(String infoId) {
        this.infoId = infoId == null ? null : infoId.trim();
    }

    /**
     * @return the value of REG_ID
     */
    public String getRegId() {
        return regId;
    }

    /**
    
     *
     * @param regId the value for REG_ID
     */
    public void setRegId(String regId) {
        this.regId = regId == null ? null : regId.trim();
    }

    /**
     * @return the value of REG_CODE
     */
    public String getRegCode() {
        return regCode;
    }

    /**
    
     *
     * @param regCode the value for REG_CODE
     */
    public void setRegCode(String regCode) {
        this.regCode = regCode == null ? null : regCode.trim();
    }

    /**
     * @return the value of ERR_ITEMS
     */
    public String getErrItems() {
        return errItems;
    }

    /**
    
     *
     * @param errItems the value for ERR_ITEMS
     */
    public void setErrItems(String errItems) {
        this.errItems = errItems == null ? null : errItems.trim();
    }

    /**
     * @return the value of ERR_TYPES
     */
    public String getErrTypes() {
        return errTypes;
    }

    /**
    
     *
     * @param errTypes the value for ERR_TYPES
     */
    public void setErrTypes(String errTypes) {
        this.errTypes = errTypes == null ? null : errTypes.trim();
    }

    /**
     * @return the value of IS_INVALID
     */
    public Integer getIsInvalid() {
        return isInvalid;
    }

    /**
    
     *
     * @param isInvalid the value for IS_INVALID
     */
    public void setIsInvalid(Integer isInvalid) {
        this.isInvalid = isInvalid;
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
        sb.append(", infoId=").append(infoId);
        sb.append(", regId=").append(regId);
        sb.append(", regCode=").append(regCode);
        sb.append(", errItems=").append(errItems);
        sb.append(", errTypes=").append(errTypes);
        sb.append(", isInvalid=").append(isInvalid);
        sb.append("]");
        return sb.toString();
    }
}