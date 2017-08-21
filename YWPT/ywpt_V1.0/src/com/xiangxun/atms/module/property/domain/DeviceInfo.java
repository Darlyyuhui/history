package com.xiangxun.atms.module.property.domain;


import com.xiangxun.atms.module.property.base.DeviceInfoBase;

public class DeviceInfo extends DeviceInfoBase {
   
    //自定义属性
    
    private String buildtimeStr;
    
    private String 	deviceTypeIds;
    
    private String codeEnd;
    
    private String lastdatatimeStr;
    
    //实时状态名字
    private String deviceStatName;
    //实时状态标志
    private int deviceStatFlag;
    //自定义分组时用
    private String customGroupFlag; 
    //方向配置 属性借用
    private String directionCode_01;
    private String directionCode_02;
    private String directionCode_03;
    private String directionCode_04;
    
    private String landtotal_01;
    private String landtotal_02;
    private String landtotal_03;
    private String landtotal_04;
    
    private String carlimitspeed_01;
    private String carlimitspeed_02;
    private String carlimitspeed_03;
    private String carlimitspeed_04;
    
    
    private String carlimitspeedcatch_01;
    private String carlimitspeedcatch_02;
    private String carlimitspeedcatch_03;
    private String carlimitspeedcatch_04;
    
    
    private String bigcarlimitspeed_01;
    private String bigcarlimitspeed_02;
    private String bigcarlimitspeed_03;
    private String bigcarlimitspeed_04;
    
    
    private String bigcarlimitspeedcatch_01;
    private String bigcarlimitspeedcatch_02;
    private String bigcarlimitspeedcatch_03;
    private String bigcarlimitspeedcatch_04;
    
    private String license1;
    private String license2;
    private String license3;
    
    private String companyName;
    private String orgNames;
    private String roadName;
    private String roadRvalue;
    private String deviceTypeNames;
    private String deviceCompanyName;
    
    private String efficaciousStimeStr;
    private String efficaciousEtimeStr;
    private String inspecttimeStr;
    private String devDirectStr;
    private String limitRoadDevData;
    
    /**附加属性（视频信息）*/
    private String directionCode;
    
    private String videotypeCode;
    
    private String ips;

    private String port;

    private String username;

    private String password;

    private String usetemplate;
    
    private String realvideoaddressid;

    private String hisvideoaddressid;
    //是否变更
    private boolean hasModified;
    
    private static final long serialVersionUID = 1L;
    
    //是否分配
    private boolean hasAllot;

    

	public String getBuildtimeStr() {
		return buildtimeStr;
	}

	public void setBuildtimeStr(String buildtimeStr) {
		this.buildtimeStr = buildtimeStr;
	}

	public String getDeviceTypeIds() {
		return deviceTypeIds;
	}

	public void setDeviceTypeIds(String deviceTypeIds) {
		this.deviceTypeIds = deviceTypeIds;
	}

	public String getCodeEnd() {
		return codeEnd;
	}

	public void setCodeEnd(String codeEnd) {
		this.codeEnd = codeEnd;
	}

	public String getLastdatatimeStr() {
		return lastdatatimeStr;
	}

	public void setLastdatatimeStr(String lastdatatimeStr) {
		this.lastdatatimeStr = lastdatatimeStr;
	}

	public String getDeviceStatName() {
		return deviceStatName;
	}

	public void setDeviceStatName(String deviceStatName) {
		this.deviceStatName = deviceStatName;
	}

	public int getDeviceStatFlag() {
		return deviceStatFlag;
	}

	public void setDeviceStatFlag(int deviceStatFlag) {
		this.deviceStatFlag = deviceStatFlag;
	}

	public String getDirectionCode_01() {
		return directionCode_01;
	}

	public void setDirectionCode_01(String directionCode_01) {
		this.directionCode_01 = directionCode_01;
	}

	public String getDirectionCode_02() {
		return directionCode_02;
	}

	public void setDirectionCode_02(String directionCode_02) {
		this.directionCode_02 = directionCode_02;
	}

	public String getDirectionCode_03() {
		return directionCode_03;
	}

	public void setDirectionCode_03(String directionCode_03) {
		this.directionCode_03 = directionCode_03;
	}

	public String getDirectionCode_04() {
		return directionCode_04;
	}

	public void setDirectionCode_04(String directionCode_04) {
		this.directionCode_04 = directionCode_04;
	}

	public String getLandtotal_01() {
		return landtotal_01;
	}

