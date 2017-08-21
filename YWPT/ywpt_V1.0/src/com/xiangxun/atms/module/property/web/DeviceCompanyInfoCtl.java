package com.xiangxun.atms.module.property.web;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

import com.google.common.collect.Table;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.cache.Cache;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.SessionParameter;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.property.domain.DeviceCompanyInfo;
import com.xiangxun.atms.module.property.service.DeviceCompanyInfoService;

/***
 * 设备厂商 信息维护
 * @author YanTao
 * @Apr 26, 2013 5:39:59 PM
 */
@Controller
@RequestMapping(value="property/devicecompany")
public class DeviceCompanyInfoCtl extends BaseCtl{
	
	@Resource
	DeviceCompanyInfoService deviceCompanyInfoService;
	
	@Resource
	Cache cache;
	
	static Random r = new Random();
	
	/***
	 * 跳转到主界面
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list/{menuid}/")
	public String list(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "id") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,HttpServletRequest request){
		
		logger.info("正在进行设备厂商列表查询。。。。。。");
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		
		//解决返回后 搜索条件消失问题  更新SESSION级别的参数状态
		SessionParameter sp = new SessionParameter();
		sp.updateSessionMap(request, menuid, searchParams);

		Page page = deviceCompanyInfoService.getCompanyInfoByCondition(searchParams,pageNumber,Page.DEFAULT_PAGE_SIZE,sortType);
		
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		
		DeviceCompanyInfo deviceCompanyInfo = new DeviceCompanyInfo();
		try {
			BeanUtils.populate(deviceCompanyInfo, searchParams);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		model.addAttribute("deviceCompanyInfo", deviceCompanyInfo);
		
		return "property/devicecompany/list";
	}
	
	/***
	 * 删除一个设备厂商
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value="delete/{ids}/",method = RequestMethod.DELETE)
	@ResponseBody
	@LogAspect(desc="删除了一个设备厂商")
	public ResponseEntity delete(@PathVariable String ids){
		ResponseEntity  entity = new ResponseEntity();
		try {
			logger.info("正在删除了一个设备厂商。。。。。。");
			String[] id = ids.split(",");
			for (String string : id) {
				DeviceCompanyInfo info = deviceCompanyInfoService.getById(string);
				deviceCompanyInfoService.deleteById(string,info);
			}
			entity.setResult("ok");
			return entity;
		} catch (Exception e) {
			entity.setResult("error");
			return entity;
		}
	}
	
	/***
	 * 添加一个设备厂商
	 * @param ftpInfo
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doAdd", method = RequestMethod.POST)
	@LogAspect(desc="添加一个设备厂商")
	public String doAdd(DeviceCompanyInfo deviceCompanyInfo,String menuid,RedirectAttributes redirectAttributes){
		deviceCompanyInfo.setId(UuidGenerateUtil.getUUID());
		//生成接收数据KEY值
		String u2 = UUID.randomUUID().toString();
		u2 = u2.replaceAll("-", "");
		deviceCompanyInfo.setDatekey(u2.substring(26));
		deviceCompanyInfoService.save(deviceCompanyInfo);
		redirectAttributes.addFlashAttribute("message","设备厂商添加成功");
		return "redirect:/property/devicecompany/list/"+menuid+"/";
	}
	


	/***
	 * 获取待修改设备厂商
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}/{menuid}/", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id,@PathVariable("menuid") String menuid,String page,Model model) {
		model.addAttribute("deviceCompanyInfo",deviceCompanyInfoService.getById(id));
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		return "property/devicecompany/update";
	}

	/***
	 * 修改设备厂商
	 * @param ftpInfo
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doUpdate", method = RequestMethod.POST)
	@LogAspect(desc="修改设备厂商")
	public String doUpdate(@ModelAttribute("preloadRole") DeviceCompanyInfo deviceCompanyInfo,String page,String menuid,RedirectAttributes redirectAttributes) {
		deviceCompanyInfoService.updateByIdSelective(deviceCompanyInfo);
		redirectAttributes.addFlashAttribute("message", "修改成功");
		return "redirect:/property/devicecompany/list/"+menuid+"/?page="+page+"&isgetsession=1";
	}
	
	/***
	 * 获取详情信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}/{menuid}/", method = RequestMethod.GET)
	public String view(@PathVariable("id") String id,@PathVariable("menuid") String menuid,String page,Model model) {
		DeviceCompanyInfo info = deviceCompanyInfoService.getById(id);
		model.addAttribute("deviceCompanyInfo", info);
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		return "property/devicecompany/view";
	}
	
	/***
	 * 先根据form的id从数据库查出DeviceCompanyInfo对象
	 * @param id
	 * @return
	 */
	@ModelAttribute("preloadRole")
	public DeviceCompanyInfo getDeviceCompanyInfo(@RequestParam(value = "id", required = false) String id) {
		if (id != null) {
			return deviceCompanyInfoService.getById(id);
		}
		return null;
	}
	
	@RequestMapping(value = "getbyid/{id}")
	@ResponseBody
	public DeviceCompanyInfo getById(@PathVariable String id) {
		if (id != null) {
			return deviceCompanyInfoService.getById(id);
		}
		return null;
	}
	
	/**
	 * xiongjie添加，获取所有设备厂家列表
	 * 
	 * @return Map<String, String>  key:id  value:name
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getall/")
	@ResponseBody
	public Map<String, String> statistics() {
		Table<String, String, String> companyList = (Table<String, String, String>)cache.get(DeviceCompanyInfo.class.getSimpleName());
		return companyList.column(DeviceCompanyInfo.class.getSimpleName());
	}
	
}
