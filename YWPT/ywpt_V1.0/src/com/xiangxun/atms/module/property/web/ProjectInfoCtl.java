package com.xiangxun.atms.module.property.web;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
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

import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.compnents.xls.export.template.SimpleXlsExportor;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.monitor.conf.Dictionary;
import com.xiangxun.atms.framework.util.DateUtil;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.SessionParameter;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderInfo;
import com.xiangxun.atms.module.eventalarm.service.WorkorderInfoService;
import com.xiangxun.atms.module.property.domain.AssetInfo;
import com.xiangxun.atms.module.property.domain.DatabaseInfo;
import com.xiangxun.atms.module.property.domain.ProjectInfo;
import com.xiangxun.atms.module.property.service.AssetInfoService;
import com.xiangxun.atms.module.property.service.ProjectInfoService;
import com.xiangxun.atms.module.sergrade.domain.FactoryInfo;
import com.xiangxun.atms.module.sergrade.service.FactoryInfoService;

/***
 * 平台软件信息维护
 * @author kouyunhao
 * 
 */
@Controller
@RequestMapping(value="property/projectinfo")
public class ProjectInfoCtl extends BaseCtl {
	
	@Resource
	FactoryInfoService factoryInfoService;
	
	@Resource
	ProjectInfoService projectInfoService;
	
	@Resource
	WorkorderInfoService workorderInfoService;
	
	@Resource
	AssetInfoService assetInfoService;
	
	/***
	 * 跳转到主界面
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list/{menuid}/")
	public String list(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "id") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,HttpServletRequest request){
		
		logger.info("正在进行平台软件信息信息列表查询。。。。。。");
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		//解决返回后 搜索条件消失问题  更新SESSION级别的参数状态
		SessionParameter sp = new SessionParameter();
		sp.updateSessionMap(request, menuid, searchParams);
		Page page = projectInfoService.getListByCondition(searchParams,pageNumber,Page.DEFAULT_PAGE_SIZE,sortType);
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
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		//服务厂商
		request.getSession().setAttribute("factoryList", factoryInfoService.findAll());
		ProjectInfo project = new ProjectInfo();
		try {
			BeanUtils.populate(project, searchParams);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		model.addAttribute("project", project);
		
		return "property/projectinfo/list";
	}
	
	/***
	 * 删除一个平台软件信息
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value="delete/{ids}/",method = RequestMethod.DELETE)
	@ResponseBody
	@LogAspect(desc="删除了一个平台软件信息")
	public ResponseEntity delete(@PathVariable String ids){
		ResponseEntity  entity = new ResponseEntity();
		try {
			logger.info("正在删除了一个平台软件信息。。。。。。");
			String[] id = ids.split(",");
			for (String string : id) {
//				//同步删除资产信息
//				List<AssetInfo> assetList = assetInfoService.findByDeviceId(string);
//				if(assetList != null && assetList.size() != 0){
//					AssetInfo asset = assetList.get(0);
//					assetInfoService.deleteById(asset.getId());
//				}
				
				ProjectInfo project = projectInfoService.getById(string);
				String assetcode = "";
				List<AssetInfo> assetList = assetInfoService.findByDeviceId(string);
				if(assetList != null && assetList.size() != 0){
					assetcode = assetList.get(0).getAssetcode();
				}
				//同步删除资产信息
				assetInfoService.deleteAssetByDeviceId(string);
				//同步删除服务商分配信息
				assetInfoService.deleteFactoryContactByDeviceId(string);
				//同步删除设备日志信息
				//assetInfoService.deleteDeviceLogByCode(project.getId());
				//同步删除工单信息
				List<WorkorderInfo> workorderList = workorderInfoService.findByDeviceCode(assetcode);
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
				assetInfoService.deleteWorkorderByCode(assetcode);
				
				projectInfoService.deleteById(string);
			}
			
			Dictionary.WebStatusMap.clear();
			
			
			entity.setResult("ok");
			return entity;
		} catch (Exception e) {
			entity.setResult("error");
			return entity;
		}
	}
	
	/***
	 * 添加一个平台软件信息
	 * @param project
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "doAdd", method = RequestMethod.POST)
	@LogAspect(desc="添加一个平台软件信息")
	public String doAdd(ProjectInfo project,AssetInfo asset,String menuid,RedirectAttributes redirectAttributes) throws ParseException{
		project.setId(UuidGenerateUtil.getUUID());
		projectInfoService.save(project);
		String toasset = asset.getToasset();
		if(toasset.equals("1")){
			asset.setId(UuidGenerateUtil.getUUID());
			asset.setAssettype("project");
			asset.setAssetname(project.getName());
			asset.setServiceid(project.getFactoryId());
			asset.setDeviceid(project.getId());
			asset.setGuaranteetime(DateUtil.parseDate(asset.getGuaranteetimestr(), "yyyy-MM-dd"));
			asset.setPurchasetime(DateUtil.parseDate(asset.getPurchasetimestr(), "yyyy-MM-dd"));
			asset.setInstalltime(DateUtil.parseDate(asset.getInstalltimestr(), "yyyy-MM-dd HH:mm:ss"));
			//服务商ID
			asset.setFactoryId(project.getFactoryId());
			assetInfoService.save(asset);
		}
		redirectAttributes.addFlashAttribute("message","添加成功");
		return "redirect:/property/projectinfo/list/"+menuid+"/";
	}

	/***
	 * 获取待修改平台软件信息信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}/{menuid}/", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id,@PathVariable("menuid") String menuid,String page,Model model) {
		ProjectInfo project = projectInfoService.getById(id);
		//判断是否已经转为资产
		String isasset = "0";
		List<AssetInfo> assetList = assetInfoService.findByDeviceId(id);
		if(assetList != null && assetList.size() != 0){
			isasset = "1";
			model.addAttribute("asset", assetList.get(0));
		}
		model.addAttribute("isasset", isasset);
		
		model.addAttribute("project",project);
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		return "property/projectinfo/update";
	}

	/***
	 * 修改平台软件信息信息
	 * @param project
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "doUpdate", method = RequestMethod.POST)
	@LogAspect(desc="修改平台软件信息信息")
	public String doUpdate(@ModelAttribute("preload") ProjectInfo project,AssetInfo asset,String page,String menuid,
			RedirectAttributes redirectAttributes, HttpServletRequest request) throws ParseException {
		projectInfoService.updateByIdSelective(project);
		
		//资产配置修改
		String isasset = request.getParameter("isasset");
		asset.setGuaranteetime(DateUtil.parseDate(asset.getGuaranteetimestr(), "yyyy-MM-dd"));
		asset.setPurchasetime(DateUtil.parseDate(asset.getPurchasetimestr(), "yyyy-MM-dd"));
		asset.setInstalltime(DateUtil.parseDate(asset.getInstalltimestr(), "yyyy-MM-dd HH:mm:ss"));
		//服务商ID
		asset.setFactoryId(project.getFactoryId());
		if(isasset.equals("1")){
			asset.setAssetname(request.getParameter("name"));
			asset.setId(request.getParameter("assetid"));
			assetInfoService.updateByIdSelective(asset);
		}else{
			String toasset = asset.getToasset();
			if(toasset.equals("1")){
				asset.setId(UuidGenerateUtil.getUUID());
				asset.setAssettype("project");
				asset.setAssetname(project.getName());
				asset.setServiceid(project.getFactoryId());
				asset.setDeviceid(project.getId());
				assetInfoService.save(asset);
			}
		}
		Dictionary.WebStatusMap.clear();
		redirectAttributes.addFlashAttribute("message", "修改成功");
		return "redirect:/property/projectinfo/list/"+menuid+"/?page="+page;
	}
	
	/***
	 * 判断名称是否重复 add by kouyunhao 2013-09-02 
	 * @param req
	 * @param nameExist
	 * @return
	 */
	@RequestMapping(value = "nameExist")
	@ResponseBody
	public String nmaeExist(HttpServletRequest req, @RequestParam(value = "name") String name) {
		List<ProjectInfo> projectList = projectInfoService.findByName(name);
		String returnStr = Boolean.FALSE.toString();
		if (projectList.size() == 0) {
			returnStr = Boolean.TRUE.toString();
		}
		String oper = req.getParameter("oper");
		//不为空说明是修改
		if(StringUtils.isNotBlank(oper)){
			if(name.equals(oper)){
				return Boolean.TRUE.toString();
			}
		}
		return returnStr;
	}

