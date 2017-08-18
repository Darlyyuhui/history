package com.xiangxun.atms.module.land.vo;

import java.util.Date;

public class LandSamplingScheme {
    private String id;

    private String planId;

    private String blockId;

    private String sampleCode;

    private String code;

    private String name;

    private String describe;

    private String regionId;

    private String dept;

    private String createId;

    private Date createTime;

    private String updateId;

    private Date updateTime;

    private Integer status;
    
    private String testItems;
    
    private String planCode;
    private String planName;
    private String blockCode;
    private String blockName;

    public String getPlanCode() {
		return planCode;
	}

	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getBlockCode() {
		return blockCode;
	}

	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

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
     * @return the value of PLAN_ID
     */
    public String getPlanId() {
        return planId;
    }

    /**
    
     *
     * @param planId the value for PLAN_ID
     */
    public void setPlanId(String planId) {
        this.planId = planId == null ? null : planId.trim();
    }

    /**
     * @return the value of BLOCK_ID
     */
    public String getBlockId() {
        return blockId;
    }

    /**
    
     *
     * @param blockId the value for BLOCK_ID
     */
    public void setBlockId(String blockId) {
        this.blockId = blockId == null ? null : blockId.trim();
    }

    /**
     * @return the value of SAMPLE_CODE
     */
    public String getSampleCode() {
        return sampleCode;
    }

    /**
    
     *
     * @param sampleCode the value for SAMPLE_CODE
     */
    public void setSampleCode(String sampleCode) {
        this.sampleCode = sampleCode == null ? null : sampleCode.trim();
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

    public String getTestItems() {
		return testItems;
	}

	public void setTestItems(String testItems) {
		this.testItems = testItems;
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
        sb.append(", planId=").append(planId);
        sb.append(", blockId=").append(blockId);
        sb.append(", sampleCode=").append(sampleCode);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", describe=").append(describe);
        sb.append(", regionId=").append(regionId);
        sb.append(", dept=").append(dept);
        sb.append(", createId=").append(createId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateId=").append(updateId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}