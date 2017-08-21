package com.xiangxun.atms.framework.monitor.nio;

import java.nio.channels.SocketChannel;

class ServerDataEvent {
	public NioServer server;// 服务器
	public SocketChannel socket;// 对应的每个客服端的通道,貌似该这样解释,在写回数据的时候非常重要
	public byte[] data;// 接受到的数据
    public String returnData;
	public String getReturnData() {
		return returnData;
	}
	public void setReturnData(String returnData) {
		this.returnData = returnData;
	}
	public ServerDataEvent(NioServer server, SocketChannel socket, byte[] data) {
		this.server = server;
		this.socket = socket;
		this.data = data;		
	}
}