	public void setLandtotal_01(String landtotal_01) {
		this.landtotal_01 = landtotal_01;
	}

	public String getLandtotal_02() {
		return landtotal_02;
	}

	public void setLandtotal_02(String landtotal_02) {
		this.landtotal_02 = landtotal_02;
	}

	public String getLandtotal_03() {
		return landtotal_03;
	}

	public void setLandtotal_03(String landtotal_03) {
		this.landtotal_03 = landtotal_03;
	}

	public String getLandtotal_04() {
		return landtotal_04;
	}

	public void setLandtotal_04(String landtotal_04) {
		this.landtotal_04 = landtotal_04;
	}

	public String getCarlimitspeed_01() {
		return carlimitspeed_01;
	}

	public void setCarlimitspeed_01(String carlimitspeed_01) {
		this.carlimitspeed_01 = carlimitspeed_01;
	}

	public String getCarlimitspeed_02() {
		return carlimitspeed_02;
	}

	public void setCarlimitspeed_02(String carlimitspeed_02) {
		this.carlimitspeed_02 = carlimitspeed_02;
	}

	public String getCarlimitspeed_03() {
		return carlimitspeed_03;
	}

	public void setCarlimitspeed_03(String carlimitspeed_03) {
		this.carlimitspeed_03 = carlimitspeed_03;
	}

	public String getCarlimitspeed_04() {
		return carlimitspeed_04;
	}

	public void setCarlimitspeed_04(String carlimitspeed_04) {
		this.carlimitspeed_04 = carlimitspeed_04;
	}

	public String getCarlimitspeedcatch_01() {
		return carlimitspeedcatch_01;
	}

	public void setCarlimitspeedcatch_01(String carlimitspeedcatch_01) {
		this.carlimitspeedcatch_01 = carlimitspeedcatch_01;
	}

	public String getCarlimitspeedcatch_02() {
		return carlimitspeedcatch_02;
	}

	public void setCarlimitspeedcatch_02(String carlimitspeedcatch_02) {
		this.carlimitspeedcatch_02 = carlimitspeedcatch_02;
	}

	public String getCarlimitspeedcatch_03() {
		return carlimitspeedcatch_03;
	}

	public void setCarlimitspeedcatch_03(String carlimitspeedcatch_03) {
		this.carlimitspeedcatch_03 = carlimitspeedcatch_03;
	}

	public String getCarlimitspeedcatch_04() {
		return carlimitspeedcatch_04;
	}

	public void setCarlimitspeedcatch_04(String carlimitspeedcatch_04) {
		this.carlimitspeedcatch_04 = carlimitspeedcatch_04;
	}

	public String getBigcarlimitspeed_01() {
		return bigcarlimitspeed_01;
	}

	public void setBigcarlimitspeed_01(String bigcarlimitspeed_01) {
		this.bigcarlimitspeed_01 = bigcarlimitspeed_01;
	}

	public String getBigcarlimitspeed_02() {
		return bigcarlimitspeed_02;
	}

	public void setBigcarlimitspeed_02(String bigcarlimitspeed_02) {
		this.bigcarlimitspeed_02 = bigcarlimitspeed_02;
	}

	public String getBigcarlimitspeed_03() {
		return bigcarlimitspeed_03;
	}

	public void setBigcarlimitspeed_03(String bigcarlimitspeed_03) {
		this.bigcarlimitspeed_03 = bigcarlimitspeed_03;
	}

	public String getBigcarlimitspeed_04() {
		return bigcarlimitspeed_04;
	}

	public void setBigcarlimitspeed_04(String bigcarlimitspeed_04) {
		this.bigcarlimitspeed_04 = bigcarlimitspeed_04;
	}

	public String getBigcarlimitspeedcatch_01() {
		return bigcarlimitspeedcatch_01;
	}

	public void setBigcarlimitspeedcatch_01(String bigcarlimitspeedcatch_01) {
		this.bigcarlimitspeedcatch_01 = bigcarlimitspeedcatch_01;
	}

	public String getBigcarlimitspeedcatch_02() {
		return bigcarlimitspeedcatch_02;
	}

	public void setBigcarlimitspeedcatch_02(String bigcarlimitspeedcatch_02) {
		this.bigcarlimitspeedcatch_02 = bigcarlimitspeedcatch_02;
	}

	public String getBigcarlimitspeedcatch_03() {
		return bigcarlimitspeedcatch_03;
	}

	public void setBigcarlimitspeedcatch_03(String bigcarlimitspeedcatch_03) {
		this.bigcarlimitspeedcatch_03 = bigcarlimitspeedcatch_03;
	}

