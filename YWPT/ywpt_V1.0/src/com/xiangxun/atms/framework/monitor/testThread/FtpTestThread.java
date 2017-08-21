package com.xiangxun.atms.framework.monitor.testThread;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.xiangxun.atms.framework.monitor.common.FtpUtil;
import com.xiangxun.atms.framework.monitor.conf.Dictionary;
import com.xiangxun.atms.framework.monitor.control.DataPushServer;
import com.xiangxun.atms.framework.monitor.vo.AlarmLog;
import com.xiangxun.xml.root.status.DatabaseStatus;
import com.xiangxun.xml.root.status.FtpStatus;

public class FtpTestThread extends Thread{
	
	String queryFtpSql = "select t.id,t.name,t.ip,t.port,t.username,t.password,t.dirname from PROPERTY_FTP_INFO t";
	
	public void run(){
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		while(true){
			
			ResultSet rs = null;
			Statement sm = null;
			Connection localConn = null;
			
			DatabaseStatus databaseStatus = Dictionary.databaseStatus;
			try{
				if(databaseStatus!=null){
					//加载驱动
					Class.forName(databaseStatus.getDriver());
					//得到连接
					localConn=DriverManager.getConnection(databaseStatus.getFullPath(), databaseStatus.getUsername(), databaseStatus.getPassword());
					sm=localConn.createStatement();
					rs=sm.executeQuery(queryFtpSql);
					while(rs.next()){
						FtpStatus ws = new FtpStatus();
						ws.setId(rs.getString(1));
						ws.setFtpname(rs.getString(2));
						ws.setIp(rs.getString(3));
						ws.setPort(rs.getString(4));
						ws.setUsername(rs.getString(5));
						ws.setPassword(rs.getString(6));
						ws.setDisk(rs.getString(7));
						Dictionary.FtpStatusMap.put(ws.getKey(),ws);		
					}
				}
			}catch(Exception e){
				//本平台数据库异常的话 平台也无法启动 不处理
			}finally{
			    if(rs!=null){
			    	try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			    }
			    if(sm!=null){
			    	try {
			    		sm.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			    }
			    if(localConn!=null){
			    	try {
			    		localConn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			    }
			}
			
			Iterator<Entry<String, FtpStatus>> ftpIterator = Dictionary.FtpStatusMap.entrySet().iterator();
			while(ftpIterator.hasNext()){
				Map.Entry<String, FtpStatus> entry = (Map.Entry<String, FtpStatus>)ftpIterator.next();
				FtpStatus ftpStatus = entry.getValue();
				FtpUtil ftpUtil = new FtpUtil(
						ftpStatus.getIp(),Integer.parseInt(ftpStatus.getPort()),
						ftpStatus.getUsername(),ftpStatus.getPassword());
				boolean flag;
				try {
					flag = ftpUtil.connectServer();
					if(flag){
						ftpStatus.setStatus("ok");
					}else{
						AlarmLog alarmLog = new AlarmLog();
						alarmLog.setDEVICE_NAME(ftpStatus.getFtpname());
						alarmLog.setDEVICE_CODE(ftpStatus.getId());
						alarmLog.setDEVICE_IP(ftpStatus.getIp());
						alarmLog.setDEVICE_TYPE("ftp");
						//（0-场外；1-场内。）
						alarmLog.setIS_OUTER("1");
						alarmLog.setEVENT_TYPE("1005");
						DataPushServer.addMessage(alarmLog);
						ftpStatus.setStatus("error");
					}
				} catch (IOException e1) {
					
				}
				ftpUtil.closeServer();
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.out.print("FTP Connection timed out");
				}
			}
			
			try {
				Thread.sleep(30*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
