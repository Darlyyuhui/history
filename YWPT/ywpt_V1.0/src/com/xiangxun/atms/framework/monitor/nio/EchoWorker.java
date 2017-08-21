package com.xiangxun.atms.framework.monitor.nio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Map.Entry;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.xiangxun.atms.framework.monitor.conf.Dictionary;
import com.xiangxun.atms.framework.monitor.control.DataPushServer;
import com.xiangxun.atms.framework.monitor.testThread.DatabaseTestThread;
import com.xiangxun.atms.framework.monitor.testThread.DeviceTestThread;
import com.xiangxun.atms.framework.monitor.testThread.FtpTestThread;
import com.xiangxun.atms.framework.monitor.testThread.StatusSendThread;
import com.xiangxun.atms.framework.monitor.testThread.WebTestThread;
import com.xiangxun.atms.framework.monitor.vo.DeviceStatus;
import com.xiangxun.atms.framework.monitor.vo.ServerInfo;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.xml.XmlRoot;
import com.xiangxun.xml.root.status.DataInsertDatabaseStatus;
import com.xiangxun.xml.root.status.DatabaseStatus;
import com.xiangxun.xml.root.status.FtpStatus;
import com.xiangxun.xml.root.status.ServerStatus;
import com.xiangxun.xml.root.status.serverStatus.XmlDiskInfo;
import com.xiangxun.xml.root.status.serverStatus.XmlProcessInfo;
import com.xiangxun.xml.root.status.serverStatus.XmlSystemInfo;
import com.xiangxun.xml.root.status.serverStatus.XmlSystemInfoList;


public class EchoWorker extends Observable implements Runnable {
	
	private List<ServerDataEvent> queue = new LinkedList<ServerDataEvent>();
	
	
	public static HashMap<String,Message> messageMap = new HashMap<String,Message>();
	public static HashMap<String,ServerStatus> serverStatusMap = new HashMap<String,ServerStatus>();
	public static ArrayList<DeviceStatus> listDeviceStatus = new ArrayList<DeviceStatus>();
//	public static ArrayList<DataUploadJcbkStatus> listDataUploadJcbkStatus = new ArrayList<DataUploadJcbkStatus>();
	public static ArrayList<DataInsertDatabaseStatus> listDataInsertDatabaseStatus = new ArrayList<DataInsertDatabaseStatus>();
//	public static ArrayList<MiddlewareStatus> listMiddlewareUploadJcbkStatus = new ArrayList<MiddlewareStatus>();
//	public static ArrayList<MiddlewareStatus> listMiddlewareInsertDatabaseStatus = new ArrayList<MiddlewareStatus>();
	
	public static DateFormat day = new SimpleDateFormat("yyyy-MM-dd");
	public static DateFormat mills = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	
	public FtpTestThread ftpTestThread = new FtpTestThread();
	public WebTestThread webTestThread = new WebTestThread();
	public DatabaseTestThread databaseTestThread = new DatabaseTestThread();
	
	public StatusSendThread statusSendThread = new StatusSendThread();
	public DeviceTestThread deviceTestThread = new DeviceTestThread();
	public DataPushServer dataPushServer = new DataPushServer();
	
	public void startTestThread(){
		//报警信息推送服务开启
		dataPushServer.startServer();
		ftpTestThread.start();
		webTestThread.start();
		//数据库服务状态监测
		databaseTestThread.start();
		statusSendThread.start();
		//设备状态监测
		deviceTestThread.start();
	}
	
