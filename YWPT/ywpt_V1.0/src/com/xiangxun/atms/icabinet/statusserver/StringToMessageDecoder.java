package com.xiangxun.atms.icabinet.statusserver;

import java.net.InetSocketAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiangxun.atms.icabinet.sdk.AlarmInfo;
import com.xiangxun.atms.icabinet.sdk.IMessageHelper;
import com.xiangxun.atms.icabinet.sdk.LoginInfo;
import com.xiangxun.atms.icabinet.statusserver.protocal.CabinetSN;
import com.xiangxun.atms.icabinet.statusserver.protocal.PatrolInfo;
import com.xiangxun.atms.icabinet.statusserver.protocal.param.CabinetRangeParam;
import com.xiangxun.atms.icabinet.tools.SensorType;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.string.StringDecoder;

public class StringToMessageDecoder extends StringDecoder {
	private static final Logger logger = LoggerFactory.getLogger(StringToMessageDecoder.class);
	private static final String CABINET_HEAD = "#CABINET;";// 机柜信息数据头
	private static final String PATROL_HEAD = "#PATROL;";// 巡更信息数据头
	private static final String ERROR_CODE = "88.8";// 错误代码，温度与湿度同时为88.0表示错误数据

	private IMessageHelper helper;
	private CabinetRangeParam params;

	public StringToMessageDecoder(IMessageHelper helper, CabinetRangeParam params) {
		this.helper = helper;
		this.params = params;
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
		if (!msg.isReadable())
			return;
		Channel ch = ctx.channel();
		InetSocketAddress address = (InetSocketAddress) ch.remoteAddress();
		String ip = address.getAddress().getHostAddress();
		byte[] content = new byte[msg.readableBytes()];
		msg.getBytes(0, content);
		String line = new String(content);
		logger.info(String.format("ip:%s; read line:%s", ip, line));

		if (StringUtils.contains(line, CABINET_HEAD)) {
			parseAlarmInfo(line, out);
		} else if (StringUtils.contains(line, PATROL_HEAD)) {
			parsePatrol(line, out);
		}
	}

	/**
	 * 机柜的报警信息
	 * 
	 * @param msg 接收到的数据行
	 * @param out decoder的数据链
	 * @throws ParseException
	 */
	private void parseAlarmInfo(String msg, List<Object> out) throws ParseException {
		String[] secs = msg.split(";");// 协议：#CABINET;sn;ip;datetime;temperature;humidity
		if (secs.length < 6)
			return;
		if (StringUtils.equals(secs[4], ERROR_CODE) && StringUtils.equals(secs[5], ERROR_CODE))
			return;
		String sn = secs[1];
		LoginInfo li = helper.getLoginInfoByIP(secs[2]);
		if (li == null || li.getId().length() == 0)
			return;
		if (StringUtils.isEmpty(li.getSn()) || !StringUtils.equalsIgnoreCase(sn, li.getSn())) {
			CabinetSN newSN = new CabinetSN(li.getId(), sn);
			out.add(newSN);
			li.setSn(sn);
		}
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
		Date eventTime = fmt.parse(secs[3]);
		float temperature = Float.parseFloat(secs[4]);
		float humidity = Float.parseFloat(secs[5]);
		boolean isValidTemperature = params.isValidTemperature(temperature);
		boolean isValidHumidity = params.isValidHumidity(humidity);
		AlarmInfo temperatureInfo = new AlarmInfo(helper.getNextID());
		temperatureInfo.setIp(li.getIp());
		temperatureInfo.setSn(sn);
		temperatureInfo.setMessage(isValidTemperature ? "温度正常" : "温度异常");
		temperatureInfo.setChannelName("0");
		temperatureInfo.setEventTime(eventTime);
		temperatureInfo.setStatus(isValidHumidity ? 0 : 2);
		temperatureInfo.setSensorType(SensorType.temperature.getID());
		temperatureInfo.setActualValue(temperature);
		out.add(temperatureInfo);

		AlarmInfo humidityInfo = new AlarmInfo(helper.getNextID());
		humidityInfo.setIp(li.getIp());
		humidityInfo.setSn(sn);
		humidityInfo.setMessage(isValidHumidity ? "湿度正常" : "湿度异常");
		humidityInfo.setChannelName("0");
		humidityInfo.setEventTime(eventTime);
		humidityInfo.setStatus(isValidHumidity ? 0 : 2);
		humidityInfo.setSensorType(SensorType.humidity.getID());
		humidityInfo.setActualValue(humidity);
		out.add(humidityInfo);
		logger.info("机柜状态信息已解析");
	}

	/**
	 * 解析巡更数据
	 * 
	 * @param msg 接收到的数据行
	 * @param out decoder的数据链
	 * @throws ParseException
	 */
	private void parsePatrol(String line, List<Object> out) throws ParseException {
		String[] secs = line.split(";");// 协议：#PATROL;sn;ip;datetime;
		if (secs.length < 4)
			return;
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
		Date eventTime = fmt.parse(secs[3]);
		out.add(new PatrolInfo("cabinet", secs[2], eventTime));
		logger.info("巡更数据已解析");
	}
}
