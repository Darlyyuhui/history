package com.xiangxun.atms.framework.monitor.control;

import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TransClient {
	
	public static DateFormat dateMills = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
	private Socket client;

	private PrintWriter dos;// 此输出流负责向另一台电脑(服务器端)传输数据
	private BufferedInputStream dis;// 此输入流负责读取另一台电脑的回应信息

	public boolean ClientStartWithoutReturn(String s) {
		System.out.println("ClientStartWithoutReturn="+s);
		try {
			dos.write(s);
			dos.flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String ClientStartWithReturn(String s) {
		try {
			dos.write(s);
			dos.flush();
			byte buffer[] = new byte[1024 * 50];
			int n = dis.read(buffer);
			String temp = new String(buffer, 0, n, "utf-8");
			return temp;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public void openClient(String ip, int port) throws UnknownHostException, IOException {
		System.out.println("openClient="+ip);
		client = new Socket(ip, port);// 服务器端的IP
		client.setSoTimeout(3000);
		client.setReuseAddress(true);
		dos = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream(), "UTF-8")), true);
		dis = new BufferedInputStream(client.getInputStream());
	}

	public void closeClient() throws IOException {
		dos.close();
		dis.close();
		client = null;
	}
}
