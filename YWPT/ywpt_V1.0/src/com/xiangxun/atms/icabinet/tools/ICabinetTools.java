package com.xiangxun.atms.icabinet.tools;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

import com.xiangxun.atms.icabinet.isapi.CommonCallback;
import com.xiangxun.atms.icabinet.isapi.HttpClientAsyn;
import com.xiangxun.atms.icabinet.isapi.IExecuteSucc;
import com.xiangxun.atms.icabinet.isapi.PutException;
import com.xiangxun.atms.icabinet.isapi.ResponseFailed;
import com.xiangxun.atms.icabinet.isapi.ResponseSucced;
import com.xiangxun.atms.icabinet.isapi.XmlUtils;
import com.xiangxun.atms.icabinet.sdk.DeviceInfo.AcSocket;
import com.xiangxun.atms.icabinet.sdk.DeviceInfo.AcTerminal;
import com.xiangxun.atms.icabinet.sdk.DeviceInfo.Sensor;

/**
 * 智能机柜操作工具
 * 
 * @author Administrator
 *
 */
public class ICabinetTools implements AutoCloseable {

	private static Logger logger = LoggerFactory.getLogger(ICabinetTools.class);
	private String host;
	private HttpClientAsyn hClientAsyn;

	public ICabinetTools(String ip, String userName, String password) {
		this(ip, "80", userName, password);
	}

	public ICabinetTools(String ip, String port, String userName, String password) {
		host = String.format("http://%s:%s", ip, port);
		logger.info("ICabinetTools:" + host);
		try {
			hClientAsyn = HttpClientAsyn.create(userName, password, true);
		} catch (Exception e) {
			logger.error("ICabinetTools:" + e.getMessage());
		}
	}

	/**
	 * 获取设备的型号与序列号(异步非阻塞方式)
	 * 
	 * @param failedCallback 失败回调接口
	 * @param execCallback 成功回调接口
	 */
	public void getDeviceModelAndSN(final ResponseFailed failedCallback, final IExecuteSucc execCallback) {
		final String url = String.format("%s%s", host, "/ISAPI/System/deviceInfo");
		hClientAsyn.get(url, new ResponseSucced<HttpResponse>() {

			@Override
			public void succed(HttpResponse resp, boolean responseStatusOK) {
				if (!responseStatusOK)
					return;
				if (execCallback == null)
					return;
				String xml = HttpClientAsyn.getString(resp, failedCallback);
				if (StringUtils.isEmpty(xml))
					return;
				String[] result = new String[2];
				result[0] = XmlUtils.findValueByTagName(xml, "DeviceInfo/model");
				result[1] = XmlUtils.findValueByTagName(xml, "DeviceInfo/serialNumber");
				execCallback.succed(result);
			}
		}, failedCallback, "getDeviceModelAndSN");

	}

	/**
	 * 修改设备名称和编号(异步非阻塞)
	 * 
	 * @param deviceName 设备名称
	 * @param no 设备编号
	 * @param failedCallback 失败回调接口
	 * @param execCallback 成功回调接口
	 */
	public void saveDeviceNameAndNo(final String deviceName, final String no, final ResponseFailed failedCallback, final IExecuteSucc execCallback) {
		final String url = String.format("%s%s", host, "/ISAPI/System/deviceInfo");

		final StrBuilder xml = new StrBuilder();
		xml.appendln("<DeviceInfo version=\"1.0\">");
		xml.appendln(String.format("<deviceName>%s</deviceName>", deviceName));
		xml.appendln(String.format("<telecontrolID>%s</telecontrolID>", no));
		xml.appendln("</DeviceInfo>");

		hClientAsyn.put(url, xml.toString(), new ResponseSucced<HttpResponse>() {
			@Override
			public void succed(HttpResponse result, boolean responseStatusOK) {
				if (responseStatusOK && HttpClientAsyn.responseOK(result)) {
					if (execCallback != null)
						execCallback.succed("设备名称和编号");
				} else if (failedCallback != null)
					failedCallback.failed(new PutException());
			}
		}, failedCallback, "saveDeviceNameAndNo");
	}

