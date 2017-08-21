package com.xiangxun.atms.module.property.web;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.xiangxun.atms.common.deptment.service.DepartmentService;
import com.xiangxun.atms.common.deptment.vo.Department;
import com.xiangxun.atms.common.dictionary.service.DicService;
import com.xiangxun.atms.common.dictionary.type.DicType;
import com.xiangxun.atms.common.dictionary.vo.Dic;
import com.xiangxun.atms.common.road.service.RoadInfoService;
import com.xiangxun.atms.common.road.vo.RoadInfo;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.compnents.xls.export.template.SimpleXlsExportor;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderInfo;
import com.xiangxun.atms.module.eventalarm.service.WorkorderInfoService;
import com.xiangxun.atms.module.gis.domain.LayerBean;
import com.xiangxun.atms.module.gis.domain.LayerEnum;
import com.xiangxun.atms.module.gis.service.IMapService;
import com.xiangxun.atms.module.property.domain.AssetInfo;
import com.xiangxun.atms.module.property.domain.DeviceCompanyInfo;
import com.xiangxun.atms.module.property.domain.ModifyRecord;
import com.xiangxun.atms.module.property.domain.VideoAddressInfo;
import com.xiangxun.atms.module.property.domain.VideoInfo;
import com.xiangxun.atms.module.property.service.AssetInfoService;
import com.xiangxun.atms.module.property.service.DeviceCompanyInfoService;
import com.xiangxun.atms.module.property.service.ModifyRecordService;
import com.xiangxun.atms.module.property.service.VideoAddressService;
import com.xiangxun.atms.module.property.service.VideoInfoHisService;
import com.xiangxun.atms.module.property.service.VideoInfoService;
import com.xiangxun.atms.module.sergrade.domain.FactoryContact;
import com.xiangxun.atms.module.sergrade.domain.FactoryInfo;
import com.xiangxun.atms.module.sergrade.service.FactoryContactService;
import com.xiangxun.atms.module.sergrade.service.FactoryInfoService;

/***
 * 监控设备 信息维护
 * @author YanTao
 * @Apr 26, 2013 5:39:59 PM
 */
@Controller
@RequestMapping(value="property/videoinfo")
public class VideoInfoCtl extends BaseCtl{
	
	@Resource
	VideoInfoService videoInfoService;
	
	@Resource
	VideoInfoHisService videoInfoHisService;
	
	@Resource
	ModifyRecordService modifyRecordService;
	
	@Resource
	WorkorderInfoService workorderInfoService;
	
	@Resource
	DicService dicService;
	
	@Resource
	DepartmentService departmentService;
	
	@Resource
	DeviceCompanyInfoService deviceCompanyInfoService;
	
	@Resource
	VideoAddressService videoAddressService;
	
	@Resource
	RoadInfoService roadInfoService;
	
	@Resource
	FactoryInfoService factoryInfoService;
	
	@Resource
	FactoryContactService factoryContactService;
	
	@Resource
	AssetInfoService assetInfoService;
	
	@Resource
	IMapService mapService;
	
	//设置最大导出数据量（超过此设置可能导致数据导出不全）
	private static final int MAX_EXPORT_NUM = 10000;
	
