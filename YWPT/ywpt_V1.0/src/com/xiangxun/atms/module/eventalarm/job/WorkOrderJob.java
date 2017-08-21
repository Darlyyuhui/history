package com.xiangxun.atms.module.eventalarm.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.xiangxun.atms.common.road.service.RoadInfoService;
import com.xiangxun.atms.common.road.vo.RoadInfo;
import com.xiangxun.atms.common.system.service.ParamsService;
import com.xiangxun.atms.common.system.vo.SystemParams;
import com.xiangxun.atms.common.user.vo.User;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.log.Logging;
import com.xiangxun.atms.framework.security.OperatorDetails;
import com.xiangxun.atms.framework.security.SpringSecurityUtils;
import com.xiangxun.atms.framework.util.JsonUtil;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderInfo;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderLog;
import com.xiangxun.atms.module.eventalarm.service.WorkorderInfoService;
import com.xiangxun.atms.module.eventalarm.service.WorkorderLogService;
import com.xiangxun.atms.module.eventalarm.status.WorkorderStatus;
import com.xiangxun.atms.module.property.domain.AssetInfo;
import com.xiangxun.atms.module.property.domain.CabInfo;
import com.xiangxun.atms.module.property.domain.CabinetAbnormalType;
import com.xiangxun.atms.module.property.domain.DatabaseInfo;
import com.xiangxun.atms.module.property.domain.DeviceCompanyInfo;
import com.xiangxun.atms.module.property.domain.DeviceInfo;
import com.xiangxun.atms.module.property.domain.FtpInfo;
import com.xiangxun.atms.module.property.domain.ProjectInfo;
import com.xiangxun.atms.module.property.domain.ServerInfo;
import com.xiangxun.atms.module.property.domain.VideoInfo;
import com.xiangxun.atms.module.property.service.AssetInfoService;
import com.xiangxun.atms.module.property.service.CabInfoService;
import com.xiangxun.atms.module.property.service.DatabaseInfoService;
import com.xiangxun.atms.module.property.service.DeviceCompanyInfoService;
import com.xiangxun.atms.module.property.service.DeviceInfoService;
import com.xiangxun.atms.module.property.service.FtpInfoService;
import com.xiangxun.atms.module.property.service.ProjectInfoService;
import com.xiangxun.atms.module.property.service.ServerInfoService;
import com.xiangxun.atms.module.property.service.VideoInfoService;
import com.xiangxun.atms.module.sergrade.domain.FactoryInfo;
import com.xiangxun.atms.module.sergrade.service.FactoryContactService;
import com.xiangxun.atms.module.sergrade.service.FactoryInfoService;
import com.xiangxun.atms.module.sms.service.SendTaskService;
import com.xiangxun.atms.module.sms.vo.SendTask;

/**
 * 系统自动派发工单定时任务器
 * 
 * @author Administrator
 *
 */
public class WorkOrderJob {
	protected Logging logger = new Logging(getClass());

	@Resource
	AssetInfoService assetInfoService;

	@Resource
	FactoryContactService factoryContactService;

	@Resource
	DeviceInfoService deviceInfoService;

	@Resource
	FactoryInfoService factoryInfoService;

	@Resource
	DeviceCompanyInfoService deviceCompanyInfoService;

	@Resource
	RoadInfoService roadInfoService;

	@Resource
	VideoInfoService videoInfoService;

	@Resource
	ServerInfoService serverInfoService;

	@Resource
	DatabaseInfoService databaseInfoService;

	@Resource
	ProjectInfoService projectInfoService;

	@Resource
	FtpInfoService ftpInfoService;

	@Resource
	WorkorderInfoService workorderInfoService;

	@Resource
	WorkorderLogService workorderLogService;

	@Resource
	ParamsService paramsService;

	@Resource
	SendTaskService sendTaskService;
	@Resource
	CabInfoService cabInfoService;

