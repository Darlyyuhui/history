package com.xiangxun.atms.module.property.domain;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiangxun.atms.framework.constant.FORMAT;


//@Document(collection="PROPERTY_ASSET_INFO")
public class AssetInfo implements Serializable {
//    @Id
    private String id;

//    @Indexed
    private String deviceid;

//    @Indexed
    private String assetcode;

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
    
    private String orgNames;
    
    private String orgIds;
    
    private String orgName;
    
    private String orgId;
   

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgNames() {
		return orgNames;
	}

	public void setOrgNames(String orgNames) {
		this.orgNames = orgNames;
	}

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

	//健康度值
    private double jkd;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid == null ? null : deviceid.trim();
    }

    public String getAssetcode() {
        return assetcode;
    }

    public void setAssetcode(String assetcode) {
        this.assetcode = assetcode == null ? null : assetcode.trim();
    }

    public String getAssetname() {
        return assetname;
    }

    public void setAssetname(String assetname) {
        this.assetname = assetname == null ? null : assetname.trim();
    }

    public String getAssetmodel() {
        return assetmodel;
    }

    public void setAssetmodel(String assetmodel) {
        this.assetmodel = assetmodel == null ? null : assetmodel.trim();
    }

    public String getAssettype() {
        return assettype;
    }

    public void setAssettype(String assettype) {
        this.assettype = assettype == null ? null : assettype.trim();
    }

    public String getAssetstatus() {
        return assetstatus;
    }

    public void setAssetstatus(String assetstatus) {
        this.assetstatus = assetstatus == null ? null : assetstatus.trim();
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
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
    }

    public String getEngineering() {
        return engineering;
    }

    public void setEngineering(String engineering) {
        this.engineering = engineering == null ? null : engineering.trim();
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
        this.installplace = installplace == null ? null : installplace.trim();
    }

    public String getServiceid() {
        return serviceid;
    }

    public void setServiceid(String serviceid) {
        this.serviceid = serviceid == null ? null : serviceid.trim();
    }

    public String getMacaddress() {
        return macaddress;
    }

    public void setMacaddress(String macaddress) {
        this.macaddress = macaddress == null ? null : macaddress.trim();
    }

    public String getCpuStatus() {
        return cpuStatus;
    }

    public void setCpuStatus(String cpuStatus) {
        this.cpuStatus = cpuStatus == null ? null : cpuStatus.trim();
    }

    public String getMemoryStatus() {
        return memoryStatus;
    }

    public void setMemoryStatus(String memoryStatus) {
        this.memoryStatus = memoryStatus == null ? null : memoryStatus.trim();
    }

    public String getDiskStatus() {
        return diskStatus;
    }

    public void setDiskStatus(String diskStatus) {
        this.diskStatus = diskStatus == null ? null : diskStatus.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getRefreshtime() {
        return refreshtime;
    }

    public void setRefreshtime(Date refreshtime) {
        this.refreshtime = refreshtime;
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

	public String getPowerStatus() {
		return powerStatus;
	}

	public void setPowerStatus(String powerStatus) {
		this.powerStatus = powerStatus == null ? null : powerStatus.trim();
	}

	public String getNetStatus() {
		return netStatus;
	}

	public void setNetStatus(String netStatus) {
		this.netStatus  = netStatus == null ? null : netStatus.trim();
	}

	public String getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(String dataStatus) {
		this.dataStatus =  dataStatus == null ? null : dataStatus.trim();
	}

	
	public int getCabinetStatus() {
		return cabinetStatus;
	}

	public void setCabinetStatus(int cabinetStatus) {
		this.cabinetStatus = cabinetStatus;
	}

	/**
	 * @return the factoryId
	 */
	public String getFactoryId() {
		return factoryId;
	}

	/**
	 * @param factoryId the factoryId to set
	 */
	public void setFactoryId(String factoryId) {
		this.factoryId = factoryId;
	}
    
	public String getPayoutstatus() {
	     return payoutstatus;
	}

	public void setPayoutstatus(String payoutstatus) {
	     this.payoutstatus = payoutstatus == null ? null : payoutstatus.trim();
	}

	public double getJkd() {
		return jkd;
	}

	public void setJkd(double jkd) {
		this.jkd = jkd;
	}


	
	
	
}