package com.xiangxun.atms.common.system.web;


import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiangxun.atms.common.deptment.service.DepartmentService;
import com.xiangxun.atms.common.deptment.vo.Department;
import com.xiangxun.atms.common.dictionary.service.DicService;
import com.xiangxun.atms.common.dictionary.type.DicType;
import com.xiangxun.atms.common.dictionary.vo.Dic;
import com.xiangxun.atms.common.road.service.RoadInfoService;
import com.xiangxun.atms.common.road.vo.RoadInfo;
import com.xiangxun.atms.common.system.service.ResourceService;
import com.xiangxun.atms.common.system.service.RetrieveService;
import com.xiangxun.atms.common.system.vo.RetrieveInfo;
import com.xiangxun.atms.common.system.vo.RetrieveInfoSearch;
import com.xiangxun.atms.common.system.vo.RetrieveInfoSearch.Criteria;
import com.xiangxun.atms.common.system.vo.SystemResource;
import com.xiangxun.atms.common.user.service.RoleService;
import com.xiangxun.atms.common.user.service.UserService;
import com.xiangxun.atms.common.user.vo.Role;
import com.xiangxun.atms.common.user.vo.User;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.compnents.xls.export.template.SimpleXlsExportor;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.gis.domain.LayerBean;
import com.xiangxun.atms.module.gis.domain.LayerEnum;
import com.xiangxun.atms.module.gis.service.IMapService;
import com.xiangxun.atms.module.property.domain.DeviceCompanyInfo;
import com.xiangxun.atms.module.property.domain.DeviceInfo;
import com.xiangxun.atms.module.property.domain.VideoInfo;
import com.xiangxun.atms.module.property.service.DeviceCompanyInfoService;
import com.xiangxun.atms.module.property.service.DeviceInfoService;
import com.xiangxun.atms.module.property.service.VideoInfoService;

/***
 * 回收站 公用组件
 * @author YanTao
 * @Apr 26, 2013 5:39:59 PM
 */
@Controller
@RequestMapping(value="system/retrieve")
public class RetrieveCtl extends BaseCtl{
	
	@Resource
	RetrieveService retrieveService;

	@Resource
	VideoInfoService videoService;
	
	@Resource
	DeviceInfoService deviceInfoService;
	
	@Resource
	DeviceCompanyInfoService deviceCompanyInfoService;
	
	@Resource
	RoadInfoService roadInfoService;
	
	@Resource
	DepartmentService departmentService;
	
	@Resource
	UserService userService;
	
	@Resource
	RoleService roleService;
	
	@Resource
	DicService dicService;
	
	@Resource
	ResourceService resourceService;
	
	@Resource
	IMapService mapService;
	
