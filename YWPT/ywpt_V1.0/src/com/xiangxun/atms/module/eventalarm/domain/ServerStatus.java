package com.xiangxun.atms.module.eventalarm.domain;

import java.io.Serializable;
import java.util.Date;
//@Document(collection="STATUS_SERVER")
public class ServerStatus implements Serializable {
//    @Id
    private String id;

//    @Indexed
    private String ip;

 //   @Indexed
    private String cpuinfo;

//    @Indexed
    private String memoryinfo;

//    @Indexed
    private String computername;

//    @Indexed
    private String osname;

//    @Indexed
    private String osversion;

//    @Indexed
    private String ostype;

//    @Indexed
    private String cpunumber;

//    @Indexed
    private String biosversion;

//    @Indexed
    private String sysareaset;

//    @Indexed
    private String inputareaset;

//    @Indexed
    private String hymemoryall;

//    @Indexed
    private String hymemoryfree;

//    @Indexed
    private String osid;

//    @Indexed
    private String osreguser;

//    @Indexed
    private String osfactory;

//    @Indexed
    private String diskinfo;

//    @Indexed
    private String diskStatus;

//    @Indexed
    private String cpuStatus;

//    @Indexed
    private String memoryStatus;

//    @Indexed
    private Date inserttime;

//    @Indexed
    private String insertpc;

//    @Indexed
    private String assetid;

//    @Indexed
    private String assetname;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getCpuinfo() {
        return cpuinfo;
    }

    public void setCpuinfo(String cpuinfo) {
        this.cpuinfo = cpuinfo == null ? null : cpuinfo.trim();
    }

    public String getMemoryinfo() {
        return memoryinfo;
    }

    public void setMemoryinfo(String memoryinfo) {
        this.memoryinfo = memoryinfo == null ? null : memoryinfo.trim();
    }

    public String getComputername() {
        return computername;
    }

    public void setComputername(String computername) {
        this.computername = computername == null ? null : computername.trim();
    }

    public String getOsname() {
        return osname;
    }

    public void setOsname(String osname) {
        this.osname = osname == null ? null : osname.trim();
    }

    public String getOsversion() {
        return osversion;
    }

    public void setOsversion(String osversion) {
        this.osversion = osversion == null ? null : osversion.trim();
    }

    public String getOstype() {
        return ostype;
    }

    public void setOstype(String ostype) {
        this.ostype = ostype == null ? null : ostype.trim();
    }

    public String getCpunumber() {
        return cpunumber;
    }

    public void setCpunumber(String cpunumber) {
        this.cpunumber = cpunumber == null ? null : cpunumber.trim();
    }

    public String getBiosversion() {
        return biosversion;
    }

    public void setBiosversion(String biosversion) {
        this.biosversion = biosversion == null ? null : biosversion.trim();
    }

    public String getSysareaset() {
        return sysareaset;
    }

    public void setSysareaset(String sysareaset) {
        this.sysareaset = sysareaset == null ? null : sysareaset.trim();
    }

    public String getInputareaset() {
        return inputareaset;
    }

    public void setInputareaset(String inputareaset) {
        this.inputareaset = inputareaset == null ? null : inputareaset.trim();
    }

    public String getHymemoryall() {
        return hymemoryall;
    }

    public void setHymemoryall(String hymemoryall) {
        this.hymemoryall = hymemoryall == null ? null : hymemoryall.trim();
    }

    public String getHymemoryfree() {
        return hymemoryfree;
    }

    public void setHymemoryfree(String hymemoryfree) {
        this.hymemoryfree = hymemoryfree == null ? null : hymemoryfree.trim();
    }

    public String getOsid() {
        return osid;
    }

    public void setOsid(String osid) {
        this.osid = osid == null ? null : osid.trim();
    }

    public String getOsreguser() {
        return osreguser;
    }

    public void setOsreguser(String osreguser) {
        this.osreguser = osreguser == null ? null : osreguser.trim();
    }

    public String getOsfactory() {
        return osfactory;
    }

    public void setOsfactory(String osfactory) {
        this.osfactory = osfactory == null ? null : osfactory.trim();
    }

    public String getDiskinfo() {
        return diskinfo;
    }

    public void setDiskinfo(String diskinfo) {
        this.diskinfo = diskinfo == null ? null : diskinfo.trim();
    }

    public String getDiskStatus() {
        return diskStatus;
    }

    public void setDiskStatus(String diskStatus) {
        this.diskStatus = diskStatus == null ? null : diskStatus.trim();
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

    public Date getInserttime() {
        return inserttime;
    }

    public void setInserttime(Date inserttime) {
        this.inserttime = inserttime;
    }

    public String getInsertpc() {
        return insertpc;
    }

    public void setInsertpc(String insertpc) {
        this.insertpc = insertpc == null ? null : insertpc.trim();
    }

    public String getAssetid() {
        return assetid;
    }

    public void setAssetid(String assetid) {
        this.assetid = assetid == null ? null : assetid.trim();
    }

    public String getAssetname() {
        return assetname;
    }

    public void setAssetname(String assetname) {
        this.assetname = assetname == null ? null : assetname.trim();
    }
}