	/***
	 *监控设备 列表页面
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list/{menuid}/")
	public String list(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "id") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,HttpServletRequest request){
		
		logger.info("正在进行监控设备信息列表查询。。。。。。");
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		//用来存放回显数据
		Map<String, Object> mapTemp = Servlets.getParametersStartingWith(request, "search_");
		
		//如果session中存在[设备信息]，则将session清空，防止session中有同名param，影响操作。
		Object menuinfoFlag = request.getSession().getAttribute("menuinfoFlag");
		if(menuinfoFlag != null){
			if(menuinfoFlag.equals("device")){
				request.getSession().removeAttribute("searchParams");
			}
		}
		//1 如果点击查询按钮，session里面存放的是选中的树节点（roadinfoId）和新的查询条件，此时将旧的查询条件从session中remove；
		//2 如果没有点击查询按钮，session中存放的是以前的查询条件（选中的树节点（roadinfoId）和旧的查询条件），不做删除。
		String sroadId = "";
		Object sessionParams = request.getSession().getAttribute("searchParams");
		if(sessionParams != null){
			Map<String, Object> paramsMap = (Map<String, Object>) sessionParams;
			if(request.getParameter("searchFlag") != null){
				if(request.getParameter("searchFlag").equals("1")){
					if(paramsMap.get("roadinfoId") != null){
						sroadId = (String) paramsMap.get("roadinfoId");
					}
					mapTemp.put("roadinfoId", sroadId);
					searchParams.put("roadinfoId", sroadId);
					
				}else if(request.getParameter("searchFlag").equals("2")){
					if(paramsMap.get("roadinfoId") != null){
						sroadId = (String) paramsMap.get("roadinfoId");
					}
					if(!sroadId.equals("")){
						paramsMap.remove("roadinfoId");
						searchParams.putAll(paramsMap);
						mapTemp.putAll(paramsMap);
					}
				}
				request.getSession().removeAttribute("searchParams");
			}else{
				searchParams.putAll(paramsMap);
				mapTemp.putAll(paramsMap);
			}
		}
		//========================== add by kouyunhao 2013-12-10 点击道路分组，显示道路下的设备信息start===============//
		Object obj = searchParams.get("roadinfoId");
		if(obj != null && !obj.equals("")){
			String roadid = (String)obj;
			if(roadid.equals("00")){
				searchParams.remove("roadinfoId");
			}else{
				String parentid = roadInfoService.getById(roadid).getPid();
				if(parentid.equals("00")){
					searchParams.put("roadinfoPid", roadid);
					searchParams.remove("roadinfoId");
				}
			}
		}
		else{
			if(!sroadId.equals("")){
				if(sroadId.equals("00")){
					searchParams.remove("roadinfoId");
				}else{
					String parentid = roadInfoService.getById(sroadId).getPid();
					if(parentid.equals("00")){
						searchParams.put("roadinfoPid", sroadId);
						searchParams.remove("roadinfoId");
					}
				}
			}
		}
		
		//========================== add by kouyunhao 2013-12-10 点击道路分组，显示道路下的设备信息end===============//
		
		
		//将查询条件放到session中
		request.getSession().setAttribute("searchParams", mapTemp);
		request.getSession().setAttribute("menuinfoFlag", "video");
		/**
		 * add by kouyunhao 2014-09-29 添加按照创建时间（INSERTTIME）倒叙排列。
		 */
		searchParams.put("sortType", "INSERTTIME desc");
		Page page = videoInfoService.getVideoInfoByCondition(searchParams,pageNumber,Page.DEFAULT_PAGE_SIZE,sortType,menuid);
		for(int i = 0; i < page.getSize(); i++){
			VideoInfo videoInfo = (VideoInfo) page.getResult().get(i);
			if(videoInfo != null){
				//添加是否变更判断
				boolean hasModified = videoInfoHisService.hasRecordModified(videoInfo.getId());
				videoInfo.setHasModified(hasModified);
			}
		}
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(mapTemp, "search_"));
		
		//设备厂家
		List<DeviceCompanyInfo> companyList = deviceCompanyInfoService.findAll();
		model.addAttribute("companyList", companyList);
		
		// 服务厂商
		List<FactoryInfo> factoryList = factoryInfoService
				.findAll();
		model.addAttribute("factoryList", factoryList);
		
		//部门下拉树显示
		JsonArray departmentjsonArray = departmentService.getDeptJsonArray(menuid);
		model.addAttribute("departmentjsonArray",departmentjsonArray.toString());
		
		//监控类型
		List<Dic> videoTypeList = dicService.getDicByType(DicType.MONITOR_TYPE);
		model.addAttribute("videoTypeList", videoTypeList);
		
		//监控方向
		List<Dic> directList = dicService.getDicByType(DicType.DIRECT);
		model.addAttribute("directList", directList);
		
		VideoInfo videoInfo = new VideoInfo();
		try {
			BeanUtils.populate(videoInfo, mapTemp);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		model.addAttribute("videoInfo", videoInfo);
		
		return "property/videoinfo/list";
	}
	
	/***
	 * 跳转到iframe上一级页面 add by kouyunhao 2013-07-18
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="sublist/{menuid}/")
	public String iframe_list(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "name") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,HttpServletRequest request){
		
		this.list(menuid, model, sortType, pageNumber, request);
		
		return "property/videoinfo/sublist";
	}
	
	/**
	 * 将页面中的查询条件从session中删除
	 * @author kouyunhao
	 * @version 1.0
	 * @param request
	 * @return
	 * Jun 10, 2014
	 */
	@RequestMapping(value="del_earchparams",method = RequestMethod.GET)
	@ResponseBody
	@LogAspect(desc="删除查询条件")
	public ResponseEntity del_earchparams(HttpServletRequest request){
		ResponseEntity result = new ResponseEntity();
		try {
			Object sessionParams = request.getSession().getAttribute("searchParams");
			if(sessionParams != null){
				String sroadId = "";
				Map<String, Object> paramsMap = (Map<String, Object>) sessionParams;
				if(paramsMap.get("roadinfoId") != null){
					sroadId = (String) paramsMap.get("roadinfoId");
				}
				Map<String, Object> newParamsMap = new HashMap<String, Object>();
				newParamsMap.put("roadinfoId", sroadId);
				request.getSession().setAttribute("searchParams", newParamsMap);
			}
			result.setResult("ok");
			return result;
		} catch (RuntimeException e) {
			logger.error(e);
			result.setResult("error");
			return result;
		}
	} 
	
	/***
	 * 监控设备信息添加页面显示
	 * @param id
	 * @param menuid
	 * @param page
	 * @param model  
	 * @return
	 */
	@RequestMapping(value="showadd/{menuid}/{roadid}/",method=RequestMethod.GET)
	public String showVideoInfoAdd(@PathVariable("menuid") String menuid,@PathVariable("roadid") String roadid,String page,Model model,RedirectAttributes redirectAttributes){
		RoadInfo roadInfo = roadInfoService.getById(roadid);
		String parentid = roadInfo.getPid();
		if(parentid.equals("00")){
			redirectAttributes.addFlashAttribute("message","一级道路下不允许添加视频监控");
			return "redirect:/property/videoinfo/sublist/"+menuid+"/";
		}
		//设备厂家
		List<DeviceCompanyInfo> companyList = deviceCompanyInfoService.findAll();
		model.addAttribute("companyList", companyList);
		
		// 服务厂商
		List<FactoryInfo> factoryList = factoryInfoService.findAll();
		model.addAttribute("factoryList", factoryList);
		
		//部门下拉树显示
		JsonArray departmentjsonArray = departmentService.getDeptJsonArray(menuid);
		model.addAttribute("departmentjsonArray",departmentjsonArray.toString());
		
		//监控类型
		List<Dic> videoTypeList = dicService.getDicByType(DicType.MONITOR_TYPE);
		model.addAttribute("videoTypeList", videoTypeList);
		
		//从字典表取  方向 DIRECTION_ID
		List<Dic> directList = dicService.getDicByType(DicType.DIRECT);
		model.addAttribute("directList",directList);
		
		// 从字典获得 行政区域 接入方式
		List<Dic> areacodeList = dicService
				.getDicByType(DicType.PLACEAREA_CODE);
		model.addAttribute("areacodeList", areacodeList);
		
		// 设备编号四位
		Integer maxNum = videoInfoService.getMaxCodeNum();
		if(maxNum==null || maxNum==0){
			maxNum = 1;
		}else{
			maxNum = maxNum + 1;
		}
		model.addAttribute("automaticNum", getMaxNum(maxNum));
		
		//播放配置模板
		List<VideoAddressInfo> realiVideoAddressList = videoAddressService.findIsRealiTimeAll("1");
		List<VideoAddressInfo> hisVideoAddressList = videoAddressService.findIsRealiTimeAll("0");
		model.addAttribute("realiVideoAddressList",realiVideoAddressList);
		model.addAttribute("hisVideoAddressList",hisVideoAddressList);
		
		
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		model.addAttribute("roadId",roadid);
		model.addAttribute("roadInfo",roadInfo);
		
		return "property/videoinfo/add";
	}
	
	/**
	 * 获取编码后四位最大值
	 * @author kouyunhao
	 * @version 1.0
	 * @param maxNum
	 * @return
	 * Dec 24, 2013
	 */
	public String getMaxNum(int maxNum){
		String result = "";
		char[] numArr = String.valueOf(maxNum).toCharArray();
		if(numArr.length == 1){
			result = "000" + maxNum;
		}else
		if(numArr.length == 2){
			result = "00" + maxNum;
		}else
		if(numArr.length == 3){
			result = "0" + maxNum;
		}else{
			result = "" + maxNum;
		}
		return result;
	}
	
	/***
	 * 监控设备 信息保存动作
	 * @param ftpInfo
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "doAdd", method = RequestMethod.POST)
	@LogAspect(desc="添加一个监控设备")
	public String doAdd(VideoInfo videoInfo,AssetInfo asset,String menuid,RedirectAttributes redirectAttributes) throws ParseException{
		//根据单选判断
		String usertemplate = videoInfo.getUsetemplate();
		if("1".equals(usertemplate)){
			videoInfo.setUsetemplate("1");
			videoInfo.setRealvideoaddressid(videoInfo.getRealvideoaddressid());
			videoInfo.setHisvideoaddressid(videoInfo.getHisvideoaddressid());
		}else{
			videoInfo.setUsetemplate("0");
			videoInfo.setRealvideoaddressid(null);
			videoInfo.setHisvideoaddressid(null);
			videoInfo.setIp(videoInfo.getIp());
			videoInfo.setPort(videoInfo.getPort());
			videoInfo.setTracode(videoInfo.getTracode());
			videoInfo.setUsername(videoInfo.getUsername());
			videoInfo.setPassword(videoInfo.getPassword());
			videoInfo.setIphis(videoInfo.getIphis());
			videoInfo.setPorthis(videoInfo.getPorthis());
			videoInfo.setTracodehis(videoInfo.getTracodehis());
			videoInfo.setUsernamehis(videoInfo.getUsernamehis());
			videoInfo.setPasswordhis(videoInfo.getPasswordhis());
		}
//		if("1".equals(usertemplate)){
//			//=========================== edit by kouyunhao 2013-08-16 解决设备信息修改时播放配置报空错误===============start//
//			List<VideoAddressInfo> realiVideoAddressList = videoAddressService.findIsRealiTimeAll("1");
//			List<VideoAddressInfo> hisVideoAddressList = videoAddressService.findIsRealiTimeAll("0");
//			for(VideoAddressInfo addrinfo : realiVideoAddressList){
//				if(videoInfo.getRealvideoaddressid().equals(addrinfo.getId())){
//					videoInfo.setIp(addrinfo.getIp());
//					videoInfo.setPort(addrinfo.getPort());
//					videoInfo.setUsername(addrinfo.getUsername());
//					videoInfo.setPassword(addrinfo.getPassword());
//					videoInfo.setTracode(addrinfo.getTracode());
//				}
//			}
//			for(VideoAddressInfo hisAddrinfo : hisVideoAddressList){
//				if(videoInfo.getHisvideoaddressid().equals(hisAddrinfo.getId())){
//					videoInfo.setIphis(hisAddrinfo.getIp());
//					videoInfo.setPorthis(hisAddrinfo.getPort());
//					videoInfo.setUsernamehis(hisAddrinfo.getUsername());
//					videoInfo.setPasswordhis(hisAddrinfo.getPassword());
//					videoInfo.setTracodehis(hisAddrinfo.getTracode());
//				}
//			}
//			//=========================== edit by kouyunhao 2013-08-16 解决设备信息修改时播放配置报空错误===============end//
//			/**videoInfo.setIp(null);
//			videoInfo.setPort(null);
//			videoInfo.setUsername(null);
//			videoInfo.setPassword(null);
//			videoInfo.setTracode(null);
//			videoInfo.setIphis(null);
//			videoInfo.setPorthis(null);
//			videoInfo.setUsernamehis(null);
//			videoInfo.setPasswordhis(null);
//			videoInfo.setTracodehis(null);*/
//		}else{
//			videoInfo.setRealvideoaddressid(null);
//			videoInfo.setHisvideoaddressid(null);
//		}
		videoInfo.setId(UuidGenerateUtil.getUUID());
		//验证设备编号是否重复
		String deviceCode = videoInfo.getCode();
		int n = videoInfoService.countByCode(deviceCode);
		if(n>0){
			redirectAttributes.addFlashAttribute("message", "设备编号重复，分析到异常数据，请联系管理员！");
			return "redirect:/property/videoinfo/sublist/"+menuid+"/?roadinfoId="+videoInfo.getRoadinfoId();
		}
		videoInfo.setInserttime(new Date());
		videoInfoService.save(videoInfo);
		
		////////////////// 地图部分添加开始 ////////////////////
		// 获得道路信息
		RoadInfo roadinfo = roadInfoService.getById(videoInfo.getRoadinfoId());
		Dic deviceType = dicService.getDicByCodeAndType(videoInfo.getVideotypeCode(), DicType.MONITOR_TYPE);
		LayerBean t = new LayerBean();
		if(null != deviceType)t.setType(deviceType.getName());
		t.setName(videoInfo.getName());
		t.setCode(videoInfo.getCode());
		if(roadinfo != null){
			t.setRoadId(roadinfo.getId());
			t.setRoadName(roadinfo.getName());
		}
		// 获得经纬度
		String mapx = videoInfo.getMapx();
		String mapy = videoInfo.getMapy();
		if(mapy!=null && !"".equals(mapy) && mapx!=null && !"".equals(mapx)){
			t.setGeometry("{\"type\":\"point\",\"points\":\""+mapx+","+mapy+"\"}");
			mapService.add(LayerEnum.VIDEO, t);
		}
		////////////////// 地图部分添加结束 ////////////////////
		String toasset = asset.getToasset();
		if(toasset.equals("1")){
			asset.setId(UuidGenerateUtil.getUUID());
			asset.setAssettype("video");
			asset.setAssetname(videoInfo.getName());
			asset.setDeviceid(videoInfo.getId());
			asset.setGuaranteetime(DateUtil.parseDate(asset.getGuaranteetimestr(), "yyyy-MM-dd"));
			asset.setPurchasetime(DateUtil.parseDate(asset.getPurchasetimestr(), "yyyy-MM-dd"));
			asset.setInstalltime(DateUtil.parseDate(asset.getInstalltimestr(), "yyyy-MM-dd HH:mm:ss"));
			assetInfoService.save(asset);
		}
		redirectAttributes.addFlashAttribute("message","监控设备添加成功");
		return "redirect:/property/videoinfo/sublist/"+menuid+"/?roadinfoId="+videoInfo.getRoadinfoId();
	}
	
	
	/***
	 * 获取待修改页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}/{menuid}/{roadinfoId}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id,@PathVariable("menuid") String menuid,@PathVariable("roadinfoId") String roadinfoId,String page,Model model) {
		VideoInfo videoInfo = videoInfoService.getById(id);
		model.addAttribute("videoInfo",videoInfo);
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		model.addAttribute("roadId",roadinfoId);
		model.addAttribute("roadInfo",roadInfoService.getById(roadinfoId));
		
		//设备厂家
		List<DeviceCompanyInfo> companyList = deviceCompanyInfoService.findAll();
		model.addAttribute("companyList", companyList);
		
		// 服务厂商
		List<FactoryInfo> factoryList = factoryInfoService.findAll();
		model.addAttribute("factoryList", factoryList);
		
		//部门 树形结构
		JsonArray departmentjsonArray = departmentService.getDeptJsonArray(menuid);
		model.addAttribute("departmentjsonArray",departmentjsonArray.toString());
		
		//已属于部门
		String orgid = videoInfo.getOrgId();
		Department department = departmentService.getById(orgid);
		model.addAttribute("department",department);
		
		//监控类型
		List<Dic> videoTypeList = dicService.getDicByType(DicType.MONITOR_TYPE);
		model.addAttribute("videoTypeList", videoTypeList);
		
		//从字典表取  方向 DIRECTION_ID
		List<Dic> directList = dicService.getDicByType(DicType.DIRECT);
		model.addAttribute("directList",directList);
		
		//判断是否已经转为资产
		String isasset = "0";
		List<AssetInfo> assetList = assetInfoService.findByDeviceId(id);
		if(assetList != null && assetList.size() != 0){
			isasset = "1";
			model.addAttribute("asset", assetList.get(0));
		}
		model.addAttribute("isasset", isasset);
		
		//播放配置模板
		List<VideoAddressInfo> realiVideoAddressList = videoAddressService.findIsRealiTimeAll("1");
		List<VideoAddressInfo> hisVideoAddressList = videoAddressService.findIsRealiTimeAll("0");
		model.addAttribute("realiVideoAddressList",realiVideoAddressList);
		model.addAttribute("hisVideoAddressList",hisVideoAddressList);
		return "property/videoinfo/update";
	}

	
	/***
	 * 查看 一个监控设备信息 详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}/{menuid}/{roadinfoId}", method = RequestMethod.GET)
	public String getVideoInfo(@PathVariable("id") String id,@PathVariable("menuid") String menuid,@PathVariable("roadinfoId") String roadinfoId,String page,Model model) {
		VideoInfo videoInfo = videoInfoService.getById(id);
		String usetemplate = videoInfo.getUsetemplate();
		if("1".equals(usetemplate)){
			VideoAddressInfo realiVideoAddress = videoAddressService.getById(videoInfo.getRealvideoaddressid());
			if(realiVideoAddress!=null){
				videoInfo.setIp(realiVideoAddress.getIp());
				videoInfo.setPort(realiVideoAddress.getPort());
				videoInfo.setUsername(realiVideoAddress.getUsername());
				videoInfo.setPassword(realiVideoAddress.getPassword());
				videoInfo.setTracode(realiVideoAddress.getTracode());
			}
			
			VideoAddressInfo hisVideoAddress = videoAddressService.getById(videoInfo.getHisvideoaddressid());
			if(hisVideoAddress!=null){
				videoInfo.setIphis(hisVideoAddress.getIp());
				videoInfo.setPorthis(hisVideoAddress.getPort());
				videoInfo.setUsernamehis(hisVideoAddress.getUsername());
				videoInfo.setPasswordhis(hisVideoAddress.getPassword());
				videoInfo.setTracodehis(hisVideoAddress.getTracode());
			}
		}
		model.addAttribute("videoInfo",videoInfo);
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		model.addAttribute("roadId",roadinfoId);
		
		return "property/videoinfo/view";
	}
	
	/***
	 * 修改视频监控设备信息 动作
	 * @param ftpInfo
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "doUpdate", method = RequestMethod.POST)
	@LogAspect(desc="修改监控设备信息")
	public String doUpdate(@ModelAttribute("preloadRole")VideoInfo videoInfo,AssetInfo asset,String page,String menuid,
			RedirectAttributes redirectAttributes, HttpServletRequest request) throws ParseException {
		
		String ismodify = request.getParameter("ismodify");
		if(ismodify.equals("1")){
			String modifyId = videoInfo.getId();
			//保存变更记录信息
			ModifyRecord modifyRecord = new ModifyRecord();
			modifyRecord.setId(UuidGenerateUtil.getUUID());
			modifyRecord.setModifyId(modifyId);
			modifyRecord.setModuleName("video");
			modifyRecord.setOperator(getCurrentUserId());
			Date datetime = new Date();
			modifyRecord.setOperatorTime(datetime);
			modifyRecord.setVersion(DateUtil.dateFormatToString(datetime, "yyyyMMddHHmmss"));
			modifyRecord.setModifyType("变更");
			modifyRecordService.save(modifyRecord);
			//将变更的记录COPY到历史表
			videoInfoService.saveHistoryRecord(modifyId);
			AssetInfo assetinfo = null;
			List<AssetInfo> assetList = assetInfoService.findByDeviceId(modifyId);
			if(assetList != null && assetList.size() != 0){
				assetinfo = assetList.get(0);
			}
			//修改变更的记录ID
			String afterRecordId = UuidGenerateUtil.getUUID();
			videoInfoService.updatePrimaryKey(afterRecordId, modifyId);
			
			//同步修改资产信息
			if(assetinfo != null){
				assetinfo.setDeviceid(afterRecordId);
				assetInfoService.updateByIdSelective(assetinfo);
			}
			//将修改后的记录ID同步更新到历史表字段
			videoInfoHisService.updateAfterRecordId(modifyId, afterRecordId);
			//同步修改运维服务商责任资产设备ID
			List<FactoryContact> list = factoryContactService.findByDeviceId(modifyId);
			for(FactoryContact factoryContact : list){
				factoryContact.setDeviceid(afterRecordId);
				factoryContactService.updateByIdSelective(factoryContact);
			}
			//设置待修改的设备ID为变更后的ID
			videoInfo.setId(afterRecordId);
		}
		//根据单选判断
		String usertemplate = videoInfo.getUsetemplate();
		if("1".equals(usertemplate)){
			videoInfo.setUsetemplate("1");
			videoInfo.setRealvideoaddressid(videoInfo.getRealvideoaddressid());
			videoInfo.setHisvideoaddressid(videoInfo.getHisvideoaddressid());
		}else{
			videoInfo.setUsetemplate("0");
			videoInfo.setRealvideoaddressid(null);
			videoInfo.setHisvideoaddressid(null);
			videoInfo.setIp(videoInfo.getIp());
			videoInfo.setPort(videoInfo.getPort());
			videoInfo.setTracode(videoInfo.getTracode());
			videoInfo.setUsername(videoInfo.getUsername());
			videoInfo.setPassword(videoInfo.getPassword());
			videoInfo.setIphis(videoInfo.getIphis());
			videoInfo.setPorthis(videoInfo.getPorthis());
			videoInfo.setTracodehis(videoInfo.getTracodehis());
			videoInfo.setUsernamehis(videoInfo.getUsernamehis());
			videoInfo.setPasswordhis(videoInfo.getPasswordhis());
		}
//		if("1".equals(usertemplate)){
//			videoInfo.setIp(null);
//			videoInfo.setPort(null);
//			videoInfo.setUsername(null);
//			videoInfo.setPassword(null);
//			videoInfo.setTracode(null);
//			videoInfo.setIphis(null);
//			videoInfo.setPorthis(null);
//			videoInfo.setUsernamehis(null);
//			videoInfo.setPasswordhis(null);
//			videoInfo.setTracodehis(null);
//		}else{
//			videoInfo.setRealvideoaddressid(null);
//			videoInfo.setHisvideoaddressid(null);
//		}
		videoInfoService.updateById(videoInfo);
		
		//////////////////地图部分修改开始 ////////////////////
		// 获得道路信息
		RoadInfo roadinfo = roadInfoService.getById(videoInfo.getRoadinfoId());
		Dic deviceType = dicService.getDicByCodeAndType(videoInfo.getVideotypeCode(), DicType.MONITOR_TYPE);
		LayerBean t = new LayerBean();
		if(null != deviceType)t.setType(deviceType.getName());
		t.setName(videoInfo.getName());
		t.setCode(videoInfo.getCode());
		if(roadinfo != null){
			t.setRoadId(roadinfo.getId());
			t.setRoadName(roadinfo.getName());
		}
		// 获得经纬度
		String mapx = videoInfo.getMapx();
		String mapy = videoInfo.getMapy();
		if(mapy!=null && !"".equals(mapy) && mapx!=null && !"".equals(mapx)){
			t.setGeometry("{\"type\":\"point\",\"points\":\""+mapx+","+mapy+"\"}");
			mapService.save(LayerEnum.VIDEO, t);
		}
		////////////////// 地图部分修改结束 ////////////////////
		
		//资产配置修改
		String isasset = request.getParameter("isasset");
		if(isasset.equals("1")){
			asset.setGuaranteetime(DateUtil.parseDate(asset.getGuaranteetimestr(), "yyyy-MM-dd"));
			asset.setPurchasetime(DateUtil.parseDate(asset.getPurchasetimestr(), "yyyy-MM-dd"));
			asset.setInstalltime(DateUtil.parseDate(asset.getInstalltimestr(), "yyyy-MM-dd HH:mm:ss"));
			asset.setId(request.getParameter("assetid"));
			assetInfoService.updateByIdSelective(asset);
		}else{
			String toasset = asset.getToasset();
			if(toasset.equals("1")){
				asset.setId(UuidGenerateUtil.getUUID());
				asset.setAssettype("video");
				asset.setAssetname(videoInfo.getName());
				asset.setDeviceid(videoInfo.getId());
				asset.setGuaranteetime(DateUtil.parseDate(asset.getGuaranteetimestr(), "yyyy-MM-dd"));
				asset.setPurchasetime(DateUtil.parseDate(asset.getPurchasetimestr(), "yyyy-MM-dd"));
				asset.setInstalltime(DateUtil.parseDate(asset.getInstalltimestr(), "yyyy-MM-dd HH:mm:ss"));
				assetInfoService.save(asset);
			}
		}
		String message = "修改成功";
		if(ismodify.equals("1")){
			message = "监控设备变更操作成功";
		}
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/property/videoinfo/sublist/"+menuid+"/?page="+page+"&roadinfoId="+videoInfo.getRoadinfoId();
	}
	
	
	/***
	 * 删除一个视频监控设备
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value="delete/{ids}/",method = RequestMethod.DELETE)
	@ResponseBody
	@LogAspect(desc="删除了一个监控设备")
	public ResponseEntity delete(@PathVariable String ids){
		ResponseEntity  entity = new ResponseEntity();
		try {
			logger.info("正在删除了一个监控设备。。。。。。");
			boolean deleteFlag = true;
			String[] id = ids.split(",");
//			for (String string : id) {
//				boolean hasModified = videoInfoHisService.hasRecordModified(string);
//				if(hasModified){
//					deleteFlag = false;
//				}
//			}
//			if(!deleteFlag){
//				entity.setResult("can't");
//				return entity;
//			}
			for (String string : id) {
//				//同步删除资产信息
//				List<AssetInfo> assetList = assetInfoService.findByDeviceId(string);
//				if(assetList != null && assetList.size() != 0){
//					AssetInfo asset = assetList.get(0);
//					assetInfoService.deleteById(asset.getId());
//				}
				
				VideoInfo videoInfo = videoInfoService.getById(string);
				//同步删除资产信息
				assetInfoService.deleteAssetByDeviceId(string);
				//同步删除服务商分配信息
				assetInfoService.deleteFactoryContactByDeviceId(string);
				//同步删除卡口历史信息
				assetInfoService.deleteVideoHisByCode(videoInfo.getCode());
				//同步删除更改记录
				assetInfoService.deleteModifyByCode(videoInfo.getCode());
				//同步删除设备日志信息
				assetInfoService.deleteDeviceLogByCode(videoInfo.getCode());
				//同步删除工单信息
				List<WorkorderInfo> workorderList = workorderInfoService.findByDeviceCode(videoInfo.getCode());
				if(workorderList != null && workorderList.size() != 0){
					for(WorkorderInfo workorder : workorderList){
						String exceptionid = workorder.getExceptionid();
						if(exceptionid != null){
							assetInfoService.deleteWorkorderReportById(exceptionid);
						}
						assetInfoService.deleteWorkorderLogByWorkId(workorder.getId());
						assetInfoService.deleteWorkorderAppriseByWorkId(workorder.getId());
					}
				}
				assetInfoService.deleteWorkorderByCode(videoInfo.getCode());
				
				videoInfoService.deleteById(string, videoInfo);
				
				//////////////////地图部分删除开始 ////////////////////
				mapService.deleteByCode(LayerEnum.VIDEO, videoInfo.getCode());
				//////////////////地图部分删除开始 ////////////////////
			}
			entity.setResult("ok");
			return entity;
		} catch (Exception e) {
			entity.setResult("error");
			return entity;
		}
	}
	
	
	/***
	 * 视频监控设备信息 导出
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="exportXls/",method=RequestMethod.GET)
	public void exportXls(HttpServletResponse response,@RequestParam(value = "sortType", defaultValue = "NAME") String sortType,ServletRequest request){
		// 3 表示从第几行开始写入
		SimpleXlsExportor export = new SimpleXlsExportor(3);
		//接收前台参数
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		/**
		 * edit by kouyunhao 2014-04-04 添加过滤，屏蔽脏数据，使导出结果与查询列表一致。
		 */
		
		String menuid = request.getParameter("menuid").toString();
		Page page = videoInfoService.getVideoInfoByCondition(searchParams,0,MAX_EXPORT_NUM,sortType,menuid);
		// 设置内容
		List result = new ArrayList();
		Object obj = page.getResult();
		if(obj!=null && obj instanceof List){
			List<VideoInfo> list = (List<VideoInfo>)obj;
			if(list != null && list.size() != 0){
				for (int i = 0; i < list.size(); i++) {
					VideoInfo videoInfo = list.get(i);
					//监控名称 设备IP  实时播放IP  历史播放IP 所在道路 监控方向 监控类型 所属部门 建设厂家 
					
					//所在道路
					String roadId = videoInfo.getRoadinfoId();
					RoadInfo roadinfo = roadInfoService.getById(roadId);
					String roadName = "";
					if(roadinfo!=null){
						roadName = roadinfo.getName();
					}
					//监控方向
					String directionCode = videoInfo.getDirectionCode();
					Dic dic1 = dicService.getDicByCodeAndType(directionCode, DicType.DIRECT);
					String directionName = "";
					if(dic1!=null){
						directionName = dic1.getName();
					}
					//监控类型
					String videotypeCode = videoInfo.getVideotypeCode();
					Dic dic2 = dicService.getDicByCodeAndType(videotypeCode, DicType.MONITOR_TYPE);
					String videotypeName = "";
					if(dic2!=null){
						videotypeName = dic2.getName();
					}
					//所属部门信息
					Department department = departmentService.getById(videoInfo.getOrgId());
					String departmentName = "";
					if(department!=null){
						departmentName  = department.getName();
					}
					//建设厂商信息
					DeviceCompanyInfo deviceCompanyInfo = deviceCompanyInfoService.getById(videoInfo.getCompanyid());
					String deviceCompanyName = "";
					if(deviceCompanyInfo!=null){
						deviceCompanyName = deviceCompanyInfo.getName();
					}
					
					// 判断用户是否使用配置模板  并赋值对应的 流媒体IP地址  端口号  用户名 密码  Yantao ADD
					
					String realip = "";
					String realport = "";
					String realusername = "";
					String realpassword = "";
					String realtracode = "";
					
					String isUseTemplate = videoInfo.getUsetemplate();
					if("1".equals(isUseTemplate)){
						String templateid = videoInfo.getRealvideoaddressid();
						//实时播放配置
						VideoAddressInfo realvideoAddress = videoAddressService.getById(templateid);
						if(realvideoAddress!=null){
							realip = realvideoAddress.getIp();
							realport = realvideoAddress.getPort();
							realusername = realvideoAddress.getUsername();
							realpassword = realvideoAddress.getPassword();
							realtracode = realvideoAddress.getTracode();
						}
					}else{
						realip = videoInfo.getIp();
						realport = videoInfo.getPort();
						realusername = videoInfo.getUsername();
						realpassword = videoInfo.getPassword();
						realtracode = videoInfo.getTracode();
					}
					
					//监控机型
					String deviceShape = "";
					String shapeCode = videoInfo.getDeviceShape();
					if("1".equals(shapeCode)){
						deviceShape = "球机";
					}else{
						deviceShape = "枪机";
					}
					
					
					//封装数组
					Object[] values = new Object[] { 
						i+1, 
						videoInfo.getName(),
						videoInfo.getDeviceIp(),
						roadName,
						directionName,
						videotypeName,
						departmentName,
						deviceCompanyName,
						
						realip,
						realport,
						realusername,
						realpassword,
						
						deviceShape,
					};
					result.add(values);
				}
			}
		}
		
		String nowtimeStr = DateUtil.dateFormatToString(new java.util.Date(), "yyyy年MM月dd日HH时mm分ss秒");
		export.setFileName("视频监控设备信息一览表["+nowtimeStr+"].xls");
		export.exportExcelFileStream("xls" + File.separator + "videoinfo_exp.xls", null, result, response);
	}
	
	/***
	 * 判断设备编号是否重复
	 * @param req
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "codeExist/{code}/")
	@ResponseBody
	public void deviceCodeExist(HttpServletResponse resp, @PathVariable String code) {
		
		//验证设备编号是否重复
		int n = videoInfoService.countByCode(code);
		if(n>0){
			try {
				JsonObject object = new JsonObject();
				object.addProperty("content","<font color=red>设备编号已经存在!</font>");
				resp.getWriter().write(object.toString());
			} catch (IOException e) {
				logger.error(e);
			}
		}else{
			try {
				JsonObject object = new JsonObject();
				object.addProperty("content","<font color=green>设备编号唯一性效验正常!</font>");
				resp.getWriter().write(object.toString());
			} catch (IOException e) {
				logger.error(e);
			}
		}
	}
	
	/***
	 * 判断name是否重复 add by kouyunhao 2013-11-15
	 * @param req
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "nameExist")
	@ResponseBody
	public String nameExist(HttpServletRequest req, @RequestParam(value = "name") String name) {
		List<VideoInfo> devList = videoInfoService.findByName(name);
		String returnStr = Boolean.FALSE.toString();
		if (devList.size() == 0) {
			returnStr = Boolean.TRUE.toString();
		}
		String oper = req.getParameter("oper");
		//不为空说明是修改
		if(org.apache.commons.lang.StringUtils.isNotBlank(oper)){
			if(name.equals(oper)){
				return Boolean.TRUE.toString();
			}
		}
		return returnStr;
	}
	
	/***
	 * 判断ip是否重复 add by kouyunhao 2013-11-15
	 * @param req
	 * @param ip
	 * @return
	 */
	@RequestMapping(value = "ipExist")
	@ResponseBody
	public String ipExist(HttpServletRequest req, @RequestParam(value = "deviceIp") String ip) {
		List<VideoInfo> devList = videoInfoService.findByIp(ip);
		String returnStr = Boolean.FALSE.toString();
		if (devList.size() == 0) {
			returnStr = Boolean.TRUE.toString();
		}
		String oper = req.getParameter("oper");
		//不为空说明是修改
		if(org.apache.commons.lang.StringUtils.isNotBlank(oper)){
			if(ip.equals(oper)){
				return Boolean.TRUE.toString();
			}
		}
		return returnStr;
	}
	
	/***
	 * 判断ip是否重复 add by kouyunhao 2013-11-15
	 * @param req
	 * @param ip
	 * @return
	 */
	@RequestMapping(value = "isIpExist/{ip}/",method = RequestMethod.GET)
	public void isIpExist(@PathVariable String ip, ServletResponse response){
		List<VideoInfo> devList = videoInfoService.findByIp(ip);
		try {
			response.getWriter().write(devList.size()==0?"no":"yes");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 监控设备变更处理
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doModify", method = RequestMethod.POST)
	@LogAspect(desc = "监控设备变更处理")
	public String doModify(ModifyRecord modifyRecord,String menuid, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		String modifyTypeName = "维修";
		String ids = modifyRecord.getModifyId();
		String[] id = ids.split(",");
		for (String string : id) {
			//保存变更记录信息
			modifyRecord.setId(UuidGenerateUtil.getUUID());
			modifyRecord.setModuleName("video");
			modifyRecord.setModifyDatetime(DateUtil.stringFormatToDate(modifyRecord.getModifyDatetimeStr(), "yyyy-MM-dd"));
			modifyRecord.setOperator(getCurrentUserId());
			Date datetime = new Date();
			modifyRecord.setOperatorTime(datetime);
			modifyRecord.setVersion(DateUtil.dateFormatToString(datetime, "yyyyMMddHHmmss"));
			String modifyType = modifyRecord.getModifyType();
			if(modifyType.equals("2")){
				modifyTypeName = "移除";
			}else if(modifyType.equals("3")){
				modifyTypeName = "报废";
			}else if(modifyType.equals("4")){
				modifyTypeName = "变更";
			}
			modifyRecord.setModifyType(modifyTypeName);
			modifyRecord.setModifyId(string);
			modifyRecordService.save(modifyRecord);
			//将变更的记录COPY到历史表
			videoInfoService.saveHistoryRecord(string);
			
			AssetInfo asset = null;
			List<AssetInfo> assetList = assetInfoService.findByDeviceId(string);
			if(assetList != null && assetList.size() != 0){
				asset = assetList.get(0);
			}
			//修改变更的记录ID
			String afterRecordId = UuidGenerateUtil.getUUID();
			videoInfoService.updatePrimaryKey(afterRecordId, string);
			//同步修改资产信息
			if(asset != null){
				asset.setDeviceid(afterRecordId);
				assetInfoService.updateByIdSelective(asset);
			}
			//将修改后的记录ID同步更新到历史表字段
			videoInfoHisService.updateAfterRecordId(string, afterRecordId);
			//同步修改运维服务商责任资产设备ID
			List<FactoryContact> list = factoryContactService.findByDeviceId(string);
			for(FactoryContact factoryContact : list){
				factoryContact.setDeviceid(afterRecordId);
				factoryContactService.updateByIdSelective(factoryContact);
			}
			redirectAttributes.addFlashAttribute("message", "监控设备" + modifyTypeName + "操作成功");
		}
		return "redirect:/property/videoinfo/list/" + menuid + "/";
	}
	
}
