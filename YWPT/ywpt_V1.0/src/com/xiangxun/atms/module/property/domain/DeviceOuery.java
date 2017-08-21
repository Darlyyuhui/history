/**
 * 
 */
package com.xiangxun.atms.module.property.domain;

import java.io.Serializable;

import com.xiangxun.atms.framework.compnents.xls.imports.anotation.XlsMapping;
import com.xiangxun.atms.framework.compnents.xls.imports.anotation.XlsValidate;
import com.xiangxun.atms.framework.compnents.xls.imports.validate.ValidateBean.RowValidateType;

/**
 * 设备导入Xls信息
 * @author kouyunhao
 * @version 1.0
 * Apr 2, 2014
 */
public class DeviceOuery implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8138969250385001177L;
	/**
	 * 
	 */
	/**
	 * 设备编号
	 */
	@XlsMapping(cellNo = 0)
	@XlsValidate(type = { RowValidateType.MAXLENGTH, RowValidateType.UNIQUE}, maxLength = 18)
	private String device_code;
	
	/**
	 * 设备名称
	 */
	@XlsMapping(cellNo = 1)
	@XlsValidate(type = { RowValidateType.MAXLENGTH, RowValidateType.MATCH}, maxLength = 255)
	private String device_name;
	
	/**
	 * 设备IP
	 */
	@XlsMapping(cellNo = 2)
	@XlsValidate(type = { RowValidateType.MAXLENGTH, RowValidateType.UNIQUE}, maxLength = 20)
	private String device_ip;
	
	/**
	 * 所在道路
	 */
	@XlsMapping(cellNo = 3)
	@XlsValidate(type = { RowValidateType.MAXLENGTH, RowValidateType.MATCH}, maxLength = 255)
	private String device_road;
	
	/**
	 * 管理部门
	 */
	@XlsMapping(cellNo = 4)
	@XlsValidate(type = { RowValidateType.MAXLENGTH, RowValidateType.MATCH}, maxLength = 255)
	private String device_depatment;
	
	/**
	 * 建设厂商
	 */
	@XlsMapping(cellNo = 5)
	@XlsValidate(type = { RowValidateType.MAXLENGTH, RowValidateType.MATCH}, maxLength = 255, required = false)
	private String device_business;
	
	/**
	 * 服务厂商
	 */
	@XlsMapping(cellNo = 6)
	@XlsValidate(type = { RowValidateType.MAXLENGTH, RowValidateType.MATCH}, maxLength = 255, required = false)
	private String device_fuwucs;
	
	/**
	 * 设备功能
	 */
	@XlsMapping(cellNo = 7)
	@XlsValidate(type = { RowValidateType.MAXLENGTH,RowValidateType.MATCH}, maxLength = 255, required = false)
	private String device_gongneng;
	/**
	 * 资产状态
	 */
	@XlsMapping(cellNo = 8)
	@XlsValidate(type = { RowValidateType.MAXLENGTH,RowValidateType.MATCH}, maxLength = 10)
	private String asset_status;
	/**
	 * 保修时间
	 */
	@XlsMapping(cellNo = 9)
	@XlsValidate(type = { RowValidateType.MAXLENGTH}, maxLength = 28, required = false)
	private String asset_baoxiutime;
	/**
	 * 采购时间
	 */
	@XlsMapping(cellNo = 10)
	@XlsValidate(type = { RowValidateType.MAXLENGTH}, maxLength = 28, required = false)
	private String asset_caigoutime;
	/**
	 * 安装时间
	 */
	@XlsMapping(cellNo = 11)
	@XlsValidate(type = { RowValidateType.MAXLENGTH}, maxLength = 28, required = false)
	private String asset_anzhuangtime;

	public String getDevice_code() {
		return device_code;
	}

	public void setDevice_code(String device_code) {
		this.device_code = device_code;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public String getDevice_ip() {
		return device_ip;
	}

	public void setDevice_ip(String device_ip) {
		this.device_ip = device_ip;
	}

	public String getDevice_road() {
		return device_road;
	}

	public void setDevice_road(String device_road) {
		this.device_road = device_road;
	}

	public String getDevice_depatment() {
		return device_depatment;
	}

	public void setDevice_depatment(String device_depatment) {
		this.device_depatment = device_depatment;
	}

	public String getDevice_business() {
		return device_business;
	}

	public void setDevice_business(String device_business) {
		this.device_business = device_business;
	}

	public String getDevice_fuwucs() {
		return device_fuwucs;
	}

	public void setDevice_fuwucs(String device_fuwucs) {
		this.device_fuwucs = device_fuwucs;
	}

	public String getDevice_gongneng() {
		return device_gongneng;
	}

	public void setDevice_gongneng(String device_gongneng) {
		this.device_gongneng = device_gongneng;
	}

	public String getAsset_status() {
		return asset_status;
	}

	public void setAsset_status(String asset_status) {
		this.asset_status = asset_status;
	}

	public String getAsset_baoxiutime() {
		return asset_baoxiutime;
	}

	public void setAsset_baoxiutime(String asset_baoxiutime) {
		this.asset_baoxiutime = asset_baoxiutime;
	}

	public String getAsset_caigoutime() {
		return asset_caigoutime;
	}

	public void setAsset_caigoutime(String asset_caigoutime) {
		this.asset_caigoutime = asset_caigoutime;
	}

	public String getAsset_anzhuangtime() {
		return asset_anzhuangtime;
	}

	public void setAsset_anzhuangtime(String asset_anzhuangtime) {
		this.asset_anzhuangtime = asset_anzhuangtime;
	}
	
}
