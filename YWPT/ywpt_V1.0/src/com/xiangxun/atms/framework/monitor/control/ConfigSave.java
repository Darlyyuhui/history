package com.xiangxun.atms.framework.monitor.control;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.xiangxun.atms.framework.monitor.conf.Dictionary;
import com.xiangxun.xml.root.status.DatabaseStatus;
import com.xiangxun.xml.root.status.FtpStatus;
import com.xiangxun.xml.root.status.WebStatus;

public class ConfigSave {
	
	public static void savaDatabase(){
		String temp = "";
		Iterator<Entry<String, DatabaseStatus>> databaseStatusIterator = Dictionary.DatabaseStatusMap.entrySet().iterator();
		while(databaseStatusIterator.hasNext()){
			Map.Entry<String, DatabaseStatus> entry = (Map.Entry<String, DatabaseStatus>)databaseStatusIterator.next();
			DatabaseStatus databaseStatus = (DatabaseStatus)entry.getValue();
			temp += 
				databaseStatus.getType()+"\t"+
				databaseStatus.getIp()+"\t"+
				databaseStatus.getPort()+"\t"+
				databaseStatus.getSid()+"\t"+
				databaseStatus.getUsername()+"\t"+
				databaseStatus.getPassword()+"\t"+
				databaseStatus.getIsCenter()+"\r\n";
		}
		try{
			BufferedWriter dos = new BufferedWriter(new FileWriter("database.ini"));
			dos.write(temp);
			dos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void savaFtp(){
		String temp = "";
		Iterator<Entry<String, FtpStatus>> ftpListIterator = Dictionary.FtpStatusMap.entrySet().iterator();
		while(ftpListIterator.hasNext()){
			Map.Entry<String, FtpStatus> entry = (Map.Entry<String, FtpStatus>)ftpListIterator.next();
			FtpStatus ftpStatus = (FtpStatus)entry.getValue();
			temp += 
				ftpStatus.getType()+"\t"+
				ftpStatus.getIp()+"\t"+
				ftpStatus.getPort()+"\t"+
				ftpStatus.getUsername()+"\t"+
				ftpStatus.getPassword()+"\t"+
				ftpStatus.getDisk()+"\r\n";
		}
		try{
			BufferedWriter dos = new BufferedWriter(new FileWriter("ftp.ini"));
			dos.write(temp);
			dos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void savaWeb(){
		String temp = "";
		Iterator<Entry<String, WebStatus>> webStatusIterator = Dictionary.WebStatusMap.entrySet().iterator();
		while(webStatusIterator.hasNext()){
			Map.Entry<String, WebStatus> entry = (Map.Entry<String, WebStatus>)webStatusIterator.next();
			WebStatus webStatus = (WebStatus)entry.getValue();
			temp += 
				webStatus.getType()+"\t"+
				webStatus.getIp()+"\t"+
				webStatus.getPort()+"\t"+
				webStatus.getRoot()+"\r\n";
		}
		try{
			BufferedWriter dos = new BufferedWriter(new FileWriter("web.ini"));
			dos.write(temp);
			dos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
