package com.xiangxun.atms.module.bs.vo;

import java.math.BigDecimal;

public class Region implements java.io.Serializable {
	private static final long serialVersionUID = -7958953817103076089L;

	private String id;

    private String name;

    private String pid;

    private Integer sort;

    private BigDecimal longitude;

    private BigDecimal latitude;

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
     * @return the value of PID
     */
    public String getPid() {
        return pid;
    }

    /**
    
     *
     * @param pid the value for PID
     */
    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    /**
     * @return the value of SORT
     */
    public Integer getSort() {
        return sort;
    }

    /**
    
     *
     * @param sort the value for SORT
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * @return the value of LONGITUDE
     */
    public BigDecimal getLongitude() {
        return longitude;
    }

    /**
    
     *
     * @param longitude the value for LONGITUDE
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the value of LATITUDE
     */
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
    
     *
     * @param latitude the value for LATITUDE
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
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
        sb.append(", pid=").append(pid);
        sb.append(", sort=").append(sort);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}