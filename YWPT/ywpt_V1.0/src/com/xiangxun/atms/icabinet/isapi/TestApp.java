package com.xiangxun.atms.icabinet.isapi;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.xiangxun.atms.icabinet.tools.ICabinetHelper;
import com.xiangxun.atms.icabinet.tools.ICabinetOption;

public class TestApp {
	private static final Logger logger = LoggerFactory.getLogger(TestApp.class);

	public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
		ICabinetOption op0 = new ICabinetOption("192.168.18.13", "2", "ICabinet2", "");
		ICabinetOption op1 = new ICabinetOption("192.168.18.14", "2", "ICabinet2", "");
		ICabinetOption op2 = new ICabinetOption("192.168.18.130", "2", "ICabinet2", "192.0.0.63");
		ICabinetOption op3 = new ICabinetOption("192.168.60.125", "2", "ICabinet2", "192.0.0.63");
		//ICabinetOption op4 = new ICabinetOption("193.169.100.152", "2", "ICabinet2", "192.0.0.63");
		ICabinetOption op4 = new ICabinetOption("192.0.0.64", "2", "ICabinet2", "192.0.0.63");
		ComboPooledDataSource ds = createDS();
		String execNo = new ICabinetHelper(ds).start(new ICabinetOption[] {  op0,op1,op2,op3,op4 });
		logger.info("execno:" + execNo);		
		TimeUnit.SECONDS.sleep(200);
		ds.close();
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
}
