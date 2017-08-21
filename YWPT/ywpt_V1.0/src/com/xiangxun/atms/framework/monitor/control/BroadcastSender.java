package com.xiangxun.atms.framework.monitor.control;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.xiangxun.atms.framework.monitor.conf.Config;


//客户端
public class BroadcastSender
{
    public static void sendPort() throws IOException{
        byte[] msg = new String("port:"+Config.socketPort).getBytes();
        /*
         * 在Java UDP中单播与广播的代码是相同的,要实现具有广播功能的程序只需要使用广播地址即可, 例如：这里使用了本地的广播地址
         */
        InetAddress inetAddr = InetAddress.getByName("255.255.255.255");
        DatagramSocket client = new DatagramSocket();
        DatagramPacket sendPack1 = new DatagramPacket(msg, msg.length, inetAddr, Config.broadcastPortForClient);
        client.send(sendPack1);
        DatagramPacket sendPack2 = new DatagramPacket(msg, msg.length, inetAddr, Config.broadcastPortForTomcat);
        client.send(sendPack2);
        
        for(int i=Config.broadcastPortForOtherBegin;i<=Config.broadcastPortForOtherEnd;i++){
        	DatagramPacket sendPackOther = new DatagramPacket(msg, msg.length, inetAddr, i);
            client.send(sendPackOther);
        }
        client.close();
    }
}

