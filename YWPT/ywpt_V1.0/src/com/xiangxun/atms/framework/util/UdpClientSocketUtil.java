package com.xiangxun.atms.framework.util;


import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;



/**
 * UDP客户端程序，用于对服务端发送数据，并接收服务端的回应信息.
 * 创建人：cd   
 */
public final class UdpClientSocketUtil {
	private DatagramSocket ds = null;
	private static int DELAY=2000;//判断超时的操作
	private byte[] buffer = new byte[1024];
	/**
	 * 构造函数，创建UDP客户端
	 * 
	 * @throws Exception
	 */
	public UdpClientSocketUtil() throws Exception {
		ds = new DatagramSocket();
	}

	/**
	 * 
	 * @param timeout
	 *            超时时间
	 * @throws Exception
	 */
	public final void setSoTimeout(final int timeout) throws Exception {
		ds.setSoTimeout(timeout);
	}

	/**
	 * 获得超时时间.
	 * 
	 * @return 返回超时时间
	 * @throws Exception
	 */
	public final int getSoTimeout() throws Exception {
		return ds.getSoTimeout();
	}

	public final DatagramSocket getSocket() {
		return ds;
	}

	/**
	 * 向指定的服务端发送数据信息.
	 * 
	 * @param host
	 *            服务器主机地址
	 * @param port
	 *            服务端端口
	 * @param bytes
	 *            发送的数据信息
	 * @return 返回构造后俄数据报
	 * @throws IOException
	 */
	public final DatagramPacket send(final String host, final int port, final byte[] bytes) throws IOException {
		DatagramPacket dp = new DatagramPacket(bytes, bytes.length, InetAddress.getByName(host), port);
		ds.send(dp);
		return dp;
	}

	/**
	 * 接收从指定的服务端发回的数据.
	 * 
	 * @param lhost
	 *            服务端主机
	 * @param lport
	 *            服务端端口
	 * @return 返回从指定的服务端发回的数据.
	 * @throws Exception
	 */
	public final String receive(final String lhost, final int lport) throws Exception {
		DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
		//封装超时操作
		setSoTimeout(DELAY);
		ds.receive(dp);
		String info = new String(dp.getData(), 0, dp.getLength());
		return info;
	}

	/**
	 * 关闭udp连接.
	 */
	public final void close() {
		try {
			ds.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 由于UDP的MTU限制,在局域网中的使用要求command不能超过1400字节
	 * @param remoteIp udp服务器的ip地址
	 * @param remotePort udp服务器的端口号
	 * @param command   需要对UDP服务器发送的命令
	 * @return   success：发送成功;fail:连接超时
	 * @throws Exception
	 */
	public String sendCommandInfo(String remoteIp,int remotePort,String command) throws Exception{	
		//UDP有MTU限制
		if(command.getBytes().length>1400){
			return "command不能超过1400字节";
		}
		UdpClientSocketUtil client = new UdpClientSocketUtil();
		int tries = 0;
		int MAXTRIES = 1;
		boolean receivedResponse = false;
		String info = null;
		do {
			//通过UDP发送数据操作：
			client.send(remoteIp, remotePort, command.getBytes());
			try {
				//接受客户端的回执操作:
				info = client.receive(remoteIp, remotePort);
				receivedResponse = true;
			} catch (InterruptedIOException e) {
				
				//超时重新尝试连接
				tries += 1;
				System.out.println("系统第"+tries+"次尝试跟"+remoteIp+"连接");
			}
		} while ((!receivedResponse) && (tries < MAXTRIES));

		// 根据是否接收到报文进行反馈
		if (receivedResponse) {
			info="success";
		    } else {
		
			info="fail";
		}

		if(client!=null){
			// 5. 关闭socket
			client.close();	
		}
		return info;
	}
	/**
	 * 测试客户端发包和接收回应信息的方法.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
//		StringBuffer buffer = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//		buffer.append("<root>");
//		buffer.append("<body>");
//		buffer.append("<ver>1.0</ver>");
//		buffer.append("<company>3</company>");//1	海康	海康IP摄像头（类型编码固定为1，不要修改）
//		buffer.append("<command>1</command>");//js 文件
//		buffer.append("<deviceIp>193.169.100.249</deviceIp>");//摄像头的ip地址
//		buffer.append("<rtspName>map4:610000610000050025</rtspName>");//设备编号
//		buffer.append("<userName></userName>");//监控访问的用户名
//		buffer.append("<passWord></passWord>");//监控访问的密码
//		buffer.append("<rtspIp>193.169.100.205</rtspIp>");//流媒体服务器
//	    buffer.append("<rtspPort>1935</rtspPort>");//流媒体端口号
//		buffer.append("</body>");
//		buffer.append("</root>");
//		//页面访问字段：rtmp://193.169.100.200/live/设备号
//		System.out.println(buffer.toString().getBytes().length);
//		//"127.0.0.1",9000 做成配置文件
//		UdpClientSocketUtil socket=new UdpClientSocketUtil();
//		String result=socket.sendCommandInfo("193.169.100.205",9000,buffer.toString());
//		System.out.println(result);
	
		StringBuffer buffer = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		buffer.append("<root>");
		buffer.append("<body>");
		buffer.append("<ver>1.0</ver>");
		buffer.append("<company>6</company>");//1	海康	海康IP摄像头（类型编码固定为1，不要修改）
		buffer.append("<command>1</command>");//js 文件
		buffer.append("<deviceIp>114.112.48.73;master.4006043110.com</deviceIp>");//摄像头的ip地址
		buffer.append("<rtspName>7</rtspName>");//设备编号
		buffer.append("<userName>szadmin</userName>");//监控访问的用户名
		buffer.append("<passWord>123456</passWord>");//监控访问的密码
		buffer.append("<rtspIp>193.169.100.205</rtspIp>");//流媒体服务器
	    buffer.append("<rtspPort>1935</rtspPort>");//流媒体端口号
	    buffer.append("<remoteip>114.112.48.67</remoteip>");//设备ip地址
	    buffer.append("<remoteport>9111</remoteport>");//设备端口号
	    buffer.append("<cameraid>S1305090221</cameraid>");//设备id
		buffer.append("</body>");
		buffer.append("</root>");
		//页面访问字段：rtmp://193.169.100.200/live/设备号
		System.out.println(buffer.toString().getBytes().length);
		//"127.0.0.1",9000 做成配置文件
		UdpClientSocketUtil socket=new UdpClientSocketUtil();
		String result=socket.sendCommandInfo("193.169.100.205",9000,buffer.toString());
		System.out.println(result);
		
//		StringBuffer buffer = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//		buffer.append("<root>");
//		buffer.append("<body>");
//		buffer.append("<ver>1.0</ver>");
//		buffer.append("<userName>demo</userName>");
//		buffer.append("<passWord>demo</passWord>");
//		buffer.append("<ip>117.34.22.148</ip>");
//		buffer.append("<port>8006</port>");
//		buffer.append("<rtspIp>193.169.100.205</rtspIp>");
//	    buffer.append("<rtspPort>1935</rtspPort>");
//		buffer.append("</body>");
//		buffer.append("</root>");
//		System.out.println("视频配置文件xml:"+buffer.toString());
//		
//		//页面访问字段：rtmp://193.169.100.200/live/设备号
//		System.out.println(buffer.toString().getBytes().length);
//		//"127.0.0.1",9000 做成配置文件
//		UdpClientSocketUtil socket=new UdpClientSocketUtil();
//		String result=socket.sendCommandInfo("193.169.100.205",9000,buffer.toString());
//		System.out.println(result);
	}
}
