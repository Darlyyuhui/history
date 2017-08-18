package com.xiangxun.ywpt.mobile.business.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiangxun.ywpt.mobile.business.util.FORMAT;


//@Document(collection="PROPERTY_ASSET_INFO")
public class AssetInfo implements Serializable {
//    @Id
    private String id;

//    @Indexed
    private String deviceid;

//    @Indexed
    private String assetcode;

//  @Indexed
  private String code;
    
//    @Indexed
    private String assetname;

//    @Indexed
    private String assetmodel;

//    @Indexed
    private String assettype;

//    @Indexed
    private String assetstatus;
    //服务商ID
//    @Indexed
    private String factoryId;

//    @Indexed
    @JsonFormat(pattern=FORMAT.DATETIME.DEFAULT)
    private Date guaranteetime;

//    @Indexed
    @JsonFormat(pattern=FORMAT.DATETIME.DEFAULT)
    private Date purchasetime;

//    @Indexed
    private String manufacturer;

//    @Indexed
    private String engineering;

//    @Indexed
    @JsonFormat(pattern=FORMAT.DATETIME.DEFAULT)
    private Date installtime;

//    @Indexed
    private String installplace;

//    @Indexed
    private String serviceid;

//    @Indexed
    private String macaddress;

//    @Indexed
    private String cpuStatus;

//    @Indexed
    private String memoryStatus;

//    @Indexed
    private String diskStatus;

//    @Indexed
    private String ip;

//    @Indexed
    @JsonFormat(pattern=FORMAT.DATETIME.DEFAULT)
    private Date refreshtime;
    
//    @Indexed
    private String powerStatus;

//    @Indexed
    private String netStatus;

//    @Indexed
    private String dataStatus;
    
    //机柜异常状态
    private int cabinetStatus;
    
    
//  @Indexed
    private String payoutstatus;
    
    private String toasset;
    
    private String guaranteetimestr;
    
    private String purchasetimestr;
    
    private String installtimestr;
    
    private String orgname;
    

    public String getOrgname() {
		return orgname;
	}


	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}


	private static final long serialVersionUID = 1L;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getDeviceid() {
		return deviceid;
	}


	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}


	public String getAssetcode() {
		return assetcode;
	}


	public void setAssetcode(String assetcode) {
		this.assetcode = assetcode;
	}


	public String getAssetname() {
		return assetname;
	}


	public void setAssetname(String assetname) {
		this.assetname = assetname;
	}


	public String getAssetmodel() {
		return assetmodel;
	}


	public void setAssetmodel(String assetmodel) {
		this.assetmodel = assetmodel;
	}


	public String getAssettype() {
		return assettype;
	}


	public void setAssettype(String assettype) {
		this.assettype = assettype;
	}


	public String getAssetstatus() {
		return assetstatus;
	}


	public void setAssetstatus(String assetstatus) {
		this.assetstatus = assetstatus;
	}


	public String getFactoryId() {
		return factoryId;
	}


	public void setFactoryId(String factoryId) {
		this.factoryId = factoryId;
	}


	public Date getGuaranteetime() {
		return guaranteetime;
	}


	public void setGuaranteetime(Date guaranteetime) {
		this.guaranteetime = guaranteetime;
	}


	public Date getPurchasetime() {
		return purchasetime;
	}


	public void setPurchasetime(Date purchasetime) {
		this.purchasetime = purchasetime;
	}


	public String getManufacturer() {
		return manufacturer;
	}


	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}


	public String getEngineering() {
		return engineering;
	}


	public void setEngineering(String engineering) {
		this.engineering = engineering;
	}


	public Date getInstalltime() {
		return installtime;
	}


	public void setInstalltime(Date installtime) {
		this.installtime = installtime;
	}


	public String getInstallplace() {
		return installplace;
	}


	public void setInstallplace(String installplace) {
		this.installplace = installplace;
	}


	public String getServiceid() {
		return serviceid;
	}


	public void setServiceid(String serviceid) {
		this.serviceid = serviceid;
	}


	public String getMacaddress() {
		return macaddress;
	}


	public void setMacaddress(String macaddress) {
		this.macaddress = macaddress;
	}


	public String getCpuStatus() {
		return cpuStatus;
	}


	public void setCpuStatus(String cpuStatus) {
		this.cpuStatus = cpuStatus;
	}


	public String getMemoryStatus() {
		return memoryStatus;
	}


	public void setMemoryStatus(String memoryStatus) {
		this.memoryStatus = memoryStatus;
	}


	public String getDiskStatus() {
		return diskStatus;
	}


	public void setDiskStatus(String diskStatus) {
		this.diskStatus = diskStatus;
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public Date getRefreshtime() {
		return refreshtime;
	}


	public void setRefreshtime(Date refreshtime) {
		this.refreshtime = refreshtime;
	}


	public String getPowerStatus() {
		return powerStatus;
	}


	public void setPowerStatus(String powerStatus) {
		this.powerStatus = powerStatus;
	}


	public String getNetStatus() {
		return netStatus;
	}


	public void setNetStatus(String netStatus) {
		this.netStatus = netStatus;
	}


	public String getDataStatus() {
		return dataStatus;
	}


	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}


	public int getCabinetStatus() {
		return cabinetStatus;
	}


	public void setCabinetStatus(int cabinetStatus) {
		this.cabinetStatus = cabinetStatus;
	}


	public String getPayoutstatus() {
		return payoutstatus;
	}


	public void setPayoutstatus(String payoutstatus) {
		this.payoutstatus = payoutstatus;
	}


	public String getToasset() {
		return toasset;
	}


	public void setToasset(String toasset) {
		this.toasset = toasset;
	}


	public String getGuaranteetimestr() {
		return guaranteetimestr;
	}


	public void setGuaranteetimestr(String guaranteetimestr) {
		this.guaranteetimestr = guaranteetimestr;
	}


	public String getPurchasetimestr() {
		return purchasetimestr;
	}


	public void setPurchasetimestr(String purchasetimestr) {
		this.purchasetimestr = purchasetimestr;
	}


	public String getInstalltimestr() {
		return installtimestr;
	}


	public void setInstalltimestr(String installtimestr) {
		this.installtimestr = installtimestr;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	
}