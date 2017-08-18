package com.xiangxun.atms.module.repair.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RepairProject implements Serializable {
	private static final long serialVersionUID = 6422411669774717225L;

	private String id;

    private String code;

    private String name;

    private Date beginTime;

    private Date endTime;

    private String technology;

    private String regionId;

    private BigDecimal area;

    private String missionTarget;

    private String effect;

    private String polluteType;

    private String explain;

    private String schedule;

    private String dept;

    private Date acceptionTime;

    private String createId;

    private Date createTime;

    private String updateId;

    private Date updateTime;
    
    
    private String blockIds;

	private String geoJson;
    public String getGeoJson() {
		return geoJson;
	}

	public void setGeoJson(String geoJson) {
		this.geoJson = geoJson;
	}

	private String blockNames;

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
     * @return the value of BEGIN_TIME
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
    
     *
     * @param beginTime the value for BEGIN_TIME
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * @return the value of END_TIME
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
    
     *
     * @param endTime the value for END_TIME
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the value of TECHNOLOGY
     */
    public String getTechnology() {
        return technology;
    }

    /**
    
     *
     * @param technology the value for TECHNOLOGY
     */
    public void setTechnology(String technology) {
        this.technology = technology == null ? null : technology.trim();
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

    public BigDecimal getArea() {
		return area;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
	}

	/**
     * @return the value of MISSION_TARGET
     */
    public String getMissionTarget() {
        return missionTarget;
    }

    /**
    
     *
     * @param missionTarget the value for MISSION_TARGET
     */
    public void setMissionTarget(String missionTarget) {
        this.missionTarget = missionTarget == null ? null : missionTarget.trim();
    }

    /**
     * @return the value of EFFECT
     */
    public String getEffect() {
        return effect;
    }

    /**
    
     *
     * @param effect the value for EFFECT
     */
    public void setEffect(String effect) {
        this.effect = effect == null ? null : effect.trim();
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
     * @return the value of EXPLAIN
     */
    public String getExplain() {
        return explain;
    }

    /**
    
     *
     * @param explain the value for EXPLAIN
     */
    public void setExplain(String explain) {
        this.explain = explain == null ? null : explain.trim();
    }

    /**
     * @return the value of SCHEDULE
     */
    public String getSchedule() {
        return schedule;
    }

    /**
    
     *
     * @param schedule the value for SCHEDULE
     */
    public void setSchedule(String schedule) {
        this.schedule = schedule == null ? null : schedule.trim();
    }

    /**
     * @return the value of DEPT
     */
    public String getDept() {
        return dept;
    }

    /**
    
     *
     * @param dept the value for DEPT
     */
    public void setDept(String dept) {
        this.dept = dept == null ? null : dept.trim();
    }

    /**
     * @return the value of ACCEPTION_TIME
     */
    public Date getAcceptionTime() {
        return acceptionTime;
    }

    /**
    
     *
     * @param acceptionTime the value for ACCEPTION_TIME
     */
    public void setAcceptionTime(Date acceptionTime) {
        this.acceptionTime = acceptionTime;
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

    public String getBlockIds() {
		return blockIds;
	}

	public void setBlockIds(String blockIds) {
		this.blockIds = blockIds;
	}

	public String getBlockNames() {
		return blockNames;
	}

	public void setBlockNames(String blockNames) {
		this.blockNames = blockNames;
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
        sb.append(", beginTime=").append(beginTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", technology=").append(technology);
        sb.append(", regionId=").append(regionId);
        sb.append(", area=").append(area);
        sb.append(", missionTarget=").append(missionTarget);
        sb.append(", effect=").append(effect);
        sb.append(", polluteType=").append(polluteType);
        sb.append(", explain=").append(explain);
        sb.append(", schedule=").append(schedule);
        sb.append(", dept=").append(dept);
        sb.append(", acceptionTime=").append(acceptionTime);
        sb.append(", createId=").append(createId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateId=").append(updateId);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}