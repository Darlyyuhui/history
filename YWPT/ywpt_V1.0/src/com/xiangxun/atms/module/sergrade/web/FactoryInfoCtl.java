
package com.xiangxun.atms.module.sergrade.web;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.sergrade.domain.FactoryInfo;
import com.xiangxun.atms.module.sergrade.domain.FactoryInfoSearch;
import com.xiangxun.atms.module.sergrade.domain.GradeInfo;
import com.xiangxun.atms.module.sergrade.service.FactoryInfoService;
import com.xiangxun.atms.module.sergrade.service.GradeInfoService;

/**
 * 运维服务商模块操作
 * @author guikaiping
 * @version 1.0
 */
@Controller
@RequestMapping(value="sergrade/factory/")
public class FactoryInfoCtl extends BaseCtl {
	
	@Resource
	FactoryInfoService factoryInfoService;
	
	@Resource
	GradeInfoService gradeInfoService;
	
	
	/**
	 * 获取运维服务商列表信息
	 * @param menuid
	 * @param model
	 * @param sortType
	 * @param pageNumber
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="info/list/{menuid}/")
	public String list(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "NAME") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,HttpServletRequest request){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");		
		super.updateSession(request, menuid, searchParams);
		
		Page page = factoryInfoService.getReport(searchParams, pageNumber, Page.DEFAULT_PAGE_SIZE, sortType, menuid);
		List<FactoryInfo> reports = page.getResult();
		// 设置内容
		for (int i = 0; i < reports.size(); i++) {
			FactoryInfo factoryInfo = reports.get(i);
			int point = Integer.parseInt(factoryInfo.getPoint().toString());
			//获取服务级别信息
			List<GradeInfo> list = gradeInfoService.findAll();
			
			//服务级别
			String level = "";
			for (GradeInfo gradeInfo : list) {
				int minpoint = Integer.parseInt(gradeInfo.getMinpoint().toString());
				int maxpoint = Integer.parseInt(gradeInfo.getMaxpoint().toString());
				if (point >= minpoint && point<=maxpoint) {
					level = gradeInfo.getCode();
				}
			}
			factoryInfo.setLevel(level);
		}	
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("totalPage", page.getTotalPageCount());
		model.addAttribute("curPageNo",pageNumber);
		
		model.addAttribute("name", searchParams.get("name"));
		model.addAttribute("linkman", searchParams.get("linkman"));
		model.addAttribute("telphone", searchParams.get("telphone"));
		
		//将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
				
		return "sergrade/factory/list";
	}
	
	
	/***
	 * 删除运维服务商信息
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value="info/delete/{ids}/",method = RequestMethod.DELETE)
	@ResponseBody
	@LogAspect(desc="删除运维服务商信息")
	public ResponseEntity delete(@PathVariable String ids){
		ResponseEntity  entity = new ResponseEntity();
		try {
			logger.info("正在进行运维服务商别信息数据删除。。。。。。");
			String[] idss = ids.split(",");
			List<String> dels = Arrays.asList(idss);
			FactoryInfoSearch factoryInfoSearch = new FactoryInfoSearch();
			factoryInfoSearch.createCriteria().andIdIn(dels);
			factoryInfoService.deleteByExample(factoryInfoSearch);
			entity.setResult("ok");
			return entity;
		} catch (Exception e) {
			entity.setResult("error");
			return entity;
		}
	}
	
	/**
	 * 运维服务商信息添加页面
	 * @param menuid
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "info/showadd/{menuid}/", method = RequestMethod.GET)
	public String showAdd(@PathVariable("menuid") String menuid, String page, Model model,RedirectAttributes redirectAttributes) {
		
		return "sergrade/factory/add";
	}
	
	/***
	 * 添加服务级别信息
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "info/doAdd", method = RequestMethod.POST)
	@LogAspect(desc="添加运维服务商信息")
	public String doAdd(FactoryInfo factoryInfo,String menuid,String page,RedirectAttributes redirectAttributes,HttpServletRequest request){
		logger.info("menuid:"+menuid);
		factoryInfo.setId(UuidGenerateUtil.getUUID());
		factoryInfo.setPoint(BigDecimal.valueOf(100));
		factoryInfoService.save(factoryInfo);
		redirectAttributes.addFlashAttribute("message","添加成功");
		return "redirect:/sergrade/factory/info/list/"+menuid + "/";
	}
	
	/***
	 * 判断编号是否重复 
	 * @param req
	 * @param model
	 * @return string
	 */
	@RequestMapping(value = "info/nameExist")
	@ResponseBody
	public String nameExist(HttpServletRequest req, @RequestParam(value = "name") String name) {
		List<FactoryInfo> list = factoryInfoService.getByName(name);
		String returnStr = Boolean.FALSE.toString();
		if (list.size() == 0) {
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
	 * 获取待修改运维服务商信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "info/update/{id}/{menuid}/", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id,@PathVariable("menuid") String menuid,String page,Model model) {
		FactoryInfo factoryInfo  = factoryInfoService.getById(id);
		
		model.addAttribute("factoryInfo",factoryInfo);
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		
		return "sergrade/factory/update";
	}

	/***
	 * 修改运维服务商信息
	 * @param role
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "info/doUpdate", method = RequestMethod.POST)
	@LogAspect(desc="修改运维服务商信息")
	public String doUpdate(@ModelAttribute("preloadFactoryInfo") FactoryInfo factoryInfo,String page,String menuid,RedirectAttributes redirectAttributes,HttpServletRequest request) {
		FactoryInfoSearch factoryInfoSearch = new FactoryInfoSearch();
		factoryInfoSearch.createCriteria().andIdEqualTo(factoryInfo.getId());
		
		factoryInfoService.updateByExampleSelective(factoryInfo,factoryInfoSearch);
		redirectAttributes.addFlashAttribute("message", "修改成功");
		return "redirect:/sergrade/factory/info/list/"+menuid+"/";
	}
	
	/**
	 * 获取详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "info/view/{id}/{menuid}/", method = RequestMethod.GET)
	public String showView(@PathVariable("id") String id, @PathVariable("menuid")
			String menuid, String page, Model model) {
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);

		FactoryInfo factoryInfo = factoryInfoService.getById(id);
		int point = Integer.parseInt(factoryInfo.getPoint().toString());
		//获取服务级别信息
		List<GradeInfo> list = gradeInfoService.findAll();
		
		//服务级别
		String level = "";
		for (GradeInfo gradeInfo : list) {
			int minpoint = Integer.parseInt(gradeInfo.getMinpoint().toString());
			int maxpoint = Integer.parseInt(gradeInfo.getMaxpoint().toString());
			if (point >= minpoint && point<=maxpoint) {
				level = gradeInfo.getCode();
			}
		}
		factoryInfo.setLevel(level);
		model.addAttribute("factoryInfo", factoryInfo);
		
		return "sergrade/factory/view";
	}
}