	public String getBigcarlimitspeedcatch_04() {
		return bigcarlimitspeedcatch_04;
	}

	public void setBigcarlimitspeedcatch_04(String bigcarlimitspeedcatch_04) {
		this.bigcarlimitspeedcatch_04 = bigcarlimitspeedcatch_04;
	}

	public String getLicense1() {
		return license1;
	}

	public void setLicense1(String license1) {
		this.license1 = license1;
	}

	public String getLicense2() {
		return license2;
	}

	public void setLicense2(String license2) {
		this.license2 = license2;
	}

	public String getLicense3() {
		return license3;
	}

	public void setLicense3(String license3) {
		this.license3 = license3;
	}

	public String getOrgNames() {
		return orgNames;
	}

	public void setOrgNames(String orgNames) {
		this.orgNames = orgNames;
	}

	public String getRoadName() {
		return roadName;
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	public String getDeviceTypeNames() {
		return deviceTypeNames;
	}

	public void setDeviceTypeNames(String deviceTypeNames) {
		this.deviceTypeNames = deviceTypeNames;
	}

	public String getEfficaciousStimeStr() {
		return efficaciousStimeStr;
	}

	public void setEfficaciousStimeStr(String efficaciousStimeStr) {
		this.efficaciousStimeStr = efficaciousStimeStr;
	}

	public String getEfficaciousEtimeStr() {
		return efficaciousEtimeStr;
	}

	public void setEfficaciousEtimeStr(String efficaciousEtimeStr) {
		this.efficaciousEtimeStr = efficaciousEtimeStr;
	}

	public String getInspecttimeStr() {
		return inspecttimeStr;
	}

	public void setInspecttimeStr(String inspecttimeStr) {
		this.inspecttimeStr = inspecttimeStr;
	}

	public String getDevDirectStr() {
		return devDirectStr;
	}

	public void setDevDirectStr(String devDirectStr) {
		this.devDirectStr = devDirectStr;
	}

	public String getLimitRoadDevData() {
		return limitRoadDevData;
	}

	public void setLimitRoadDevData(String limitRoadDevData) {
		this.limitRoadDevData = limitRoadDevData;
	}

	public String getDirectionCode() {
		return directionCode;
	}

	public void setDirectionCode(String directionCode) {
		this.directionCode = directionCode;
	}

	public String getVideotypeCode() {
		return videotypeCode;
	}

	public void setVideotypeCode(String videotypeCode) {
		this.videotypeCode = videotypeCode;
	}

	public String getIps() {
		return ips;
	}

	public void setIps(String ips) {
		this.ips = ips;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsetemplate() {
		return usetemplate;
	}

	public void setUsetemplate(String usetemplate) {
		this.usetemplate = usetemplate;
	}

	public String getRealvideoaddressid() {
		return realvideoaddressid;
	}

	public void setRealvideoaddressid(String realvideoaddressid) {
		this.realvideoaddressid = realvideoaddressid;
	}

	public String getHisvideoaddressid() {
		return hisvideoaddressid;
	}

	public void setHisvideoaddressid(String hisvideoaddressid) {
		this.hisvideoaddressid = hisvideoaddressid;
	}

	/**
	 * @return the roadRvalue
	 */
	public String getRoadRvalue() {
		return roadRvalue;
	}

	/**
	 * @param roadRvalue the roadRvalue to set
	 */
	public void setRoadRvalue(String roadRvalue) {
		this.roadRvalue = roadRvalue;
	}

	public String getCustomGroupFlag() {
		return customGroupFlag;
	}

	public void setCustomGroupFlag(String customGroupFlag) {
		this.customGroupFlag = customGroupFlag;
	}

	/**
	 * @return the deviceCompanyName
	 */
	public String getDeviceCompanyName() {
		return deviceCompanyName;
	}

	/**
	 * @param deviceCompanyName the deviceCompanyName to set
	 */
	public void setDeviceCompanyName(String deviceCompanyName) {
		this.deviceCompanyName = deviceCompanyName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public boolean isHasModified() {
		return hasModified;
	}

	public void setHasModified(boolean hasModified) {
		this.hasModified = hasModified;
	}

	/**
	 * @return the hasAllot
	 */
	public boolean isHasAllot() {
		return hasAllot;
	}

	/**
	 * @param hasAllot the hasAllot to set
	 */
	public void setHasAllot(boolean hasAllot) {
		this.hasAllot = hasAllot;
	}


    
}