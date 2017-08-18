package com.xiangxun.epms.mobile.business.domain;

import java.math.BigDecimal;

public class Analysis extends AnalysisLand {
	private String id;

	private String regId;

	private String code;

	private String type_name;

	private String analy_name;

	private BigDecimal analy_value;

	private String sampleStatus;

	private Integer isOver;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getAnaly_name() {
		return analy_name;
	}

	public void setAnaly_name(String analy_name) {
		this.analy_name = analy_name;
	}

	public BigDecimal getAnaly_value() {
		return analy_value;
	}

	public void setAnaly_value(BigDecimal analy_value) {
		this.analy_value = analy_value;
	}

	public String getSampleStatus() {
		return sampleStatus;
	}

	public void setSampleStatus(String sampleStatus) {
		this.sampleStatus = sampleStatus;
	}

	public Integer getIsOver() {
		return isOver;
	}

	public void setIsOver(Integer isOver) {
		this.isOver = isOver;
	}

}