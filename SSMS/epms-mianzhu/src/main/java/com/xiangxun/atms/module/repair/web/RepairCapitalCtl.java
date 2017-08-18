package com.xiangxun.atms.module.repair.web;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiangxun.atms.core.service.BaseService;
import com.xiangxun.atms.framework.base.Page;
import com.xiangxun.atms.framework.log.anotation.LogAspect;
import com.xiangxun.atms.framework.util.Servlets;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.base.web.BaseCtl;
import com.xiangxun.atms.module.repair.service.RepairCapitalService;
import com.xiangxun.atms.module.repair.service.RepairProjectService;
import com.xiangxun.atms.module.repair.vo.RepairCapital;
import com.xiangxun.atms.module.repair.vo.RepairCapitalSearch;
import com.xiangxun.atms.module.repair.vo.RepairProjectSearch;

@Controller
@RequestMapping(value = "repair/capital")
@SessionAttributes(value = {"projects"})
public class RepairCapitalCtl extends BaseCtl<RepairCapital, RepairCapitalSearch> {
	
    @Resource
    RepairCapitalService repairCapitalService;
    @Resource
    RepairProjectService repairProjectService;

	@Override
	protected BaseService<RepairCapital, RepairCapitalSearch> getBaseService() {
		return repairCapitalService;
	}

	@RequestMapping(value = "list/{menuid}/")
	public String list(@PathVariable String menuid
			, @RequestParam(value = "sortType", defaultValue = "T.CREATE_TIME DESC") String sortType
			, @RequestParam(value = "page", defaultValue = "0") int pageNumber
			, Model model, HttpServletRequest request) {
		this.initModel(model);
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		super.updateSession(request, menuid, searchParams);
		
		Page page = repairCapitalService.getListByCondition(searchParams, pageNumber
				, Page.DEFAULT_PAGE_SIZE, sortType);
		model.addAllAttributes(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
		model.addAttribute("menuid", menuid);
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("page", pageNumber);
		return "repair/capital/list";
	}
	
	private void initModel(Model model) {
		RepairProjectSearch search = new RepairProjectSearch();
		search.setOrderByClause("CREATE_TIME DESC");
		model.addAttribute("projects", repairProjectService.selectByExample(search));
	}
	
	@RequestMapping(value = "add/{menuid}/")
	public String add(@PathVariable("menuid") String menuid, String page, Model model, HttpServletRequest request) {
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		return "repair/capital/add";
	}
	
	@RequestMapping(value = "doAdd/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="保存【土壤修复项目档案】")
	public String doAdd(@PathVariable String menuid, RepairCapital info
			, RedirectAttributes attr, MultipartHttpServletRequest fileRequest) {
		info.setId(UuidGenerateUtil.getUUIDLong());
		info.setCreateId(getCurrentUserId());
		info.setCreateTime(new Date());
		repairCapitalService.saveInfo(info, fileRequest);
		attr.addFlashAttribute("message", "新增成功");
		return "redirect:/repair/capital/list/"+menuid+"/";
	}

	@RequestMapping(value = "update/{menuid}/{id}/")
	public String update(@PathVariable("menuid") String menuid, @PathVariable("id") String id, String page, Model model) {
		RepairCapital info = repairCapitalService.getById(id);
		model.addAttribute("info", info);
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		return "repair/capital/update";
	}
	
	@RequestMapping(value = "doUpdate/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="修改【土壤修复项目档案】")
	public String doUpdate(@PathVariable String menuid, String page, @ModelAttribute("info")RepairCapital info
			, RedirectAttributes attr, MultipartHttpServletRequest fileRequest) {
		info.setUpdateId(getCurrentUserId());
		info.setUpdateTime(new Date());
		repairCapitalService.updateInfo(info, fileRequest);
		attr.addFlashAttribute("message", "修改成功");
		return "redirect:/repair/capital/list/"+menuid+"/?isgetsession=1&page="+page;
	}

    @RequestMapping(value="doDelete/",method = RequestMethod.POST)
    @ResponseBody
    @LogAspect(desc="删除【土壤修复项目档案】")
    public ResponseEntity delete(@RequestParam(value = "ids", required = true) String ids) {
        ResponseEntity  entity = new ResponseEntity();
        try {
            logger.info("正在进行【土壤修复项目档案】数据删除。。。。。。");
            String[] id = ids.split(",");
            for (String string : id) {
                repairCapitalService.deleteById(string);
            }
            entity.setResult("ok");
            return entity;
        } catch (Exception e) {
            entity.setResult("error");
            return entity;
        }
    }
    
    @RequestMapping(value = "showView/{menuid}/{id}/")
	public String showView(@PathVariable("menuid") String menuid, @PathVariable("id") String id, String page, Model model) {
    	RepairCapital info = repairCapitalService.getById(id);
		model.addAttribute("info", info);
		model.addAttribute("menuid", menuid);
		model.addAttribute("page", page);
		return "repair/capital/view";
	}
	
}