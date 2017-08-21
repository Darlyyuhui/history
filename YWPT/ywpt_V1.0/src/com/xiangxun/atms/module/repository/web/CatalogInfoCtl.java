/**
 * 
 */
package com.xiangxun.atms.module.repository.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.repository.domain.CatalogInfo;
import com.xiangxun.atms.module.repository.service.CatalogInfoService;
import com.xiangxun.atms.module.repository.service.KnowledgeInfoService;

/**
 * 知识库目录管理
 * @author kouyunhao
 */
@Controller
@RequestMapping(value="repository/catalog")
public class CatalogInfoCtl extends BaseCtl {
	
	@Resource
	CatalogInfoService catalogInfoService;
	
	@Resource 
	KnowledgeInfoService knowledgeInfoService;
	
	@Resource
	private Validator validator;
	
	/***
	 * 异步树形结构服务
	 * @param id
	 * @param resp
	 */
	@RequestMapping(value="showTree/",method = RequestMethod.POST)
	public void showTree(@RequestParam(value="id",defaultValue="0") String id,ServletResponse resp){
		List<CatalogInfo> docList =  catalogInfoService.getChildren(id);
		List<CatalogInfo> catalogList =  catalogInfoService.findAll();
		JsonArray jsonArray = new JsonArray();
		if(docList != null && docList.size() != 0){
			Collections.sort(docList,new Comparator<CatalogInfo>() {
				@Override
				public int compare(CatalogInfo o1, CatalogInfo o2) {
					return Integer.valueOf(o1.getId().substring(5, 13))-Integer.valueOf(o2.getId().substring(5, 13));
				}
			});
			for (CatalogInfo catalog : docList) {
				JsonObject json = new JsonObject();
				json.addProperty("id",catalog.getId());
				json.addProperty("pid",catalog.getPid());
				json.addProperty("isParent",catalogInfoService.hasChild(catalog.getId()));
				json.addProperty("name",catalog.getName());
				jsonArray.add(json);
			}
		}else{
			JsonObject json = new JsonObject();
			json.addProperty("id","00");
			json.addProperty("pid","0");
			json.addProperty("name","知识库管理");
			if(catalogList != null && catalogList.size() != 0){
				json.addProperty("isParent",true);
			}else{
				json.addProperty("isParent",false);
			}
			jsonArray.add(json);
		}
		logger.debug("response json:{0}", jsonArray.toString());
		try {
			resp.getWriter().write(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 删除文件夹对象
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value="delete/{ids}/",method = RequestMethod.DELETE)
	@ResponseBody
	@LogAspect(desc="删除文件夹对象")
	public ResponseEntity delete(@PathVariable String ids){
		ResponseEntity  entity = new ResponseEntity();
		try {
			logger.info("正在进行文件夹对象删除。。。。。。");
			if(catalogInfoService.hasChild(ids)){
				entity.setResult("error1");
				return entity;
			}else{
				if(knowledgeInfoService.isContainsFile(ids)){
					entity.setResult("error2");
					return entity;
				}else{
					catalogInfoService.deleteById(ids);
					entity.setResult("ok");
					return entity;
				}
			}
		} catch (Exception e) {
			entity.setResult("error");
			return entity;
		}
	}
	
	/***
	 * 获得待修改文件夹
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value="update/{id}/{menuid}/",method = RequestMethod.GET)
	@LogAspect(desc="获得待修改文件夹")
	public String update(@PathVariable("id") String id,@PathVariable("menuid") String menuid,Model model, HttpServletRequest request) {
		
		CatalogInfo catalog  = catalogInfoService.getById(id);
		model.addAttribute("catalog",catalog);
		model.addAttribute("type",request.getParameter("type")); 
		model.addAttribute("pname", id.equals("00")?"知识库管理":catalogInfoService.getById(id).getName());
		model.addAttribute("menuid",menuid); 
		return "repository/catalog/update";
	}
	
	/***
	 * 修改文件夹
	 * @param role
	 * @param menuid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "doUpdate/{type}/{menuid}/", method = RequestMethod.POST)
	@LogAspect(desc="修改文件夹")
	public String doUpdateFolder(@ModelAttribute("preloadVioType") CatalogInfo catalogInfo,@PathVariable String type,@PathVariable String menuid,RedirectAttributes redirectAttributes) {
		catalogInfoService.updateByIdSelective(catalogInfo);
		redirectAttributes.addFlashAttribute("message", "目录修改成功");
		return "redirect:/repository/"+type+"/sublist/"+menuid+"/";
	}
	
	/***
	 * 先根据form的id从数据库查出对象，再把Form提交的内容绑定到该对象上
	 * @param id
	 * @return
	 */
	@ModelAttribute("preloadCatalogInfo")
	public CatalogInfo getCatalogInfo(@RequestParam(value = "id", required = false) String id) {
		if (id != null) {
			return catalogInfoService.getById(id);
		}
		return null;
	}
	
	/***
	 * 判断名称是否重复
	 * @param req
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "nameExist")
	@ResponseBody
	public String nameExist(HttpServletRequest req, @RequestParam(value = "name") String name) {
		CatalogInfo catalog = catalogInfoService.getByName(name.trim());
		String returnStr = Boolean.FALSE.toString();
		if (catalog == null) {
			returnStr = Boolean.TRUE.toString();
		}
		String oper = req.getParameter("oper");
		//不为空说明是修改
		if(StringUtils.isNotBlank(oper)){
			if (catalog != null) {
				if(catalog.getName().equals(oper)){
					return Boolean.TRUE.toString();
				}
			}
		}
		return returnStr;
	}
	
	/**
	 * 阻止三级节点下再创建文件夹
	 * @author kouyunhao
	 * @version 1.0
	 * @param menuid
	 * @param pid
	 * @param page
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * Feb 19, 2014
	 */
	@RequestMapping(value="showAdd/{menuid}/{pid}/",method = RequestMethod.GET)
	@LogAspect(desc="新建文件夹")
	public String showRoadInfoAdd(@PathVariable("menuid") String menuid, @PathVariable("pid") String pid, String page, 
			Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
//		if (!pid.equals("00")) {
//			// ========================== add by kouyunhao 2013-08-12 添加验证，阻止三级节点下再创建文件夹===============//
//			String thirdNode = catalogInfoService.getThirdNode(pid);
//			if (thirdNode != null) {
//				redirectAttributes.addFlashAttribute("message", "三级节点下不允许再创建文件夹");
//				return "redirect:/emergency/file/sublist/"+menuid+"/";
//			}
//		}
		model.addAttribute("pname", pid.equals("00")?"知识库管理":catalogInfoService.getById(pid).getName());
		model.addAttribute("type",request.getParameter("type")); 
		model.addAttribute("menuid", menuid);
		model.addAttribute("pid", pid);
		return "repository/catalog/add";
	}
	
	/**
	 * 添加文件夹
	 * @author kouyunhao
	 * @version 1.0
	 * @param catalog
	 * @param modal
	 * @param redirectAttributes
	 * @param request
	 * @return
	 * Sep 12, 2013
	 */
	@RequestMapping(value="doAdd/{type}/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="新建目录")
	public String doAddFolder(CatalogInfo catalog,@PathVariable String type,@PathVariable String menuid,RedirectAttributes redirectAttributes, HttpServletRequest request){
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<CatalogInfo>> failures = validator.validate(catalog);
		if (!failures.isEmpty()) {
			redirectAttributes.addAttribute("message","目录新建失败");
		}
		String pid = request.getParameter("pid");
		catalog.setId(UuidGenerateUtil.getUUIDLong());
		catalog.setName(catalog.getName());
		catalog.setNote(catalog.getNote());
		catalog.setPid(pid == "" ? "" : pid);
		CatalogInfo pcatalog = catalogInfoService.getById(pid);
		if(pcatalog != null){
			catalog.setLevels(new BigDecimal(Integer.parseInt(String.valueOf(pcatalog.getLevels())+1)));
		}else{
			catalog.setLevels(new BigDecimal(1));
		}
		catalogInfoService.save(catalog);
		redirectAttributes.addFlashAttribute("message","目录新建成功");
		
		return "redirect:/repository/"+type+"/sublist/"+menuid+"/";
	}

}
