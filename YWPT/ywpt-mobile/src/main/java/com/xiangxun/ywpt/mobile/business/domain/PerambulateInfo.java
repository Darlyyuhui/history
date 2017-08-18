package com.xiangxun.ywpt.mobile.business.domain;

import java.io.Serializable;
import java.util.Date;

//巡检表实体类
public class PerambulateInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//id
    private String id;
    
    //设备id
    private String deviceid;
    
    //查询时间
    private Date inserttime;
    
    //用户id
    private String userid;
    
    //用户name
    private String name;
    
    //用户手机号
    private String mobile;
    
    //设备比编号
    private String code;
    
    //设备名称
    private String devicename;
    
    //设备ip
    private String ip;
    //设备ip
    private String deviceip;
    
    //设备类型
    private String assettype;
    
  //设备类型
    private String devicetype;
    
    
    //设备位置
    private String deviceplace;
    
    //安装时间
    private String installtime;
    
    //设备位置
    private String mapx;
    
    //安装时间
    private String mapy;
    //安装时间
    private String position;
    
  //安装时间
    private String devicecode;
    
    
    
    
    public String getDevicecode() {
		return devicecode;
	}

	public void setDevicecode(String devicecode) {
		this.devicecode = devicecode;
	}

	public String getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDeviceip() {
		return deviceip;
	}

	public void setDeviceip(String deviceip) {
		this.deviceip = deviceip;
	}

	public String getMapx() {
		return mapx;
	}

	public void setMapx(String mapx) {
		this.mapx = mapx;
	}

	public String getMapy() {
		return mapy;
	}

	public void setMapy(String mapy) {
		this.mapy = mapy;
	}

	public String getDeviceplace() {
		return deviceplace;
	}

	public void setDeviceplace(String deviceplace) {
		this.deviceplace = deviceplace;
	}

	public String getInstalltime() {
		return installtime;
	}

	public void setInstalltime(String installtime) {
		this.installtime = installtime;
	}

	public String getAssettype() {
		return assettype;
	}

	public void setAssettype(String assettype) {
		this.assettype = assettype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDevicename() {
		return devicename;
	}

	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getInserttime() {
		return inserttime;
	}

	public void setInserttime(Date inserttime) {
		this.inserttime = inserttime;
	}

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



	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public byte[] getPicture1() {
		return picture1;
	}

	public void setPicture1(byte[] picture1) {
		this.picture1 = picture1;
	}

	public byte[] getPicture2() {
		return picture2;
	}

	public void setPicture2(byte[] picture2) {
		this.picture2 = picture2;
	}

	public byte[] getPicture3() {
		return picture3;
	}

	public void setPicture3(byte[] picture3) {
		this.picture3 = picture3;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	//原因
    private String reason;
    
    //图片1
    private byte[] picture1;
    
    //图片2
    private byte[] picture2;

	//图片3
    private byte[] picture3;
    
    //备注
    private String note;
    
    
}
