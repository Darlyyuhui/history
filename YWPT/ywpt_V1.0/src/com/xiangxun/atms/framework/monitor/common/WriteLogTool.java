package com.xiangxun.atms.framework.monitor.common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 书写日志信息到指定的文件中
 */
public class WriteLogTool{

	private static String rootPath = "SYSTEM_LOG";
	
	/**
	 * 将信息写到文件中
	 * @param msg
	 */
	public static void writeMsgToFile(String msg) {
		
		System.out.println(msg);
		
		DateFormat date = new SimpleDateFormat("yyyy-MM-dd");	
		DateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String processName = java.lang.management.ManagementFactory.getRuntimeMXBean().getName(); 
        String processID = processName.substring(0,processName.indexOf('@')); 
        
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(getFileName(date.format(new Date())+".log"),true);
			fileWriter.write("ID "+processID+"\t | INFO   | " + time.format(new Date()) + " | " + msg + "\r\n");
			fileWriter.flush();
		} catch (IOException e) {
			System.out.println("### 写日志到文件异常 ### >>> " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("### 写日志到文件异常 ### >>> " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("### 关闭写日志的流异常 ### >>> " + e.getMessage());
				e.printStackTrace();
			}
		}	
	}
	
	/**
	 * 获取要保存的文件
	 * @return fileName
	 */
	private static String getFileName(String fileName) {
		
		//创建目录
		File folder = new File(rootPath);
		if(!folder.exists()) {
			folder.mkdir();
		}
		//创建文件
		File file = new File(rootPath+"/"+fileName);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("### 新建日志文件异常 ### >>> " + e.getMessage());
				e.printStackTrace();
			}
		}
		
		fileName = rootPath + "/" + fileName;
		
		return fileName;
	}
}