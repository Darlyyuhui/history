package com.xiangxun.atms.framework.monitor;

import java.io.IOException;
import java.net.UnknownHostException;

import com.xiangxun.atms.framework.monitor.common.WriteLogTool;
import com.xiangxun.atms.framework.monitor.conf.Config;
import com.xiangxun.atms.framework.monitor.conf.Dictionary;
import com.xiangxun.atms.framework.monitor.control.BroadcastSender;
import com.xiangxun.atms.framework.monitor.control.TransClient;
import com.xiangxun.atms.framework.monitor.nio.EchoWorker;
import com.xiangxun.atms.framework.monitor.nio.NioServer;

public class MonitorMain {
	
	
	public static void init() {	
		
		try{
			// 初始化参数
			Config conf = new Config();
			conf.setFileConfig();	
		}catch(NullPointerException e) {
			e.printStackTrace();
			javax.swing.JOptionPane.showMessageDialog(null,"config.ini 参数异常！");
			System.exit(0);
		}catch(IOException e){
			e.printStackTrace();
			javax.swing.JOptionPane.showMessageDialog(null,"config.ini 读取异常！");
			System.exit(0);
		}
		
		try{
			// 初始化参数
			Dictionary dic = new Dictionary();
			//初始化要监控的对象
			dic.InitDbConfig();
		}catch(NullPointerException e) {
			e.printStackTrace();
			javax.swing.JOptionPane.showMessageDialog(null,"服务器配置参数异常！");
			System.exit(0);
		}catch(IOException e){
			e.printStackTrace();
			javax.swing.JOptionPane.showMessageDialog(null,"服务器配置参数异常！");
			System.exit(0);
		}
		
		try {
			TransClient tryTrans = new TransClient();
			tryTrans.openClient("127.0.0.1", Config.socketPort);
			tryTrans.closeClient();
			
			System.out.println("错误！程序检测到服务已经启动！关闭程序！");
			javax.swing.JOptionPane.showMessageDialog(null,"程序检测到服务已经启动！\n您不能同时启动多个服务！");							
			System.exit(0);
		} catch (UnknownHostException e1) {
		} catch (IOException e1) {
		}
		
		
		try{
			EchoWorker worker = new EchoWorker();
			NioServer nioServer = new NioServer(null, Config.socketPort, worker);
			nioServer.checkPort();
			Config.isRun = true;
			new Thread(worker).start();
			new Thread(nioServer).start();
			worker.startTestThread();
			Config.isPortCanUse = true;
		}catch(IOException e){
			System.out.println("错误！监听端口"+Config.socketPort+"失败！关闭程序！");
			javax.swing.JOptionPane.showMessageDialog(null,"错误！监听端口"+Config.socketPort+"失败！关闭程序！");							
			System.exit(0);
		}
		
		Thread broadcastThread = new Thread(){
			public void run() {
				while(true){
					try {
						BroadcastSender.sendPort();
					} catch (IOException e) {
						e.printStackTrace();
					}	
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
					}
				}
			}
		};
		broadcastThread.start();
		
		while(true){
			if(Config.isPortCanUse){
				WriteLogTool.writeMsgToFile("-------------------------------------------------------------------------------");
				WriteLogTool.writeMsgToFile("Server Start");
				Config.isRun = true;
			}
			if(Config.isRun){
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}

	
}
