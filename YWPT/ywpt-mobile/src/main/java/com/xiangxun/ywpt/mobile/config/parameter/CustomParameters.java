package com.xiangxun.ywpt.mobile.config.parameter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * 读取自定义的配置文件
 * @author HaoXiang
 * 2017年3月1日
 */
@Configuration
@PropertySource("classpath:custom.properties")
public class CustomParameters {

	/**
	 * web服务访问地址
	 */
	@Value("${custom.webUrl}")
	private String webUrl;
	
	/**
	 * session超时时间，单位：分钟
	 */
	@Value("${custom.sessionTimeOut}")
	private int sessionTimeOut = 20;
	
	/**
	 * web服务存放附件的根目录（硬盘路径）
	 */
	@Value("${custom.fileRootPath}")
	private String fileRootPath;

	@Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigure() {
        return new PropertySourcesPlaceholderConfigurer();
    }
	
	public String getWebUrl() {
		return webUrl;
	}
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public int getSessionTimeOut() {
		return sessionTimeOut;
	}

	public void setSessionTimeOut(int sessionTimeOut) {
		this.sessionTimeOut = sessionTimeOut;
	}

	public String getFileRootPath() {
		return fileRootPath;
	}

	public void setFileRootPath(String fileRootPath) {
		this.fileRootPath = fileRootPath;
	}
	
}
