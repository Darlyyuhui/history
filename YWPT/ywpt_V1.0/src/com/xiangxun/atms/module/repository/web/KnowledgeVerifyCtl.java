package com.xiangxun.atms.module.repository.web;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiangxun.atms.common.dictionary.service.DicService;
import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.module.eventalarm.domain.WorkorderInfo;
import com.xiangxun.atms.module.eventalarm.service.PublicMethodService;
import com.xiangxun.atms.module.eventalarm.service.WorkorderInfoService;
import com.xiangxun.atms.module.repository.domain.CatalogInfo;
import com.xiangxun.atms.module.repository.domain.KnowledgeInfo;
import com.xiangxun.atms.module.repository.service.CatalogInfoService;
import com.xiangxun.atms.module.repository.service.KnowledgeInfoService;

/**
 * 知识库审核
 * @author kouyunhao
 *
 */
@Controller
@RequestMapping(value = "repository/verify")
public class KnowledgeVerifyCtl extends BaseCtl {
	
	@Resource 
	KnowledgeInfoService knowledgeInfoService;
	
	@Resource
	WorkorderInfoService workorderInfoService;
	
	@Resource
	CatalogInfoService catalogInfoService;
	
	@Resource
	PublicMethodService publicMethodService;

	@Resource
	DicService dicService;
	
	/***
	 * 跳转到主界面
	 * @param menuid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list/{menuid}/")
	public String list(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "name") String sortType,
		@RequestParam(value = "page", defaultValue = "0") int pageNumber, String type, HttpServletRequest request){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("applyflag", "1");
		searchParams.put("verifyflag", "0");
		Page page = knowledgeInfoService.getListByCondition(searchParams, pageNumber,Page.DEFAULT_PAGE_SIZE, sortType);
		Object obj = page.getResult();
		if(obj!=null && obj instanceof List){
			@SuppressWarnings("unchecked")
			List<KnowledgeInfo> list = (List<KnowledgeInfo>)obj;
			if(list != null && list.size() != 0){
				for(KnowledgeInfo knowledge : list){
					CatalogInfo catalog = catalogInfoService.getById(knowledge.getPid());
					if(catalog != null){
						knowledge.setKnowledgetype(catalog.getName());
					}
				}
			}
		}
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		KnowledgeInfo knowledge = new KnowledgeInfo();
		try {
			//将查询的map转换成user对象
			BeanUtils.populate(knowledge, searchParams);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		model.addAttribute("knowledge", knowledge);
		request.getSession().setAttribute("catalogList", catalogInfoService.findAll());
		return "repository/verify/list";
	}
	
	/**
	 * 获取详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}/{menuid}/", method = RequestMethod.GET)
	public String showView(@PathVariable("id")
	String id, @PathVariable("menuid")
	String menuid, String page, Model model, HttpServletRequest request) {
		KnowledgeInfo knowledge  = knowledgeInfoService.getById(id);
		String path = knowledge.getPath();
		if(path != null){
			String uploadname = "";
			File file = new File(request.getSession().getServletContext()
					.getRealPath("/")+path);
			if(!file.exists()){
				model.addAttribute("filexist", "-1");
			}else{
				uploadname = path.replace(path.substring(path.lastIndexOf(".")), ".swf");
			}
			model.addAttribute("uploadname", uploadname);
		}
		CatalogInfo catalog = catalogInfoService.getById(knowledge.getPid());
		if(catalog != null){
			knowledge.setKnowledgetype(catalog.getName());
		}
		WorkorderInfo workorder = workorderInfoService.getById(knowledge.getNote());
		if(workorder != null){
			publicMethodService.setStatusColor(workorder);
			model.addAttribute("workorder", workorder);
		}
		model.addAttribute("knowledge", knowledge);
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		return "repository/verify/view";
	}
	
	/***
	 * 提交审核
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value="doApply/{ids}/{type}/",method = RequestMethod.GET)
	@LogAspect(desc="知识库文件提交审核")
	public String apply(@PathVariable String ids, @PathVariable String type, String menuid, 
			RedirectAttributes redirectAttributes, HttpServletRequest request){
		logger.info("提交审核");
		String[] codess = ids.split(",");
		List<String> idList = Arrays.asList(codess);
		for(String id : idList){
			KnowledgeInfo knowledge = knowledgeInfoService.getById(id);
			knowledge.setApplyflag("1");
			knowledge.setApplytime(new Date());
			if(knowledge.getVerifyflag().equals("1")){
				knowledge.setVerifyflag("0");
				knowledge.setVerifytime(null);
				knowledge.setVerifier("");
				knowledge.setVerifyresult("0");
				knowledge.setRebutreason("");
			}
			
			knowledgeInfoService.updateByIdSelective(knowledge);
		}
		redirectAttributes.addFlashAttribute("message", "提交审核成功");
		return "redirect:/repository/"+type+"/sublist/"+menuid+"/";
	}
	
	/***
	 * 文件审核驳回
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value = "rebut", method = RequestMethod.POST)
	@LogAspect(desc="知识库审核驳回")
	public String refuse(KnowledgeInfo knowledge,String menuid, String page, 
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		String reason = knowledge.getRebutreason();
		String ids = knowledge.getId();
		String[] id = ids.split(",");
		for (String string : id) {
			knowledge = knowledgeInfoService.getById(string);
			knowledge.setVerifyflag("1");
			knowledge.setVerifytime(new Date());
			knowledge.setVerifier(getCurrentUserId());
			knowledge.setVerifyresult("2");
			knowledge.setRebutreason(reason);
			knowledgeInfoService.updateByIdSelective(knowledge);
		}
		redirectAttributes.addFlashAttribute("message", "审核驳回成功");
		return "redirect:/repository/verify/list/"+menuid+"/?page="+page+"&isgetsession=1";
	}
	
	/***
	 * 文件审核通过
	 * @param ids
	 * @param resp
	 */
	@RequestMapping(value="pass/{ids}/{menuid}/",method = RequestMethod.GET)
	@LogAspect(desc="知识库审核通过")
	public String verify(@PathVariable String ids, @PathVariable String menuid, String page, 
			RedirectAttributes redirectAttributes, HttpServletRequest request){
		logger.info("提交审核");
		String[] codess = ids.split(",");
		List<String> idList = Arrays.asList(codess);
		String reason = request.getParameter("reason");
		for(String id : idList){
			KnowledgeInfo knowledge = knowledgeInfoService.getById(id);
			knowledge.setVerifyflag("1");
			knowledge.setVerifytime(new Date());
			knowledge.setVerifier(getCurrentUserId());
			knowledge.setVerifyresult("1");
			knowledge.setRebutreason(reason);
			knowledgeInfoService.updateByIdSelective(knowledge);
		}
		redirectAttributes.addFlashAttribute("message", "审核通过成功");
		return "redirect:/repository/verify/list/"+menuid+"/?page="+page+"&isgetsession=1";
	}

}