	/**
	 * 设置远程主机IP和端口号（智能机柜将向此地址进行上传数据）（异步非阻塞）
	 * 
	 * @param ip 远程主机的ip
	 * @param port 远程主机监听的端口
	 * @param failedCallback 失败回调接口
	 * @param execCallback 成功回调接口
	 * 
	 */
	public void setRemoteHostIpAndPort(final String ip, final String port, final ResponseFailed failedCallback, final IExecuteSucc execCallback) {
		final String url = String.format("%s%s", host, "/ISAPI/ContentMgmt/RemoteHost/1");
		StrBuilder xml = new StrBuilder();
		xml.appendln("<RemoteHost version=\"1.0\">");
		xml.appendln("<id>1</id>");
		xml.appendln(String.format("<ipAddr>%s</ipAddr>", ip));
		xml.appendln(String.format("<port>%s</port>", port));
		xml.appendln("</RemoteHost>");
		hClientAsyn.put(url, xml.toString(), new ResponseSucced<HttpResponse>() {
			@Override
			public void succed(HttpResponse result, boolean responseStatusOK) {
				if (responseStatusOK && HttpClientAsyn.responseOK(result)) {
					if (execCallback != null)
						execCallback.succed("IP和端口号");
				} else if (failedCallback != null)
					failedCallback.failed(new PutException());
			}
		}, failedCallback, "setRemoteHostIpAndPort");
	}

	/**
	 * 设置报警主机的地址（异步非阻塞）
	 * 
	 * @param ip 报警主机的ip
	 * @param port 报警主机的端口
	 * @param failedCallback 失败回调接口
	 * @param execCallback 成功回调接口
	 * 
	 */
	public void setAlarmCenterIpAndPort(final String ip, final String port, final ResponseFailed failedCallback, final IExecuteSucc execCallback) {
		final String url = String.format("%s%s", host, "/ISAPI/Event/notification/alarmCenter/1");
		final StrBuilder xml = new StrBuilder();
		xml.appendln("<alarmCenterNotification version=\"1.0\">");
		xml.appendln("<id>1</id>");
		xml.appendln("<addressingFormatType>ipaddress</addressingFormatType>");
		xml.appendln(String.format("<ipAddress>%s</ipAddress>", ip));
		xml.appendln(String.format("<portNo>%s</portNo>", port));
		xml.appendln("</alarmCenterNotification>");

		hClientAsyn.put(url, xml.toString(), new ResponseSucced<HttpResponse>() {
			@Override
			public void succed(HttpResponse result, boolean responseStatusOK) {
				if (responseStatusOK && HttpClientAsyn.responseOK(result)) {
					if (execCallback != null)
						execCallback.succed("报警主机的IP和端口号");
				} else if (failedCallback != null)
					failedCallback.failed(new PutException());
			}
		}, failedCallback, "setAlarmCenterIpAndPort");

	}

	/**
	 * 设置Http,SDK访问的端口号（异步非阻塞）
	 * 
	 * @param httpPort http访问端口
	 * @param managePort 服务端口，SDK访问的端口
	 * @param failedCallback 失败回调接口
	 * @param execCallback 成功回调接口
	 */
	public void setDevicePortNo(final String httpPort, final String managePort, final ResponseFailed failedCallback, final IExecuteSucc execCallback) {
		final String url = String.format("%s%s", host, "/ISAPI/Security/adminAccesses");
		hClientAsyn.get(url, new ResponseSucced<HttpResponse>() {
			@Override
			public void succed(HttpResponse resp, boolean responseStatusOK) {
				if (!responseStatusOK)
					return;
				String xml = HttpClientAsyn.getString(resp, failedCallback);
				if (StringUtils.isEmpty(xml))
					return;
				String condTag = "AdminAccessProtocolList/AdminAccessProtocol/id";
				String[] targetTags = new String[] { "portNo" };

				xml = XmlUtils.modifyTagValueByTagValue(xml, condTag, "1", targetTags, new String[] { httpPort });
				xml = XmlUtils.modifyTagValueByTagValue(xml, condTag, "4", targetTags, new String[] { managePort });
				hClientAsyn.put(url, xml, new ResponseSucced<HttpResponse>() {
					@Override
					public void succed(HttpResponse result, boolean responseStatusOK) {
						if (responseStatusOK && HttpClientAsyn.responseOK(result)) {
							if (execCallback != null)
								execCallback.succed("服务端口号");
						} else if (failedCallback != null)
							failedCallback.failed(new PutException());
					}
				}, failedCallback, "setDevicePortNo2");
			}
		}, failedCallback, "setDevicePortNo1");
	}

