package com.xiangxun.atms.framework.monitor.conf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import com.xiangxun.xml.root.status.*;

public class Dictionary {
	
	public static ConcurrentHashMap<String,FtpStatus> FtpStatusMap = new ConcurrentHashMap<String,FtpStatus>();	
	public static ConcurrentHashMap<String,WebStatus> WebStatusMap = new ConcurrentHashMap<String,WebStatus>();
	public static ConcurrentHashMap<String,DatabaseStatus> DatabaseStatusMap = new ConcurrentHashMap<String,DatabaseStatus>();
	
	public static DatabaseStatus databaseStatus;
	
	private String classpath;
	
	public Dictionary(){
		classpath = Dictionary.class.getClassLoader().getResource("/").getPath() + File.separator;
	}
	
	public void InitDbConfig() throws FileNotFoundException,IOException {	
		InputStreamReader isr = new InputStreamReader(new FileInputStream(new File(classpath+"database.ini")), "gb2312");
		BufferedReader bw = new BufferedReader(isr);
		String temp;
		String infor[];	
		while ((temp = bw.readLine()) != null) {			
			if(temp.length()>0){
				if(temp.charAt(0)!='#'){
					// 替换/t为空格
					temp = replace(temp,"\t"," ");
					// 去除多余空格
					temp = temp.replaceAll(" {2,}", " ");
					infor = temp.split(" ");
					if(infor.length==7 && "1".equals(infor[6])){
						databaseStatus = new DatabaseStatus();
						databaseStatus.setType(infor[0]);
						databaseStatus.setIp(infor[1]);
						databaseStatus.setPort(infor[2]);
						databaseStatus.setSid(infor[3]);
						databaseStatus.setUsername(infor[4]);
						databaseStatus.setPassword(infor[5]);
						databaseStatus.setStatus("");
						databaseStatus.setDriverAndFullPath();
						databaseStatus.setIsCenter(infor[6]);
					}
				}
			}
		}
		bw.close();
	}
	
	
	/**
	  * 字符串替换函数
	  * @param from 要替换的字符
	  * @param to 要替换成的目标字符
	  * @param source 要替换的字符串
	  * @return 替换后的字符串
	*/
	public static String replace(String strSource, String strFrom, String strTo) {    
	    if (strSource == null) {        
	    	return null;    
	    }  
	    int i = 0;
	    if ((i = strSource.indexOf(strFrom, i)) >= 0) {
	    	char[] cSrc = strSource.toCharArray();
	    	char[] cTo = strTo.toCharArray();
	    	int len = strFrom.length();  
	    	StringBuffer buf = new StringBuffer(cSrc.length);  
	    	buf.append(cSrc, 0, i).append(cTo);
	    	i += len;    
	    	int j = i;       
	    	while ((i = strSource.indexOf(strFrom, i)) > 0) {  
	    		buf.append(cSrc, j, i - j).append(cTo);   
	    		i += len;  
	    		j = i;        
	    	}        
	    	buf.append(cSrc, j, cSrc.length - j); 
	    	return buf.toString(); 
	    } 
	    return strSource;
	} 
	
	// 纠正所有的文件名错误字符为_  包括字符 / \ : * ? ？ " ” < > | - __ \n
	public static String changError(String error){
		String returnS;
		returnS = Dictionary.replace(error," ","_");
		returnS = Dictionary.replace(returnS,"/","_");
		returnS = Dictionary.replace(returnS,"\\","_");
		returnS = Dictionary.replace(returnS,":","_");
		returnS = Dictionary.replace(returnS,"*","_");
		returnS = Dictionary.replace(returnS,"?","_");
		returnS = Dictionary.replace(returnS,"？","_");
		returnS = Dictionary.replace(returnS,"\"","_");
		returnS = Dictionary.replace(returnS,"“","_");
		returnS = Dictionary.replace(returnS,"<","_");
		returnS = Dictionary.replace(returnS,">","_");
		returnS = Dictionary.replace(returnS,"|","_");	
		returnS = Dictionary.replace(returnS,"-","_");
		returnS = Dictionary.replace(returnS,"__","_");	
		returnS = Dictionary.replace(returnS,"\n","");
		return returnS;
	}
}
