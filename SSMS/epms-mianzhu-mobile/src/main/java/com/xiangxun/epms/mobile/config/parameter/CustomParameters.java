package com.xiangxun.epms.mobile.config.parameter;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * 读取自定义的配置文件
 * @author HaoXiang
 * 2017年3月1日
 */
@Configuration
public class CustomParameters extends PropertiesConfiguration implements java.io.Serializable {

	private static final long serialVersionUID = 4189051157217401374L;

	public CustomParameters() throws ConfigurationException {
		super("custom.properties");
	}

	/**
	 * web服务访问地址
	 */
	public String getWebUrl() {
		return this.getString("custom.webUrl");
	}

	/**
	 * session超时时间，单位：分钟
	 */
	public int getSessionTimeOut() {
		return this.getInt("custom.sessionTimeOut");
	}
	
	/**
	 * web服务存放附件的根目录（硬盘路径）
	 */
	public String getFileRootPath() {
		return this.getString("custom.fileRootPath");
	}
	
}