	/**
	 * 设置设备的Ip地址（仅仅对第一个网卡进行设置）
	 * 
	 * @param failedCallback 失败回调接口
	 * @param execCallback 成功回调接口
	 */
	public void setDeviceIp(final String ip, final ResponseFailed failedCallback, final IExecuteSucc execCallback) {
		final String url = String.format("%s%s", host, "/ISAPI/System/Network/interfaces");
		hClientAsyn.get(url, new ResponseSucced<HttpResponse>() {
			@Override
			public void succed(HttpResponse resp, boolean responseStatusOK) {
				if (!responseStatusOK)
					return;
				String xml = HttpClientAsyn.getString(resp, failedCallback);
				String niID = "1";
				List<Node> nodes = XmlUtils.getNodesByTag(xml, "id");
				if (nodes.size() == 0) {
					if (execCallback != null)
						execCallback.succed(null);
					return;
				}
				for (Node idNode : nodes) {
					if (StringUtils.equalsIgnoreCase(idNode.getParentNode().getNodeName(), "NetworkInterface"))
						niID = idNode.getTextContent();
				}
				final String url = String.format("%s%s%s", host, "/ISAPI/System/Network/interfaces/", niID);
				hClientAsyn.get(url, new ResponseSucced<HttpResponse>() {
					@Override
					public void succed(HttpResponse resp2, boolean responseStatusOK) {
						String xml = HttpClientAsyn.getString(resp2, failedCallback);
						String[] tags = new String[] { "NetworkInterface/IPAddress/ipAddress" };
						String[] vals = new String[] { ip };
						xml = XmlUtils.modifyTagValue(xml, tags, vals);
						hClientAsyn.put(url, xml, new ResponseSucced<HttpResponse>() {
							@Override
							public void succed(HttpResponse result, boolean responseStatusOK) {
								if (responseStatusOK && HttpClientAsyn.responseOK(result)) {
									if (execCallback != null)
										execCallback.succed("IP");
								} else if (failedCallback != null)
									failedCallback.failed(new PutException());
							}
						}, failedCallback, "setDeviceIp3");
					}
				}, failedCallback, "setDeviceIp2");
			}
		}, failedCallback, "setDeviceIp1");
	}

