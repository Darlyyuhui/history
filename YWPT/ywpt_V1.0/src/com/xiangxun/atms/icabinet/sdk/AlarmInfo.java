package com.xiangxun.atms.icabinet.sdk;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlarmInfo {
	private static Logger logger = LoggerFactory.getLogger(AlarmInfo.class);
	private static String sql = "{call sp_alarm_icabinet_log_insert(?,?,?,?,?,?,null,?,?,?,?,?,?,?,?)}";
	private String id;// 数据库ID
	private String sn;// 机柜序列号
	private String ip;// 机柜IP
	private String channelName = "";// 上传通道名称
	private Date eventTime = new Date();// 上传时间
	private int sensorType = 0;// 传感器类型 0 ：非传感器， 1： 温度 2：湿度 16：AC端子 17：AC插座
	private String message;// 上传的信息
	private byte[] photo1; // 上传的图片1
	private byte[] photo2; // 上传的图片2
	private int status = 0; // 信息级别: 0:正常、1：异常、2、报警
	private float actualValue = 0;// 当前值
	private byte powerSupply = 1;// 是否正常供电 0-不在供电，1-正在供电
	private float voltageValue = 0;// 电压值
	private float currentValue = 0;// 电流值

	public AlarmInfo(String id) {
		this.id = id;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getEventTime() {
		return eventTime;
	}

	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

	public void setEventDateTime(int year, int month, int day, int hour, int minute, int second, int milliSecond) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day, hour, minute, second);
		cal.set(Calendar.MILLISECOND, milliSecond);		
		this.eventTime = cal.getTime();
	}

	public int getSensorType() {
		return sensorType;
	}

	public void setSensorType(int sensorType) {
		this.sensorType = sensorType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public byte[] getPhoto1() {
		return photo1;
	}

	public void setPhoto1(byte[] photo) {
		this.photo1 = photo;
	}

	public byte[] getPhoto2() {
		return photo2;
	}

	public void setPhoto2(byte[] photo2) {
		this.photo2 = photo2;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public float getActualValue() {
		return actualValue;
	}

	public void setActualValue(float actualValue) {
		this.actualValue = actualValue;
	}

	public byte isPowerSupply() {
		return powerSupply;
	}

	public void setPowerSupply(byte powerSupply) {
		this.powerSupply = powerSupply;
	}

	public float getVoltageValue() {
		return voltageValue;
	}

	public void setVoltageValue(float voltageValue) {
		this.voltageValue = voltageValue;
	}

	public float getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(float currentValue) {
		this.currentValue = currentValue;
	}

	/**
	 * 保存数据
	 * 
	 * @param conn 数据库连接
	 */
	public void save(Connection conn) {
		if (conn == null)
			return;
		// try (PreparedStatement stat = conn.prepareStatement(sql);) {
		try (CallableStatement stat = conn.prepareCall(sql);) {
			stat.setString(1, id);
			stat.setString(2, sn);
			stat.setString(3, ip);
			stat.setTimestamp(4, new Timestamp(eventTime.getTime()));
			stat.setInt(5, sensorType);
			stat.setString(6, message);
			// XMLType xmltype = XMLType.createXML(conn, xmldata);
			// stat.setObject(7, xmltype);
			stat.setBytes(7, photo1);
			stat.setBytes(8, photo2);
			stat.setString(9, channelName);
			stat.setInt(10, status);
			stat.setFloat(11, actualValue);
			stat.setByte(12, powerSupply);
			stat.setFloat(13, voltageValue);
			stat.setFloat(14, currentValue);
			stat.executeUpdate();
			conn.commit();
			logger.info("信息已经保存");
			stat.close();
		} catch (SQLException ex) {
			logger.error(ex.getMessage());
		}
	}
}
