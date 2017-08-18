package com.xiangxun.epms.mobile.business.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SamplingLandReg {

	    private String id;

	    private String code;

	    private String name;

	    private String depth;

	    private String soilType;

	    private BigDecimal longitude;

	    private BigDecimal latitude;

	   // private String regionId;

	    private Date samplingTime;

	    //private String testItem;

	    private String samplingUser;

	    private String receiveUser;

	    private String sendUser;

	    private String samplingSource;

	    private Integer checkStatus;

	    private String checkUser;

	    private Date checkTime;

	    private String createId;

	    private Date createTime;

	    private String updateId;

	    private Date updateTime;

	    private Integer status;

	    private String missionId;
	    
	    private String pointId;
	    
	    private String schemeId;
	    
	    private List<Files> file;
	    
	    private String  blockName;
	    
	    private String  regionName;
	    
	    private String sampleName;
	    
	    private String soilName;

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
	     * @return the value of DEPTH
	     */
	    public String getDepth() {
	        return depth;
	    }

	    /**
	    
	     *
	     * @param depth the value for DEPTH
	     */
	    public void setDepth(String depth) {
	        this.depth = depth == null ? null : depth.trim();
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
	        this.longitude = longitude ;
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
	        this.latitude = latitude ;
	    }

	    /**
	     * @return the value of SAMPLING_TIME
	     */
	    public Date getSamplingTime() {
	        return samplingTime;
	    }

	    /**
	    
	     *
	     * @param samplingTime the value for SAMPLING_TIME
	     */
	    public void setSamplingTime(Date samplingTime) {
	        this.samplingTime = samplingTime;
	    }

	    /**
	     * @return the value of SAMPLING_USER
	     */
	    public String getSamplingUser() {
	        return samplingUser;
	    }

	    /**
	    
	     *
	     * @param samplingUser the value for SAMPLING_USER
	     */
	    public void setSamplingUser(String samplingUser) {
	        this.samplingUser = samplingUser == null ? null : samplingUser.trim();
	    }

	    /**
	     * @return the value of RECEIVE_USER
	     */
	    public String getReceiveUser() {
	        return receiveUser;
	    }

	    /**
	    
	     *
	     * @param receiveUser the value for RECEIVE_USER
	     */
	    public void setReceiveUser(String receiveUser) {
	        this.receiveUser = receiveUser == null ? null : receiveUser.trim();
	    }

	    /**
	     * @return the value of SEND_USER
	     */
	    public String getSendUser() {
	        return sendUser;
	    }

	    /**
	    
	     *
	     * @param sendUser the value for SEND_USER
	     */
	    public void setSendUser(String sendUser) {
	        this.sendUser = sendUser == null ? null : sendUser.trim();
	    }

	    /**
	     * @return the value of SAMPLING_SOURCE
	     */
	    public String getSamplingSource() {
	        return samplingSource;
	    }

	    /**
	    
	     *
	     * @param samplingSource the value for SAMPLING_SOURCE
	     */
	    public void setSamplingSource(String samplingSource) {
	        this.samplingSource = samplingSource == null ? null : samplingSource.trim();
	    }

	    /**
	     * @return the value of CHECK_STATUS
	     */
	    public Integer getCheckStatus() {
	        return checkStatus;
	    }

	    /**
	    
	     *
	     * @param checkStatus the value for CHECK_STATUS
	     */
	    public void setCheckStatus(Integer checkStatus) {
	        this.checkStatus = checkStatus;
	    }

	    /**
	     * @return the value of CHECK_USER
	     */
	    public String getCheckUser() {
	        return checkUser;
	    }

	    /**
	    
	     *
	     * @param checkUser the value for CHECK_USER
	     */
	    public void setCheckUser(String checkUser) {
	        this.checkUser = checkUser == null ? null : checkUser.trim();
	    }

	    /**
	     * @return the value of CHECK_TIME
	     */
	    public Date getCheckTime() {
	        return checkTime;
	    }

	    /**
	    
	     *
	     * @param checkTime the value for CHECK_TIME
	     */
	    public void setCheckTime(Date checkTime) {
	        this.checkTime = checkTime;
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
	     * @return the value of MISSION_ID
	     */
	    public String getMissionId() {
	        return missionId;
	    }

	    /**
	    
	     *
	     * @param missionId the value for MISSION_ID
	     */
	    public void setMissionId(String missionId) {
	        this.missionId = missionId == null ? null : missionId.trim();
	    }

		public String getPointId() {
			return pointId;
		}

		public void setPointId(String pointId) {
			this.pointId = pointId==null?null:pointId.trim();
		}
		public String getSchemeId() {
			return schemeId;
		}

		public void setSchemeId(String schemeId) {
			this.schemeId = schemeId==null?null:schemeId.trim();
		}
		public List<Files> getFile() {
			return file;
		}

		public void setFile(List<Files> file) {
			this.file = file;
		}

		public String getBlockName() {
			return blockName;
		}

		public void setBlockName(String blockName) {
			this.blockName = blockName==null?"":blockName;
		}

		public String getRegionName() {
			return regionName;
		}

		public void setRegionName(String regionName) {
			this.regionName = regionName==null?"":regionName;
		}

		public String getSampleName() {
			return sampleName;
		}

		public void setSampleName(String sampleName) {
			this.sampleName = sampleName==null?"":sampleName;
		}
		public String getSoilName() {
			return soilName;
		}

		public void setSoilName(String soilName) {
			this.soilName = soilName;
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
	        sb.append(", depth=").append(depth);
	        sb.append(", soilType=").append(soilType);
	        sb.append(", longitude=").append(longitude);
	        sb.append(", latitude=").append(latitude);
	        sb.append(", samplingTime=").append(samplingTime);
	        sb.append(", samplingUser=").append(samplingUser);
	        sb.append(", receiveUser=").append(receiveUser);
	        sb.append(", sendUser=").append(sendUser);
	        sb.append(", samplingSource=").append(samplingSource);
	        sb.append(", checkStatus=").append(checkStatus);
	        sb.append(", checkUser=").append(checkUser);
	        sb.append(", checkTime=").append(checkTime);
	        sb.append(", createId=").append(createId);
	        sb.append(", createTime=").append(createTime);
	        sb.append(", updateId=").append(updateId);
	        sb.append(", updateTime=").append(updateTime);
	        sb.append(", status=").append(status);
	        sb.append(", missionId=").append(missionId);
	        sb.append("]");
	        return sb.toString();
	    }

	}
	


