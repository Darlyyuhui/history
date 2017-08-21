package com.xiangxun.atms.module.property.domain;

import java.math.BigDecimal;
import java.util.Date;

//@Document(collection="PROPERTY_SERVER_INFO_HIS")
public class ServerInfoHis {
//    @Indexed
    private String id;

//    @Indexed
    private String name;

//    @Indexed
    private String code;

//    @Indexed
    private String serverip;

//    @Indexed
    private String model;

//    @Indexed
    private String type;

//    @Indexed
    private String cpuModel;

//    @Indexed
    private String cpuType;

//    @Indexed
    private BigDecimal cpuNum;

//    @Indexed
    private BigDecimal cpuCoreNum;

//    @Indexed
    private BigDecimal threeCache;

//    @Indexed
    private BigDecimal threadNum;

//    @Indexed
    private String ramType;

//    @Indexed
    private String ramVolume;

//    @Indexed
    private String diskPortType;

//    @Indexed
    private String diskVolume;

//    @Indexed
    private Date addTime;

//    @Indexed
    private String operator;

//    @Indexed
    private String note;

//    @Indexed
    private String orgId;

//    @Indexed
    private String factoryId;

//    @Indexed
    private String status;

//    @Indexed
    private String afterRecordId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getServerip() {
        return serverip;
    }

    public void setServerip(String serverip) {
        this.serverip = serverip == null ? null : serverip.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getCpuModel() {
        return cpuModel;
    }

    public void setCpuModel(String cpuModel) {
        this.cpuModel = cpuModel == null ? null : cpuModel.trim();
    }

    public String getCpuType() {
        return cpuType;
    }

    public void setCpuType(String cpuType) {
        this.cpuType = cpuType == null ? null : cpuType.trim();
    }

    public BigDecimal getCpuNum() {
        return cpuNum;
    }

    public void setCpuNum(BigDecimal cpuNum) {
        this.cpuNum = cpuNum;
    }

    public BigDecimal getCpuCoreNum() {
        return cpuCoreNum;
    }

    public void setCpuCoreNum(BigDecimal cpuCoreNum) {
        this.cpuCoreNum = cpuCoreNum;
    }

    public BigDecimal getThreeCache() {
        return threeCache;
    }

    public void setThreeCache(BigDecimal threeCache) {
        this.threeCache = threeCache;
    }

    public BigDecimal getThreadNum() {
        return threadNum;
    }

    public void setThreadNum(BigDecimal threadNum) {
        this.threadNum = threadNum;
    }

    public String getRamType() {
        return ramType;
    }

    public void setRamType(String ramType) {
        this.ramType = ramType == null ? null : ramType.trim();
    }

    public String getRamVolume() {
        return ramVolume;
    }

    public void setRamVolume(String ramVolume) {
        this.ramVolume = ramVolume == null ? null : ramVolume.trim();
    }

    public String getDiskPortType() {
        return diskPortType;
    }

    public void setDiskPortType(String diskPortType) {
        this.diskPortType = diskPortType == null ? null : diskPortType.trim();
    }

    public String getDiskVolume() {
        return diskVolume;
    }

    public void setDiskVolume(String diskVolume) {
        this.diskVolume = diskVolume == null ? null : diskVolume.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId == null ? null : factoryId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getAfterRecordId() {
        return afterRecordId;
    }

    public void setAfterRecordId(String afterRecordId) {
        this.afterRecordId = afterRecordId == null ? null : afterRecordId.trim();
    }
}