	public void process() {
		logger.info("根据系统参数配置，自动扫描是否要进行工单自动派发功能开始......");
		String message = "";
		// 测试如果系统参数为0，则不进行
		SystemParams openworkorder = paramsService.getParamsByKeyAndType("openworkorder", "1");
		if (openworkorder == null || "0".equals(openworkorder.getValue())) {
			logger.info("系统自动派发工单功能未开启:工单自动派发功能结束......");
			return;
		}
		// 构造过滤条件
		List<String> listAray = filterConditions();

		// 1.获取所有的资产
		List<AssetInfo> list = assetInfoService.getAllMapListByStatusAbnormal();
		// 2.如果资产为空，则返回
		if (list.isEmpty() || list.size() == 0) {
			logger.info("系统未添加资产:工单自动派发功能结束......");
			return;
		}
		// 如果资产不为空，则循环遍历所有的资产，进行工单派发
		for (AssetInfo assetInfo : list) {
			boolean flag = false;
			// 开始过滤
			flag = filterDate(assetInfo, listAray);
			// 如果不符合标准则直接跳出不派发
			if (!flag) continue;

			// 3.按照资产查询出来所有的设备状态
			// 即获取工单所有信息
			String devicetype = assetInfo.getAssettype();
			String deviceid = assetInfo.getDeviceid();
			FactoryInfo factory = null;
			List<User> contactList = factoryContactService.findContactList(deviceid);
			if (contactList == null || contactList.size() == 0) {
				message = "该资产没有设置维护人员" + "资产编号：" + assetInfo.getId() + "资产名称：" + assetInfo.getAssetname();
				logger.debug("自动派发失败     ----》" + message);
				continue;
			}
			setContactData(contactList);
			boolean dataerror = false;
			DeviceInfo device = new DeviceInfo();
			VideoInfo video = null;
			ServerInfo server = null;
			DatabaseInfo database = null;
			FtpInfo ftp = null;
			RoadInfo road = null;
			DeviceCompanyInfo company = null;
			ProjectInfo project = null;
			WorkorderInfo workorder = new WorkorderInfo();
			CabInfo cabInfo = null;
			String position = "";
			if (devicetype.equals("device")) {

				device = deviceInfoService.getById(deviceid);
				if (device != null) {
					factory = factoryInfoService.getById(device.getFactoryId());
					company = deviceCompanyInfoService.getById(device.getCompanyId());
					road = roadInfoService.getById(device.getRoadId());
					// 设置工单基本信息
					device.setOrgId(device.getOrgId());
					position = road.getName();
				} else {
					message = "该设备信息不存在" + assetInfo.getId();
					dataerror = true;
				}
			} else if (devicetype.equals("cabinet")) {
				logger.info("机柜派发中。。。。");
				cabInfo = cabInfoService.getById(assetInfo.getDeviceid());
				if (cabInfo == null) {
					message = "该机柜信息不存在" + assetInfo.getId();
					logger.info("机柜工单派发："+message);
					dataerror = true;
				} else {
					road = roadInfoService.getById(cabInfo.getRoadId());
					factory = factoryInfoService.getById(cabInfo.getFactoryId());

					device.setCode(assetInfo.getAssetcode());
					device.setIp(cabInfo.getIp());
					device.setName(cabInfo.getName());
					device.setOrgId(cabInfo.getOrgId());
					position = road.getName();
					logger.info("机柜工单派发："+device.getIp());
				}
			} else if (devicetype.equals("video")) {
				video = videoInfoService.getById(deviceid);
				if (video != null) {
					factory = factoryInfoService.getById(video.getFactoryId());
					company = deviceCompanyInfoService.getById(video.getCompanyid());
					road = roadInfoService.getById(video.getRoadinfoId());
					device.setCode(video.getCode());
					device.setIp(video.getIp());
					device.setName(video.getName());
					device.setDeviceTypeNames(devicetype);
					device.setOrgId(video.getOrgId());
					position = road.getName();
				} else {
					message = "该vioeo信息不存在" + assetInfo.getId();
					dataerror = true;
				}

			} else if (devicetype.equals("server")) {
				server = serverInfoService.getById(deviceid);
				if (server != null) {
					factory = factoryInfoService.getById(server.getFactoryId());
					device.setCode(server.getCode());
					device.setIp(server.getServerip());
					device.setName(server.getName());
					device.setDeviceTypeNames(devicetype);
					device.setOrgId(server.getOrgId());
					position = "室内机房";
				} else {
					message = "该服务器信息不存在" + assetInfo.getId();
					dataerror = true;
				}

			} else if (devicetype.equals("database")) {
				database = databaseInfoService.getById(deviceid);
				if (database != null) {
					factory = factoryInfoService.getById(database.getFactoryId());
					device.setCode(assetInfo.getAssetcode());
					device.setIp(assetInfo.getIp());
					device.setName(assetInfo.getAssetname());
					device.setDeviceTypeNames(devicetype);
					position = assetInfo.getInstallplace();
				} else {
					message = "该数据库信息不存在" + assetInfo.getId();
					dataerror = true;
				}
			} else if (devicetype.equals("ftp")) {
				ftp = ftpInfoService.getById(deviceid);
				if (ftp != null) {
					factory = factoryInfoService.getById(ftp.getFactoryId());
					device.setCode(assetInfo.getAssetcode());
					device.setIp(assetInfo.getIp());
					device.setName(assetInfo.getAssetname());
					device.setDeviceTypeNames(devicetype);
					position = assetInfo.getInstallplace();
				} else {
					message = "该ftp信息不存在" + assetInfo.getId();
					dataerror = true;
				}
			} else if (devicetype.equals("project")) {
				project = projectInfoService.getById(deviceid);
				if (project != null) {
					factory = factoryInfoService.getById(project.getFactoryId());
					device.setCode(assetInfo.getAssetcode());
					device.setIp(assetInfo.getIp());
					device.setName(assetInfo.getAssetname());
					device.setDeviceTypeNames(devicetype);
					position = assetInfo.getInstallplace();
				} else {
					message = "该平台信息不存在" + assetInfo.getId();
					dataerror = true;
				}
			}

			if (dataerror) {
				logger.info("自动派发失败     ----》" + message);
				continue;
			}

			// 4.设置工单信息

			// 设置工单基本信息
			workorder.setDevicecode(device.getCode());
			workorder.setDeviceip(device.getIp());
			workorder.setDevicename(device.getName());
			workorder.setDevicetype(devicetype);
			workorder.setPosition(position);
			workorder.setCompanyid(factory.getId());
			if(device.getOrgId() != null && !"".equals(device.getOrgId())){
				workorder.setOrgid(device.getOrgId());
			}
			
			User userContact = contactList.get(0);
			// 当维护人员不止一个的时候，判断谁的工单少
			for (int i = 0; i < contactList.size() - 1; i++) {
				for (int j = 0; j < contactList.size() - i - 1; j++) {
					User user1 = contactList.get(j);
					User user2 = contactList.get(j + 1);
					int count1 = workorderInfoService.countByContact(user1.getId());
					int count2 = workorderInfoService.countByContact(user2.getId());
					if (count1 > count2) {
						userContact = user2;
					}
				}
			}
			// 获取工单最少的维护人员的工单数量
			int userOrder = workorderInfoService.countByContact(userContact.getId());
			// 当数量大于配置的工单数时，不派发。

			SystemParams maxworder = paramsService.getParamsByKeyAndType("maxworder", "1");
			if (userOrder >= Integer.parseInt(maxworder.getValue())) {
				message = "负责该资产:" + device.getName() + "的:  "+ userContact.getName() + "工单数量太多";
				logger.info("自动派发失败     ----》" + message);
				continue;
			}

			// 设置工单信息
			workorder.setContact(userContact.getId());
			workorder.setTelephone(userContact.getMobile());

			workorder.setId(UuidGenerateUtil.getUUID());

			if (devicetype.equals("device")) {
				workorder.setOrgid(device.getOrgId());
			} else if (devicetype.equals("video")) {
				workorder.setOrgid(video.getOrgId());
			} else if (devicetype.equals("server")) {
				workorder.setOrgid(server.getOrgId());
			} else if (devicetype.equals("cabinet")) {
				workorder.setOrgid(cabInfo.getOrgId());
				workorder.setIsouter("0");
			} else {
				workorder.setIsouter("1");
			}
			if (devicetype.equals("database") || devicetype.equals("ftp") || devicetype.equals("project")) {
				workorder.setDevicecode(workorder.getId());
			}
			
			
			String company1 = userContact.getName() + "，你好：位于[" + position + "]的" + device.getName() +"设备发生故障。";
			//String company1 = factory.getName() + "的" + userContact.getName() + "，你好：" + "位于[" + position + "]上的设备编号为[" + device.getCode() + "]的设备[" + device.getName() + "]发生故障，请及时维修。";
			workorder.setMessages(company1);
			workorder.setAssignaccount("00");
			workorder.setAssigntime(new Date());
			workorder.setAssetid(assetInfo.getId());

			// 5.提交工单
			try {
				workorderInfoService.save(workorder);
				device.setIssend("1");
				deviceInfoService.updateByIdSelective(device);

				// 发送短信服务
				SendTask sendTask = new SendTask();
				sendTask.setDestnumber(userContact.getMobile());
				sendTask.setContent(company1);
				sendTaskService.save(sendTask);
			} catch (Exception e) {
				message = "短信服务或者工单提交保存失败";
				logger.info("自动派发失败" + message);
				continue;
			}
			// 6.更新自动分派状态
			assetInfo.setPayoutstatus("1");
			assetInfoService.updateByIdSelective(assetInfo);

			// 添加日志信息
			WorkorderLog log = new WorkorderLog();
			log.setId(UuidGenerateUtil.getUUID());
			log.setOperator("00");
			log.setAccount("自动派发");
			log.setOpertime(new Date());
			log.setOpercontent("工单派发");
			log.setWorkstatus(WorkorderStatus.ASSIGNED.getValue());
			log.setWorkid(workorder.getId());
			workorderLogService.save(log);
			message = "资产名称："+ assetInfo.getAssetname();
			logger.info("自动派发成功" + message);
		}
		logger.info("根据系统参数配置，自动扫描是否要进行工单自动派发功能结束......");
	}

