package com.xiangxun.atms.module.repository.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiangxun.atms.framework.base.BaseCtl;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.module.repository.domain.KnowledgeInfo;
import com.xiangxun.atms.module.repository.service.CatalogInfoService;
import com.xiangxun.atms.module.repository.service.KnowledgeInfoService;

/**
 * 知识库管理首页
 * @author kouyunhao
 *
 */
@Controller
@RequestMapping(value = "repository/main")
public class RepositoryCtl extends BaseCtl {
	
	@Resource 
	KnowledgeInfoService knowledgeInfoService;
	
	@Resource
	CatalogInfoService catalogInfoService;
	
	@RequestMapping(value="show/{menuid}/")
	public String show(@PathVariable String menuid,ModelMap model,@RequestParam(value = "sortType", defaultValue = "CREATETIME desc") String sortType,
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,HttpServletRequest request){
		String operator = null;
		Map<String, Object> params = new HashMap<String, Object>();
		String deptid = getCurrentUser().getDeptId();
		if(!deptid.equals("00")){
			operator = getCurrentUserId();
		}
		params.put("operator", operator);
		List knowledgelist = knowledgeInfoService.getKnowledgeByType(params);
		model.addAttribute("knowledgelist", knowledgelist);
		Map<String, Object> knowledgeParams = new HashMap<String, Object>();
//		knowledgeParams.put("verifyresult", "1");
		knowledgeParams.put("sortType", "CREATETIME desc");
		if(!deptid.equals("00")){
			knowledgeParams.put("operator", getCurrentUserId());
		}
		Page page = knowledgeInfoService.getListByCondition(knowledgeParams, 1,5, sortType);
		Object obj1 = page.getResult();
		if(obj1!=null && obj1 instanceof List){
			@SuppressWarnings("unchecked")
			List<KnowledgeInfo> list = (List<KnowledgeInfo>)obj1;
			if(list != null && list.size() != 0){
				for (KnowledgeInfo resource : list) {
					resource.setPid(catalogInfoService.getById(resource.getPid()).getName());
				}
			}
		}
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		Map<String, Object> statusParams = new HashMap<String, Object>();
		statusParams.put("sortType", "CREATETIME desc");
		if(!deptid.equals("00")){
			statusParams.put("operator", getCurrentUserId());
		}
		Page status = knowledgeInfoService.getListByCondition(statusParams,pageNumber,9,sortType);
		Object obj = status.getResult();
		if(obj!=null && obj instanceof List){
			@SuppressWarnings("unchecked")
			List<KnowledgeInfo> list = (List<KnowledgeInfo>)obj;
			if(list != null && list.size() != 0){
				for (KnowledgeInfo resource : list) {
					resource.setPid(catalogInfoService.getById(resource.getPid()).getName());
				}
			}
		}
		model.addAttribute("statusList", status);
		model.addAttribute("page", pageNumber);
		return "repository/main/show";
	}

}
