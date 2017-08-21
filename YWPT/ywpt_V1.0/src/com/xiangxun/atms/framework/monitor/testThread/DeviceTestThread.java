package com.xiangxun.atms.framework.monitor.testThread;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;

import com.xiangxun.atms.framework.base.ApplicationContextHolder;
import com.xiangxun.atms.framework.monitor.conf.Dictionary;
import com.xiangxun.atms.framework.monitor.control.DataPushServer;
import com.xiangxun.atms.framework.monitor.nio.EchoWorker;
import com.xiangxun.atms.framework.monitor.vo.AlarmLog;
import com.xiangxun.atms.framework.monitor.vo.DeviceStatus;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.xml.root.status.DataInsertDatabaseStatus;
import com.xiangxun.xml.root.status.DatabaseStatus;

public class DeviceTestThread extends Thread {
	private DataSource ds;

	public DeviceTestThread() {
		this.ds = (DataSource) ApplicationContextHolder.getBean("dataSource");
	}

	public void run() {

		try {
			Thread.sleep(1000 * 30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		while (true) {
			// System.out.println("==== DeviceTestThread ===== "+new
			// java.util.Date()+" == listDataInsertDatabaseStatus size ==
			// "+EchoWorker.listDataInsertDatabaseStatus.size());
			
			for (int i = 0; i < EchoWorker.listDeviceStatus.size(); i++) {
				
				DeviceStatus deviceStatus=EchoWorker.listDeviceStatus.get(i);
				// 首先判断数据库中单个设备的数据是否正常
				int datastatustemp = 3;
				boolean isCabinet = StringUtils.equalsIgnoreCase(deviceStatus.getDeviceType(), "cabinet");
				if (!isCabinet) {
					for (DataInsertDatabaseStatus dataInsertDatabaseStatus : EchoWorker.listDataInsertDatabaseStatus) {
						String tradevCode = "";
						if (dataInsertDatabaseStatus.getDeviceNew() != null && !dataInsertDatabaseStatus.getDeviceNew().equals("")) {
							tradevCode = dataInsertDatabaseStatus.getDeviceNew();
						} else {
							tradevCode = dataInsertDatabaseStatus.getDevice();
						}
						// 用资产表的所有卡口设备 和 有状态的设备对比 如果存在则证明已接入
						if (null != deviceStatus.deviceCode && deviceStatus.deviceCode.equals(tradevCode)) {
							String carTimeStr = dataInsertDatabaseStatus.getLastTime();
							if (carTimeStr != null && !"".equals(carTimeStr)) {
								long carTimeLong = DateUtil.stringFormatToDate(carTimeStr, "yyyy-MM-dd HH:mm:ss").getTime();
								long currentTimeLong = System.currentTimeMillis();
								// 半小时单位时间
								long perTimeLong = 1800 * 1000;
								if ((currentTimeLong - carTimeLong) > perTimeLong) {
									// 说明数据延迟
									datastatustemp = 2;
								} else if ((carTimeLong - currentTimeLong) > perTimeLong) {
									// 说明数据异常（超前）
									datastatustemp = 2;
								} else {
									datastatustemp = 1;
								}
							} else {
								// 如果走到这里 说明传输没有传 最后一条记录的时间 暂不记录异常
								datastatustemp = 1;
							}
							break;
						}
					}
				}
				deviceStatus.dataStatus = datastatustemp;
				// 如果数据是好的 则全部都正常
				if (datastatustemp == 1) {
					deviceStatus.netStatus = 1;
					deviceStatus.powerStatus = 1;
				} else {
					// 如果数据不正常 则判断网络 和 供电 是否正常
					deviceStatus.powerStatus = 0;
					// 网络状态
					String ip = deviceStatus.ip;
					try {
						boolean status = InetAddress.getByName(ip).isReachable(3000);
						if (status) {
							// 网络通 则证明 网络 和 电源状态正常
							deviceStatus.netStatus = 1;
							deviceStatus.powerStatus = 1;
							// 数据异常产生告警
							if (isCabinet) {// 如果机柜通
								if (deviceStatus.getCabinetStatus() < 0) {// 未知状态									
									CabinetStatusWritter writter = new CabinetStatusWritter(deviceStatus.getAssetId(), 0, ds);// 设成正常状态
									new Thread(writter).start();
									
								} else {
									int netError = (int) Math.pow(2, 7); // 第7位128表示网络状态0.通1.不通
									if ((deviceStatus.getCabinetStatus() & netError) == netError) {
										int cStatus = deviceStatus.getCabinetStatus() - netError;// 通										
										CabinetStatusWritter writter = new CabinetStatusWritter(deviceStatus.getAssetId(), cStatus, ds);
										new Thread(writter).start();										
									}
								}
							} else {
								if (datastatustemp == 2 || datastatustemp == 3) {
									// 数据状态不正常 直接插入报警表
									AlarmLog alarmLog = new AlarmLog();
									alarmLog.setDEVICE_NAME(deviceStatus.assetName);
									alarmLog.setDEVICE_CODE(deviceStatus.assetId);
									alarmLog.setDEVICE_IP(deviceStatus.ip);
									alarmLog.setDEVICE_TYPE("device");
									alarmLog.setIS_OUTER("0");// （0-场外；1-场内）
									alarmLog.setEVENT_TYPE("1008");// 数据状态异常对应的事件类型
									DataPushServer.addMessage(alarmLog);
								}
							}
						} else {// 网络不通
							deviceStatus.netStatus = 3;
							deviceStatus.dataStatus = 3;
							// 网络不通直接插入报警表
							AlarmLog alarmLog = new AlarmLog();
							alarmLog.setDEVICE_NAME(deviceStatus.assetName);
							alarmLog.setDEVICE_CODE(deviceStatus.assetId);
							alarmLog.setDEVICE_IP(deviceStatus.ip);
							alarmLog.setDEVICE_TYPE(isCabinet ? "cabinet" : "device");
							alarmLog.setIS_OUTER("0");// （0-场外；1-场内）
							alarmLog.setEVENT_TYPE(isCabinet ? "2012" : "1007");// 网络，供电状态
																				// 异常对应的事件类型
							DataPushServer.addMessage(alarmLog);

							if (isCabinet) {
								int netError = (int) Math.pow(2, 7);// 第7位表示网络状态
								if ((Math.max(deviceStatus.getCabinetStatus(), 0) & netError) == 0) {// 去掉未知状态
									int cStatus = Math.max(deviceStatus.getCabinetStatus(), 0) + netError;// 不通
									CabinetStatusWritter writter = new CabinetStatusWritter(deviceStatus.getAssetId(), cStatus, ds);
									new Thread(writter).start();
								}
							}
						}
					} catch (IOException e) {
					} 
				}
			
				deviceStatus = null;
			}
			
			/*for (DeviceStatus deviceStatus : EchoWorker.listDeviceStatus) {
				// 首先判断数据库中单个设备的数据是否正常
				int datastatustemp = 3;
				boolean isCabinet = StringUtils.equalsIgnoreCase(deviceStatus.getDeviceType(), "cabinet");
				if (!isCabinet) {
					for (DataInsertDatabaseStatus dataInsertDatabaseStatus : EchoWorker.listDataInsertDatabaseStatus) {
						String tradevCode = "";
						if (dataInsertDatabaseStatus.getDeviceNew() != null && !dataInsertDatabaseStatus.getDeviceNew().equals("")) {
							tradevCode = dataInsertDatabaseStatus.getDeviceNew();
						} else {
							tradevCode = dataInsertDatabaseStatus.getDevice();
						}
						// 用资产表的所有卡口设备 和 有状态的设备对比 如果存在则证明已接入
						if (null != deviceStatus.deviceCode && deviceStatus.deviceCode.equals(tradevCode)) {
							String carTimeStr = dataInsertDatabaseStatus.getLastTime();
							if (carTimeStr != null && !"".equals(carTimeStr)) {
								long carTimeLong = DateUtil.stringFormatToDate(carTimeStr, "yyyy-MM-dd HH:mm:ss").getTime();
								long currentTimeLong = System.currentTimeMillis();
								// 半小时单位时间
								long perTimeLong = 1800 * 1000;
								if ((currentTimeLong - carTimeLong) > perTimeLong) {
									// 说明数据延迟
									datastatustemp = 2;
								} else if ((carTimeLong - currentTimeLong) > perTimeLong) {
									// 说明数据异常（超前）
									datastatustemp = 2;
								} else {
									datastatustemp = 1;
								}
							} else {
								// 如果走到这里 说明传输没有传 最后一条记录的时间 暂不记录异常
								datastatustemp = 1;
							}
							break;
						}
					}
				}
				deviceStatus.dataStatus = datastatustemp;
				// 如果数据是好的 则全部都正常
				if (datastatustemp == 1) {
					deviceStatus.netStatus = 1;
					deviceStatus.powerStatus = 1;
				} else {
					// 如果数据不正常 则判断网络 和 供电 是否正常
					deviceStatus.powerStatus = 0;
					// 网络状态
					String ip = deviceStatus.ip;
					try {
						boolean status = InetAddress.getByName(ip).isReachable(3000);
						if (status) {
							// 网络通 则证明 网络 和 电源状态正常
							deviceStatus.netStatus = 1;
							deviceStatus.powerStatus = 1;
							// 数据异常产生告警
							if (isCabinet) {// 如果机柜通
								if (deviceStatus.getCabinetStatus() < 0) {// 未知状态
									CabinetStatusWritter writter = new CabinetStatusWritter(deviceStatus.getAssetId(), 0, ds.getConnection());// 设成正常状态
									new Thread(writter).start();
								} else {
									int netError = (int) Math.pow(2, 7); // 第7位128表示网络状态0.通1.不通
									if ((deviceStatus.getCabinetStatus() & netError) == netError) {
										int cStatus = deviceStatus.getCabinetStatus() - netError;// 通
										CabinetStatusWritter writter = new CabinetStatusWritter(deviceStatus.getAssetId(), cStatus, ds.getConnection());
										new Thread(writter).start();
									}
								}
							} else {
								if (datastatustemp == 2 || datastatustemp == 3) {
									// 数据状态不正常 直接插入报警表
									AlarmLog alarmLog = new AlarmLog();
									alarmLog.setDEVICE_NAME(deviceStatus.assetName);
									alarmLog.setDEVICE_CODE(deviceStatus.assetId);
									alarmLog.setDEVICE_IP(deviceStatus.ip);
									alarmLog.setDEVICE_TYPE("device");
									alarmLog.setIS_OUTER("0");// （0-场外；1-场内）
									alarmLog.setEVENT_TYPE("1008");// 数据状态异常对应的事件类型
									DataPushServer.addMessage(alarmLog);
								}
							}
						} else {// 网络不通
							deviceStatus.netStatus = 3;
							deviceStatus.dataStatus = 3;
							// 网络不通直接插入报警表
							AlarmLog alarmLog = new AlarmLog();
							alarmLog.setDEVICE_NAME(deviceStatus.assetName);
							alarmLog.setDEVICE_CODE(deviceStatus.assetId);
							alarmLog.setDEVICE_IP(deviceStatus.ip);
							alarmLog.setDEVICE_TYPE(isCabinet ? "cabinet" : "device");
							alarmLog.setIS_OUTER("0");// （0-场外；1-场内）
							alarmLog.setEVENT_TYPE(isCabinet ? "2012" : "1007");// 网络，供电状态
																				// 异常对应的事件类型
							DataPushServer.addMessage(alarmLog);

							if (isCabinet) {
								int netError = (int) Math.pow(2, 7);// 第7位表示网络状态
								if ((Math.max(deviceStatus.getCabinetStatus(), 0) & netError) == 0) {// 去掉未知状态
									int cStatus = Math.max(deviceStatus.getCabinetStatus(), 0) + netError;// 不通
									CabinetStatusWritter writter = new CabinetStatusWritter(deviceStatus.getAssetId(), cStatus, ds.getConnection());
									new Thread(writter).start();
								}
							}
						}
					} catch (UnknownHostException | SQLException e) {
					} catch (IOException e) {
					}
				}
			}*/

			try {
				Thread.sleep(1000 * 30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}

class CabinetStatusWritter implements Runnable {
	private String id;
	private int status;
	private DataSource ds;
	private String sql = "update property_asset_info set cabinet_status=? where id=?";

	public CabinetStatusWritter(String id, int status, DataSource ds) {
		this.id = id;
		this.status = status;
		this.ds = ds;
	}

	@Override
	public void run() {
		try(Connection conn =ds.getConnection();//
				PreparedStatement stat = conn.prepareStatement(sql)) {			
			stat.setInt(1, status);
			stat.setString(2, id);
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