	/***
	 * 跳转到主界面
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list/{menuid}/")
	public String list(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "DELTIME") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,ServletRequest request){
		
		logger.info("正在进行回收站信息列表查询。。。。。。");
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");

		Page page = retrieveService.getByCondition(searchParams,pageNumber,Page.DEFAULT_PAGE_SIZE,sortType);
		
		RetrieveInfo retrieveInfo = new RetrieveInfo();
		try {
			BeanUtils.populate(retrieveInfo, searchParams);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		model.addAttribute("retrieveInfo", retrieveInfo);
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		
		return "system/retrieve/list";
	}
	

	/***
	 * 查看 一个回收站信息 详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}/{menuid}/", method = RequestMethod.GET)
	public String getRetrieveInfo(@PathVariable("id") String id,@PathVariable("menuid") String menuid,String page,Model model) {
		RetrieveInfo retrieveInfo = retrieveService.getById(id);
		Date date = retrieveInfo.getDeltime();
		retrieveInfo.setDeltimeStr(DateUtil.dateFormatToString(date, "yyyy-MM-dd HH:mm:SS"));
		model.addAttribute("retrieveInfo",retrieveInfo);
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		return "system/retrieve/view";
	}
	
	
	/***
	 * 删除一个回收站信息(假删除)
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value="delete/{ids}/",method = RequestMethod.DELETE)
	@ResponseBody
	@LogAspect(desc="删除了一个回收站信息")
	public String delete(@PathVariable String id,String page,String menuid,RedirectAttributes redirectAttributes){
		logger.info("正在删除了一个回收站信息。。。。。。");
		RetrieveInfo retrieveInfo = new RetrieveInfo();
		retrieveInfo.setId(id);
		retrieveInfo.setClearflag("1");
		retrieveService.updateByIdSelective(retrieveInfo);
		redirectAttributes.addFlashAttribute("message", "删除成功");
		return "redirect:/system/retrieve/list/"+menuid+"/?page="+page;
	}
	
	
	/***
	 * 恢复回收站的信息
	 * @param ids
	 * @param resp
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="revert/{ids}/",method = RequestMethod.DELETE)
	@ResponseBody
	@LogAspect(desc="还原了一个回收站的信息")
	public ResponseEntity revert(@PathVariable String ids){
		ResponseEntity  entity = new ResponseEntity();
		try {
			logger.info("正在还原了一个回收站的信息。。。。。。");
			String[] id = ids.split(",");
			for (String string : id) {
				RetrieveInfo retrieveInfo = retrieveService.getById(string);
				String content = retrieveInfo.getContent();
				String classname = retrieveInfo.getPojoclass();
				if("VideoInfo".equals(classname)){
					JSONObject js = JSONObject.fromObject(content);
					VideoInfo videoInfo = (VideoInfo)js.toBean(js, VideoInfo.class);
					videoService.save(videoInfo);
				}
				if("DeviceInfo".equals(classname)){
					JSONObject js = JSONObject.fromObject(content);
					DeviceInfo deviceInfo = (DeviceInfo)js.toBean(js, DeviceInfo.class);
					//--------恢复设备时 同时恢复GIS中的设备信息------ YanTao Add ---- START
					LayerBean t = new LayerBean();
					// 获得设备类型
					String code = deviceInfo.getCode();
					String typecode = code.substring(12,14);
					Dic deviceType = dicService.getDicByCodeAndType(typecode, DicType.DEVICE_TYPE);
					t.setType(deviceType.getName());
					t.setName(deviceInfo.getName());
					t.setCode(code);
					
					// 获得道路信息
					RoadInfo roadinfo = roadInfoService.getById(deviceInfo.getRoadId());
					if(roadinfo != null){
						t.setRoadId(roadinfo.getId());
						t.setRoadName(roadinfo.getName());
					}
					
					// 获得经纬度
					String mapy = deviceInfo.getMapy();
					String mapx = deviceInfo.getMapx();
					if(mapy!=null && !"".equals(mapy) && mapx!=null && !"".equals(mapx)){
						double dmapx = Double.parseDouble(mapx);
						double dmapy = Double.parseDouble(mapy);
						t.setGeometryByXY(dmapx, dmapy);
						mapService.add(LayerEnum.CROSS, t);
					}
					//--------恢复设备时 同时恢复GIS中的坐标点------ YanTao Add ---- end
					
					deviceInfoService.save(deviceInfo);
				}
				if("DeviceCompanyInfo".equals(classname)){
					JSONObject js = JSONObject.fromObject(content);
					DeviceCompanyInfo deviceCompanyInfo = (DeviceCompanyInfo)js.toBean(js, DeviceCompanyInfo.class);
					deviceCompanyInfoService.save(deviceCompanyInfo);
				}
				if("RoadInfo".equals(classname)){
					JSONObject js = JSONObject.fromObject(content);
					RoadInfo roadInfo = (RoadInfo)js.toBean(js, RoadInfo.class);
					roadInfoService.save(roadInfo);
				}
				if("Department".equals(classname)){
					JSONObject js = JSONObject.fromObject(content);
					Department department = (Department)js.toBean(js, Department.class);
					departmentService.save(department);
				}
				if("User".equals(classname)){
					JSONObject js = JSONObject.fromObject(content);
					User user = (User)js.toBean(js, User.class);
					userService.save(user);
				}
				if("Role".equals(classname)){
					JSONObject js = JSONObject.fromObject(content);
					Role role = (Role)js.toBean(js, Role.class);
					roleService.save(role);
				}
				if("Dic".equals(classname)){
					JSONObject js = JSONObject.fromObject(content);
					Dic dic = (Dic)js.toBean(js, Dic.class);
					dicService.save(dic);
				}
				if("SystemResource".equals(classname)){
					JSONObject js = JSONObject.fromObject(content);
					SystemResource systemResource = (SystemResource)js.toBean(js, SystemResource.class);
					resourceService.save(systemResource);
				}
				retrieveService.deleteById(string);
			}
			entity.setResult("ok");
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			entity.setResult("error");
			return entity;
		}
	}
	
	
	/***
	 * 回收站信息 导出
	 * @param response
	 */
	@RequestMapping(value="exportXls/",method=RequestMethod.GET)
	public void exportXls(HttpServletResponse response,@RequestParam(value = "sortType", defaultValue = "DELTIME") String sortType,ServletRequest request){
		// 3 表示从第几行开始写入
		SimpleXlsExportor export = new SimpleXlsExportor(3);
		//接收前台参数
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		// 获取数据
		RetrieveInfoSearch search = new RetrieveInfoSearch();
		//判断封装前台条件
		Criteria criteria = search.createCriteria();
		criteria.andIdIsNotNull();
		if(searchParams!=null){
			if(StringUtils.notEmpty(searchParams.get("operator")+"")){
				criteria.andOperatorLike("%"+searchParams.get("operator").toString()+"%");
			}
			if(StringUtils.notEmpty(searchParams.get("startTime")+"")){
				Date sdate = DateUtil.stringFormatToDate(searchParams.get("startTime").toString(), "yyyy-MM-dd HH:mm:SS");
				criteria.andDeltimeGreaterThanOrEqualTo(sdate);
			}
			if(StringUtils.notEmpty(searchParams.get("endTime")+"")){
				Date edate = DateUtil.stringFormatToDate(searchParams.get("endTime").toString(), "yyyy-MM-dd HH:mm:SS");
				criteria.andDeltimeLessThanOrEqualTo(edate);
			}
		}
		if(StringUtils.notEmpty(sortType)){
			search.setOrderByClause(sortType);
		}
		
		List<RetrieveInfo> list = retrieveService.selectByExample(search);
		// 设置内容
		List<Object[]> result = new ArrayList<Object[]>();
		for (int i = 0; i < list.size(); i++) {
			RetrieveInfo retrieveInfo = list.get(i);
			//删除时间格式转换
			Date delTime = retrieveInfo.getDeltime();
			retrieveInfo.setDeltimeStr(DateUtil.dateFormatToString(delTime, "yyyy-MM-dd HH:mm:SS"));
			Object[] values = new Object[] { 
				i+1, 
				retrieveInfo.getOperator(),
				retrieveInfo.getOperatorip(),
				retrieveInfo.getDeltimeStr(),
				retrieveInfo.getContent(),
				retrieveInfo.getPojoclass(),
			};
			result.add(values);
		}
		
		String nowtimeStr = DateUtil.dateFormatToString(new java.util.Date(), "yyyy年MM月dd日HH时mm分ss秒");
		export.setFileName("回收站信息一览表["+nowtimeStr+"].xls");
		export.exportExcelFileStream("xls" + File.separator + "retrieve_exp.xls", null, result, response);
	}
	
	
}