	public void processData(NioServer server, SocketChannel socket,byte[] data, int count, SelectionKey key) throws IOException{
		// 远程地址
		String ip = socket.socket().getInetAddress().getHostAddress();
		if(ip==null){
			ip="";
		}
		byte[] dataCopy = new byte[count];
		System.arraycopy(data, 0, dataCopy, 0, count);
		String rev="";
		try {
			rev = new String(dataCopy,"utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		// 如果存在
		if(messageMap.containsKey(ip)){
			(messageMap.get(ip)).setXmlTemp(messageMap.get(ip).getXmlTemp() + rev);
		}
		else{
			messageMap.put(ip, new Message());
		}
		
		
		if(messageMap.get(ip).getXmlTemp().endsWith("\n")){
			
			String xml = messageMap.get(ip).getXmlTemp().substring(0, messageMap.get(ip).getXmlTemp().length()-1).trim();
			
			try{
				// xml解析
				XStream xstream = new XStream(new DomDriver("UTF-8",new XmlFriendlyNameCoder("-_","_")));
				xstream.ignoreUnknownElements();
				xstream.autodetectAnnotations(true);
				xstream.alias("root",XmlRoot.class);
				
				// 收到的XML转为对象
				XmlRoot xmlRoot = (XmlRoot)xstream.fromXML(xml);
				
				String type = xmlRoot.getType();
				if(type.charAt(0)=='1'){
					if(xmlRoot.getServerList().getList().size()>0){
						ServerStatus xmlServerStatus = xmlRoot.getServerList().getList().get(0);
						xmlServerStatus.setIp(ip);
						serverStatusMap.put(ip, xmlServerStatus);
						//每个服务器的情况  这一块为保证实时性 在EchoWorker直接实现
						DatabaseStatus databaseStatusTemp = Dictionary.databaseStatus;
						if(databaseStatusTemp!=null){
							XmlSystemInfoList syslist = xmlServerStatus.getSystemInfoList();
							if(syslist!=null){
							//加载驱动
							Class.forName(databaseStatusTemp.getDriver());
							//得到连接
							Connection conn = DriverManager.getConnection(databaseStatusTemp.getFullPath(), databaseStatusTemp.getUsername(), databaseStatusTemp.getPassword());
							Statement statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
							
							//禁止平台录入的服务器资产以外的数据进入
							ResultSet rs = statement.executeQuery("select t.id,t.assetname from PROPERTY_ASSET_INFO t where t.assettype = 'server' and t.ip = '"+ip+"'");
							
							  if(rs.next()){
								  ServerInfo info = new ServerInfo();
								  info.setServerId(rs.getString(1));
								  info.setServerName(rs.getString(2));
								  
								  StringBuffer values = new StringBuffer("");
									StringBuffer column = new StringBuffer("");
									
									column.append("insert into STATUS_SERVER (id,ip,Cpuinfo,Memoryinfo");
									values.append("'"+UuidGenerateUtil.getUUIDLong()+"','"+ip+"','"+xmlServerStatus.getCpuInfo()+"','"+xmlServerStatus.getMemoryInfo()+"'");
									
										List<XmlSystemInfo> sysinfolist = syslist.getList();
										if(sysinfolist!=null&&sysinfolist.size()>0){
											for(int i=0;i<sysinfolist.size();i++){
												XmlSystemInfo sysinfo = sysinfolist.get(i);
												if("主机名".equals(sysinfo.getName())){
													column.append(",computername");
													values.append(",'"+sysinfo.getInfo()+"'");
												}
												if("OS 名称".equals(sysinfo.getName())){
													column.append(",osname");
													values.append(",'"+sysinfo.getInfo()+"'");
												}
												if("OS 版本".equals(sysinfo.getName())){
													column.append(",osversion");
													values.append(",'"+sysinfo.getInfo()+"'");
												}
												if("系统类型".equals(sysinfo.getName())){
													column.append(",ostype");
													values.append(",'"+sysinfo.getInfo()+"'");
												}
												if("处理器".equals(sysinfo.getName())){
													column.append(",cpunumber");
													values.append(",'"+sysinfo.getInfo()+"'");
												}
												if("BIOS 版本".equals(sysinfo.getName())){
													column.append(",biosversion");
													values.append(",'"+sysinfo.getInfo()+"'");
												}
												if("系统区域设置".equals(sysinfo.getName())){
													column.append(",sysareaset");
													values.append(",'"+sysinfo.getInfo()+"'");
												}
												if("输入法区域设置".equals(sysinfo.getName())){
													column.append(",inputareaset");
													values.append(",'"+sysinfo.getInfo()+"'");
												}
												if("物理内存总量".equals(sysinfo.getName())){
													column.append(",hymemoryall");
													values.append(",'"+sysinfo.getInfo()+"'");
												}
												if("可用的物理内存".equals(sysinfo.getName())){
													column.append(",hymemoryfree");
													values.append(",'"+sysinfo.getInfo()+"'");
												}
												if("产品 ID".equals(sysinfo.getName())){
													column.append(",osid");
													values.append(",'"+sysinfo.getInfo()+"'");
												}
												if("注册的所有人".equals(sysinfo.getName())){
													column.append(",osreguser");
													values.append(",'"+sysinfo.getInfo()+"'");
												}
												if("OS 制造商".equals(sysinfo.getName())){
													column.append(",osfactory");
													values.append(",'"+sysinfo.getInfo()+"'");
												}
											}
										}
										column.append(",DISKINFO,DISK_STATUS,CPU_STATUS,MEMORY_STATUS,INSERTTIME,INSERTPC,ASSETID,ASSETNAME) values (");
									
									//硬盘空间
									List<XmlDiskInfo> list = xmlServerStatus.getDiskInfoList().getList();
									String diskInfo = "";
									for(XmlDiskInfo xmlDiskInfo:list){
										diskInfo += xmlDiskInfo.getDiskName()+
										":"+xmlDiskInfo.getUsePercent()+
										":"+xmlDiskInfo.getUsed()+
										":"+xmlDiskInfo.getSize()+",";
									}
									//硬盘状态
									String diskStatus = "1";
									values.append(",'"+diskInfo+"'");
									values.append(",'"+diskStatus+"'");
									//CPU状态
									String cpuStatus = "1";
									float cpuInfo = Float.parseFloat(xmlServerStatus.getCpuInfo());
									if(cpuInfo>90){
										cpuStatus = "3";
									}else if(cpuInfo>60){
										cpuStatus = "2";
									}
									values.append(",'"+cpuStatus+"'");
									//内存状态
									float memoryInfo = Float.parseFloat(xmlServerStatus.getMemoryInfo());
									String memoryStatus = "1";
									if(memoryInfo>90){
										memoryStatus = "3";
									}
									else if(memoryInfo>60){
										memoryStatus = "2";
									}
									values.append(",'"+memoryStatus+"'");
									values.append(",sysdate");
									//来源机器
									InetAddress add = InetAddress.getLocalHost();
									String pcname = add.getHostName().toString();
									values.append(",'"+pcname+"'");
									//对应资产信息
									values.append(",'"+info.getServerId()+"'");
									values.append(",'"+info.getServerName()+"')");
									
									column.append(values);
									statement.execute(column.toString());
									conn.commit();
									
									//进程信息
									String sqlDelProcess = "delete from STATUS_SERVER_PROCESS where  ip = '"+ ip +"'";
									Statement statementdel = conn.createStatement();
									statementdel.execute(sqlDelProcess);
									conn.commit();
									
									List<XmlProcessInfo> processlist = xmlServerStatus.getProcessInfoList().getList();
									String sqlInsertProcess = 
										"insert into STATUS_SERVER_PROCESS (id,name,pid,talkname,memory,userstr,ip,inserttime) values (?,?,?,?,?,?,?,sysdate)";
									PreparedStatement statementp = conn.prepareStatement(sqlInsertProcess);
									for(XmlProcessInfo process:processlist){
										statementp.setString(1, UuidGenerateUtil.getUUIDLong());
										statementp.setString(2, process.getName());
										statementp.setString(3, process.getPid());
										statementp.setString(4, process.getTalkName());
										statementp.setString(5, process.getMemory());
										statementp.setString(6, process.getUser());
										statementp.setString(7, ip);
										statementp.addBatch();
									}
									statementp.executeBatch();
									conn.commit();
									
									
									rs.close();
									statement.close();
									statementdel.close();
									statementp.close();
									conn.close();
							  }
							
							}
						}
						// 如果传过来的为FTP服务器IP 则确定FTP的所在盘符和硬盘空间
						Iterator<Entry<String, FtpStatus>> ftpListIterator = Dictionary.FtpStatusMap.entrySet().iterator();
						while(ftpListIterator.hasNext()){
							Map.Entry<String, FtpStatus> entry = (Map.Entry<String, FtpStatus>)ftpListIterator.next();
							for(XmlDiskInfo xmlDiskInfo:xmlServerStatus.getDiskInfoList().getList()){
								if(entry.getValue().ip.equals(ip)&&xmlDiskInfo.getDiskName().startsWith(entry.getValue().disk)){
									entry.getValue().space = xmlDiskInfo.getUsePercent();
								}
							}
						}
					}
				}
//				if(type.charAt(4)=='1'){
//					System.out.println("收到缉查布控上传信息");
//					if(xmlRoot.getDataUploadJcbkStatusList().getList()!=null){
//						for(DataUploadJcbkStatus dataUploadJcbkStatus:xmlRoot.getDataUploadJcbkStatusList().getList()){
//							listDataUploadJcbkStatus.add(dataUploadJcbkStatus);
//						}
//					}
//				}
				if(type.charAt(5)=='1'){
					System.out.println("收到平台数据上传信息");
					//System.out.println("收到的XML信息："+xml);
					if(xmlRoot.getDataInsertDatabaseStatusList().getList()!=null){
						for(DataInsertDatabaseStatus dataInsertDatabaseStatus:xmlRoot.getDataInsertDatabaseStatusList().getList()){
							System.out.println("-getDevice--"+dataInsertDatabaseStatus.getDevice()+"--getLastTime--"+dataInsertDatabaseStatus.getLastTime());
							listDataInsertDatabaseStatus.add(dataInsertDatabaseStatus);
						}
					}
				}
//				if(type.charAt(6)=='1'){
//					System.out.println("收到缉查布控中间件状态信息");
//					listMiddlewareUploadJcbkStatus.clear();
//					if(xmlRoot.getMiddlewareUploadJcbkStatusList().getList()!=null){
//						for(int i=0;i<xmlRoot.getMiddlewareUploadJcbkStatusList().getList().size();i++){
//							MiddlewareStatus mddlewareStatus = xmlRoot.getMiddlewareUploadJcbkStatusList().getList().get(i);
//							listMiddlewareUploadJcbkStatus.add(mddlewareStatus);
//						}
//					}
//				}
//				if(type.charAt(7)=='1'){
//					System.out.println("收到平台中间件状态信息");
//					listMiddlewareInsertDatabaseStatus.clear();
//					if(xmlRoot.getMiddlewareInsertDatabaseStatusList().getList()!=null){
//						for(int i=0;i<xmlRoot.getMiddlewareInsertDatabaseStatusList().getList().size();i++){
//							MiddlewareStatus mddlewareStatus = xmlRoot.getMiddlewareInsertDatabaseStatusList().getList().get(i);
//							listMiddlewareInsertDatabaseStatus.add(mddlewareStatus);
//						}
//					}
//				}
				
				messageMap.get(ip).setXml(xml);
				messageMap.get(ip).setXmlTemp("");
			}catch(Exception e){
				messageMap.get(ip).setXmlTemp("");
			}
		}
		this.addQueue( server, socket, ("ok"+"\n").getBytes("utf-8"),true);
	}

	private void addQueue(NioServer server, SocketChannel socket, byte[] bytes,boolean isError) {
		synchronized (this.queue) {	
			this.queue.add(new ServerDataEvent(server, socket, bytes));
			this.queue.notify();
		}
	}

	// 处理写事件
	public void run() {
		ServerDataEvent dataEvent;
		while (true) {
			synchronized (queue) {
				while (queue.isEmpty()) {
					try {
						queue.wait();
					} catch (InterruptedException e) {
					}
				}
				dataEvent = queue.remove(0);
			}
			dataEvent.server.send(dataEvent.socket, dataEvent.data);
		}
	}
}