	/**
	 * 修改AC端子的信息（异步非阻塞）
	 * 
	 * @param samplingTime 采样间隔（秒）
	 * @param failedCallback 失败回调接口
	 * @param execCallback 成功回调接口
	 */
	public void setACTerminal(final String samplingTime, final ResponseFailed failedCallback, final IExecuteSucc execCallback) {
		String url = String.format("%s%s", host, "/ISAPI/System/Sensor/ports");
		hClientAsyn.get(url, new ResponseSucced<HttpResponse>() {
			@Override
			public void succed(HttpResponse resp, boolean responseStatusOK) {
				if (!responseStatusOK)
					return;
				String xml = HttpClientAsyn.getString(resp, failedCallback);
				if (StringUtils.isEmpty(xml))
					return;
				List<Node> nodes = XmlUtils.getNodesByTag(xml, "ACTerminal");
				if (nodes.size() == 0) {
					if (execCallback != null) {
						logger.info("没有安装AC端子");
						execCallback.succed("没有安装AC端子");
					}
					return;
				}
				for (Node node : nodes) {
					final AcTerminal tmn = new AcTerminal();
					tmn.id = XmlUtils.getChildValue(node.getParentNode(), "id");
					tmn.name = XmlUtils.getChildValue(node.getParentNode(), "name");
					tmn.enabled = XmlUtils.getChildValue(node.getParentNode(), "enabled");
					tmn.samplingTime = XmlUtils.getChildValue(node.getParentNode(), "samplingTime");
					tmn.isPowerOn = XmlUtils.getChildValue(node.getParentNode(), "ACTerminal/isPowerOn");
					tmn.normalOrExcept = XmlUtils.getChildValue(node.getParentNode(), "ACTerminal/normalOrExcept");

					final String url = String.format("%s%s%s", host, "/ISAPI/System/Sensor/ports/", tmn.id);
					StrBuilder xm = new StrBuilder();
					xm.appendln("<SensorPort version=\"1.0\">");
					xm.appendln(String.format("<id>%s</id>", tmn.id));
					xm.appendln(String.format("<enabled>%s</enabled>", tmn.enabled));
					xm.appendln(String.format("<name>%s</name>", tmn.name));
					xm.appendln(String.format("<samplingTime>%s</samplingTime>", tmn.samplingTime));
					xm.appendln("</SensorPort>");

					hClientAsyn.put(url, xm.toString(), new ResponseSucced<HttpResponse>() {
						@Override
						public void succed(HttpResponse result, boolean responseStatusOK) {
							if (responseStatusOK && HttpClientAsyn.responseOK(result)) {
								if (execCallback != null)
									execCallback.succed("ID:" + tmn.id);
							} else if (failedCallback != null)
								failedCallback.failed(new PutException());
						}
					}, failedCallback, "setACTerminal2");
				}
			}
		}, failedCallback, "setACTerminal1");
	}

	/**
	 * 获取所有AC插座的信息(异步非阻塞)
	 * 
	 * @param callback 执行结果返回
	 * @param failedCallback 执行错误返回调用
	 * 
	 */
	public void getAcSockets(final CommonCallback callback, final ResponseFailed failedCallback) {

		String url = String.format("%s%s", host, "/ISAPI/System/Sensor/ports");
		hClientAsyn.get(url, new ResponseSucced<HttpResponse>() {
			@Override
			public void succed(HttpResponse resp, boolean responseStatusOK) {
				if (!responseStatusOK)
					return;
				List<AcSocket> ids = new ArrayList<AcSocket>();
				String xml = HttpClientAsyn.getString(resp, failedCallback);
				for (Node node : XmlUtils.getNodesByTag(xml, "SocketRegions")) {
					AcSocket socket = new AcSocket();
					socket.id = XmlUtils.getChildValue(node.getParentNode(), "id");
					socket.name = XmlUtils.getChildValue(node.getParentNode(), "name");
					socket.enabled = XmlUtils.getChildValue(node.getParentNode(), "enabled");
					socket.samplingTime = XmlUtils.getChildValue(node.getParentNode(), "samplingTime");

					socket.currentRegionMin = XmlUtils.getChildValue(node.getParentNode(), "SocketRegions/CurrentRegion/regionMin");
					socket.currentRegionMax = XmlUtils.getChildValue(node.getParentNode(), "SocketRegions/CurrentRegion/regionMax");
					socket.currentNormalOrExcept = XmlUtils.getChildValue(node.getParentNode(), "SocketRegions/CurrentRegion/normalOrExcept");
					socket.currentID = XmlUtils.getChildValue(node.getParentNode(), "SocketRegions/CurrentRegion/id");

					socket.voltageRegionMin = XmlUtils.getChildValue(node.getParentNode(), "SocketRegions/VoltageRegion/regionMin");
					socket.voltageRegionMax = XmlUtils.getChildValue(node.getParentNode(), "SocketRegions/VoltageRegion/regionMax");
					socket.voltageNormalOrExcept = XmlUtils.getChildValue(node.getParentNode(), "SocketRegions/VoltageRegion/normalOrExcept");
					socket.voltageID = XmlUtils.getChildValue(node.getParentNode(), "SocketRegions/VoltageRegion/id");

					ids.add(socket);
				}
				callback.call(ids.toArray(new AcSocket[0]));
			}
		}, failedCallback, "getAcSockets");
	}