	private List<String> filterConditions() {
		List<SystemParams> openArry = paramsService.selectByMHName("torder");
		List<String> listAray = new ArrayList<String>();
		for (int i = 0; i < openArry.size(); i++) {
			SystemParams systemParams = openArry.get(i);
			if ("0".equals(systemParams.getValue())) {
				continue;
			}
			String name = systemParams.getName();
			String replace = name.replace("Status", "_Status");
			listAray.add(replace.substring(6, replace.length()));
		}
		return listAray;
	}

	private boolean filterDate(AssetInfo assetInfo, List<String> listAray) {
		boolean flag = false;
		List<CabinetAbnormalType> catTypeList = new ArrayList<>();
		for (String type : listAray) {
			CabinetAbnormalType aType = CabinetAbnormalType.getByName(type);
			if (aType != CabinetAbnormalType.UnKnown)
				catTypeList.add(aType);
		}
		for (String type : listAray) {
			if (assetInfo.getAssettype().equals("server")) {
				if ("Cpu_Status".equals(type)) {
					if (assetInfo.getCpuStatus() == null || !"1".endsWith(assetInfo.getCpuStatus())) {
						flag = true;
						break;
					}
				}
				if ("Memory_Status".equals(type)) {
					if (assetInfo.getMemoryStatus() == null || !"1".endsWith(assetInfo.getMemoryStatus())) {
						flag = true;
						break;
					}
				}
				if ("Disk_Status".equals(type)) {
					if (assetInfo.getDiskStatus() == null || !"1".equals(assetInfo.getDiskStatus())) {
						flag = true;
						break;
					}
				}
			}

			if (assetInfo.getAssettype().equals("device")) {
				if ("Power_Status".equals(type)) {
					if (assetInfo.getPowerStatus() == null || !"1".equals(assetInfo.getPowerStatus())) {
						flag = true;
						break;
					}
				}
				if ("Net_Status".equals(type)) {
					if (assetInfo.getNetStatus() == null || !"1".equals(assetInfo.getNetStatus())) {
						flag = true;
						break;
					}
				}
				if ("Data_Status".equals(type)) {
					if (assetInfo.getDataStatus() == null || !"1".equals(assetInfo.getDataStatus())) {
						flag = true;
						break;
					}
				}
			}

			if (assetInfo.getAssettype().equals("cabinet")) {
				if (catTypeList.size() > 0) {
					for (CabinetAbnormalType catype : catTypeList) {
						if ((assetInfo.getCabinetStatus() & catype.getId()) == catype.getId()) {
							flag = true;
							break;
						}
					}
				}
			}

			if (assetInfo.getAssettype().equals("database") || assetInfo.getAssettype().equals("ftp") || assetInfo.getAssettype().equals("project")) {
				if ("Net_Status".equals(type)) {
					if (assetInfo.getNetStatus() == null || !"1".equals(assetInfo.getNetStatus())) {
						flag = true;
						break;
					}
				}
				if ("Data_Status".equals(type)) {
					if (assetInfo.getDataStatus() == null || !"1".equals(assetInfo.getDataStatus())) {
						flag = true;
						break;
					}
				}
			}

		}
		return flag;
	}

	/**
	 * 设置服务厂商联系人jsondata
	 * 
	 * @author kouyunhao
	 * @version 1.0
	 * @param builderList Mar 6, 2014
	 */
	public void setContactData(List<User> contactList) {
		if (contactList != null && contactList.size() != 0) {
			for (User contact : contactList) {
				try {
					contact.setUserdata(JsonUtil.toJson(contact));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/***
	 * 获取登陆用户id
	 * 
	 * @return
	 */
	protected String getCurrentUserId() {
		OperatorDetails userInfo = getCurrentUser();
		if (userInfo == null) {
			throw new UsernameNotFoundException("用户不存在");
		}
		return userInfo.getId();
	}

	/***
	 * 获取登陆用户
	 * 
	 * @return
	 */
	protected OperatorDetails getCurrentUser() {
		return SpringSecurityUtils.getCurrentUser();
	}
}
