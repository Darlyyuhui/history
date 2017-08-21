package com.xiangxun.atms.icabinet.tools;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ThreadPoolExecutor;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.icabinet.isapi.IExecuteSucc;
import com.xiangxun.atms.icabinet.isapi.NonICabinetException;
import com.xiangxun.atms.icabinet.isapi.ResponseFailed;

/**
 * 机柜信息下发器
 * 
 * @author Administrator
 *
 */
public class ICabinetOptionner {
	private static final Logger logger = LoggerFactory.getLogger(ICabinetOptionner.class);
	private Object locker = new Object();
	private final String execNo;
	private final DataSource ds;
	private final int COMPLETEVALUE; // 完成后二进制位对应的整数
	private ICabinetOption[] options;
	private ThreadPoolExecutor service;

	/**
	 * 创建机柜信息下发器实例
	 * 
	 * @param ds 数据源
	 * @param options 机柜信息数组
	 * @return 机柜信息下发器实例
	 */
	public ICabinetOptionner(ThreadPoolExecutor service, DataSource ds, ICabinetOption[] options) {
		this.service = service;
		this.ds = ds;
		this.options = options;
		execNo = UuidGenerateUtil.getUUIDLong();
		COMPLETEVALUE = (int) (Math.pow(2, 8) - 1);
	}

	/**
	 * 1.当PROGRESS的值等于该值时，表示已经执行完毕，否则还在执行中；<br>
	 * 2.当PROGRESS的值等于该值时：如果 STATUS的值也等于该值时，表示已经执行成功，否则执行失败；
	 * 
	 * @return
	 */
	public int getCompleteValue() {
		return COMPLETEVALUE;
	}

	/**
	 * 开始执行下发，并返回本次的执行编号
	 * 
	 * @return 执行编号
	 */
	public String start() {
		for (ICabinetOption op : options) {
			service.execute(new Runner(op));
		}
		return execNo;
	}

	/**
	 * 写执行日志记录
	 * 
	 * @param ip 当前机柜的IP
	 */
	private void writeOptionLog(String ip) {
		String sql = "insert into OPTION_ICABINET_LOG(ID,EXEC_NO,IP,PROGRESS,STATUS,MESSAGE,OP_DATE) values(?,?,?,?,?,?,sysdate)";
		try (Connection conn = ds.getConnection(); PreparedStatement stat = conn.prepareStatement(sql)) {
			stat.setString(1, UuidGenerateUtil.getUUIDLong());
			stat.setString(2, execNo);
			stat.setString(3, ip);
			stat.setInt(4, 0);
			stat.setInt(5, 0);
			stat.setString(6, "准备下发");
			stat.executeUpdate();
		} catch (SQLException ex) {
			logger.error(ex.getMessage());
		}
	}

	/**
	 * 使用线程执行机柜信息的下发
	 * 
	 * @author Administrator
	 *
	 */
	class Runner implements Runnable {

		private ICabinetOption op;

		public Runner(ICabinetOption option) {
			this.op = option;
		}

		@Override
		public void run() {			
			try (ICabinetTools cabinet = new ICabinetTools(op.getIp(), op.getPort(), op.getUserName(), op.getPassword())) {				
				// 将日志记录写进去
				writeOptionLog(op.getIp());
				// 获取设备的型号与序列号
				cabinet.getDeviceModelAndSN(new Fail(0, ds, op.getIp()), new IExecuteSucc() {
					@Override
					public void succed(Object tag) {
						String[] modelAndSn = (String[]) tag;
						logger.info("智能机柜型号:" + modelAndSn[0]);
						if (StringUtils.startsWith(modelAndSn[0], "DS-TP3200-EC")) {
							new Succ(0, ds, op.getIp()).succed(tag);
						} else {
							new Fail(0, ds, op.getIp()).failed(new NonICabinetException("目标不是机柜"));
							return;
						}
						logger.info(String.format("开始下发机柜%s", op.getIp()));
						try (ICabinetTools tools = new ICabinetTools(op.getIp(), op.getPort(), op.getUserName(), op.getPassword())) {
							// 修改设备名称和编号
							tools.saveDeviceNameAndNo(op.getName(), op.getNo(), new Fail(1, ds, op.getIp()), new Succ(1, ds, op.getIp()));
							// 设置远程主机IP和端口号
							tools.setRemoteHostIpAndPort(op.getAlarmHostIP(), op.getAlarmHostPort(), new Fail(2, ds, op.getIp()), new Succ(2, ds, op.getIp()));
							// 设置报警主机的地址
							tools.setAlarmCenterIpAndPort(op.getAlarmHostIP(), op.getAlarmHostPort(), new Fail(3, ds, op.getIp()), new Succ(3, ds, op.getIp()));
							// 修改AC端子的信息
							tools.setACTerminal(op.getSamplingTime(), new Fail(4, ds, op.getIp()), new Succ(4, ds, op.getIp()));
							// 设置AC插座的电压电流范围
							tools.setDeviceACSocket(op.getSamplingTime(), op.getAcSocketCurrentMin(), op.getAcSocketCurrentMax(), op.getAcSocketVoltageMin(), op.getAcSocketVoltageMax(), new Fail(5, ds, op.getIp()), new Succ(5, ds, op.getIp()));
							// 设置温度传感器正常范围
							tools.setTemperatureRegions(op.getSamplingTime(), op.getTemperatureMin(), op.getTemperatureMax(), op.getTemperatureRegionMin(), op.getTemperatureRegionMax(), new Fail(6, ds, op.getIp()), new Succ(6, ds, op.getIp()));
							// 设置湿度传感器正常范围
							tools.setHumidityRegions(op.getSamplingTime(), op.getHumidityMin(), op.getHumidityMax(), op.getHumidityRegionMin(), op.getHumidityRegionMax(), new Fail(7, ds, op.getIp()), new Succ(7, ds, op.getIp()));
							// 设置Http,SDK访问的端口号
							// cabinet.setDevicePortNo("80", "8000", new Fail(8, ds, op.getIp()), new Succ(8, ds, op.getIp()));
						} catch (Exception ex) {
							logger.error(ex.getClass().getName() + ":" + ex.getMessage());
						}
					}
				});
			} catch (Exception ex) {
				logger.error(ex.getClass().getName() + ":" + ex.getMessage());
			}
		}
	}

