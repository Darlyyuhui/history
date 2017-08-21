package com.xiangxun.atms.framework.monitor.nio;

import com.xiangxun.atms.framework.monitor.common.WriteLogTool;


public class OutputListener extends Thread{
	String ip;
	boolean isFinish;
	
	public OutputListener(String ip){
		super();
		this.ip = ip;
	}
	
	public void setFinish(boolean isFinish){
		this.isFinish = isFinish;
	}
	
	public void run(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		if(!isFinish){
			WriteLogTool.writeMsgToFile("客户端地址 "+ip.substring(1, ip.length())+" 通道阻塞");
		}
	}
}
