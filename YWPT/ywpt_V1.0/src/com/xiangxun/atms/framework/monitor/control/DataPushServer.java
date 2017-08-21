package com.xiangxun.atms.framework.monitor.control;

import java.util.LinkedList;
import java.util.List;

import com.xiangxun.atms.framework.monitor.testThread.StatusSendThread;
import com.xiangxun.atms.framework.monitor.vo.AlarmLog;

//专门负责向消息中间件推送异常告警信息 一份通过StatusSendThread入库 一份给消息服务
public class DataPushServer extends Thread{

	
	// 需要推送的报警信息
	private static List<AlarmLog> queueList = new LinkedList<AlarmLog>();
	
	private static boolean serverStatus = false; //状态

	public DataPushServer() {
	}

	public void startServer(){
		serverStatus = true;
		this.start();
	}
	
	public void stopServer() {
		serverStatus = false;
	}
	
	// 给数据调用的接口
	public static void addMessage(AlarmLog dataForPushTemp) {
		StatusSendThread.addMessage(dataForPushTemp);
		synchronized (queueList) {	
			queueList.add(dataForPushTemp);
			queueList.notifyAll();			
		}
	}
	
	public void run() {
		while(serverStatus){
			synchronized (queueList) {
				while(!queueList.isEmpty()) {											
					AlarmLog dataForPushTemp = queueList.remove(0);
					// 向所有客户端发送数据  暂时不实现
					if(dataForPushTemp!=null){
						
					}
				}	
				
				try {
					queueList.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
