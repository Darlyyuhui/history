
package com.xiangxun.atms.module.sergrade.web;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.xiangxun.atms.common.dictionary.service.DicService;
import com.xiangxun.atms.common.dictionary.type.DicType;
import com.xiangxun.atms.common.dictionary.vo.Dic;
import com.xiangxun.atms.common.dictionary.vo.DicSearch;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.type.DataJson;
import com.xiangxun.atms.framework.util.JsonUtil;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.sergrade.domain.GradeInfo;
import com.xiangxun.atms.module.sergrade.domain.GradeInfoSearch;
import com.xiangxun.atms.module.sergrade.service.GradeInfoService;

/**
 * 服务级别模块操作
 * @author guikaiping
 * @version 1.0
 */
@Controller
@RequestMapping(value="sergrade/grade/")
public class GradeInfoCtl extends BaseCtl {
	
	@Resource
	GradeInfoService gradeInfoService;
	@Resource
	DicService dicService;
	
	/**
	 * 获取服务级别列表信息
	 * @param menuid
	 * @param model
	 * @param sortType
	 * @param pageNumber
	 * @param request
	 * @return
	 */
	@RequestMapping(value="info/list/{menuid}/")
	public String list(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "CODE") String sortType,
			@RequestParam(value = "page", defaultValue = "0") int pageNumber,HttpServletRequest request){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");		
		super.updateSession(request, menuid, searchParams);
		
		Page page = gradeInfoService.getReport(searchParams, pageNumber, Page.DEFAULT_PAGE_SIZE, sortType, menuid);
		
		List<Dic> gradetype = dicService.getDicByType(DicType.GRADE_TYPE);
		model.addAttribute("gradetype", gradetype);
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("totalPage", page.getTotalPageCount());
		model.addAttribute("curPageNo",pageNumber);
		
		model.addAttribute("code", searchParams.get("code"));
		
		//将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
				
		return "sergrade/grade/list";
	}
	
	
	/***
	 * 删除服务级别信息
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value="info/delete/{ids}/",method = RequestMethod.DELETE)
	@ResponseBody
	@LogAspect(desc="删除服务级别信息")
	public ResponseEntity delete(@PathVariable String ids){
		ResponseEntity  entity = new ResponseEntity();
		try {
			logger.info("正在进行服务级别信息数据删除。。。。。。");
			String[] idss = ids.split(",");
			List<String> dels = Arrays.asList(idss);
			GradeInfoSearch gradeInfoSearch = new GradeInfoSearch();
			gradeInfoSearch.createCriteria().andIdIn(dels);
			gradeInfoService.deleteByExample(gradeInfoSearch);
			entity.setResult("ok");
			return entity;
		} catch (Exception e) {
			entity.setResult("error");
			return entity;
		}
	}
	
	/***
	 * 服务级别改变方法
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "info/gradeChange", method = RequestMethod.GET)
	@LogAspect(desc="服务级别改变方法")
	public String gradeChange(HttpServletRequest request){
		String code = request.getParameter("code");
		DicSearch search = new DicSearch();
		DicSearch.Criteria incriteria = search.createCriteria();
		incriteria.andCodeEqualTo(code);
		incriteria.andTypeEqualTo("gradetype");

		String remark = dicService.selectByExample(search).get(0).getRemark();
		
		return remark;
	}
	
	/**
	 * 服务级别信息添加页面
	 * @param menuid
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "info/showadd/{menuid}/", method = RequestMethod.GET)
	public String showAdd(@PathVariable("menuid") String menuid, String page, Model model,RedirectAttributes redirectAttributes) {
		List<Dic> gradetype = dicService.getDicByType(DicType.GRADE_TYPE);
		model.addAttribute("gradetype", gradetype);
		return "sergrade/grade/add";
	}
	
	/***
	 * 添加服务级别信息
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "info/doAdd", method = RequestMethod.POST)
	@LogAspect(desc="添加服务级别信息")
	public String doAdd(GradeInfo gradeInfo,String menuid,String page,RedirectAttributes redirectAttributes,HttpServletRequest request){
		logger.info("menuid:"+menuid);
		gradeInfo.setId(UuidGenerateUtil.getUUID());
		gradeInfoService.save(gradeInfo);
		redirectAttributes.addFlashAttribute("message","添加成功");
		return "redirect:/sergrade/grade/info/list/"+menuid + "/";
	}
	
	/***
	 * 判断编号是否重复 
	 * @param req
	 * @param model
	 * @return string
	 */
	@RequestMapping(value = "info/codeExist")
	@ResponseBody
	public String codeExist(HttpServletRequest req, @RequestParam(value = "code") String code) {
		List<GradeInfo> list = gradeInfoService.getByCode(code);
		String returnStr = Boolean.FALSE.toString();
		if (list.size() == 0) {
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
	 * 获取待修改设备信息
	 * @param code
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "info/update/{id}/{menuid}/", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id,@PathVariable("menuid") String menuid,String page,Model model) {
		GradeInfo gradeInfo  = gradeInfoService.getById(id);
		
		model.addAttribute("gradeInfo",gradeInfo);
		model.addAttribute("menuid",menuid);
		model.addAttribute("page",page);
		
		return "sergrade/grade/update";
	}

	/***
	 * 修改服务级别信息
	 * @param role
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "info/doUpdate", method = RequestMethod.POST)
	@LogAspect(desc="修改服务级别信息")
	public String doUpdate(@ModelAttribute("preloadGradeInfo") GradeInfo gradeInfo,String page,String menuid,RedirectAttributes redirectAttributes,HttpServletRequest request) {
		GradeInfoSearch gradeInfoSearch = new GradeInfoSearch();
		gradeInfoSearch.createCriteria().andIdEqualTo(gradeInfo.getId());
		
		gradeInfoService.updateByExampleSelective(gradeInfo,gradeInfoSearch);
		redirectAttributes.addFlashAttribute("message", "修改成功");
		return "redirect:/sergrade/grade/info/list/"+menuid+"/";
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

		GradeInfo gradeInfo = gradeInfoService.getById(id);
		
		model.addAttribute("gradeInfo", gradeInfo);
		
		return "sergrade/grade/view";
	}
	
	/**
	 * 服务级别阀值设置页面
	 * @param model
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="info/showpointset/", method = RequestMethod.POST)
	public void showpointset(HttpServletRequest request,HttpServletResponse response){
		int pageNumber=Integer.valueOf(request.getParameter("pageNumber"));
		String menuid = request.getParameter("menuid");
		Map<String, Object> map=new HashMap<String, Object>();
		Page page = gradeInfoService.getReport(map, pageNumber, Page.DEFAULT_PAGE_SIZE, "CODE", menuid);
		DataJson  data = new DataJson();	    
		try {
			StringBuffer dmlistStr=new StringBuffer(""); 
            for(int i=0;i<page.getSize();i++){
            	GradeInfo info = (GradeInfo) page.getResult().get(i);
            	dmlistStr.append("<tr>");
            	dmlistStr.append("<input type='hidden' name='id' value='"+info.getId()+"'>");
            	dmlistStr.append("<input type='hidden' name='code' value='"+info.getCode()+"'>");
            	dmlistStr.append("<td>"+dicService.getNameByKey("Dic", DicType.GRADE_TYPE.toString(), info.getCode())+"</td>");
            	dmlistStr.append("<td>");
            	dmlistStr.append("<input style='width:50px;' id='minpoint"+info.getCode()+"' type='text' name='minpoint' value='"+info.getMinpoint()+"' class='digits' required>");
            	dmlistStr.append("-<input style='width:50px;' id='maxpoint"+info.getCode()+"' type='text' name='maxpoint' value='"+info.getMaxpoint()+"' class='digits' required>");
            	dmlistStr.append("<font color='red'>*</font>");
            	dmlistStr.append("</td>");
            	dmlistStr.append("</tr>");
            }
			Map<String, Object> mapdata=new HashMap<String, Object>();
			mapdata.put("dmlistStr",dmlistStr.toString());
			data.setData(mapdata);
			data.setSuccess(true);
	    } catch (Exception e) {
	  		e.printStackTrace();
	  		data.setSuccess(false);
	  	}
	  	String jsonStr;
		try {
			jsonStr = JsonUtil.toJson(data);
			response.setCharacterEncoding("utf-8");
		  	response.getWriter().print(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 服务级别阀值设置
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "info/doPointset", method = RequestMethod.POST)
	@LogAspect(desc="设置服务级别阀值")
	public String doPonintset(RedirectAttributes redirectAttributes,HttpServletRequest request) {
		String[] ids = request.getParameterValues("id");
		String[] minpoints = request.getParameterValues("minpoint");
		String[] maxpoints = request.getParameterValues("maxpoint");
		String menuid = request.getParameter("menuid");
		for (int i = 0; i < ids.length; i++) {
			GradeInfo gradeInfo = new GradeInfo();
			GradeInfoSearch gradeInfoSearch = new GradeInfoSearch();
			gradeInfoSearch.createCriteria().andIdEqualTo(ids[i]);
			gradeInfo.setMinpoint(BigDecimal.valueOf(Integer.valueOf(minpoints[i])));
			gradeInfo.setMaxpoint(BigDecimal.valueOf(Integer.valueOf(maxpoints[i])));
			gradeInfoService.updateByExampleSelective(gradeInfo,gradeInfoSearch);
		}
		
		redirectAttributes.addFlashAttribute("message", "服务级别阀值设置成功");
		return "redirect:/sergrade/grade/info/list/"+menuid+"/";
	}
	
}
