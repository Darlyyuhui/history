package com.xiangxun.atms.icabinet.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.xiangxun.atms.common.system.service.ParamsService;
import com.xiangxun.atms.common.system.service.impl.ParamsServiceImpl;
import com.xiangxun.atms.framework.base.ApplicationContextHolder;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.icabinet.isapi.Configure;
import com.xiangxun.atms.icabinet.sdk.AlarmInfo;
import com.xiangxun.atms.icabinet.sdk.ICabinetSDK;
import com.xiangxun.atms.icabinet.sdk.IMessageDeliver;
import com.xiangxun.atms.icabinet.sdk.IMessageHelper;
import com.xiangxun.atms.icabinet.sdk.LoginInfo;
import com.xiangxun.atms.icabinet.sdk.MessageSender;
import com.xiangxun.atms.icabinet.statusserver.StatusServer;
import com.xiangxun.atms.icabinet.statusserver.protocal.param.CabinetRangeParam;
import com.xiangxun.atms.module.eventalarm.service.WorkorderInfoService;

/**
 * 智能机柜信息上传监听服务启动
 *
 */
public class ICabinetStarter implements ServletContextListener, IMessageDeliver, IMessageHelper {
	private static final Logger logger = LoggerFactory.getLogger(ICabinetStarter.class);
	private static final String sql = "select ip,id,sn from PROPERTY_ICABINET_INFO where ip=?";
	public static boolean SDKInited = false;
	private long handle = -1;
	private DataSource ds;
	private Cache<String, LoginInfo> cabinetInfo;
	private StatusServer statusServer;
	private Configure conf;
	private ParamsService paramsService;	

	public ICabinetStarter() {
		if (!SDKInited) {
			ICabinetSDK.init();
			SDKInited = true;
		}
		this.cabinetInfo = CacheBuilder.newBuilder().expireAfterWrite(2, TimeUnit.HOURS).build();// 缓存2个小时
		conf = new Configure();		
	}

	public void contextInitialized(ServletContextEvent event) {
		MessageSender sender = new MessageSender(this, this);
		handle = ICabinetSDK.startListen(null, 7200, sender);
		
		ds = (DataSource) ApplicationContextHolder.getBean("dataSource");		
		paramsService = ApplicationContextHolder.getBean(ParamsServiceImpl.class);
		String tMin = paramsService.selectByMHName("icabinet_temperature_range_min").get(0).getValue();
		String tMax = paramsService.selectByMHName("icabinet_temperature_range_max").get(0).getValue();
		String hMin = paramsService.selectByMHName("icabinet_humidity_range_min").get(0).getValue();
		String hMax = paramsService.selectByMHName("icabinet_humidity_range_max").get(0).getValue();
		CabinetRangeParam cabinetParams = new CabinetRangeParam(tMin, tMax, hMin, hMax);		
		
		WorkorderInfoService workorder =  ApplicationContextHolder.getBean(WorkorderInfoService.class);
		
		logger.info("智能机柜报警监控已经启动");
		statusServer = new StatusServer(conf.getPort(), ds, this, cabinetParams, workorder);
		statusServer.init();
		statusServer.start();
		logger.info("机柜信息接收服务已经启动");
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		logger.info("stoping");
		statusServer.stop();
		if (handle > -1 && ICabinetSDK.stopListen(handle))
			logger.info("智能机柜报警监控已经停止");
		else
			logger.error("智能机柜报警监控停止失败");
	}

	/**
	 * 当前设备是否智能机柜
	 * 
	 * @param ip IP
	 */
	@Override
	public boolean isICabinet(String ip) {
		LoginInfo info = getLoginInfoByIP(ip);
		return info != null && StringUtils.isNotEmpty(info.getIp());
	}

	/**
	 * 为新警报获取一个ID
	 */
	@Override
	public String getNextID() {
		return UuidGenerateUtil.getUUIDLong();
	}

	/**
	 * 是否应该抓拍图片
	 */
	@Override
	public boolean shouldCaptureImage(String ip) {
		LoginInfo info = getLoginInfoByIP(ip);
		if (info != null && StringUtils.isNotEmpty(info.getIp()) && info.shouldCaptureImage())
			return true;
		return false;
	}

	/**
	 * 警告信息的交付处理（存盘）
	 */
	@Override
	public void messageDeliver(AlarmInfo msg) {
		if (msg == null) {
			logger.info("AlarmInfo is null");
			return;
		}
		logger.info(msg.getMessage());
		try (Connection conn = ds.getConnection()) {
			msg.save(conn);
			logger.info("机柜报警信息已经保存");
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 取IP的登录信息
	 * 
	 * @param ip
	 * @return 如果存在则表明是机柜设备
	 */
	public LoginInfo getLoginInfoByIP(final String ip) {

		try {
			return cabinetInfo.get(ip, new Callable<LoginInfo>() {
				@Override
				public LoginInfo call() throws Exception {
					LoginInfo loginInfo = new LoginInfo("", "", "");
					logger.info(String.format("查询IP:%s的机柜信息", ip));
					try (Connection conn = ds.getConnection(); PreparedStatement stat = conn.prepareStatement(sql)) {
						stat.setString(1, ip);
						ResultSet rs = stat.executeQuery();
						if (rs.next()) {
							String id = rs.getString(2);
							String sn = rs.getString(3);
							loginInfo = new LoginInfo(ip, id, sn);
						}
						rs.close();
					}
					return loginInfo;
				}
			});
		} catch (ExecutionException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
}
