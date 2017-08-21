package com.xiangxun.atms.module.eventalarm.web;

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
import com.xiangxun.atms.module.eventalarm.domain.WorkorderRule;
import com.xiangxun.atms.module.eventalarm.service.WorkorderRuleService;

/**
 * 工单评估规则
 * @author kouyunhao
 *
 */
@Controller
@RequestMapping(value = "alarm/workrule")
public class WorkorderRuleCtl extends BaseCtl {
	
	@Resource
	WorkorderRuleService workorderRuleService;
	
	/***
	 * 跳转到主界面
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list/{menuid}/")
	public String list(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "id") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,HttpServletRequest request){
		
		logger.info("正在进行工单评估规则信息列表查询。。。。。。");
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		//解决返回后 搜索条件消失问题  更新SESSION级别的参数状态
		SessionParameter sp = new SessionParameter();
		sp.updateSessionMap(request, menuid, searchParams);
		Page page = workorderRuleService.getListByCondition(searchParams,pageNumber,Page.DEFAULT_PAGE_SIZE,sortType);
		
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", "ID desc");
		model.addAttribute("pageList", page);
		
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		
		WorkorderRule rule = new WorkorderRule();
		try {
			BeanUtils.populate(rule, searchParams);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		model.addAttribute("rule", rule);
		
		return "alarm/workorder/rule/list";
	}
	
	/***
	 * 删除一个工单评估规则
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value="delete/{ids}/",method = RequestMethod.DELETE)
	@ResponseBody
	@LogAspect(desc="删除了一个工单评估规则")
	public ResponseEntity delete(@PathVariable String ids){
		ResponseEntity  entity = new ResponseEntity();
		try {
			logger.info("正在删除了一个工单评估规则。。。。。。");
			String[] id = ids.split(",");
			for (String string : id) {
				workorderRuleService.deleteById(string);
			}
			entity.setResult("ok");
			return entity;
		} catch (Exception e) {
			entity.setResult("error");
			return entity;
		}
	}
	
	/***
	 * 添加一个工单评估规则
	 * @param rule
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doAdd", method = RequestMethod.POST)
	@LogAspect(desc="添加一个工单评估规则")
	public String doAdd(WorkorderRule rule,String menuid,RedirectAttributes redirectAttributes){
		rule.setId(UuidGenerateUtil.getUUID());
		workorderRuleService.save(rule);
		redirectAttributes.addFlashAttribute("message","工单评估规则添加成功");
		return "redirect:/alarm/workrule/list/"+menuid+"/";
	}

	/***
	 * 获取待修改工单评估规则信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}/{menuid}/", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id,@PathVariable("menuid") String menuid,String page,Model model) {
		model.addAttribute("rule",workorderRuleService.getById(id));
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		return "alarm/workorder/rule/update";
	}

	/***
	 * 修改工单评估规则信息
	 * @param rule
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doUpdate", method = RequestMethod.POST)
	@LogAspect(desc="修改工单评估规则信息")
	public String doUpdate(@ModelAttribute("preload") WorkorderRule rule,String page,String menuid,RedirectAttributes redirectAttributes) {
		workorderRuleService.updateByIdSelective(rule);
		redirectAttributes.addFlashAttribute("message", "修改成功");
		return "redirect:/alarm/workrule/list/"+menuid+"/?page="+page;
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
		List<WorkorderRule> ruleList = workorderRuleService.findByName(name);
		String returnStr = Boolean.FALSE.toString();
		if (ruleList.size() == 0) {
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
	 * 判断编号是否重复 
	 * @param req
	 * @param nameExist
	 * @return
	 */
	@RequestMapping(value = "codeExist")
	@ResponseBody
	public String codeExist(HttpServletRequest req, @RequestParam(value = "code") String code) {
		List<WorkorderRule> ruleList = workorderRuleService.findByCode(code);
		String returnStr = Boolean.FALSE.toString();
		if (ruleList.size() == 0) {
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
		WorkorderRule rule = workorderRuleService.getById(id);
		model.addAttribute("rule", rule);
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		return "alarm/workorder/rule/view";
	}

}
