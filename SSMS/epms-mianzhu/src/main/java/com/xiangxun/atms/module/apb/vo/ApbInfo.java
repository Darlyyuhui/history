package com.xiangxun.atms.module.apb.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ApbInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6485073676587007361L;

	private String id;

    private String code;

    private String name;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private String address;

    private String annualOutput;

    private BigDecimal area;

    private String mainProduct;

    private String describe;

    private String ambient;

    private String soilType;

    private String polluteDesc;

    private String createId;

    private Date createTime;

    private String updateId;

    private Date updateTime;

    public String getGeoJson() {
		return geoJson;
	}

	public void setGeoJson(String geoJson) {
		this.geoJson = geoJson;
	}

	private String geoJson;
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

    public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	/**
     * @return the value of ADDRESS
     */
    public String getAddress() {
        return address;
    }

    /**
    
     *
     * @param address the value for ADDRESS
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * @return the value of ANNUAL_OUTPUT
     */
    public String getAnnualOutput() {
        return annualOutput;
    }

    /**
    
     *
     * @param annualOutput the value for ANNUAL_OUTPUT
     */
    public void setAnnualOutput(String annualOutput) {
        this.annualOutput = annualOutput == null ? null : annualOutput.trim();
    }

    /**
     * @return the value of AREA
     */
    public BigDecimal getArea() {
        return area;
    }

    /**
    
     *
     * @param area the value for AREA
     */
    public void setArea(BigDecimal area) {
        this.area = area == null ? null : area;
    }

    /**
     * @return the value of MAIN_PRODUCT
     */
    public String getMainProduct() {
        return mainProduct;
    }

    /**
    
     *
     * @param mainProduct the value for MAIN_PRODUCT
     */
    public void setMainProduct(String mainProduct) {
        this.mainProduct = mainProduct == null ? null : mainProduct.trim();
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
     * @return the value of AMBIENT
     */
    public String getAmbient() {
        return ambient;
    }

    /**
    
     *
     * @param ambient the value for AMBIENT
     */
    public void setAmbient(String ambient) {
        this.ambient = ambient == null ? null : ambient.trim();
    }

    /**
     * @return the value of SOIL_TYPE
     */
    public String getSoilType() {
        return soilType;
    }

    /**
    
     *
     * @param soilType the value for SOIL_TYPE
     */
    public void setSoilType(String soilType) {
        this.soilType = soilType == null ? null : soilType.trim();
    }

    /**
     * @return the value of POLLUTE_DESC
     */
    public String getPolluteDesc() {
        return polluteDesc;
    }

    /**
    
     *
     * @param polluteDesc the value for POLLUTE_DESC
     */
    public void setPolluteDesc(String polluteDesc) {
        this.polluteDesc = polluteDesc == null ? null : polluteDesc.trim();
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
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", address=").append(address);
        sb.append(", annualOutput=").append(annualOutput);
        sb.append(", area=").append(area);
        sb.append(", mainProduct=").append(mainProduct);
        sb.append(", describe=").append(describe);
        sb.append(", ambient=").append(ambient);
        sb.append(", soilType=").append(soilType);
        sb.append(", polluteDesc=").append(polluteDesc);
        sb.append(", createId=").append(createId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateId=").append(updateId);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}