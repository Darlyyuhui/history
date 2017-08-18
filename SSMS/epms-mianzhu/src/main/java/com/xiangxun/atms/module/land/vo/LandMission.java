package com.xiangxun.atms.module.land.vo;

import java.util.Date;

public class LandMission {
    private String id;

    private String code;

    private String dept;

    private Integer missionStatus;

    private String navigation;

    private String createId;

    private Date createTime;

    private String updateId;

    private Date updateTime;

    private Integer status;

    private String schemeId;

    private String name;
    
    //区域ID
    private String regionId;
    private String regionName;
    //检测项目
    private String testItems;
    //方案编号
    private String sCode;
    //方案名称
    private String sName;

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
     * @return the value of MISSION_STATUS
     */
    public Integer getMissionStatus() {
        return missionStatus;
    }

    /**
    
     *
     * @param missionStatus the value for MISSION_STATUS
     */
    public void setMissionStatus(Integer missionStatus) {
        this.missionStatus = missionStatus;
    }

    /**
     * @return the value of NAVIGATION
     */
    public String getNavigation() {
        return navigation;
    }

    /**
    
     *
     * @param navigation the value for NAVIGATION
     */
    public void setNavigation(String navigation) {
        this.navigation = navigation == null ? null : navigation.trim();
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
     * @return the value of STATUS
     */
    public Integer getStatus() {
        return status;
    }

    /**
    
     *
     * @param status the value for STATUS
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the value of SCHEME_ID
     */
    public String getSchemeId() {
        return schemeId;
    }

    /**
    
     *
     * @param schemeId the value for SCHEME_ID
     */
    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId == null ? null : schemeId.trim();
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

    public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getTestItems() {
		return testItems;
	}

	public void setTestItems(String testItems) {
		this.testItems = testItems;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getRegionName() {
		return regionName;
	}

	public String getsCode() {
		return sCode;
	}

	public void setsCode(String sCode) {
		this.sCode = sCode;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
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
        sb.append(", dept=").append(dept);
        sb.append(", missionStatus=").append(missionStatus);
        sb.append(", navigation=").append(navigation);
        sb.append(", createId=").append(createId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateId=").append(updateId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", status=").append(status);
        sb.append(", schemeId=").append(schemeId);
        sb.append(", name=").append(name);
        sb.append("]");
        return sb.toString();
    }
}