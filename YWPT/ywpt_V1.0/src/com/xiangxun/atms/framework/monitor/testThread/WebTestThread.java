package com.xiangxun.atms.framework.monitor.testThread;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.xiangxun.atms.framework.monitor.common.UrlResource;
import com.xiangxun.atms.framework.monitor.conf.Dictionary;
import com.xiangxun.atms.framework.monitor.control.DataPushServer;
import com.xiangxun.atms.framework.monitor.vo.AlarmLog;
import com.xiangxun.xml.root.status.DatabaseStatus;
import com.xiangxun.xml.root.status.WebStatus;

public class WebTestThread extends Thread {
	
	String queryWebSql = "select t.id,t.name,t.ip,t.port,t.url,T.FACTORY_ID from PROPERTY_PROJECT_INFO t";
	
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
					rs=sm.executeQuery(queryWebSql);
					while(rs.next()){
						WebStatus ws = new WebStatus();
						ws.setId(rs.getString(1));
						ws.setWebname(rs.getString(2));
						ws.setIp(rs.getString(3));
						ws.setPort(rs.getString(4));
						ws.setRoot(rs.getString(5));
						Dictionary.WebStatusMap.put(ws.getKey(),ws);		
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
			
			Iterator<Entry<String, WebStatus>> webIterator = Dictionary.WebStatusMap.entrySet().iterator();
			while(webIterator.hasNext()){
				Map.Entry<String, WebStatus> entry = (Map.Entry<String, WebStatus>)webIterator.next();
				WebStatus webStatus = entry.getValue();
				try {
					UrlResource.getUrlDetail(entry.getKey(),true);
					webStatus.setStatus("ok");
				} catch (Exception e) {
					//System.out.println("--------------web----Exception---------------------");
					//e.printStackTrace();
					//System.out.println("--------------web----Exception---------------------");
					AlarmLog alarmLog = new AlarmLog();
					alarmLog.setDEVICE_NAME(webStatus.getWebname());
					alarmLog.setDEVICE_CODE(webStatus.getId());
					alarmLog.setDEVICE_IP(webStatus.getIp());
					alarmLog.setDEVICE_TYPE("project");
					//（0-场外；1-场内。）
					alarmLog.setIS_OUTER("1");
					alarmLog.setEVENT_TYPE("1006");
					DataPushServer.addMessage(alarmLog);
					webStatus.setStatus("error");
				}
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
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
