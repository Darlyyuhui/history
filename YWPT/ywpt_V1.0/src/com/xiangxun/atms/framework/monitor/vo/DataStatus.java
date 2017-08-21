package com.xiangxun.atms.framework.monitor.vo;

public class DataStatus {

	public Integer successInsertCount = 0;
	public Integer errorInsertCount = 0;
	public Integer successUploadCount = 0;
	public Integer errorUploadCount = 0;

	public Integer insertErrorNoRuleCount = 0;
	public Integer insertErrorRepeatCount = 0;
	public Integer insertErrorSpeedCount = 0;
	
	public Integer uploadErrorNoRuleCount = 0;
	public Integer uploadErrorRepeatCount = 0;
	public Integer uploadErrorTimeCount = 0;
	public Integer uploadErrorSpeedCount = 0;
	public Integer uploadErrorNoPlateCount = 0;
	public Integer uploadErrorPlateCount = 0;
	public Integer uploadErrorImageCount = 0;
}
