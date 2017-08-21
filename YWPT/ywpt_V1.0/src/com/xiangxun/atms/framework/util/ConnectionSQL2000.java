package com.xiangxun.atms.framework.util;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.xiangxun.atms.framework.cache.CacheFactory;


public class ConnectionSQL2000 {
	
	
	/**
	 * 日志 logger
	 */
	public static Logger log = Logger.getLogger(CacheFactory.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ConnectionSQL2000 sql200=new ConnectionSQL2000();
//		sql200.sendMsg();
	}

	private static Properties p = null;
	private static final String SQL = "INSERT INTO tbl_SMSendTask (CreatorID, TaskName, SmSendedNum, OperationType, SuboperationType, SendType,OrgAddr, DestAddr, SM_Content, SendTime, NeedStateReport, ServiceID, FeeType,FeeCode, MsgID, SMType, MessageID, DestAddrType, SubTime, TaskStatus,Sendlevel, Sendstate, TryTimes) " +
			"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	// 打开数据库连接
	private static Connection getConnetion() {
		ConnectionSQL2000 db = new ConnectionSQL2000();
		db.readIni();
		try {
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
			String sql = "jdbc:microsoft:sqlserver://" + p.getProperty("ip")
					+ ":" + p.getProperty("port") + "; DatabaseName="
					+ p.getProperty("instance");
			return DriverManager.getConnection(sql, p.getProperty("username"),
					p.getProperty("password"));
		} catch (ClassNotFoundException e) {
			System.out.println("驱动加载失败...");
			log.error("驱动加载失败..."+ e);

		} catch (SQLException e) {
			System.out.println("数据库连接异常...");
			log.error("数据库连接异常..."+ e);
		}
		return null;
	}

	// 读取配置文件:
	private void readIni(){
	    InputStream in;
		String path = getClass().getResource("/").getPath();
		try {
			in = new BufferedInputStream(new FileInputStream(new File(path+"xiangxun.properties")));
			p = new Properties();
			try {
				p.load(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			log.error("配置文件读取失败...."+ e);
		}
	}
	

	//发送短信接口:
	@SuppressWarnings("static-access")
	public void sendMsg(String moblies,String contents) {
		Connection conn = getConnetion();
		PreparedStatement st=null;
		try {
			st= conn.prepareStatement(SQL);
			st.setInt(1,01 );//CreatorID
			st.setString(2,"" );//TaskName
			st.setInt(3, 0);//SmSendedNum
			st.setString(4, "WAS");// OperationType
			st.setInt(5, 66);// SuboperationType, 
			st.setInt(6, 1);// SendType,	
			st.setString(7, "10657313300507");// OrgAddr, 
			st.setString(8, moblies);// DestAddr, 
			st.setString(9, contents);  //SM_Content, 
			st.setDate(10, new java.sql.Date(new java.util.Date().getTime()));  //SendTime,
			st.setInt(11, 0);  //NeedStateReport
			st.setString(12, "MHB0010100");  //ServiceID
			st.setString(13, "02");  //FeeType
			st.setString(14, "01");  //FeeCode 
			st.setString(15, "10");  //MsgID
			st.setString(16, "");//SMType,
			st.setInt(17, 0);//MessageID,
			st.setString(18, "");  //DestAddrType
			st.setDate(19, new java.sql.Date(new java.util.Date().getTime()));  //SubTime
			st.setInt(20, 0);//TaskStatus
			st.setInt(21, 0);//Sendlevel
			st.setInt(22, 0);////Sendstate, 
			st.setString(23, "3");//TryTimes
			st.execute();
		} catch (SQLException e) {
			log.error("短信发送..插入运营商网关时发生异常..."+ e);
		} finally {
			this.closeConnection(conn, st);
		}

	}
	
	
	// 关闭连接
	public static void closeConnection(Connection conn, PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				log.error("短信发送PreparedStatement关闭异常..."+ e);
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				log.error("短信发送Connection关闭异常..." + e);
			}
		}
	}
	
}
