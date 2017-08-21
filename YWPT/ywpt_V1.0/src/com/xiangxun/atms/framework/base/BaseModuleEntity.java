package com.xiangxun.atms.framework.base;

/**
 * <p>业务数据基础分封装类</p>
 * @author zhouhaij
 * @since 1.0
 * @version
 */
public class BaseModuleEntity {

	/***
	 * 设备编码
	 */
	private String deviceCode;

	/**
	 * @return the deviceCode
	 */
	public String getDeviceCode() {
		return this.deviceCode;
	}

	/**
	 * @param deviceCode the deviceCode to set
	 */
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if (!(obj instanceof BaseModuleEntity)) return false;
		BaseModuleEntity entity = (BaseModuleEntity)obj;
		return entity.getDeviceCode().equals(this.deviceCode);
	}
	
}
