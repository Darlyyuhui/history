package com.xiangxun.atms.framework.monitor.testThread;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.xiangxun.atms.framework.monitor.conf.Dictionary;
import com.xiangxun.atms.framework.monitor.nio.EchoWorker;
import com.xiangxun.atms.framework.monitor.vo.AlarmLog;
import com.xiangxun.atms.framework.monitor.vo.DeviceStatus;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.xml.root.status.DataInsertDatabaseStatus;
import com.xiangxun.xml.root.status.DatabaseStatus;
import com.xiangxun.xml.root.status.FtpStatus;
import com.xiangxun.xml.root.status.WebStatus;

public class StatusSendThread extends Thread {

	// 需要保存的报警信息
	private static List<AlarmLog> queueList = new LinkedList<AlarmLog>();

	// 给数据调用的接口
	public static void addMessage(AlarmLog dataForPushTemp) {
		synchronized (queueList) {
			queueList.add(dataForPushTemp);
			queueList.notifyAll();
		}
	}

	public void run() {

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		int max = 5;
		int t = max;

		while (true) {
			// 从数据库配置文件中 取出数据库连接信息
			DatabaseStatus databaseStatusTemp = Dictionary.databaseStatus;
			try {
				// 加载驱动
				Class.forName(databaseStatusTemp.getDriver());
				// 得到连接
				Connection conn = DriverManager.getConnection(databaseStatusTemp.getFullPath(),
						databaseStatusTemp.getUsername(), databaseStatusTemp.getPassword());
				Statement statementDevice = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,
						ResultSet.CONCUR_UPDATABLE);
				// 从数据库资产表 查处所有卡口类型的设备信息 存入listDeviceStatus中
				if (EchoWorker.listDeviceStatus.isEmpty()) {
					ResultSet rs = statementDevice
							.executeQuery("select a.id,a.assetname,a.ip,d.code,a.assettype,a.power_status,a.net_status,a.data_status,a.cabinet_status from PROPERTY_ASSET_INFO a "
									+ "left join PROPERTY_DEVICE_INFO d on a.deviceid = d.id where a.assettype in ('device','cabinet')");
					while (rs.next()) {
						String assetId = rs.getString(1);
						String assetName = rs.getString(2);
						String ip = rs.getString(3);
						String devCode = rs.getString(4);
						String deviceType = rs.getString(5);
						int powerStatus = rs.getInt(6);
						int netStatus = rs.getInt(7);
						int dataStatus = rs.getInt(8);
						int cabinetStatus = rs.getInt(9);
						EchoWorker.listDeviceStatus.add(new DeviceStatus(assetId, assetName, ip, devCode, deviceType,powerStatus,netStatus,dataStatus,cabinetStatus));

					}
					rs.close();
				}
				// 将异常告警日志 插入告警表
				String sqlInsertErrorLog = "insert into ALARM_DEVICE_LOG (id, device_name, device_code, device_ip, device_type, is_outer, alarm_time, event_type) values (?,?,?,?,?,?,sysdate,?)";
				PreparedStatement statementErrorLog = conn.prepareStatement(sqlInsertErrorLog);
				for (AlarmLog log : queueList) {
					statementErrorLog.setString(1, UuidGenerateUtil.getUUIDLong());
					// 资产名称
					statementErrorLog.setString(2, log.getDEVICE_NAME());
					// 资产ID
					statementErrorLog.setString(3, log.getDEVICE_CODE());
					statementErrorLog.setString(4, log.getDEVICE_IP());
					statementErrorLog.setString(5, log.getDEVICE_TYPE());
					statementErrorLog.setString(6, log.getIS_OUTER());
					statementErrorLog.setString(7, log.getEVENT_TYPE());
					statementErrorLog.addBatch();
				}

				statementErrorLog.executeBatch();
				queueList.clear();
				conn.commit();
				// 每过30次 进行下面的代码
				t--;
				if (t == 0) {
					t = max;
					String serverName = InetAddress.getLocalHost().getHostName().toString();
					// 每隔30次 开始批量插入状态值（非告警数据） 数据来源 PROPERTY_ASSET_INFO 表
					String sqlInsertDeviceStatus = "insert into STATUS_DEVICE (ID,IP,ASSET_ID,POWER_STATUS,NET_STATUS,DATA_STATUS,INSERTTIME,INSERTPC) "
							+ "values (?,?,?,?,?,?,sysdate,?)";
					// 把数据库查出来的 listDeviceStatus 与 传输传来的
					// listDataInsertDatabaseStatus 进行循环对比CODE 有相同CODE的则证明数据正常
					System.out.println("========= " + new java.util.Date() + " == listDataInsertDatabaseStatus size == "
							+ EchoWorker.listDataInsertDatabaseStatus.size());
					for (DeviceStatus deviceStatus : EchoWorker.listDeviceStatus) {
						int datastatus = 3;
						for (DataInsertDatabaseStatus dataInsertDatabaseStatus : EchoWorker.listDataInsertDatabaseStatus) {
							String device = "";
							// 如果dataInsertDatabaseStatus对象的 deviceNew
							// 如果不为空，则证明入库时编码改变
							if (dataInsertDatabaseStatus.getDeviceNew() != null
									&& !dataInsertDatabaseStatus.getDeviceNew().equals("")) {
								device = dataInsertDatabaseStatus.getDeviceNew();
							} else {
								device = dataInsertDatabaseStatus.getDevice();
							}
							// 用资产表的所有卡口设备 和 有状态的设备对比 如果存在则证明正常
							if (null != deviceStatus.deviceCode && deviceStatus.deviceCode.equals(device)) {
								String carTimeStr = dataInsertDatabaseStatus.getLastTime();
								if (carTimeStr != null && !"".equals(carTimeStr)) {
									long carTimeLong = DateUtil.stringFormatToDate(carTimeStr, "yyyy-MM-dd HH:mm:ss")
											.getTime();
									long currentTimeLong = System.currentTimeMillis();
									// 半小时单位时间
									long perTimeLong = 1800 * 1000;
									if ((currentTimeLong - carTimeLong) > perTimeLong) {
										// 说明数据延迟
										datastatus = 2;
									} else if ((carTimeLong - currentTimeLong) > perTimeLong) {
										// 说明数据异常（超前）
										datastatus = 2;
									} else {
										datastatus = 1;
									}
								} else {
									// 如果走到这里 说明传输没有传 最后一条记录的时间 暂不记录异常
									datastatus = 1;
								}
								System.out.println("设备：" + device + "正常");
								datastatus = 1;
								break;
							}
						}
						deviceStatus.dataStatus = datastatus;
						if ("1".equals(datastatus)) {
							deviceStatus.netStatus = 1;
							deviceStatus.powerStatus = 1;
						}
						System.out
								.println("设备匹配结果===" + deviceStatus.deviceCode + "===" + deviceStatus.dataStatus + "");
					}

					// 在判断完设备状态后 批量插入本平台数据库
					PreparedStatement statementDeviceStatus = conn.prepareStatement(sqlInsertDeviceStatus);
					for (DeviceStatus deviceStatus : EchoWorker.listDeviceStatus) {
						statementDeviceStatus.setString(1, UuidGenerateUtil.getUUIDLong());
						statementDeviceStatus.setString(2, deviceStatus.ip);
						statementDeviceStatus.setString(3, deviceStatus.assetId);
						statementDeviceStatus.setInt(6, deviceStatus.dataStatus);
						// 入库时候再做一次规则匹配判断 防止多线程引起的过程中修改赋值
						if (deviceStatus.dataStatus == 1) {
							statementDeviceStatus.setInt(4, 1);
							statementDeviceStatus.setInt(5, 1);
						} else {
							statementDeviceStatus.setInt(4, deviceStatus.powerStatus);
							statementDeviceStatus.setInt(5, deviceStatus.netStatus);
						}
						statementDeviceStatus.setString(7, serverName);
						statementDeviceStatus.addBatch();
					}
					statementDeviceStatus.executeBatch();
					conn.commit();

					// FTP WEB DB 应用的情况 目前这三种的集合信息 在Dictionary中通过读文件存储 后面改为查数据库
					String sqlInsertAppStatus = "insert into STATUS_APP (ID, IP, TYPE, CONNECT_STATUS, RESOURCE_INFO, APP_NAME, APP_STATUS,PATH,INSERTTIME,INSERTPC) "
							+ "values (?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement statementAppStatus = conn.prepareStatement(sqlInsertAppStatus);

					Iterator<Entry<String, FtpStatus>> ftpListIterator = Dictionary.FtpStatusMap.entrySet().iterator();
					while (ftpListIterator.hasNext()) {
						Map.Entry<String, FtpStatus> entryFtpStatus = (Map.Entry<String, FtpStatus>) ftpListIterator
								.next();						
						FtpStatus ftpStatus = ((FtpStatus) entryFtpStatus.getValue());
						if (ftpStatus.getStatus() == null)
							continue;
						statementAppStatus.setString(1, UuidGenerateUtil.getUUIDLong());
						statementAppStatus.setString(2, ftpStatus.getIp());
						statementAppStatus.setString(3, "ftp");
						statementAppStatus.setInt(4, "ok".equals(ftpStatus.getStatus()) ? 1 : 3);
						statementAppStatus.setString(5, ftpStatus.getSpace());
						statementAppStatus.setString(6, ftpStatus.getType());
						statementAppStatus.setInt(7, "ok".equals(ftpStatus.getStatus()) ? 1 : 3);
						statementAppStatus.setString(8, ftpStatus.getKey());
						statementAppStatus.setTimestamp(9, new Timestamp((new Date()).getTime()));
						statementAppStatus.setString(10, serverName);
						statementAppStatus.addBatch();
					}

					Iterator<Entry<String, WebStatus>> webStatusIterator = Dictionary.WebStatusMap.entrySet()
							.iterator();
					while (webStatusIterator.hasNext()) {
						Map.Entry<String, WebStatus> entryWebStatus = (Map.Entry<String, WebStatus>) webStatusIterator.next();
						WebStatus webStatus = (WebStatus) entryWebStatus.getValue();
						if (webStatus.getStatus() == null)
							continue;
						statementAppStatus.setString(1, UuidGenerateUtil.getUUIDLong());
						statementAppStatus.setString(2, webStatus.getIp());
						statementAppStatus.setString(3, "project");
						statementAppStatus.setInt(4, "ok".equals(webStatus.getStatus()) ? 1 : 3);
						statementAppStatus.setString(5, "");
						statementAppStatus.setString(6, webStatus.type);
						statementAppStatus.setInt(7, "ok".equals(webStatus.getStatus()) ? 1 : 3);
						statementAppStatus.setString(8, webStatus.getKey());
						statementAppStatus.setTimestamp(9, new Timestamp((new Date()).getTime()));
						statementAppStatus.setString(10, serverName);
						statementAppStatus.addBatch();
					}

					Iterator<Entry<String, DatabaseStatus>> databaseStatusIterator = Dictionary.DatabaseStatusMap
							.entrySet().iterator();
					while (databaseStatusIterator.hasNext()) {
						Map.Entry<String, DatabaseStatus> entryDatabaseStatus = (Map.Entry<String, DatabaseStatus>) databaseStatusIterator
								.next();
						DatabaseStatus databaseStatus = (DatabaseStatus) entryDatabaseStatus.getValue();
						if (databaseStatus.getStatus() == null)
							continue;
						statementAppStatus.setString(1, UuidGenerateUtil.getUUIDLong());
						statementAppStatus.setString(2, databaseStatus.getIp());
						statementAppStatus.setString(3, "database");
						statementAppStatus.setInt(4, "ok".equals(databaseStatus.getStatus()) ? 1 : 3);
						String databaseSpace = "";
						for (String databaseSpaceTemp : databaseStatus.getSpaceList().getList()) {
							databaseSpace += databaseSpaceTemp + ",";
						}
						statementAppStatus.setString(5, databaseSpace);
						statementAppStatus.setString(6, databaseStatus.type);
						statementAppStatus.setInt(7, "ok".equals(databaseStatus.getStatus()) ? 1 : 3);
						statementAppStatus.setString(8, databaseStatus.getKey());
						statementAppStatus.setTimestamp(9, new Timestamp((new Date()).getTime()));
						statementAppStatus.setString(10, serverName);
						statementAppStatus.addBatch();
					}
					statementAppStatus.executeBatch();
					conn.commit();

					statementAppStatus.close();
					statementDeviceStatus.close();
				}

				statementDevice.close();
				statementErrorLog.close();
				conn.close();
			} catch (java.sql.SQLException e) {
				e.printStackTrace();
				databaseStatusTemp.setStatus("error");
			} catch (Exception e) {
				databaseStatusTemp.setStatus("error");
				e.printStackTrace();
			}

			try {
				Thread.sleep(1000 * 10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
