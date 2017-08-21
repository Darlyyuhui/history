package com.xiangxun.atms.module.property.web;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiangxun.atms.common.dictionary.service.DicService;
import com.xiangxun.atms.common.user.service.UserService;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.compnents.xls.export.template.ExcelEntity;
import com.xiangxun.atms.framework.compnents.xls.export.template.ExcelMerge;
import com.xiangxun.atms.framework.compnents.xls.export.template.SimplePostExportProssor;
import com.xiangxun.atms.framework.compnents.xls.export.template.SimpleXlsExportor;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.module.property.domain.DeviceInfo;
import com.xiangxun.atms.module.property.domain.DeviceInfoHis;
import com.xiangxun.atms.module.property.domain.DeviceTypeInfo;
import com.xiangxun.atms.module.property.domain.ModifyRecord;
import com.xiangxun.atms.module.property.domain.ServerInfoHis;
import com.xiangxun.atms.module.property.domain.VideoInfoHis;
import com.xiangxun.atms.module.property.service.DeviceInfoHisService;
import com.xiangxun.atms.module.property.service.DeviceInfoService;
import com.xiangxun.atms.module.property.service.DeviceTypeService;
import com.xiangxun.atms.module.property.service.ModifyRecordService;
import com.xiangxun.atms.module.property.service.ServerInfoHisService;
import com.xiangxun.atms.module.property.service.ServerInfoService;
import com.xiangxun.atms.module.property.service.VideoInfoHisService;
import com.xiangxun.atms.module.property.service.VideoInfoService;
import com.xiangxun.atms.module.sergrade.service.FactoryInfoService;

/**
 * 变更记录操作
 * @author kouyunhao
 *
 */
@Controller
@RequestMapping(value = "modify/record")
public class ModifyRecordCtl extends BaseCtl {
	
	@Resource
	ModifyRecordService modifyRecordService;
	
	@Resource
	DeviceInfoHisService deviceInfoHisService;
	
	@Resource
	DeviceInfoService deviceInfoService;
	
	@Resource
	VideoInfoHisService videoInfoHisService;
	
	@Resource
	VideoInfoService videoInfoService;
	
	@Resource
	ServerInfoHisService serverInfoHisService;
	
	@Resource
	ServerInfoService serverInfoService;
	
	@Resource
	DeviceTypeService deviceTypeService;
	
	@Resource
	FactoryInfoService factoryInfoService;
	
	@Resource
	DicService dicService;
	
	@Resource
	UserService userService;
	