	/**
	 * 修改AC插座的电压电流的xml
	 * 
	 * @param socket AC插座
	 * @param samplingTime 采样间隔（秒）
	 * @param currentMin 电流正常范围最小值
	 * @param currentMax 电流正常范围最大值
	 * @param voltageMin 电压正常范围最小值
	 * @param voltageMax 电压正常范围最大值
	 * @return xml
	 */
	private String getAcSocketXml(AcSocket socket, String samplingTime, String currentMin, String currentMax, String voltageMin, String voltageMax) {
		StrBuilder xml = new StrBuilder();
		xml.appendln("<?xml version='1.0' encoding='UTF-8'?>");
		xml.appendln("<SensorPort>");
		xml.appendln(String.format("  <id>%s</id>", socket.id));
		xml.appendln(String.format("  <name>%s</name>", socket.name));
		xml.appendln(String.format("  <samplingTime>%s</samplingTime>", samplingTime));
		xml.appendln(String.format("  <enabled>%s</enabled>", socket.enabled));
		xml.appendln("  <SocketRegions>");
		xml.appendln("    <CurrentRegion>");
		xml.appendln(String.format("      <regionMin>%s</regionMin>", currentMin));
		xml.appendln(String.format("      <regionMax>%s</regionMax>", currentMax));
		xml.appendln(String.format("      <normalOrExcept>%s</normalOrExcept>", 1));// 1.正常区间,2.异常区间
		xml.appendln(String.format("      <id>%s</id>", socket.currentID));
		xml.appendln("    </CurrentRegion>");
		xml.appendln("    <VoltageRegion>");
		xml.appendln(String.format("      <regionMin>%s</regionMin>", voltageMin));
		xml.appendln(String.format("      <regionMax>%s</regionMax>", voltageMax));
		xml.appendln(String.format("      <normalOrExcept>%s</normalOrExcept>", 1));// 1.正常区间,2.异常区间
		xml.appendln(String.format("      <id>%s</id>", socket.voltageID));
		xml.appendln("    </VoltageRegion>");
		xml.appendln("  </SocketRegions>");
		xml.appendln("</SensorPort>");
		return xml.toString();
	}

	/**
	 * 设置AC插座的电压电流范围(异步非阻塞)
	 * 
	 * @param samplingTime 采样间隔
	 * @param currentMin 电流正常范围最小值
	 * @param currentMax 电流正常范围最大值
	 * @param voltageMin 电压正常范围最小值
	 * @param voltageMax 电压正常范围最大值
	 * @param failedCallback 失败回调接口
	 * @param execCallback 成功回调接口
	 */
	public void setDeviceACSocket(final String samplingTime, final String currentMin, final String currentMax, final String voltageMin, final String voltageMax, final ResponseFailed failedCallback, final IExecuteSucc execCallback) {
		getAcSockets(new CommonCallback() {

			@Override
			public void call(Object values) {
				AcSocket[] acSockets = (AcSocket[]) values;
				if (acSockets.length == 0) {
					if (execCallback != null) {
						logger.info("没有安装AC插座");
						execCallback.succed("没有安装AC插座");
					}
					return;
				}
				for (final AcSocket socket : acSockets) {
					final String url = String.format("%s%s%s", host, "/ISAPI/System/Sensor/ports/", socket.id);
					String xml = getAcSocketXml(socket, samplingTime, currentMin, currentMax, voltageMin, voltageMax);

					hClientAsyn.put(url, xml, new ResponseSucced<HttpResponse>() {
						@Override
						public void succed(HttpResponse result, boolean responseStatusOK) {
							if (responseStatusOK && HttpClientAsyn.responseOK(result)) {
								if (execCallback != null)
									execCallback.succed("ID:" + socket.id);
							} else if (failedCallback != null)
								failedCallback.failed(new PutException());
						}
					}, failedCallback, "setDeviceACSocket");
				}
			}
		}, failedCallback);
	}

