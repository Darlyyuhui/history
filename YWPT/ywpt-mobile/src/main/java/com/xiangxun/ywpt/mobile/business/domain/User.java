package com.xiangxun.ywpt.mobile.business.domain;

public class User {

	//用户实体类
	private String id;
	//账号
	private String account;
	//密码
	private String pwd;
	private String name;
	//状态:0 1
	private int disabled;
	//电话
	private String mobile;
	//说明
	private String memo;
	//ip
	private String ipRule;
	private String setVisitTimes;
	private String limitVisitTimes;
	private String orgId;
	private String orgName;

	
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDisabled() {
		return disabled;
	}
	public void setDisabled(int disabled) {
		this.disabled = disabled;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getIpRule() {
		return ipRule;
	}
	public void setIpRule(String ipRule) {
		this.ipRule = ipRule;
	}
	public String getSetVisitTimes() {
		return setVisitTimes;
	}
	public void setSetVisitTimes(String setVisitTimes) {
		this.setVisitTimes = setVisitTimes;
	}
	public String getLimitVisitTimes() {
		return limitVisitTimes;
	}
	public void setLimitVisitTimes(String limitVisitTimes) {
		this.limitVisitTimes = limitVisitTimes;
	}

}
