package com.xiangxun.atms.framework.monitor.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.xiangxun.atms.framework.monitor.conf.Config;
import com.xiangxun.atms.framework.monitor.common.WriteLogTool;

public class NioServer implements Runnable {
	private InetAddress hostAddress;
	private int port;
	private static ServerSocketChannel serverChannel;	
	private static Selector selector;
	private ByteBuffer readBuffer = ByteBuffer.allocate(819200);
	private EchoWorker worker;

	DateFormat dateMills = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
	
	//channel的变化
	private List<ChangeRequest> pendingChanges = new LinkedList<ChangeRequest>();
	//保存每一个要回显客户端数据
	private Map<SocketChannel,List<ByteBuffer>> pendingData = new HashMap<SocketChannel,List<ByteBuffer>>();
	
	public NioServer(InetAddress hostAddress, int port, EchoWorker worker){
		this.hostAddress = hostAddress;
		this.port = port;
		this.worker = worker; 
	}
	
	public void checkPort() throws IOException {
		//调用初始化函数
		selector = this.initSelector();
	}

	public void send(SocketChannel socket, byte[] data) {	
		synchronized (this.pendingChanges) {
			//说明感兴趣的事情
			this.pendingChanges.add(new ChangeRequest(socket, ChangeRequest.CHANGEOPS, SelectionKey.OP_WRITE));
			// 对将要写的数据进行排队
			synchronized (this.pendingData) {
				List<ByteBuffer> queue =  this.pendingData.get(socket);
				if (queue == null) {
					queue = new ArrayList<ByteBuffer>();
					this.pendingData.put(socket, queue);
				}
				queue.add(ByteBuffer.wrap(data));
			}
		}
		selector.wakeup();//唤醒的实质是希望主线程去执行写事件
	}

	public void run() {
		while (Config.isRun) {
				try {
					// 是否有等待处理的事件
					synchronized (this.pendingChanges) {
						Iterator<ChangeRequest> changes = this.pendingChanges.iterator();
						while (changes.hasNext()) {
							ChangeRequest change = (ChangeRequest) changes.next();
							switch (change.type) {
							// 放弃改变的状态
							case ChangeRequest.CHANGEOPS:
								try {
									SelectionKey key = change.socket.keyFor(selector);// 获取表示通道向给定选择器注册的键(主要目的是获取对应客户端的socket的channel)
									
									// 如果端口没有打开，则直接关闭
									if(!change.socket.isOpen()){
										try{
											String ip = change.socket.socket().getInetAddress().toString();							
											//WriteLogTool.writeMsgToFile("强制关闭非正常关闭的端口\t来自客户端 "+ip.substring(1, ip.length()));
										} catch (NullPointerException e) {
											//WriteLogTool.writeMsgToFile("强制关闭非正常关闭的端口\t无法定位客户端地址");
										}
										change.socket.close();
										continue;
									}
									key.interestOps(change.ops);// 实质上是注册写的事件,OP_WRITE,只能在等待写的时候才能对wrire有兴趣
								} catch (Exception e) {
									e.printStackTrace();
									continue;
								}
								break;
							}							
						}
						this.pendingChanges.clear();
					}				
					// 等待已经注册过的channel的事件发生
					selector.select();
					// 查找有兴趣的key
					Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
					while (selectedKeys.hasNext()) {
						if(Config.isRun){
							SelectionKey key = (SelectionKey) selectedKeys.next();
							selectedKeys.remove();
							if (!key.isValid()) {
								continue;
							}
							// 处理接受
							if (key.isAcceptable()) {
								this.accept(key);
							// 处理读
							} else if (key.isReadable()) {
								this.read(key);
							// 处理写
							} else if (key.isWritable()) {
								this.write(key);
							}
						}
					}
				} catch (Exception e) {
					if (Config.isRun) {
						WriteLogTool.writeMsgToFile("系统发生异常:" + e.getMessage());
						e.printStackTrace();
						continue;
					}
					else{
						WriteLogTool.writeMsgToFile("系统发生异常:" + e.getMessage());
						e.printStackTrace();
						break;
					}			
				}
		}
	}

	private void accept(SelectionKey key) throws IOException {	
		ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
		SocketChannel socketChannel = serverSocketChannel.accept();
		/*
		String nowTime =  dateMills.format(new Date());	
		String ip = socketChannel.socket().getInetAddress().getHostAddress();
		WriteLogTool.writeMsgToFile("welcome host: "+ip+" "+nowTime);
		*/
		socketChannel.configureBlocking(false);
		//千万不能在这里注册写事件,只能对读有兴趣
		socketChannel.register(selector, SelectionKey.OP_READ);
	}