	/**
	 * 获取传感器修改的XML结构
	 * 
	 * @param sensor 传感器属性
	 * @param regionMin 检测范围最小值
	 * @param regionMax 检测范围最大值
	 * @return xml
	 */
	private String getSensorXml(Sensor sensor, String regionMin, final String regionMax) {
		StrBuilder xml = new StrBuilder();
		xml.appendln("<?xml version='1.0' encoding='UTF-8'?>");
		xml.appendln("<SensorPort>");
		xml.appendln(String.format("<id>%s</id>", sensor.id));
		xml.appendln(String.format("<enabled>%s</enabled>", sensor.enabled));
		xml.appendln(String.format("<name>%s</name>", sensor.name));
		xml.appendln(String.format("<samplingTime>%s</samplingTime>", sensor.samplingTime));
		xml.appendln("<AnalogRegions version=\"1.0\">");
		xml.appendln(String.format("<type>%s</type>", sensor.type));
		xml.appendln(String.format("<maxValue>%s</maxValue>", sensor.maxValue));
		xml.appendln(String.format("<minValue>%s</minValue>", sensor.minValue));
		xml.appendln(String.format("<sensitivity>%s</sensitivity>", sensor.sensitivity));
		xml.appendln(String.format("<unit>%s</unit>", sensor.unit));

		xml.appendln("<Region version=\"1.0\">");
		xml.appendln("    <id>0</id>");
		xml.appendln(String.format("    <regionMin>%s</regionMin>", regionMin));
		xml.appendln(String.format("    <regionMax>%s</regionMax>", regionMax));
		xml.appendln("    <normalOrExcept>1</normalOrExcept>");// 1.正常区间,2.异常区间
		xml.appendln("</Region>");

		xml.appendln("</AnalogRegions>");
		xml.appendln("</SensorPort>");
		return xml.toString();
	}

