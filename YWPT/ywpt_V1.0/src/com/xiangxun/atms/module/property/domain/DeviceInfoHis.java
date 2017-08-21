package com.xiangxun.atms.module.property.domain;

//@Document(collection="PROPERTY_DEVICE_INFO_HIS")
public class DeviceInfoHis extends DeviceInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -893825757528321732L;
	
//	@Indexed
    private String afterRecordId;

	public String getAfterRecordId() {
		return afterRecordId;
	}

	public void setAfterRecordId(String afterRecordId) {
		this.afterRecordId = afterRecordId;
	}

}
