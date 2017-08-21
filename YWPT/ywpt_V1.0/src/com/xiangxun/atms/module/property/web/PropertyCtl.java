package com.xiangxun.atms.module.property.web;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiangxun.atms.common.dictionary.service.DicService;
import com.xiangxun.atms.common.dictionary.type.DicType;
import com.xiangxun.atms.common.dictionary.vo.Dic;
import com.xiangxun.atms.common.system.service.ParamsService;
import com.xiangxun.atms.common.system.vo.SystemParams;
import com.xiangxun.atms.framework.base.ApplicationContextHolder;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.module.property.domain.DatabaseInfo;
import com.xiangxun.atms.module.property.domain.DeviceInfo;
import com.xiangxun.atms.module.property.domain.DeviceTypeInfo;
import com.xiangxun.atms.module.property.domain.FtpInfo;
import com.xiangxun.atms.module.property.domain.ProjectInfo;
import com.xiangxun.atms.module.property.domain.ShowIcabinetStatus;
import com.xiangxun.atms.module.property.service.DatabaseInfoService;
import com.xiangxun.atms.module.property.service.DeviceInfoHisService;
import com.xiangxun.atms.module.property.service.DeviceInfoService;
import com.xiangxun.atms.module.property.service.DeviceTypeService;
import com.xiangxun.atms.module.property.service.FtpInfoService;
import com.xiangxun.atms.module.property.service.ProjectInfoService;
import com.xiangxun.atms.module.property.service.ServerInfoService;
import com.xiangxun.atms.module.property.service.VideoInfoService;
import com.xiangxun.atms.module.sergrade.domain.FactoryInfo;
import com.xiangxun.atms.module.sergrade.service.FactoryInfoService;

/**
 * 资产配置管理首页
 * @author kouyunhao
 *
 */
@Controller
@RequestMapping(value = "property/main")
public class PropertyCtl extends BaseCtl {
	
	@Resource
	DeviceInfoService deviceInfoService;
	
	@Resource
	VideoInfoService videoInfoService;
	
	@Resource
	ServerInfoService serverInfoService;
	
	@Resource
	DeviceInfoHisService deviceInfoHisService;
	
	@Resource
	DatabaseInfoService databaseInfoService;
	
	@Resource
	FtpInfoService ftpInfoService;
	
	@Resource
	ProjectInfoService projectInfoService;
	
	@Resource
	DeviceTypeService deviceTypeService;
	
	@Resource
	FactoryInfoService factoryInfoService;
	
	@Resource
	DicService dicService;
	
	@Resource
	ParamsService paramsService;
	
