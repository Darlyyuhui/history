package com.xiangxun.atms.module.bs.vo;

public class Owner implements java.io.Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8391798351273718885L;

	private String id;

    private String name;

    private String regionId;

    private String remark;

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
     * @return the value of REGION_ID
     */
    public String getRegionId() {
        return regionId;
    }

    /**
    
     *
     * @param regionId the value for REGION_ID
     */
    public void setRegionId(String regionId) {
        this.regionId = regionId == null ? null : regionId.trim();
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
    
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", regionId=").append(regionId);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}