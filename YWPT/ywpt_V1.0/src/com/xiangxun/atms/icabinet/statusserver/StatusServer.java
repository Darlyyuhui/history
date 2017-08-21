package com.xiangxun.atms.icabinet.statusserver;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiangxun.atms.icabinet.sdk.IMessageHelper;
import com.xiangxun.atms.icabinet.statusserver.protocal.param.CabinetRangeParam;
import com.xiangxun.atms.module.eventalarm.service.WorkorderInfoService;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

/**
 * 机柜状态传感器接收服务
 * @author szf
 *
 */
public class StatusServer implements AutoCloseable {

	private int port = 6015;
	private EventLoopGroup bossGroup = null;
	private EventLoopGroup workerGroup = null;
	private ServerBootstrap sb = new ServerBootstrap();
	private DataSource ds;
	private IMessageHelper helper;
	private CabinetRangeParam params;
	private WorkorderInfoService workorder;

	private final Logger logger = LoggerFactory.getLogger(StatusServer.class);

	public StatusServer(int port, DataSource ds, IMessageHelper helper, CabinetRangeParam params, WorkorderInfoService workorder) {
		this.port = port;
		this.ds = ds;
		this.helper = helper;
		this.params = params;
		this.workorder = workorder;
	}

	public void init() {
		final MessageHandler messageHandler = new MessageHandler(ds, workorder);
		final ByteBuf delimiter = Unpooled.copiedBuffer(new byte[] { 0x0D, 0x0A });

		bossGroup = new NioEventLoopGroup();
		workerGroup = new NioEventLoopGroup();
		try {
			sb.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel sc) throws Exception {
					sc.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter))// 按行分隔
							.addLast(new StringToMessageDecoder(helper, params))//
							.addLast(messageHandler);//
				}
			}).option(ChannelOption.SO_BACKLOG, 1024)//
					.option(ChannelOption.TCP_NODELAY, true)//
					.childOption(ChannelOption.SO_KEEPALIVE, true);

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void start() {

		try {
			logger.info("Socket Server has started,bind port:" + port);
			ChannelFuture cf = sb.bind(port);
			cf.addListener(new ChannelFutureListener() {

				public void operationComplete(ChannelFuture channelFuture) throws Exception {
					if (channelFuture.isSuccess()) {
						logger.info("Server bound");
					} else {
						channelFuture.cause().printStackTrace();
					}
				}
			});
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void stop() {
		workerGroup.shutdownGracefully();
		bossGroup.shutdownGracefully();
	}

	@Override
	public void close() throws Exception {
		stop();
	}

}