	@RequestMapping(value="show/{menuid}/")
	public String show(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "NAME desc") String sortType,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,@RequestParam(value = "tab", defaultValue = "all") String tab,HttpServletRequest request){
		String deviceSort = "INSERTTIME desc";
		String videoSort = "INSERTTIME desc";
		String serverSort = "ADD_TIME desc";
		devicestatus(model, deviceSort, pageNumber, 7, menuid);
		videostatus(model, videoSort, pageNumber, 7, menuid);
		serverstatus(model, serverSort, pageNumber, 7, menuid);
		cabinetstatus(model, sortType, pageNumber, 7, menuid);
		databasestatus(model, sortType, pageNumber, 7);
		ftpstatus(model, sortType, pageNumber, 7);
		projectstatus(model, sortType, pageNumber, 7);
		model.addAttribute("page", pageNumber);
		
		//tianbo增加获取系统参数勾选的参数
		List<SystemParams> openInfoList = paramsService.selectByMHName("open");
		//去除掉value为0的系统参数"opendevice",
		String[] paramsType = new String[] {"openserver", "opendatabase", "openftp", "openproject"};
		for (int i = openInfoList.size() - 1; i >= 0; i--) {
			SystemParams systemParams = openInfoList.get(i);
			if("0".equals(systemParams.getValue())){
				openInfoList.remove(i);
			}else{
				if(!isHave(paramsType,systemParams.getName())){
					openInfoList.remove(i);
				}
			}
		}
		model.addAttribute("openInfoList", openInfoList);
		//去除掉value为0的系统参数"openserver", "opendatabase", "openftp", "openproject",只需留下“opendevice”和“opencabinet”
		List<SystemParams> openInfoList1 = paramsService.selectByMHName("open");
		String[] paramsType1 = new String[] {"opendevice", "opencabinet"};
		for (int i = openInfoList1.size() - 1; i >= 0; i--) {
			SystemParams systemParams1 = openInfoList1.get(i);
			if("0".equals(systemParams1.getValue())){
				openInfoList1.remove(i);
			}else{
				if(!isHave(paramsType1,systemParams1.getName())){
					openInfoList1.remove(i);
				}
			}
		}
		model.addAttribute("openInfoList1",openInfoList1);
		return "property/main/show";
	}
	
	public static boolean isHave(String[] strs,String s){ 
		/*此方法有两个参数，第一个是要查找的字符串数组，第二个是要查找的字符或字符串 
		* */ 
		for(int i=0;i<strs.length;i++){ 
		if(strs[i].indexOf(s)!=-1){//循环查找字符串数组中的每个字符串中是否包含所有查找的内容 
		return true;//查找到了就返回真，不在继续查询 
		} 
		} 
		return false;//没找到返回false 
		} 

	
	/**
	 * 卡口设备检测信息
	 * @param model
	 * @param sortType
	 * @param pageNumber
	 * @param pageSize
	 */
	public void devicestatus(ModelMap model, String sortType, int pageNumber, int pageSize, String menuid){
		Map<String,Object> map = new HashMap<String,Object>();
		Page page = deviceInfoService.getListByCondition(map,pageNumber, pageSize, sortType, menuid);
		getDeviceTypeNames(page, model);
		model.addAttribute("devicepage", page);
		
	}
	
	public void getDeviceTypeNames(Page page, ModelMap model){
		/**
		 * add by kouyunhao 2013-08-12 增加设备类型和设备功能到查询结果集中
		 */
		List<Dic> deviceTypes = dicService.getDicByType(DicType.DEVICE_TYPE);
		model.addAttribute("deviceTypeDic", deviceTypes);
		for(int i = 0; i < page.getSize(); i++){
			DeviceInfo devInfo = (DeviceInfo) page.getResult().get(i);
			if(devInfo != null){
				//添加是否变更判断
				boolean hasModified = deviceInfoHisService.hasRecordModified(devInfo.getId());
				devInfo.setHasModified(hasModified);
				
				if(devInfo.getCode().length() >= 14 ){
					devInfo.setDevicetypecode(devInfo.getCode().substring(12, 14));

				}
				//devInfo.setDevicetypecode(devInfo.getCode().substring(12, 14));
				StringBuffer dtNameStrs = new StringBuffer("");
				List<DeviceTypeInfo> dtList = deviceTypeService.getTypeByDeviceId(devInfo.getId());
				for (int j = 0; j < dtList.size(); j++) {
					DeviceTypeInfo typeInfo = dtList.get(j);
					dtNameStrs.append(typeInfo.getName());
					if (j < dtList.size() - 1) {
						dtNameStrs.append(",");
					}
				}
				devInfo.setDeviceTypeNames(dtNameStrs.toString());
			}
		}
	}
	
	/**
	 * 监控设备检测信息
	 * @param model
	 * @param sortType
	 * @param pageNumber
	 * @param pageSize
	 */
	public void videostatus(ModelMap model, String sortType, int pageNumber, int pageSize, String menuid){
		Map<String,Object> map = new HashMap<String,Object>();
		Page page = videoInfoService.getListByCondition(map,pageNumber, pageSize, sortType, menuid);
		model.addAttribute("videopage", page);
		
	}
	
	/**
	 * 服务器设备检测信息
	 * @param model
	 * @param sortType
	 * @param pageNumber
	 * @param pageSize
	 */
	public void serverstatus(ModelMap model, String sortType, int pageNumber, int pageSize, String menuid){
		Map<String,Object> map = new HashMap<String,Object>();
		Page page = serverInfoService.getListByCondition(map,pageNumber, pageSize, sortType, menuid);
		model.addAttribute("serverpage", page);
		
	}
	
	/**
	 * 机柜检测信息
	 * @param model
	 * @param sortType
	 * @param pageNumber
	 * @param pageSize
	 */
	public void cabinetstatus(ModelMap model, String sortType, int pageNumber, int pageSize,String menuid){
		
		List<ShowIcabinetStatus> status = new ArrayList<>();
		String assetName="",assetCode="",orgID="";
		
		int totalCount = getCabinetStatus(assetName, assetCode, orgID, pageNumber, pageSize, status);

		Page page = Page.getPage(totalCount, status, pageNumber, pageSize);
		model.addAttribute("cabinetpage", page);
	}
	
	/**
	 * 调用存储过程查询机柜状态信息
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param statusList
	 * @return
	 */

	private int getCabinetStatus(String assetName, String assetCode, String orgID, int pageNo, int pageSize, List<ShowIcabinetStatus> statusList) {
		int rowCount = 0;
		String sql = "{call sp_icabinet_status_get(?,?,?,?,?,?,?)}";
		DataSource ds = (DataSource) ApplicationContextHolder.getBean("dataSource");
		try (Connection conn = ds.getConnection(); CallableStatement stat = conn.prepareCall(sql)) {
			stat.setString(1, assetName);
			stat.setString(2, assetCode);
			stat.setInt(3, pageNo);
			stat.setInt(4, pageSize);
			stat.setString(5, orgID);
			stat.registerOutParameter(6, Types.INTEGER);
			stat.registerOutParameter(7, Types.CLOB);
			stat.execute();
			rowCount = stat.getInt(6);
			BufferedReader br = new BufferedReader(stat.getClob(7).getCharacterStream());
			StrBuilder xml = new StrBuilder();
			String row = br.readLine();
			while (StringUtils.isNotEmpty(row)) {
				xml.appendln(row);
				row = br.readLine();
			}			
			SAXReader sr = new SAXReader();
			try (InputStream in = new ByteArrayInputStream(xml.toString().getBytes("UTF-8"))) {
				Document document = sr.read(in);
				Element root = document.getRootElement();
				List<Element> elementList = root.elements();				
				for (Element e : elementList) {					
					ShowIcabinetStatus status = new ShowIcabinetStatus();
					status.setIp(e.elementText("ip"));
					status.setAssetname(e.elementText("name"));
					status.setACcurrent(e.elementText("current"));
					status.setACvoltage(e.elementText("voltage"));
					status.setAssetcode(e.elementText("code"));
					status.setAssetstatus(e.elementText("status"));
					status.setHumidity(e.elementText("humidity"));
					status.setTemperature(e.elementText("temperature"));
					status.setSmoke(e.elementText("smoke"));
					status.setShock(e.elementText("shock"));
					status.setId(e.elementText("code"));
					status.setAssettype(e.elementText("type"));
					status.setACterminal(e.elementText("ac"));
					status.setACcurrentvalue(e.elementText("v_socket_current"));
					status.setACvoltagevalue(e.elementText("v_socket_voltage"));
					status.setHumidityvalue(e.elementText("v_humidity"));
					status.setTemperaturevalue(e.elementText("v_temperature"));
					status.setShockvalue(e.elementText("v_shock"));
					status.setServiceid(e.elementText("serviceid"));
					status.setPayoutstatus(e.elementText("payoutstatus"));
					status.setPowerStatus(e.elementText("power_status"));
					statusList.add(status);

				}

			} catch (IOException | DocumentException e1) {

				e1.printStackTrace();
			}
		} catch (SQLException | IOException e2) {
			e2.printStackTrace();
		}
		return rowCount;
	}
	
	
	
	/**
	 * 数据库检测信息
	 * @param model
	 * @param sortType
	 * @param pageNumber
	 * @param pageSize
	 */
	public void databasestatus(ModelMap model, String sortType, int pageNumber, int pageSize){
		Map<String,Object> map = new HashMap<String,Object>();
		Page page = databaseInfoService.getListByCondition(map,pageNumber, pageSize, sortType);
		Object obj = page.getResult();
		if(obj!=null && obj instanceof List){
			@SuppressWarnings("unchecked")
			List<DatabaseInfo> list = (List<DatabaseInfo>)obj;
			if(list != null && list.size() != 0){
				for (DatabaseInfo resource : list) {
					FactoryInfo factory = factoryInfoService.getById(resource.getFactoryId());
					if(factory != null){
						resource.setFactoryName(factory.getName());
					}
				}
			}
		}
		model.addAttribute("databasepage", page);
		
	}
	
	/**
	 * FTP检测信息
	 * @param model
	 * @param sortType
	 * @param pageNumber
	 * @param pageSize
	 */
	public void ftpstatus(ModelMap model, String sortType, int pageNumber, int pageSize){
		Map<String,Object> map = new HashMap<String,Object>();
		Page page = ftpInfoService.getListByCondition(map,pageNumber, pageSize, sortType);
		Object obj = page.getResult();
		if(obj!=null && obj instanceof List){
			@SuppressWarnings("unchecked")
			List<FtpInfo> list = (List<FtpInfo>)obj;
			if(list != null && list.size() != 0){
				for (FtpInfo resource : list) {
					FactoryInfo factory = factoryInfoService.getById(resource.getFactoryId());
					if(factory != null){
						resource.setFactoryName(factory.getName());
					}
				}
			}
		}
		model.addAttribute("ftppage", page);
		
	}
	
	/**
	 * 平台检测信息
	 * @param model
	 * @param sortType
	 * @param pageNumber
	 * @param pageSize
	 */
	public void projectstatus(ModelMap model, String sortType, int pageNumber, int pageSize){
		Map<String,Object> map = new HashMap<String,Object>();
		Page page = projectInfoService.getListByCondition(map,pageNumber, pageSize, sortType);
		Object obj = page.getResult();
		if(obj!=null && obj instanceof List){
			@SuppressWarnings("unchecked")
			List<ProjectInfo> list = (List<ProjectInfo>)obj;
			if(list != null && list.size() != 0){
				for (ProjectInfo resource : list) {
					FactoryInfo factory = factoryInfoService.getById(resource.getFactoryId());
					if(factory != null){
						resource.setFactoryName(factory.getName());
					}
				}
			}
		}
		model.addAttribute("projectpage", page);
		
	}

}