	/**
	 * 变更记录查询
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "search/{menuid}/")
	public String search(@PathVariable
	String menuid, ModelMap model,
			@RequestParam(value = "sortType", defaultValue = "MODULE_NAME")
			String sortType, @RequestParam(value = "page", defaultValue = "0")
			int pageNumber, HttpServletRequest request) {

		logger.info("正在进行设备列表查询。。。。。。");
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		//按照版本号（VERSION）倒叙排列。
		searchParams.put("sortType", "VERSION desc");
		int[] times = {1,2,3};
		searchParams.put("times", times);
		Page page = modifyRecordService.getListByCondition(searchParams,
				pageNumber, Page.DEFAULT_PAGE_SIZE, sortType, menuid);

		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets
				.encodeParameterStringWithPrefix(searchParams, "search_"));
		ModifyRecord record = new ModifyRecord();
		try {
			BeanUtils.populate(record, searchParams);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		model.addAttribute("record", record);
		return "modify/record/search";
	}
	
	/**
	 * 获取详情
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}/{menuid}", method = RequestMethod.GET)
	public String getView(@PathVariable("id") String id, @PathVariable("menuid") String menuid, Model model, HttpServletRequest request) {
		//查询变更记录信息
		ModifyRecord record = modifyRecordService.getById(id);
		String modifyId = record.getModifyId();
		String type = record.getModuleName();
		//查询对应历史表信息
		Map<String, Object> beforeParams = new HashMap<String, Object>();
		Map<String, Object> afterParams = new HashMap<String, Object>();
		beforeParams.put("id", modifyId);
		String afterId = "";
		boolean isLatest = false;
		Page before = null;
		Page after = null;
		if(type.equals("device")){
			before = deviceInfoHisService.getListByCondition(beforeParams,
					1, 1, null, menuid);
//			String afterId = setDeviceFileds(before);
			Object obj = before.getResult();
			if(obj!=null && obj instanceof List){
				List<DeviceInfoHis> list = (List<DeviceInfoHis>)obj;
				if(list != null && list.size() != 0){
					DeviceInfoHis history = list.get(0);
					afterId = history.getAfterRecordId();
					
					//解决报错
					if(history.getCode().length() >= 14){
						history.setDevicetypecode(history.getCode().substring(12, 14));

					}
					//history.setDevicetypecode(history.getCode().substring(12, 14));
					StringBuffer dtNameStrs = new StringBuffer("");
					List<DeviceTypeInfo> dtList = deviceTypeService.getTypeByDeviceId(history.getId());
					for (int j = 0; j < dtList.size(); j++) {
						DeviceTypeInfo typeInfo = dtList.get(j);
						dtNameStrs.append(typeInfo.getName());
						if (j < dtList.size() - 1) {
							dtNameStrs.append(",");
						}
					}
					history.setDeviceTypeNames(dtNameStrs.toString());
				}
			}
			afterParams.put("id", afterId);
			after = deviceInfoHisService.getListByCondition(afterParams,
					1, 1, null, menuid);
			if(after.getTotalSize() == 0){
				after = deviceInfoService.getListByCondition(afterParams,
						1, 1, null, menuid);
				Object obj1 = after.getResult();
				if(obj1 !=null && obj1 instanceof List){
					List<DeviceInfo> list = (List<DeviceInfo>)obj1;
					if(list != null && list.size() != 0){
						DeviceInfo history = list.get(0);
						
						if(history.getCode().length() >= 14){
							history.setDevicetypecode(history.getCode().substring(12, 14));
							
						}
						//history.setDevicetypecode(history.getCode().substring(12, 14));
						
						
						StringBuffer dtNameStrs = new StringBuffer("");
						List<DeviceTypeInfo> dtList = deviceTypeService.getTypeByDeviceId(history.getId());
						for (int j = 0; j < dtList.size(); j++) {
							DeviceTypeInfo typeInfo = dtList.get(j);
							dtNameStrs.append(typeInfo.getName());
							if (j < dtList.size() - 1) {
								dtNameStrs.append(",");
							}
						}
						history.setDeviceTypeNames(dtNameStrs.toString());
					}
				}
				isLatest = true;
			}else{
				Object obj2 = after.getResult();
				if(obj2 !=null && obj2 instanceof List){
					List<DeviceInfoHis> list = (List<DeviceInfoHis>)obj2;
					if(list != null && list.size() != 0){
						DeviceInfoHis history = list.get(0);
						
						if(history.getCode().length() >= 14){
							history.setDevicetypecode(history.getCode().substring(12, 14));							
						}
						//history.setDevicetypecode(history.getCode().substring(12, 14));
						
						
						StringBuffer dtNameStrs = new StringBuffer("");
						List<DeviceTypeInfo> dtList = deviceTypeService.getTypeByDeviceId(history.getId());
						for (int j = 0; j < dtList.size(); j++) {
							DeviceTypeInfo typeInfo = dtList.get(j);
							dtNameStrs.append(typeInfo.getName());
							if (j < dtList.size() - 1) {
								dtNameStrs.append(",");
							}
						}
						history.setDeviceTypeNames(dtNameStrs.toString());
					}
				}
			}
		}else if(type.equals("video")){
			before = videoInfoHisService.getListByCondition(beforeParams,
					1, 1, null, menuid);
			Object obj = before.getResult();
			if(obj!=null && obj instanceof List){
				List<VideoInfoHis> list = (List<VideoInfoHis>)obj;
				if(list != null && list.size() != 0){
					VideoInfoHis history = list.get(0);
					afterId = history.getAfterRecordId();
				}
			}
			afterParams.put("id", afterId);
			after = videoInfoHisService.getListByCondition(afterParams,
					1, 1, null, menuid);
			if(after.getTotalSize() == 0){
				after = videoInfoService.getListByCondition(afterParams,
						1, 1, null, menuid);
				isLatest = true;
			}
		}else if(type.equals("server")){
			before = serverInfoHisService.getListByCondition(beforeParams,
					1, 1, null, menuid);
			Object obj = before.getResult();
			if(obj!=null && obj instanceof List){
				List<ServerInfoHis> list = (List<ServerInfoHis>)obj;
				if(list != null && list.size() != 0){
					ServerInfoHis history = list.get(0);
					afterId = history.getAfterRecordId();
				}
			}
			afterParams.put("id", afterId);
			after = serverInfoHisService.getListByCondition(afterParams,
					1, 1, null, menuid);
			if(after.getTotalSize() == 0){
				after = serverInfoService.getListByCondition(afterParams,
						1, 1, null, menuid);
				isLatest = true;
			}
		}
		model.addAttribute("record", record);
		model.addAttribute("type", type);
		model.addAttribute("before", before);
		model.addAttribute("after", after);
		model.addAttribute("isLatest", isLatest);
		model.addAttribute("menuid", menuid);
		return "modify/record/view";
	}
	
	public String setDeviceFileds(Page page){
		String afterId = "";
		Object obj = page.getResult();
		if(obj!=null && obj instanceof List){
			List<DeviceInfoHis> list = (List<DeviceInfoHis>)obj;
			if(list != null && list.size() != 0){
				DeviceInfoHis history = list.get(0);
				afterId = history.getAfterRecordId();
				history.setDevicetypecode(history.getCode().substring(12, 14));
				StringBuffer dtNameStrs = new StringBuffer("");
				List<DeviceTypeInfo> dtList = deviceTypeService.getTypeByDeviceId(history.getId());
				for (int j = 0; j < dtList.size(); j++) {
					DeviceTypeInfo typeInfo = dtList.get(j);
					dtNameStrs.append(typeInfo.getName());
					if (j < dtList.size() - 1) {
						dtNameStrs.append(",");
					}
				}
				history.setDeviceTypeNames(dtNameStrs.toString());
			}
		}
		return afterId;
	}
	
	/***
	 * 变更记录导出
	 * @param menuid
	 * @param model
	 * @param request
	 * @param resp
	 */
	@LogAspect(desc="变更记录导出")
	@RequestMapping(value = "doExport/{menuid}/",method=RequestMethod.GET)
	private void export(String menuid, ModelMap model,HttpServletRequest request,HttpServletResponse response){
		// 3 表示从第几行开始写入
		SimpleXlsExportor export = new SimpleXlsExportor(3);
				
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		int[] times = {1,2,3};
		searchParams.put("times", times);
		Page page = modifyRecordService.getListByCondition(searchParams, 0,999999, "VERSION desc", menuid);
	
		// 设置内容
		List result = new ArrayList();
		Object obj = page.getResult();
		List<ModifyRecord> reports = null;
		if(obj!=null && obj instanceof List){
			reports = (List<ModifyRecord>)obj;
			if(reports != null && reports.size() != 0){
				for (int i = 0; i < reports.size(); i++) {
					Object[] values = new Object[9];
					ModifyRecord report = reports.get(i);
					values[0] = i+1;
					values[1] = report.getModuleName();
					values[2] = report.getModifyType() == null ? null : report.getModifyType();
					values[3] = report.getModifyOperator() == null ? null : report.getModifyOperator();
					values[4] = report.getModifyDatetime() == null ? null : DateUtil.formatTime("yyyy-MM-dd", report.getModifyDatetime());
					values[5] = report.getModifyReason() == null ? null : report.getModifyReason();
					values[6] = report.getVersion();
					values[7] = report.getOperator() == null ? null : userService.getById(report.getOperator()).getName();
					values[8] = report.getOperatorTime() == null ? null : DateUtil.formatTime("yyyy-MM-dd HH:mm:ss", report.getOperatorTime());
					result.add(values);
				}
			}
		}
		//统计日期
		String day = DateUtil.formatTime("yyyy-MM-dd HH:mm:ss", new Date());
		
		//构造要修改的值集合
		List<ExcelEntity> excels = new ArrayList<ExcelEntity>();
		//修改统计日期的值
		excels.add(new ExcelEntity(1,0,"统计日期："+day+"　总计："+reports.size()));
		List<ExcelMerge> merges = new ArrayList<ExcelMerge>();
		ExcelEntity[] t = new ExcelEntity[]{};
		ExcelMerge[] h = new ExcelMerge[]{};
		
		//将需要动态替换的数组传入PostExportProssor，导出时会进行自动处理
		export.setPostProcessor(new SimplePostExportProssor(excels.toArray(t),merges.toArray(h)));
	
		export.setFileName("资产变更记录统计分析报表("+day+").xls");
		export.exportExcelFileStream("xls" + File.separator + "modify_exp.xls", null, result, response);
	}

}
