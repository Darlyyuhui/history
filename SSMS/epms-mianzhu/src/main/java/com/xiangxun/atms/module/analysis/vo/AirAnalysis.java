package com.xiangxun.atms.module.analysis.vo;

import java.math.BigDecimal;
import java.util.Date;

public class AirAnalysis implements java.io.Serializable {
	private static final long serialVersionUID = -5557549766786868213L;

	private String id;

    private String regId;

    private String code;

    private BigDecimal cadmium;

    private Integer isOver;

    private String polluteFlux;

    private String remark;

    private String createId;

    private Date createTime;

    private String updateId;

    private Date updateTime;
    
    private int status;
    
    private String dept;
    /**
     * 查询返回参数
     */
    //样品日期
    private Date samplingTime;
    //采样点位
    private String pointId;
    //样品体积
    private String collectVolume;
    //所属乡镇
    private String regionId;
    /**
     * 导出用属性
     */
    private String samplingTimeStr;
    private String isOverStr;

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
     * @return the value of POLLUTE_FLUX
     */
    public String getPolluteFlux() {
        return polluteFlux;
    }

    /**
    
     *
     * @param polluteFlux the value for POLLUTE_FLUX
     */
    public void setPolluteFlux(String polluteFlux) {
        this.polluteFlux = polluteFlux == null ? null : polluteFlux.trim();
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


	public Date getSamplingTime() {
		return samplingTime;
	}

	public void setSamplingTime(Date samplingTime) {
		this.samplingTime = samplingTime;
	}

	public String getPointId() {
		return pointId;
	}

	public void setPointId(String pointId) {
		this.pointId = pointId;
	}

	public String getCollectVolume() {
		return collectVolume;
	}

	public void setCollectVolume(String collectVolume) {
		this.collectVolume = collectVolume;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getSamplingTimeStr() {
		return samplingTimeStr;
	}

	public void setSamplingTimeStr(String samplingTimeStr) {
		this.samplingTimeStr = samplingTimeStr;
	}

	public String getIsOverStr() {
		return isOverStr;
	}

	public void setIsOverStr(String isOverStr) {
		this.isOverStr = isOverStr;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
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
        sb.append(", cadmium=").append(cadmium);
        sb.append(", isOver=").append(isOver);
        sb.append(", polluteFlux=").append(polluteFlux);
        sb.append(", remark=").append(remark);
        sb.append(", createId=").append(createId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateId=").append(updateId);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}