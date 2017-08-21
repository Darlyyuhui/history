package com.xiangxun.atms.common.system.web;


import java.lang.reflect.InvocationTargetException;
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

import com.xiangxun.atms.common.system.service.SysIndexModelService;
import com.xiangxun.atms.common.system.vo.SysIndexModel;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.SessionParameter;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;

/***
 * @author YanTao
 * @Apr 26, 2013 5:39:59 PM
 */
@Controller
@RequestMapping(value="system/sysindexmodel")
public class SysIndexModelCtl extends BaseCtl{
	
	@Resource
	SysIndexModelService sysIndexModelService;

	
	@RequestMapping(value="list/{menuid}/")
	public String list(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "id") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,HttpServletRequest request){
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		//解决返回后 搜索条件消失问题  更新SESSION级别的参数状态
		SessionParameter sp = new SessionParameter();
		sp.updateSessionMap(request, menuid, searchParams);
		Page page = sysIndexModelService.getByCondition(searchParams,pageNumber,Page.DEFAULT_PAGE_SIZE,sortType);
		
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		
		SysIndexModel sysIndexModel = new SysIndexModel();
		try {
			BeanUtils.populate(sysIndexModel, searchParams);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		model.addAttribute("sysIndexModel", sysIndexModel);
		
		return "system/sysindexmodel/list";
	}
	
	/***
	 * 删除一个首页显示组件
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value="delete/{ids}/",method = RequestMethod.DELETE)
	@ResponseBody
	@LogAspect(desc="删除了一个首页显示组件")
	public ResponseEntity delete(@PathVariable String ids){
		ResponseEntity  entity = new ResponseEntity();
		try {
			String[] id = ids.split(",");
			for (String string : id) {
				sysIndexModelService.deleteById(string);
			}
			entity.setResult("ok");
			return entity;
		} catch (Exception e) {
			entity.setResult("error");
			return entity;
		}
	}
	
	/***
	 * 添加一个首页显示组件
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doAdd", method = RequestMethod.POST)
	@LogAspect(desc="添加一个首页显示组件")
	public String doAdd(SysIndexModel sysIndexModel,String menuid,RedirectAttributes redirectAttributes){
		sysIndexModel.setId(UuidGenerateUtil.getUUID());
		sysIndexModelService.save(sysIndexModel);
		redirectAttributes.addFlashAttribute("message","首页显示组件添加成功");
		return "redirect:/system/sysindexmodel/list/"+menuid+"/";
	}

	/***
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}/{menuid}/", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id,@PathVariable("menuid") String menuid,String page,Model model) {
		model.addAttribute("sysIndexModel",sysIndexModelService.getById(id));
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		return "system/sysindexmodel/update";
	}

	/***
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doUpdate", method = RequestMethod.POST)
	@LogAspect(desc="修改首页显示组件信息")
	public String doUpdate(@ModelAttribute("preload")SysIndexModel sysIndexModel,String page,String menuid,RedirectAttributes redirectAttributes) {
		sysIndexModelService.updateByIdSelective(sysIndexModel);
		redirectAttributes.addFlashAttribute("message", "修改成功");
		return "redirect:/system/sysindexmodel/list/"+menuid+"/?page="+page;
	}
	
	/***
	 * @param req
	 * @param nameExist
	 * @return
	 */
	@RequestMapping(value = "codeExist")
	@ResponseBody
	public String nmaeExist(HttpServletRequest req, @RequestParam(value = "code") String code) {
		SysIndexModel sysIndexModel = sysIndexModelService.getByCode(code);
		String returnStr = Boolean.FALSE.toString();
		if (sysIndexModel == null) {
			returnStr = Boolean.TRUE.toString();
		}
		String oper = req.getParameter("oper");
		//不为空说明是修改
		if(StringUtils.isNotBlank(oper)){
			if(code.equals(oper)){
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
		SysIndexModel sysIndexModel = sysIndexModelService.getById(id);
		model.addAttribute("sysIndexModel", sysIndexModel);
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		return "system/sysindexmodel/view";
	}
	
	/***
	 * 先根据form的id从数据库查出首页显示组件
	 * @param id
	 * @return
	 */
	@ModelAttribute("preload")
	public SysIndexModel getSysIndexModel(@RequestParam(value = "id", required = false) String id) {
		if (id != null) {
			return sysIndexModelService.getById(id);
		}
		return null;
	}
	
}
