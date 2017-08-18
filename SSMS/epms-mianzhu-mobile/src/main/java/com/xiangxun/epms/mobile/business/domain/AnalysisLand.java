package com.xiangxun.epms.mobile.business.domain;

import java.math.BigDecimal;
import java.util.Date;

public class AnalysisLand {
	private String id;

    private String regId;

    private String code;

    private BigDecimal ph;

    private BigDecimal cadmium;

    private BigDecimal availableCadmium;

    private String sampleStatus;

    private Integer isOver;

    private String overItem;

    private String createId;

    private Date createTime;

    private String updateId;

    private Date updateTime;
    
    private int status;
    
    
    /**
     * 查询返回参数
     */
    //样品名称
    private String name;
    //经度
    private String longitude;
    //纬度
    private String latitude;
    //所属区域
    private String regionId;
    //样品时间
    private Date samplingTime;
    private String samplingTimeStr;
    
    
    public String type_name;

    public String analy_name;

    public BigDecimal analy_value;

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
     * @return the value of PH
     */
    public BigDecimal getPh() {
        return ph;
    }

    /**
    
     *
     * @param ph the value for PH
     */
    public void setPh(BigDecimal ph) {
        this.ph = ph;
    }

    /**
     * @return the value of CADMIUM
     */
    public BigDecimal getCadmium() {
        return cadmium;
    }

    /**
    
     *
     * @param cadmium the value for CADMIUM
     */
    public void setCadmium(BigDecimal cadmium) {
        this.cadmium = cadmium;
    }

    /**
     * @return the value of AVAILABLE_CADMIUM
     */
    public BigDecimal getAvailableCadmium() {
        return availableCadmium;
    }

    /**
    
     *
     * @param availableCadmium the value for AVAILABLE_CADMIUM
     */
    public void setAvailableCadmium(BigDecimal availableCadmium) {
        this.availableCadmium = availableCadmium;
    }

    /**
     * @return the value of IS_OVER
     */
    public Integer getIsOver() {
        return isOver;
    }

    /**
    
     *
     * @param isOver the value for IS_OVER
     */
    public void setIsOver(Integer isOver) {
        this.isOver = isOver;
    }

    /**
     * @return the value of OVER_ITEM
     */
    public String getOverItem() {
        return overItem;
    }

    /**
    
     *
     * @param overItem the value for OVER_ITEM
     */
    public void setOverItem(String overItem) {
        this.overItem = overItem == null ? null : overItem.trim();
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

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name==null?" ":name;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public Date getSamplingTime() {
		return samplingTime;
	}

	public void setSamplingTime(Date samplingTime) {
		this.samplingTime = samplingTime;
	}

	public String getSampleStatus() {
		return sampleStatus;
	}

	public void setSampleStatus(String sampleStatus) {
		this.sampleStatus = sampleStatus;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSamplingTimeStr() {
		return samplingTimeStr;
	}

	public void setSamplingTimeStr(String samplingTimeStr) {
		this.samplingTimeStr = samplingTimeStr;
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
        sb.append(", regId=").append(regId);
        sb.append(", code=").append(code);
        sb.append(", ph=").append(ph);
        sb.append(", cadmium=").append(cadmium);
        sb.append(", availableCadmium=").append(availableCadmium);
        sb.append(", status=").append(status);
        sb.append(", isOver=").append(isOver);
        sb.append(", overItem=").append(overItem);
        sb.append(", createId=").append(createId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateId=").append(updateId);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }

}