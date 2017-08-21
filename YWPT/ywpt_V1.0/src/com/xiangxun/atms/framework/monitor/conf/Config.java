package com.xiangxun.atms.framework.monitor.conf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import com.xiangxun.atms.common.deptment.web.DepartmentCtl;



public class Config {
	
	public static String ver="v1.0"; //版本
	
	public static boolean isRun=false; //程序是否在运行 
	
	//端口是否可用
	public static boolean isPortCanUse=false;
	
	// 工作端口号
	public static int socketPort=5111;
	
	// 广播端口号
	public static int broadcastPortForClient=51110;
	public static int broadcastPortForTomcat=51111;
	
	public static int broadcastPortForOtherBegin=51112;
	public static int broadcastPortForOtherEnd=51130;
	
	
	
	// 设置配置文件
	public void setFileConfig() throws IOException{
		
//		String path = new File(this.getClass().getResource("/").getPath()).toString();
		String classPath = Config.class.getClassLoader().getResource("/").getPath();
//		URL resource = this.getClass().getClassLoader().getResource("");
//		String rootPath = "";
//		//windows下
//		if("\\".equals(File.separator)){
//		rootPath = classPath.substring(1,classPath.indexOf("/WEB-INF/classes"));
//		rootPath = rootPath.replace("/", "\\");
//		}
//		   File directory = new File("");// 参数为空
//	       String courseFile = directory.getCanonicalPath();
//	       String property = System.getProperty("user.dir");
//	       
	       
		
		BufferedReader in= new BufferedReader(new FileReader(classPath+File.separator+"config.ini"));
		Properties properties = new Properties();
		properties.load(in);	
		
		// 工作端口号
		socketPort = Integer.parseInt(properties.getProperty("socketPort").trim());
		// 广播端口号
		broadcastPortForClient = Integer.parseInt(properties.getProperty("broadcastPortForClient").trim());
		broadcastPortForTomcat = Integer.parseInt(properties.getProperty("broadcastPortForTomcat").trim());
		broadcastPortForOtherBegin = Integer.parseInt(properties.getProperty("broadcastPortForOtherBegin").trim());
		broadcastPortForOtherEnd = Integer.parseInt(properties.getProperty("broadcastPortForOtherEnd").trim());
	}
}