	/***
	 * 判断IP是否重复 add by kouyunhao 2013-09-02 
	 * @param req
	 * @param nameExist
	 * @return
	 */
	@RequestMapping(value = "ipExist")
	@ResponseBody
	public String ipExist(HttpServletRequest req, @RequestParam(value = "projectip") String projectip) {
		List<ProjectInfo> projectList = projectInfoService.findByIp(projectip);
		String returnStr = Boolean.FALSE.toString();
		if (projectList.size() == 0) {
			returnStr = Boolean.TRUE.toString();
		}
		String oper = req.getParameter("oper");
		//不为空说明是修改
		if(StringUtils.isNotBlank(oper)){
			if(projectip.equals(oper)){
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
		List<ProjectInfo> devList = projectInfoService.findByIp(ip);
		try {
			response.getWriter().write(devList.size()==0?"no":"yes");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 获取详情信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}/{menuid}/", method = RequestMethod.GET)
	public String view(@PathVariable("id") String id,@PathVariable("menuid") String menuid,String page,Model model) {
		ProjectInfo project = projectInfoService.getById(id);
		FactoryInfo factory = factoryInfoService.getById(project.getFactoryId());
		if(factory != null){
			project.setFactoryName(factory.getName());
		}
		model.addAttribute("project", project);
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		return "property/projectinfo/view";
	}
	
	/***
	 * 数据库信息 导出
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="exportXls/{menuid}/",method=RequestMethod.GET)
	public void exportXls(@PathVariable String menuid,HttpServletResponse response,@RequestParam(value = "sortType", defaultValue = "NAME") String sortType,
			ServletRequest request){
		// 3 表示从第几行开始写入
		SimpleXlsExportor export = new SimpleXlsExportor(3);
		//接收前台参数
		sortType = "id des";
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		List<ProjectInfo> list = projectInfoService.selectByExampleDiy(searchParams,sortType,menuid);
		
		// 设置内容
		List result = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			ProjectInfo projectInfo = list.get(i);
			
			//封装数组
			Object[] values = new Object[] { 
				i+1, 			
				projectInfo.getName(),
				projectInfo.getIp(),
				projectInfo.getPort(),
				projectInfo.getUrl(),
				//查询运维服务商
				(factoryInfoService.getById(projectInfo.getFactoryId())).getName(),


			};
			result.add(values);
		}
		
		String nowtimeStr = DateUtil.dateFormatToString(new java.util.Date(), "yyyy年MM月dd日HH时mm分ss秒");
		export.setFileName("平台信息一览表["+nowtimeStr+"].xls");
		export.exportExcelFileStream("xls" + File.separator + "projectInfo_exp.xls", null, result, response);
	}
	

}
