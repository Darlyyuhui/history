package com.xiangxun.atms.icabinet.sdk;

import java.beans.PropertyVetoException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 测试用例
 * 
 * @author Administrator
 *
 */
public class TestICabinetSDK implements IMessageDeliver,IMessageHelper {
	private static Logger logger = LoggerFactory.getLogger(TestICabinetSDK.class);
	private final ComboPooledDataSource ds = createDS();
	
	public static void main(String[] args) {
		
		TestICabinetSDK sdk= new TestICabinetSDK();
		MessageSender sender = new MessageSender( sdk, sdk);
		try {
			logger.info("starting");
			ICabinetSDK.init();
			long handler = ICabinetSDK.startListen("192.0.0.63", 7200, sender);

			logger.info(String.format("handler:%d", handler));
			TimeUnit.SECONDS.sleep(1000);
			logger.info("stoping");
			if (ICabinetSDK.stopListen(handler))
				logger.info("stopped succed");
			else
				logger.info("stopped failed");	
			sdk.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void close()
	{
		ds.close();
	}
	@Override
	public void messageDeliver(AlarmInfo info) {	
		if (info == null)
			logger.info("info is null");
		logger.info(String.format("ChnanelName:%s %s", info.getChannelName(), info.getMessage()));
		try {
			if (info.getPhoto1() != null) {
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-ddhhmmss");
				String ns = fmt.format(new Date());
				try (FileOutputStream fo = new FileOutputStream(String.format("c:\\imgs\\%s-1.jpg", ns))) {
					fo.write(info.getPhoto1(), 0, info.getPhoto1().length);
				}
			}
			if (info.getPhoto2() != null) {
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-ddhhmmss");
				String ns = fmt.format(new Date());
				try (FileOutputStream fo = new FileOutputStream(String.format("c:\\imgs\\%s-2.jpg", ns))) {
					fo.write(info.getPhoto2(), 0, info.getPhoto2().length);
				} 
			}
		} catch (Exception ex) {
			logger.error(ex.getCause().getMessage());
		}
		try (Connection conn = ds.getConnection()) {
			info.save(conn);
		} catch (SQLException ex) {
			logger.error(ex.getCause().getMessage());
		}
	}	

	public static ComboPooledDataSource createDS() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass("oracle.jdbc.driver.OracleDriver");
			dataSource.setJdbcUrl("jdbc:oracle:thin:@193.169.100.250:1521:ywpt");
			dataSource.setUser("admin");
			dataSource.setPassword("yyww115115");

			dataSource.setMinPoolSize(20);
			dataSource.setMaxPoolSize(50);
			dataSource.setInitialPoolSize(10);
			dataSource.setMaxIdleTime(30);
			dataSource.setAcquireIncrement(5);
			dataSource.setIdleConnectionTestPeriod(18000);
			dataSource.setMaxIdleTime(25000);

		} catch (PropertyVetoException ex) {
			ex.printStackTrace();
		}
		return dataSource;
	}
	@Override
	public String getNextID() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(calendar.getTime());

		StringBuffer u1 = new StringBuffer(formatNumber(calendar.get(Calendar.YEAR), 2, '0'));
		u1.append(formatNumber(calendar.get(Calendar.MONTH) + 1, 2, '0'));
		u1.append(formatNumber(calendar.get(Calendar.DAY_OF_MONTH), 2, '0'));
		u1.append(formatNumber(calendar.get(Calendar.HOUR_OF_DAY), 2, '0'));
		u1.append(formatNumber(calendar.get(Calendar.MINUTE), 2, '0'));
		u1.append(formatNumber(calendar.get(Calendar.SECOND), 2, '0'));
		u1.append(formatNumber(calendar.get(Calendar.MILLISECOND), 3, '0'));

		String u2 = UUID.randomUUID().toString();
		u2 = u2.replaceAll("-", "");
		return u1.toString() + u2.substring(15);
	}

	private  String formatNumber(int number, int destLength, char paddedChar) {
		String oldString = String.valueOf(number);
		StringBuffer newString = new StringBuffer("");
		int oldLength = oldString.length();
		if (oldLength > destLength) {
			newString.append(oldString.substring(oldLength - destLength));
		} else if (oldLength == destLength) {
			newString.append(oldString);
		} else {
			for (int i = 0; i < destLength - oldLength; i++) {
				newString.append(paddedChar);
			}
			newString.append(oldString);
		}
		return newString.toString();
	}

	@Override
	public boolean isICabinet(String sn) {		
		return true;
	}

	@Override
	public boolean shouldCaptureImage(String ip) {
		
		return true;
	}

	@Override
	public LoginInfo getLoginInfoByIP(String ip) {		
		return new LoginInfo(ip,"","");
	}	

	
}
