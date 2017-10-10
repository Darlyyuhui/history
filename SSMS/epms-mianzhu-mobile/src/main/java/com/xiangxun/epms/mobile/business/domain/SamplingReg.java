package com.xiangxun.epms.mobile.business.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SamplingReg {
	 private String id;

	    private String code;

	    private List<Files> file;
	    
	    private String blockName;
	    private String  regionName;
	    private String SampleName;
	    //其他类型的下拉选
	    private String othersamplyName;
	    //样品类型下拉选
	    private String typesamplyName;
	    public String getBlockName() {
			return blockName;
		}

		public void setBlockName(String blockName) {
			this.blockName = blockName;
		}

		public String getRegionName() {
			return regionName;
		}

		public void setRegionName(String regionName) {
			this.regionName = regionName;
		}

		public String getSampleName() {
			return SampleName;
		}

		public void setSampleName(String sampleName) {
			SampleName = sampleName;
		}

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
	   
	    
	    private String schemeId;
	    
	    private String tableName;
	    
	    private String samplingCode;
	    
	    /**
	     * 土壤采样不同属性 name农产品采样也有
	     */
	    private String depth;

	   // private String samplingType;
	    
	    private String name;
	    
	    /**
	     * 水采样不同属性  typeCode背景土壤采样采样也有
	     */
	   
	    private String typeCode;
	    
	    
	    private  String samplingType;
	    
	    private  String riversName;
	    
	    private BigDecimal mudLongitude;
	    
	    private BigDecimal mudLatitude;
	    
	    /**
	     * 农产品采样不同属性 name 与土壤采样都有
	     */
	    private String position;
	 // private String samplingType;
	    
	    /**
	     * 背景土壤采样不同属性  typeCode与水采样都有
	     */
	    
	    private String ambient;
	    
	    private String years;
	   // private String typeCode;
	    
	    private String wallSource;
	    
	    /**
	     * 肥料采样特有属性
	     */
	   private String shopName;
	   
	   private String shopkeeper;
	   
	   private String tel;
	   
	   private String dealManure;
	   
	   /**
	    * 大气采样特有属性
	    */
	   private String containerVolume;
	   
	   private String collectVolume;
	   private String pointId;
	   
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
	  

	    /**
	    
	     *
	     * @param soilType the value for SOIL_TYPE
	     */
	  

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
		public String getTypeCode() {
			return typeCode;
		}

		public void setTypeCode(String typeCode) {
			this.typeCode = typeCode;
		}

		public String getSamplingType() {
			return samplingType;
		}

		public void setSamplingType(String samplingType) {
			this.samplingType = samplingType;
		}

		public String getRiversName() {
			return riversName;
		}

		public void setRiversName(String riversName) {
			this.riversName = riversName;
		}

		public BigDecimal getMudLongitude() {
			return mudLongitude;
		}

		public void setMudLongitude(BigDecimal mudLongitude) {
			this.mudLongitude = mudLongitude;
		}

		public BigDecimal getMudLatitude() {
			return mudLatitude;
		}

		public void setMudLatitude(BigDecimal mudLatitude) {
			this.mudLatitude = mudLatitude;
		}

		public String getPosition() {
			return position;
		}

		public void setPosition(String position) {
			this.position = position;
		}

		public String getAmbient() {
			return ambient;
		}

		public void setAmbient(String ambient) {
			this.ambient = ambient;
		}

		public String getYears() {
			return years;
		}

		public void setYears(String years) {
			this.years = years;
		}

		public String getWallSource() {
			return wallSource;
		}

		public void setWallSource(String wallSource) {
			this.wallSource = wallSource;
		}

		public String getShopName() {
			return shopName;
		}

		public void setShopName(String shopName) {
			this.shopName = shopName;
		}

		public String getShopkeeper() {
			return shopkeeper;
		}

		public void setShopkeeper(String shopkeeper) {
			this.shopkeeper = shopkeeper;
		}

		public String getTel() {
			return tel;
		}

		public void setTel(String tel) {
			this.tel = tel;
		}

		public String getDealManure() {
			return dealManure;
		}

		public void setDealManure(String dealManure) {
			this.dealManure = dealManure;
		}

		public String getContainerVolume() {
			return containerVolume;
		}

		public void setContainerVolume(String containerVolume) {
			this.containerVolume = containerVolume;
		}

		public String getCollectVolume() {
			return collectVolume;
		}

		public void setCollectVolume(String collectVolume) {
			this.collectVolume = collectVolume;
		}
		public String getTableName() {
			return tableName;
		}

		public void setTableName(String tableName) {
			this.tableName = tableName;
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

		public List<Files> getFile() {
			return file;
		}

		public void setFile(List<Files> file) {
			this.file = file;
		}

		public String getSamplingCode() {
			return samplingCode;
		}

		public void setSamplingCode(String samplingCode) {
			this.samplingCode = samplingCode;
		}

		public String getOthersamplyName() {
			return othersamplyName;
		}

		public void setOthersamplyName(String othersamplyName) {
			this.othersamplyName = othersamplyName;
		}

		public String getTypesamplyName() {
			return typesamplyName;
		}

		public void setTypesamplyName(String typesamplyName) {
			this.typesamplyName = typesamplyName;
		}
}
