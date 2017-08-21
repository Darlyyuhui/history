package com.xiangxun.atms.framework.monitor.testThread;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.xiangxun.atms.framework.monitor.conf.Dictionary;
import com.xiangxun.atms.framework.monitor.control.DataPushServer;
import com.xiangxun.atms.framework.monitor.vo.AlarmLog;
import com.xiangxun.xml.root.status.DatabaseStatus;

public class DatabaseTestThread extends Thread {
	
	
	
	String sql = "SELECT rpad(DBF.TABLESPACE_NAME,40),  "+
			"	lpad(trunc((DBF.TOTALSPACE - DFS.FREESPACE),0)||'M/'|| trunc(DBF.TOTALSPACE,0)||'M',16) ,  "+
			"	lpad(to_char((1 - (DFS.FREESPACE / DBF.TOTALSPACE)) * 100.00,'fm990.00')||'%',8)  "+
			"	FROM (SELECT T.TABLESPACE_NAME,  "+
	        "       SUM(T.BYTES) / 1024 / 1024 TOTALSPACE,  "+
	        "       SUM(T.BLOCKS) TOTALBLOCKS  "+
	        "       FROM DBA_DATA_FILES T  "+
	        "       GROUP BY T.TABLESPACE_NAME) DBF,  "+
	        "       (SELECT TT.TABLESPACE_NAME,  "+
	        "       SUM(TT.BYTES) / 1024 / 1024 FREESPACE,  "+
	        "       SUM(TT.BLOCKS) FREEBLOCKS  "+
	        "       FROM DBA_FREE_SPACE TT  "+
	        "       GROUP BY TT.TABLESPACE_NAME) DFS " +
	        "	WHERE TRIM(DBF.TABLESPACE_NAME) = TRIM(DFS.TABLESPACE_NAME) order by DBF.TABLESPACE_NAME asc" ;
	
	String queryDbSql = "select t.id,t.name,t.dialect,t.ip,t.port,t.username,t.password,T.SID,T.FACTORY_ID from PROPERTY_DATABASE_INFO t";
	
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
					rs=sm.executeQuery(queryDbSql);
					while(rs.next()){
						DatabaseStatus dbs = new DatabaseStatus();
						dbs.setId(rs.getString(1));
						dbs.setDbname(rs.getString(2));
						dbs.setType(rs.getString(3));
						dbs.setIp(rs.getString(4));
						dbs.setPort(rs.getString(5));
						dbs.setUsername(rs.getString(6));
						dbs.setPassword(rs.getString(7));
						dbs.setSid(rs.getString(8));
						dbs.setStatus("");
						dbs.setDriverAndFullPath();
						dbs.setIsCenter("0");
						Dictionary.DatabaseStatusMap.put(dbs.getKey(),dbs);		
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
			
			Iterator<Entry<String, DatabaseStatus>> databaseIterator = Dictionary.DatabaseStatusMap.entrySet().iterator();
			while(databaseIterator.hasNext()){
				Map.Entry<String, DatabaseStatus> entry = (Map.Entry<String, DatabaseStatus>)databaseIterator.next();
				DatabaseStatus dbs = entry.getValue();
				//判断是否是本平台数据库 是则进行列表刷新
				try{
					//加载驱动
					Class.forName(dbs.getDriver());
					//得到连接
					System.out.println("db FullPath==  "+dbs.getFullPath());
					localConn=DriverManager.getConnection(dbs.getFullPath(), dbs.getUsername(), dbs.getPassword());
					sm=localConn.createStatement();
					if("Oracle".equals(dbs.getType())){
						rs=sm.executeQuery(sql);
						dbs.setStatus("ok");
						dbs.spaceList.getList().clear();
						while(rs.next()){
							dbs.spaceList.getList().add(rs.getString(1)+rs.getString(2)+rs.getString(3));
						}
					}
				}catch(Exception e){
					//System.out.println("-------------db-----Exception---------------------");
					/*e.printStackTrace();*/
					System.out.println("-------------db-----Exception---------------------");
					AlarmLog alarmLog = new AlarmLog();
					alarmLog.setDEVICE_NAME(dbs.getDbname());
					alarmLog.setDEVICE_CODE(dbs.getId());
					alarmLog.setDEVICE_IP(dbs.getIp());
					alarmLog.setDEVICE_TYPE("database");
					alarmLog.setIS_OUTER("1");//（0-场外；1-场内）
					alarmLog.setEVENT_TYPE("1004");
					DataPushServer.addMessage(alarmLog);
					dbs.setStatus("error");
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
