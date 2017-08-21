package com.xiangxun.atms.icabinet.tools;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ICabinetOption {
	private static Logger logger = LoggerFactory.getLogger(ICabinetOption.class);

	private String ip; // 机柜的ip
	private String port = "80";// 机柜的访问端口号（ISAPI，http)，使用海康默认值：80
	private String name;// 机柜的名称
	private String no; // 机柜的编号
	private String userName = "admin";// 机柜登录的用户名 ，使用海康默认值：admin
	private String password = "12345";// 机柜登录的密码 ，使用海康默认值：12345

	private String alarmHostIP; // 设置的上报主机IP
	private String alarmHostPort = "7200"; // 设置的上报主机端口号 ，使用海康默认值：7200

	private String samplingTime = "10"; // 采样间隔(秒)
	private String acSocketCurrentMin = "0", acSocketCurrentMax = "1000", acSocketVoltageMin = "200", acSocketVoltageMax = "240";// AC插座：电流最小最大值，电压最小最大值

	private String temperatureMin = "1", temperatureMax = "100", temperatureRegionMin = "10", temperatureRegionMax = "60";// 温度：量程（最小，最大）， 正常范围（最小，最大）
	private String humidityMin = "1", humidityMax = "100", humidityRegionMin = "10", humidityRegionMax = "60";// 湿度：量程（最小，最大）， 正常范围（最小，最大）

	/**
	 * 构造函数
	 * 
	 * @param ip 机柜的IP
	 * @param no 机柜的编号
	 * @param name 机柜的名称
	 * @param alarmHostIP 上传的报警主机的IP
	 */
	public ICabinetOption(String ip, String no, String name, String alarmHostIP) {
		this.ip = ip;
		this.no = no;
		this.name = name;

		this.alarmHostIP = StringUtils.isEmpty(alarmHostIP) ? getLocalHostAddress() : alarmHostIP;
	}	

	/**
	 * 取本地IP（默认取了一个）
	 * 
	 * @return
	 */
	private String getLocalHostAddress() {
		String ip0 = null;
		String ip1 = null, ip2 = null, ip3 = null;
		String[] ipSecs = ip.split("\\.");
		try {
			Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
			while (en.hasMoreElements()) {
				NetworkInterface networkinterface = en.nextElement();
				Enumeration<InetAddress> addresses = networkinterface.getInetAddresses();
				while (addresses.hasMoreElements()) {
					InetAddress addr = addresses.nextElement();
					if (!addr.isLoopbackAddress() && !addr.isLinkLocalAddress() /*&& addr.isSiteLocalAddress()*/) {
						String localIP = addr.getHostAddress();						
						if (ip0 == null)
							ip0 = localIP;
						String[] ts = localIP.split("\\.");						
						if (StringUtils.equals(ts[0], ipSecs[0])) {
							ip1 = localIP;
							if (StringUtils.equals(ts[1], ipSecs[1])) {
								ip2 = localIP;
								if (StringUtils.equals(ts[2], ipSecs[2]))
									ip3 = localIP;
							}
						}
					}
				}
			}
		} catch (SocketException e) {
			logger.error(e.getMessage());
		}		
		return StringUtils.isNotEmpty(ip3) ? ip3 : (StringUtils.isNotEmpty(ip2) ? ip2 : (StringUtils.isNotEmpty(ip1) ? ip1 : ip0));
	}

	public String getPort() {
		return port;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getAlarmHostIP() {
		return alarmHostIP;
	}

	public String getAlarmHostPort() {
		return alarmHostPort;
	}

	public String getSamplingTime() {
		return samplingTime;
	}

	public void setSamplingTime(String samplingTime) {
		this.samplingTime = samplingTime;
	}

	public String getAcSocketCurrentMin() {
		return acSocketCurrentMin;
	}

	public void setAcSocketCurrentMin(String acSocketCurrentMin) {
		this.acSocketCurrentMin = acSocketCurrentMin;
	}

	public String getAcSocketCurrentMax() {
		return acSocketCurrentMax;
	}

	public void setAcSocketCurrentMax(String acSocketCurrentMax) {
		this.acSocketCurrentMax = acSocketCurrentMax;
	}

	public String getAcSocketVoltageMin() {
		return acSocketVoltageMin;
	}

	public void setAcSocketVoltageMin(String acSocketVoltageMin) {
		this.acSocketVoltageMin = acSocketVoltageMin;
	}

	public String getAcSocketVoltageMax() {
		return acSocketVoltageMax;
	}

	public void setAcSocketVoltageMax(String acSocketVoltageMax) {
		this.acSocketVoltageMax = acSocketVoltageMax;
	}

	public String getTemperatureMin() {
		return temperatureMin;
	}

	public void setTemperatureMin(String temperatureMin) {
		this.temperatureMin = temperatureMin;
	}

	public String getTemperatureMax() {
		return temperatureMax;
	}

	public void setTemperatureMax(String temperatureMax) {
		this.temperatureMax = temperatureMax;
	}

	public String getTemperatureRegionMin() {
		return temperatureRegionMin;
	}

	public void setTemperatureRegionMin(String temperatureRegionMin) {
		this.temperatureRegionMin = temperatureRegionMin;
	}

	public String getTemperatureRegionMax() {
		return temperatureRegionMax;
	}

	public void setTemperatureRegionMax(String temperatureRegionMax) {
		this.temperatureRegionMax = temperatureRegionMax;
	}

	public String getHumidityMin() {
		return humidityMin;
	}

	public void setHumidityMin(String humidityMin) {
		this.humidityMin = humidityMin;
	}

	public String getHumidityMax() {
		return humidityMax;
	}

	public void setHumidityMax(String humidityMax) {
		this.humidityMax = humidityMax;
	}

	public String getHumidityRegionMin() {
		return humidityRegionMin;
	}

	public void setHumidityRegionMin(String humidityRegionMin) {
		this.humidityRegionMin = humidityRegionMin;
	}

	public String getHumidityRegionMax() {
		return humidityRegionMax;
	}

	public void setHumidityRegionMax(String humidityRegionMax) {
		this.humidityRegionMax = humidityRegionMax;
	}

	public String getIp() {
		return ip;
	}
}