	private void read(SelectionKey key) throws IOException {
		SocketChannel socketChannel = (SocketChannel) key.channel();
		this.readBuffer.clear();
		int numRead;
		try {
			numRead = socketChannel.read(this.readBuffer);
		} catch (IOException e) {		
			
			//e.printStackTrace();
			
			/*try{
				String ip = socketChannel.socket().getInetAddress().toString();							
				WriteLogTool.writeMsgToFile("sorket通道通信异常\t来自客户端 "+ip.substring(1, ip.length()));
			} catch (NullPointerException e1) {
				WriteLogTool.writeMsgToFile("sorket通道通信异常\t无法定位客户端地址");
			}
			WriteLogTool.writeMsgToFile("sorket通道异常信息\t"+e.getMessage());*/
			
			//如果远端关闭或者发生了异常
			key.cancel();
			socketChannel.close();
			return;
		}
		if (numRead == -1) {
			/*try{
				String ip = socketChannel.socket().getInetAddress().toString();							
				//WriteLogTool.writeMsgToFile("sorket通道正常，但未见数据发送\t来自客户端 "+ip.substring(1, ip.length()));
			} catch (NullPointerException e1) {
				//WriteLogTool.writeMsgToFile("sorket通道正常，但未见数据发送\t无法定位客户端地址");
			}*/
			//如果远端关闭或者读完
			key.channel().close();
			key.cancel();
			return;
		}
		// 把读的数据交给工作线程	
		this.worker.processData(this, socketChannel, this.readBuffer.array(), numRead, key);
	}

	//写数据是一个永恒的事情
	private void write(SelectionKey key) throws IOException {
		SocketChannel socketChannel = (SocketChannel) key.channel();
		//String rs=(String)key.attachment();//可以从信道读取传过来的数据		
		synchronized (this.pendingData) {
			String ip = socketChannel.socket().getInetAddress().toString();	
			List<ByteBuffer> queue =  this.pendingData.get(socketChannel);
			if(socketChannel.isConnected()&&socketChannel.isOpen()){
				while (!queue.isEmpty()) {	
					ByteBuffer buf = (ByteBuffer) queue.get(0);
					try {						
						OutputListener testOutput = new OutputListener(ip);
						testOutput.setFinish(false);
						testOutput.start();
						int len=socketChannel.write(buf);
						
	     	            //如果网络情况很糟糕
						if(len==0){	
							testOutput.setFinish(false);
							key.interestOps(key.interestOps()|SelectionKey.OP_WRITE);
							selector.wakeup();//唤醒主线程
							socketChannel.close();
							break;   			
						}
						if (buf.remaining() > 0) {
							testOutput.setFinish(false);
							socketChannel.close();
							continue;  
						}
						
						testOutput.setFinish(true);
					} catch (Exception e) {
						//WriteLogTool.writeMsgToFile("客户端地址 "+ip.substring(1, ip.length())+" 回执失败");
						queue.remove(0);
						continue;
					}
					queue.remove(0);
				}
			}
			else{					
				//WriteLogTool.writeMsgToFile("客户端地址 "+ip.substring(1, ip.length())+" sorket通道关闭");
				socketChannel.socket().close();
			}
			if (queue.isEmpty()) {
				//如果写的事件完成，那么对写不在有兴趣,再次注册读
				key.interestOps(SelectionKey.OP_READ);
			}
			//ReadFile.getReadCond((String)key.attachment());
		}
	}

	private Selector initSelector() throws IOException {
		Selector socketSelector = SelectorProvider.provider().openSelector();
		serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		InetSocketAddress isa = new InetSocketAddress(this.hostAddress, this.port);
		serverChannel.socket().setReuseAddress(true);
		serverChannel.socket().bind(isa);
		serverChannel.register(socketSelector, SelectionKey.OP_ACCEPT);
		return socketSelector;
	}
	
	// 关闭ServerSocketChannel
    public synchronized void stop() {
        selector.wakeup();
    }
    
    // 关闭所有的channel
    @SuppressWarnings("unchecked")
	public void closeAllChannels() {
		for (Iterator itr = selector.keys().iterator(); itr.hasNext();) {
			SelectionKey key = (SelectionKey) itr.next();
			try {
				key.channel().close();
			} catch (IOException ex) {
			}
		}
	}
}