	/**
	 * 设定传感器的正常范围(异步非阻塞)
	 * 
	 * @param samplingTime 采样间隔（秒）
	 * @param min 量程最小值
	 * @param max 量程最大值
	 * @param regionMin 正常范围最小值
	 * @param regionMax 正常范围最大值
	 * @param sensorType 传感器的类型 *
	 * @param failedCallback 失败回调接口
	 * @param execCallback 成功回调接口
	 */
	public void setSensorRegion(final String samplingTime, final String min, final String max, final String regionMin, final String regionMax, final SensorType sensorType, final ResponseFailed failedCallback, final IExecuteSucc execCallback) {
		String url = String.format("%s%s", host, "/ISAPI/System/Sensor/ports");
		hClientAsyn.get(url, new ResponseSucced<HttpResponse>() {
			@Override
			public void succed(HttpResponse resp, boolean responseStatusOK) {
				if (!responseStatusOK)
					return;
				String xml = HttpClientAsyn.getString(resp, failedCallback);
				if (StringUtils.isEmpty(xml))
					return;
				List<Node> analogNodes = XmlUtils.getNodesByTag(xml, "AnalogRegions");
				if (analogNodes.size() == 0) {
					if (execCallback != null) {
						logger.info(host + "没有发现" + sensorType.getTitle());
						execCallback.succed("没有发现" + sensorType.getTitle());
					}
				}
				for (Node analogNode : analogNodes) {
					String type = XmlUtils.getChildValue(analogNode, "type");
					if (!StringUtils.equalsIgnoreCase(type, sensorType.getName()))// 判断类型为指定的传感器类型
						continue;
					final String id = XmlUtils.getChildValue(analogNode.getParentNode(), "id");
					final String url = String.format("%s%s%s", host, "/ISAPI/System/Sensor/ports/", id);
					// try{TimeUnit.MILLISECONDS.sleep(100);}catch(Exception ex){}
					hClientAsyn.get(url, new ResponseSucced<HttpResponse>() {

						@Override
						public void succed(HttpResponse resp2, boolean responseStatusOK) {
							if (!responseStatusOK)
								return;
							String xml = HttpClientAsyn.getString(resp2, failedCallback);
							if (StringUtils.isEmpty(xml))
								return;
							Sensor sensor = new Sensor();
							sensor.id = XmlUtils.findValueByTagName(xml, "SensorPort/id");
							sensor.enabled = XmlUtils.findValueByTagName(xml, "SensorPort/enabled");
							sensor.name = XmlUtils.findValueByTagName(xml, "SensorPort/name");
							sensor.samplingTime = samplingTime;
							sensor.type = XmlUtils.findValueByTagName(xml, "SensorPort/AnalogRegions/type");
							sensor.maxValue = StringUtils.isEmpty(max) ? XmlUtils.findValueByTagName(xml, "SensorPort/AnalogRegions/maxValue") : max;
							sensor.minValue = StringUtils.isEmpty(min) ? XmlUtils.findValueByTagName(xml, "SensorPort/AnalogRegions/minValue") : min;
							sensor.sensitivity = XmlUtils.findValueByTagName(xml, "SensorPort/AnalogRegions/sensitivity");
							sensor.unit = XmlUtils.findValueByTagName(xml, "SensorPort/AnalogRegions/unit");

							xml = getSensorXml(sensor, regionMin, regionMax);
							hClientAsyn.put(url, xml, new ResponseSucced<HttpResponse>() {

								@Override
								public void succed(HttpResponse result, boolean responseStatusOK) {
									if (responseStatusOK && HttpClientAsyn.responseOK(result)) {
										if (execCallback != null)
											execCallback.succed("ID:" + id);
									} else if (failedCallback != null)
										failedCallback.failed(new PutException());
								}
							}, failedCallback, "setSensorRegion1");
						}
					}, failedCallback, "setSensorRegion2");
				}
			}
		}, failedCallback, "setSensorRegion3");
	}

	/**
	 * 设置温度传感器正常范围
	 * 
	 * @param samplingTime 采样间隔(秒)
	 * @param min 量程最小值
	 * @param max 量程最大值
	 * @param regionMin 正常范围最小值
	 * @param regionMax 正常范围最大值
	 * @param sensorType 传感器的类型
	 * @param failedCallback 失败回调接口
	 * @param execCallback 成功回调接口
	 */
	public void setTemperatureRegions(String samplingTime, String min, String max, String regionMin, String regionMax, final ResponseFailed failedCallback, IExecuteSucc execCallback) {
		setSensorRegion(samplingTime, min, max, regionMin, regionMax, SensorType.temperature, failedCallback, execCallback);
	}

	/**
	 * 设置湿度传感器正常范围
	 * 
	 * @param samplingTime 采样间隔(秒)
	 * @param min 量程最小值
	 * @param max 量程最大值
	 * @param regionMin 正常范围最小值
	 * @param regionMax 正常范围最大值
	 * @param sensorType 传感器的类型
	 * @param failedCallback 失败回调接口
	 * @param execCallback 成功回调接口
	 */
	public void setHumidityRegions(String samplingTime, String min, String max, String regionMin, String regionMax, final ResponseFailed failedCallback, IExecuteSucc execCallback) {
		setSensorRegion(samplingTime, min, max, regionMin, regionMax, SensorType.humidity, failedCallback, execCallback);
	}

	/**
	 * 释放连接
	 */
	@Override
	public void close() throws Exception {
		hClientAsyn.close();
	}
}
