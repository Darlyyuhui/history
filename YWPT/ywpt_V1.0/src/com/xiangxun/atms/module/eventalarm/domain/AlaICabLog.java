package com.xiangxun.atms.module.eventalarm.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.xiangxun.atms.module.property.domain.AssetInfo;

public class AlaICabLog implements Serializable {
    private String id;

    private String sn;

    private String ip;

    private Date eventTime;

    private Short sensorType;

    private Object message;

    private Object messageEx;

    private BigDecimal status;

    private BigDecimal actualvalue;

    private Short powersupply;

    private BigDecimal voltagevalue;

    private BigDecimal currentvalue;

    private String channelName;

    private static final long serialVersionUID = 1L;
    
    private AssetInfo assetinfo;

    public AssetInfo getAssetinfo() {
		return assetinfo;
	}

	public void setAssetinfo(AssetInfo assetinfo) {
		this.assetinfo = assetinfo;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public Short getSensorType() {
        return sensorType;
    }

    public void setSensorType(Short sensorType) {
        this.sensorType = sensorType;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Object getMessageEx() {
        return messageEx;
    }

    public void setMessageEx(Object messageEx) {
        this.messageEx = messageEx;
    }

    public BigDecimal getStatus() {
        return status;
    }

    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    public BigDecimal getActualvalue() {
        return actualvalue;
    }

    public void setActualvalue(BigDecimal actualvalue) {
        this.actualvalue = actualvalue;
    }

    public Short getPowersupply() {
        return powersupply;
    }

    public void setPowersupply(Short powersupply) {
        this.powersupply = powersupply;
    }

    public BigDecimal getVoltagevalue() {
        return voltagevalue;
    }

    public void setVoltagevalue(BigDecimal voltagevalue) {
        this.voltagevalue = voltagevalue;
    }

    public BigDecimal getCurrentvalue() {
        return currentvalue;
    }

    public void setCurrentvalue(BigDecimal currentvalue) {
        this.currentvalue = currentvalue;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }
}