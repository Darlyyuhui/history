package com.xiangxun.atms.module.property.web;


import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.SessionParameter;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.property.domain.DeviceFtpInfo;
import com.xiangxun.atms.module.property.service.DeviceFtpInfoService;

/***
 * FTP服务器信息维护
 * @author YanTao
 * @Apr 26, 2013 5:39:59 PM
 */
@Controller
@RequestMapping(value="property/deviceftp")
public class DeviceFtpInfoCtl extends BaseCtl{
	
	@Resource
	DeviceFtpInfoService deviceFtpInfoService;

	
	/***
	 * 跳转到主界面
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list/{menuid}/")
	public String list(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "id") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,HttpServletRequest request){
		
		logger.info("正在进行FTP服务器信息列表查询。。。。。。");
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		//解决返回后 搜索条件消失问题  更新SESSION级别的参数状态
		SessionParameter sp = new SessionParameter();
		sp.updateSessionMap(request, menuid, searchParams);
		Page page = deviceFtpInfoService.getFtpByCondition(searchParams,pageNumber,Page.DEFAULT_PAGE_SIZE,sortType);
		
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		
		DeviceFtpInfo ftpInfo = new DeviceFtpInfo();
		try {
			BeanUtils.populate(ftpInfo, searchParams);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		model.addAttribute("ftp", ftpInfo);
		
		return "property/deviceftp/list";
	}
	
	/***
	 * 删除一个FTP服务器
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value="delete/{ids}/",method = RequestMethod.DELETE)
	@ResponseBody
	@LogAspect(desc="删除了一个FTP服务器")
	public ResponseEntity delete(@PathVariable String ids){
		ResponseEntity  entity = new ResponseEntity();
		try {
			logger.info("正在删除了一个FTP服务器。。。。。。");
			String[] id = ids.split(",");
			for (String string : id) {
				deviceFtpInfoService.deleteById(string);
			}
			entity.setResult("ok");
			return entity;
		} catch (Exception e) {
			entity.setResult("error");
			return entity;
		}
	}
	
	/***
	 * 添加一个FTP服务器
	 * @param ftpInfo
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doAdd", method = RequestMethod.POST)
	@LogAspect(desc="添加一个FTP服务器")
	public String doAdd(DeviceFtpInfo ftpInfo,String menuid,RedirectAttributes redirectAttributes){
		ftpInfo.setId(UuidGenerateUtil.getUUID());
		deviceFtpInfoService.save(ftpInfo);
		redirectAttributes.addFlashAttribute("message","FTP服务器添加成功");
		return "redirect:/property/deviceftp/list/"+menuid+"/";
	}

	/***
	 * 获取待修改FTP服务器信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}/{menuid}/", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id,@PathVariable("menuid") String menuid,String page,Model model) {
		model.addAttribute("ftpInfo",deviceFtpInfoService.getById(id));
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		return "property/deviceftp/update";
	}

	/***
	 * 修改FTP服务器信息
	 * @param ftpInfo
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doUpdate", method = RequestMethod.POST)
	@LogAspect(desc="修改FTP服务器信息")
	public String doUpdate(@ModelAttribute("preload") DeviceFtpInfo ftpInfo,String page,String menuid,RedirectAttributes redirectAttributes) {
		deviceFtpInfoService.updateByIdSelective(ftpInfo);
		redirectAttributes.addFlashAttribute("message", "修改成功");
		return "redirect:/property/deviceftp/list/"+menuid+"/?page="+page;
	}
	
	/***
	 * 判断应急专家是否重复 add by kouyunhao 2013-09-02 
	 * @param req
	 * @param nameExist
	 * @return
	 */
	@RequestMapping(value = "ipExist")
	@ResponseBody
	public String nmaeExist(HttpServletRequest req, @RequestParam(value = "ftpip") String ftpip) {
		List<DeviceFtpInfo> ftpInfoList = deviceFtpInfoService.getByIp(ftpip);
		String returnStr = Boolean.FALSE.toString();
		if (ftpInfoList.size() == 0) {
			returnStr = Boolean.TRUE.toString();
		}
		String oper = req.getParameter("oper");
		//不为空说明是修改
		if(StringUtils.isNotBlank(oper)){
			if(ftpip.equals(oper)){
				return Boolean.TRUE.toString();
			}
		}
		return returnStr;
	}
	
	
	/***
	 * 获取详情信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}/{menuid}/", method = RequestMethod.GET)
	public String view(@PathVariable("id") String id,@PathVariable("menuid") String menuid,String page,Model model) {
		DeviceFtpInfo ftpinfo = deviceFtpInfoService.getById(id);
		model.addAttribute("ftpInfo", ftpinfo);
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		return "property/deviceftp/view";
	}
	
	/***
	 * 先根据form的id从数据库查出FtpInfo对象
	 * @param id
	 * @return
	 */
	@ModelAttribute("preload")
	public DeviceFtpInfo getFtpInfo(@RequestParam(value = "id", required = false) String id) {
		if (id != null) {
			return deviceFtpInfoService.getById(id);
		}
		return null;
	}
	
}
