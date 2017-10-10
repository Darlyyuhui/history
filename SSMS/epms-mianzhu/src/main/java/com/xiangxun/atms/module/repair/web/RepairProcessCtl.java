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
import com.xiangxun.atms.framework.util.StringUtils;
import com.xiangxun.atms.framework.util.UuidGenerateUtil;
import com.xiangxun.atms.framework.validator.ResponseEntity;
import com.xiangxun.atms.module.base.web.BaseCtl;
import com.xiangxun.atms.module.repair.service.RepairProcessService;
import com.xiangxun.atms.module.repair.service.RepairProjectService;
import com.xiangxun.atms.module.repair.vo.RepairProcess;
import com.xiangxun.atms.module.repair.vo.RepairProcessSearch;

@Controller
@RequestMapping(value = "repair/process")
@SessionAttributes(value = {"projectInfo"})
public class RepairProcessCtl extends BaseCtl<RepairProcess, RepairProcessSearch> {

	@Resource
	RepairProcessService repairProcessService;
	
	@Resource
	RepairProjectService repairProjectService;

	@Override
	protected BaseService<RepairProcess, RepairProcessSearch> getBaseService() {
		return repairProcessService;
	}
	
	@RequestMapping(value = "list/{menuid}/{proId}/")
	public String list(@PathVariable("menuid") String menuid, @PathVariable("proId") String proId
			, @RequestParam(value = "sortType", defaultValue = "CREATE_TIME DESC") String sortType
			, @RequestParam(value = "page", defaultValue = "0") int pageNumber
			, Model model, HttpServletRequest request) {
		this.initModel(proId, model);
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("proId", proId);
		Page page = repairProcessService.getListByCondition(searchParams, pageNumber
				, Page.DEFAULT_PAGE_SIZE, sortType);
		model.addAllAttributes(searchParams);
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefixByPost(searchParams, "search_"));
		model.addAttribute("sortType", sortType);
		model.addAttribute("pageList", page);
		model.addAttribute("page", pageNumber);
		model.addAttribute("menuid", menuid);
		return "repair/process/list";
	}
	
	/**
	 * 将项目信息查出并放入session
	 * @param blockId
	 * @param model
	 */
	private void initModel(String proId, Model model) {
		model.addAttribute("projectInfo", repairProjectService.getById(proId));
	}
	
	@RequestMapping(value = "add/{menuid}/")
	public String add(@PathVariable("menuid") String menuid, String proId, String page, Model model, HttpServletRequest request) {
		if (StringUtils.isNotEmpty(proId)) {
			this.initModel(proId, model);
		}
		model.addAttribute("page", page);
		model.addAttribute("menuid", menuid);
		return "repair/process/add";
	}
	
	@RequestMapping(value = "doAdd/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="保存【土壤修复过程】")
	public String doAdd(@PathVariable String menuid, RepairProcess info
			, MultipartHttpServletRequest fileRequest, RedirectAttributes attr) {
		info.setId(UuidGenerateUtil.getUUIDLong());
		info.setCreateId(getCurrentUserId());
		info.setCreateTime(new Date());
		repairProcessService.saveInfo(info, fileRequest);
		
		attr.addFlashAttribute("message", "新增成功");
		return "redirect:/repair/process/list/"+menuid+"/"+info.getProId()+"/";
	}

	@RequestMapping(value = "update/{menuid}/{id}/")
	public String update(@PathVariable("menuid") String menuid, @PathVariable("id") String id, String page, Model model) {
		RepairProcess info = repairProcessService.getById(id);
		model.addAttribute("info", info);
		model.addAttribute("page", page);
		model.addAttribute("menuid", menuid);
		return "repair/process/update";
	}
	
	@RequestMapping(value = "doUpdate/{menuid}/",method = RequestMethod.POST)
	@LogAspect(desc="修改【土壤修复过程】")
	public String doUpdate(@PathVariable("menuid") String menuid
			, @ModelAttribute("info")RepairProcess info
			, MultipartHttpServletRequest fileRequest, RedirectAttributes attr) {
		info.setUpdateId(getCurrentUserId());
		info.setUpdateTime(new Date());
		repairProcessService.updateInfo(info, fileRequest);
		attr.addFlashAttribute("message", "修改成功");
		return "redirect:/repair/process/list/"+menuid+"/"+info.getProId()+"/";
	}

    @RequestMapping(value="doDelete/",method = RequestMethod.POST)
    @ResponseBody
    @LogAspect(desc="删除【土壤修复过程】")
    public ResponseEntity delete(@RequestParam(value = "ids", required = true) String ids) {
        ResponseEntity  entity = new ResponseEntity();
        try {
            logger.info("正在进行【土壤修复过程】数据删除。。。。。。");
            String[] id = ids.split(",");
            for (String string : id) {
                repairProcessService.deleteById(string);
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
    	RepairProcess info = repairProcessService.getById(id);
		model.addAttribute("info", info);
		model.addAttribute("page", page);
		model.addAttribute("menuid", menuid);
		return "repair/process/view";
	}
	
}