	/**
	 * 执行成功的回调（写执行进程与成功状态（位操作））
	 * 
	 * @author Administrator
	 *
	 */
	class Succ implements IExecuteSucc {
		private int bitNum = 0;
		private boolean succed = false;
		private DataSource ds;
		private String ip;
		private static final String SQL = "{call update_icabinet_log(?,?,?,?,1)}";
		private static final String UPDATESQL = "update property_icabinet_info set SN=? where IP=?";

		/**
		 * 成功后写标志位
		 * 
		 * @param bitNum 位号
		 * @param ds 数据源
		 * @param ip 机柜IP
		 */
		public Succ(int bitNum, DataSource ds, String ip) {
			this.bitNum = bitNum;
			this.ds = ds;
			this.ip = ip;
		}

		@Override
		public void succed(Object tag) {
			if (succed) // 防止执行多次，造成值不正确
				return;
			succed = true;
			synchronized (locker) {
				logger.info(String.format("%s bit num:%d succed", ip, bitNum));
				try (Connection conn = ds.getConnection()) {
					try (CallableStatement stat = conn.prepareCall(SQL)) {
						stat.setString(1, execNo);
						stat.setString(2, ip);
						stat.setInt(3, bitNum);
						stat.setInt(4, COMPLETEVALUE);
						stat.execute();
					}
					if (bitNum == 0) {// 获取设备SN
						String[] modelAndSN = (String[]) tag;
						try (PreparedStatement stat = conn.prepareStatement(UPDATESQL)) {
							if (StringUtils.startsWithIgnoreCase(modelAndSN[1],"DS-TP3200-EC/GW"))
								stat.setString(1, StringUtils.substringAfter(modelAndSN[1], "DS-TP3200-EC/GW"));// 去掉型号
							else
							    stat.setString(1, StringUtils.substringAfter(modelAndSN[1], "DS-TP3200-EC"));// 去掉型号
							stat.setString(2, ip);
							stat.executeUpdate();
						}
					}
				} catch (SQLException ex) {
					logger.error(ex.getMessage());
				}
			}
		}
	}

	/**
	 * 执行失败的回调（写执行进程（位操作）与写异常信息））
	 * 
	 * @author Administrator
	 *
	 */
	class Fail implements ResponseFailed {
		private int bitNum = 0;
		private DataSource ds;
		private String ip;
		private boolean failed = false;
		private static final String SQL = "{call update_icabinet_log(?,?,?,?,0,?)}";

		public Fail(int bitNum, DataSource ds, String ip) {
			this.bitNum = bitNum;
			this.ds = ds;
			this.ip = ip;
		}

		@Override
		public void failed(Exception ex) {
			if (failed) // 防止执行多次，造成值不正确
				return;
			String errMsg = ex.getMessage();
			if (ex instanceof IOException)
				errMsg = "设备无法连接";
			logger.info(String.format("智能机柜IP[%s]参数下发失败:%s", ip, ex.getMessage()));
			failed = true;
			synchronized (locker) {
				logger.info(String.format("%s bit num:%d error", ip, bitNum));
				try (Connection conn = ds.getConnection(); CallableStatement stat = conn.prepareCall(SQL)) {
					stat.setString(1, execNo);
					stat.setString(2, ip);
					stat.setInt(3, bitNum);
					stat.setInt(4, COMPLETEVALUE);
					stat.setString(5, errMsg);
					stat.execute();
				} catch (SQLException e) {
					logger.error(e.getMessage());
				}
			}
		}
	}

}
