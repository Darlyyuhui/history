package com.xiangxun.atms.icabinet.isapi;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 智能机柜参数下发时的连接参数
 * 
 * @author Administrator
 *
 */
public class Configure {
	private final Logger logger = LoggerFactory.getLogger(Configure.class);
	private Properties prop = new Properties();

	public Configure() {
		try (InputStream in = Configure.class.getClassLoader().getResourceAsStream("icabinet-conn.properties")) {
			prop.load(in);
		} catch (IOException ex) {
			logger.error(ex.getMessage());
		}
	}

	/**
	 * Socket超时
	 * 
	 * @return
	 */
	public int getSocketTimeout() {		
		return Integer.parseInt(prop.getProperty("socket-timeout", "864000000"));
	}

	/**
	 * Connection超时
	 * 
	 * @return
	 */
	public int getConnectionTimeout() {
		return Integer.parseInt(prop.getProperty("connection-timeout", "3000"));
	}

	/**
	 * request超时
	 * 
	 * @return
	 */
	public int getRequestTimeout() {
		return Integer.parseInt(prop.getProperty("request-timeout", "864000000"));
	}

	/**
	 * 连接池最大连接数
	 * 
	 * @return
	 */
	public int getPoolMax() {
		return Integer.parseInt(prop.getProperty("connection-pool-max", "100"));
	}

	/**
	 * 同时连接到每台主机的最大连接数（过大可能压垮设备，过小效率低）
	 * 
	 * @return
	 */
	public int getPerRouteMax() {
		return Integer.parseInt(prop.getProperty("connection-perroute-max", "5"));
	}
	
	/**
	 * 接收服务器的端口
	 * @return
	 */
	public int getPort() {
		return Integer.parseInt(prop.getProperty("port", "6015"));
	}
}
