package com.xiangxun.atms.module.land.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.xiangxun.atms.module.base.vo.BaseVo;

public class LandBlock extends BaseVo {
	private static final long serialVersionUID = -1928925991632726013L;

	private String id;

    private String code;

    private String name;

    private String typeCode;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private String regionId;

    private BigDecimal area;

    private String soilType;

    private String polluteType;

    private String ownerId;

    private String createId;

    private Date createTime;

    private String updateId;

    private Date updateTime;
    
    
    private Integer isError;

    private Integer repairStatus;
    
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

	public BigDecimal getArea() {
		return super.makeNum(area);
	}

	public void setArea(BigDecimal area) {
		this.area = area;
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
     * @return the value of POLLUTE_TYPE
     */
    public String getPolluteType() {
        return polluteType;
    }

    /**
    
     *
     * @param polluteType the value for POLLUTE_TYPE
     */
    public void setPolluteType(String polluteType) {
        this.polluteType = polluteType == null ? null : polluteType.trim();
    }

    /**
     * @return the value of OWNER_ID
     */
    public String getOwnerId() {
        return ownerId;
    }

    /**
    
     *
     * @param ownerId the value for OWNER_ID
     */
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId == null ? null : ownerId.trim();
    }

    /**
     * @return the value of IS_ERROR
     */
    public Integer getIsError() {
        return isError;
    }

    /**
    
     *
     * @param isError the value for IS_ERROR
     */
    public void setIsError(Integer isError) {
        this.isError = isError;
    }

    /**
     * @return the value of REPAIR_STATUS
     */
    public Integer getRepairStatus() {
        return repairStatus;
    }

    /**
    
     *
     * @param repairStatus the value for REPAIR_STATUS
     */
    public void setRepairStatus(Integer repairStatus) {
        this.repairStatus = repairStatus == null ? null : repairStatus;
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
        sb.append(", typeCode=").append(typeCode);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", regionId=").append(regionId);
        sb.append(", area=").append(area);
        sb.append(", soilType=").append(soilType);
        sb.append(", polluteType=").append(polluteType);
        sb.append(", ownerId=").append(ownerId);
        sb.append(", isError=").append(isError);
        sb.append(", repairStatus=").append(repairStatus);
        sb.append(", createId=").append(createId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateId=").append(updateId);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}