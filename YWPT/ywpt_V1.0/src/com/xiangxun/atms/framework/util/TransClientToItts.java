package com.xiangxun.atms.framework.util;


import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TransClientToItts {
	
	private Socket client;
	private PrintWriter dos;// 此输出流负责向另一台电脑(服务器端)传输数据
	private BufferedInputStream dis;// 此输入流负责读取另一台电脑的回应信息

	public TransClientToItts() throws UnknownHostException, IOException {
	}
	
	
	//开启套接字连接
	public void openClient(String ip, int port) throws UnknownHostException, IOException {
		client = new Socket(ip, port);// 服务器端的IP
		client.setReuseAddress(true);
		dos = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream(), "UTF-8")), true);
		dis = new BufferedInputStream(client.getInputStream());
	}

	//无回复信息的发送方式
	public boolean ClientStartWithoutReturn(String s) {
		try {
			dos.write(s);
			dos.flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//有回复信息的发送方式
	public String ClientStartWithReturn(String s) {
		try {
			dos.write(s);
			dos.flush();
			byte buffer[] = new byte[1024 * 50];
			int n = dis.read(buffer);
			String temp = new String(buffer, 0, n, "utf-8");
			System.out.println(temp);
			if (s.equals(temp))
				return "";
			else
				return temp;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	//关闭套接字连接
	public void closeClient() throws IOException {
		dos.close();
		dis.close();
		client = null;
	}

	public static void main(String[] args) {

		try {
			TransClientToItts tryTrans = new TransClientToItts();
			tryTrans.openClient("193.169.100.181", 5521);
			String m = "0x\n";
			String returnMessage = tryTrans.ClientStartWithReturn(m);
			System.out.println("收到回执信息:"+returnMessage);
			tryTrans.closeClient();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (ConnectException e){
			System.out.println("连接超时...");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
