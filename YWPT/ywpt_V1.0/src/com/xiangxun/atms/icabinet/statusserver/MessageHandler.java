package com.xiangxun.atms.icabinet.statusserver;

import java.net.InetSocketAddress;
import java.sql.Connection;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiangxun.atms.icabinet.sdk.AlarmInfo;
import com.xiangxun.atms.icabinet.statusserver.protocal.CabinetSN;
import com.xiangxun.atms.icabinet.statusserver.protocal.PatrolInfo;
import com.xiangxun.atms.module.eventalarm.service.WorkorderInfoService;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

@ChannelHandler.Sharable
public class MessageHandler extends ChannelInboundHandlerAdapter {
	private final Logger logger = LoggerFactory.getLogger(MessageHandler.class);
	private DataSource ds;
	private WorkorderInfoService workorder;

	public MessageHandler(DataSource ds, WorkorderInfoService workorder) {
		this.ds = ds;
		this.workorder = workorder;
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		super.channelRegistered(ctx);
		String ip = getIp(ctx);
		Channel ch = ctx.channel();
		InetSocketAddress address = (InetSocketAddress) ch.remoteAddress();
		logger.info("channelRegistered:" + ip + " port:" + address.getPort());
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		try {
			if (msg instanceof AlarmInfo) {
				AlarmInfo message = AlarmInfo.class.cast(msg);
				logger.info("read message:" + message.getMessage());
				try(Connection conn = ds.getConnection())
				{
				    message.save(conn);
				}
				logger.info("机柜状态信息已保存");
			} else if (msg instanceof PatrolInfo) {
				PatrolInfo pi = PatrolInfo.class.cast(msg);
				workorder.xunGengWordOrder(pi.getIp(), pi.getDeviceType(), pi.getDate());
				logger.info("巡更信息已处理");
			} else if (msg instanceof CabinetSN) {
				CabinetSN sn = CabinetSN.class.cast(msg);
				try(Connection conn = ds.getConnection())
				{
				    sn.update(conn);
				}
				logger.info("机柜SN已更新");
			}
		} finally {
			ReferenceCountUtil.release(msg);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		String ip = getIp(ctx);
		logger.error(String.format("Unexpected exception ip:%s %s", ip, cause.getMessage()));
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		super.channelUnregistered(ctx);
		String ip = getIp(ctx);
		logger.info("channel unregistered:" + ip);

	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
		String ip = getIp(ctx);
		logger.info("channel closed:" + ip);

	}

	private String getIp(ChannelHandlerContext ctx) {
		Channel ch = ctx.channel();
		InetSocketAddress address = (InetSocketAddress) ch.remoteAddress();
		String ip = address.getAddress().getHostAddress();
		return ip;
	}
}
